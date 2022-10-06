package com.cscie97.ledger;

import java.util.*;

import static com.cscie97.ledger.LedgerException.Reason.*;
import static com.cscie97.ledger.LedgerException.Commands.*;

/**
 * From the design:
 * The Block aggregates groups of 10 transactions. Transactions should be added to the block in the order that they are
 * received.Prior to adding a transaction to a block, the transaction must be validated. Transactions that are invalid
 * should be discarded. The block also contains an account balance map that reflects the balance of all accounts after
 * all the transactions within the block have been applied.  The account balance map should be copied from the previous
 * block and updated to reflect the transactions in the current block. The block contains the hash of the previous block.
 * It also contains the hash of itself.
 */
public class Block implements Cloneable {

    /* block number, assigned at the time of creation */
    public final int blockNumber;

    /* hash for the previous block */
    private final String prevHash;

    /* has will be calculated after 10 transaction and block will be committed */
    private String hash;

    /* transactions - only valid transactions get added here */
    private ArrayList<Transaction> transactions;

    /* keep track of account balances after applying transactions */
    private HashMap<String, Account> accountBalanceMap;

    /* reference to previous block */
    private Block previousBlock;

    /**
     * Constructor, called by ledger service to start a new block
     * @param blockNumber
     * @param prevHash
     * @param previousBlock
     * @param accountBalanceMapFromPrevBlock
     */
    public Block( int blockNumber, String prevHash, Block previousBlock, HashMap<String, Account> accountBalanceMapFromPrevBlock) {

        this.blockNumber = blockNumber;
        this.prevHash = prevHash;
        this.previousBlock = previousBlock;

        // deep copy the account balances
        accountBalanceMap = Utils.deepCopy(accountBalanceMapFromPrevBlock);

        // linked list of transactions in this block.
        this.transactions = new ArrayList<>();
    }

    /**
     * @return block number
     */
    public int getBlockNumber() {
        return blockNumber;
    }

    /**
     * add an account
     * @param account
     */
    public void addAccount(Account account) {
        accountBalanceMap.put(account.getAddress(), account);
    }

    /**
     * Ledger service needs the hash to pass it on to next block
     * @return
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param account must be valid
     * @return account balance
     */
    public int getAccountBalance(String account)  {
        assert accountBalanceMap.containsKey(account);
        return accountBalanceMap.get(account).getBalance();
    }

    /**
     * @return all accounts balances
     */
    public HashMap<String, Account> getAccountBalanceMap() {
        return accountBalanceMap;
    }

    /**
     * Update the account balances and record the transaction.
     * Transaction is validated by the ledger service
     * @param transaction
     */
    public void recordTransaction(Transaction transaction) {
        updateAccounts(transaction, accountBalanceMap);
        transaction.setRecorded();
        transactions.add(transaction);
    }

    /**
     * Update account balances
     * @param transaction
     * @param balances
     */
    public void updateAccounts(Transaction transaction, HashMap<String, Account> balances) {
        // update Account balances
        Account payerAccount = balances.get(transaction.getPayer());
        Account receiverAccount = balances.get(transaction.getReceiver());
        Account masterAccount = balances.get("master");

        payerAccount.setBalance( payerAccount.getBalance() - transaction.getAmount() - transaction.getFee());
        masterAccount.setBalance(masterAccount.getBalance() + transaction.getFee());
        receiverAccount.setBalance(receiverAccount.getBalance() + transaction.getAmount());
    }


    /**
     * Ledger service will call this tio validate the block and the complete block-chain
     * @param startingBalances
     * @param expectedBlockNum
     * @param prevHash
     * @param hashingAlgorithm
     * @throws LedgerException
     */
    public void validate(HashMap<String, Account> startingBalances, int expectedBlockNum, String prevHash, HashingAlgorithm hashingAlgorithm) throws LedgerException {

        // must have 10 transactions
        Utils.assertTrue(transactions.size() == 10, VALIDATE, INVALID_TRANSACTION_COUNT );

        // block number matches
        Utils.assertTrue(blockNumber == expectedBlockNum, VALIDATE, BLOCK_NUMBER_MISMATCH);

        // prev hash is good
        Utils.assertTrue(this.prevHash.equals(prevHash), VALIDATE, PREV_HASH_MISMATCH);

        // re-compute the hash and validate with the stored hash
        Utils.assertTrue(this.hash.equals(generateHash(hashingAlgorithm)),VALIDATE, PREV_HASH_MISMATCH );

        // validate the balances

        // re-apply all the transaction, this will update the balances
        // in temporary copy
        for( Transaction transaction: transactions) {
            updateAccounts(transaction, startingBalances);
        }

        // compare the balances of temporary copy with the stored ones.
        // throw an exception in case mismatch is detected.
        for( HashMap.Entry<String, Account> entry: accountBalanceMap.entrySet()) {
            int balanceRecorded = entry.getValue().getBalance();
            int balanceRecalculated = startingBalances.get(entry.getKey()).getBalance();

            Utils.assertTrue(balanceRecorded == balanceRecalculated, VALIDATE, BALANCE_MISMATCH);
        }
    }

    /**
     * @return true when it reaches 10 transactions.
     */
    public boolean isBlockFull() {
        return transactions.size() == 10;
    }

    /**
     * Ledger Service will call this method, once block is ready to be committed.
     * @param hashingAlgorithm
     * @return
     */
    public String addHash(HashingAlgorithm hashingAlgorithm) {
        hash = generateHash(hashingAlgorithm);
        return hash;
    }

    /**
     * generate the hash for the block.
     * @param hashingAlgorithm
     * @return
     */
    private String generateHash(HashingAlgorithm hashingAlgorithm) {

        // use Merkel Tree based algorithm to generate hash for 10 transactions
        String tHash = MerkelTreeHashingAlgorithm.generateHash(transactions, hashingAlgorithm);

        // generate has for the account balances
        String accountBalanceHash = hashingAlgorithm.generateHash(accountBalanceMap.toString());

        // use prevHash to calculate the hash
        hash = hashingAlgorithm.generateHash(tHash + accountBalanceHash + prevHash);

        return hash;
    }

    /**
     * Clone the Block. We do not want to return the real block to
     * end user
     * @return
     */
    public Block clone() {

        // no fields to deep copy.
        Block block = null;

        // general exception handling
        try {
            block = (Block)(super.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

        // deep copy Transactions
        block.transactions = Utils.deepCopy(transactions);

        // deep copy Account balances
        block.accountBalanceMap = Utils.deepCopy(accountBalanceMap);

        block.previousBlock = null; // hide the reference to previous block.

        return block;
    }

    /**
     * pretty print the block
     * @return
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Block Details: ");

        builder.append("\nTransactions[");
        for( Transaction transaction : transactions) {
            builder.append("\n\t").append(transaction);
        }

        builder.append("\nAccountBalances: ");
        for( HashMap.Entry<String,Account> entry: accountBalanceMap.entrySet()) {
            builder.append("\n\t").append(entry.getValue());
        }
        return builder.toString();
    }
}
