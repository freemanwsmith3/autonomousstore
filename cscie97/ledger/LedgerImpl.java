package com.cscie97.ledger;

import java.util.HashMap;

import static com.cscie97.ledger.LedgerException.Reason.*;
import static com.cscie97.ledger.LedgerException.Commands.*;

/**
 * Description - As specified in the Design document:
 * The Ledger manages the Blocks of the blockchain.It also provides the API
 * used by clients of the Ledger. The Ledger processes transaction processing requests,
 * and also queries about the state of the Ledger, including Account balances, Transaction details,
 * and Block details.
 *
 * This design specifies the implementation of a blockchainLedger Service.  The Ledger Service manages the transactions,
 * accounts, and blocks that make up the Blockchain. Users submit transactions which once validated are added to a block.
 * As Blocks fill up with Transactions,Account balances are updated, and the Blocks are added to the Ledger.
 * Once committed to theLedger, a Block, and the contained Transactions andAccount balances are immutable.
 * To ensure the immutability of the blocks, the blocks are chained together by including the hash of the previous
 * block as a field in each new block.The blockchain can be validated at any time by recomputing the hashes of
 * each block and comparing the result with the hash that has been stored in the next block.
 */
public class LedgerImpl implements Ledger {

    /* name of the ledger */
    private final String name;

    /* description */
    private final String description;

    /* initial seed, used to calculate prev-hash for the genesis block */
    private final String seed;

    /* last committed block */
    private Block committedBlock;

    /* current block, transaction on this block are not committed to the blockchain yet */
    public static Block currentBlock;

    /* block number to Block association. number starts from 1 */
    public static HashMap<Integer, Block> blockMap = new HashMap<>();

    /* Algorithm used for hashing. for example SHA-256 */
    private final HashingAlgorithm hashingAlgorithm;

    public static final String masterAccount = "master";
    /**
     * Constructor
     * @param name
     * @param description
     * @param seed
     */
    public LedgerImpl(String name, String description, String seed ) {

        this.name = name;
        this.description = description;
        this.seed = seed;

        hashingAlgorithm = new SHA256HashingAlgorithm();

        // create genesis block. We will add transactions to genesis block and commit once ready
        currentBlock = new Block(1, hashingAlgorithm.generateHash(seed), null, new HashMap<>());

        // create master account and initialize the balance
        try {
            currentBlock.addAccount(AccountFactory.create(masterAccount, Integer.MAX_VALUE));
        } catch ( LedgerException ex) {
            throw new RuntimeException("Error creating Ledger " + ex.getReason());
        }
    }

    /**
     * Create a new account, assign a unique identifier, and set the balance to 0.
     * Return the account identifier.
     * @param address
     * @return
     * @throws LedgerException
     */
    @Override
    public Account createAccount(String address) throws LedgerException {
        return this.createAccount(address, 0);
    }

    /**
     * Private method to create a new account and return clone of the actual account object
     * @param address
     * @param balance
     * @return
     * @throws LedgerException
     */
    private Account createAccount(String address, int balance) throws LedgerException {
        Account account = AccountFactory.create(address, balance);
        currentBlock.addAccount(account);
        return account.clone();
    }

    /**
     * Process a transaction. Validate the Transaction and if valid, add the Transaction to
     * the current Block and update the associated Account balances for the currentBlock.
     * Return the assigned transaction id.  If the transaction is not valid, throw a LedgerException.
     * @param transactionId
     * @param amount
     * @param fee
     * @param note
     * @param payer
     * @param receiver
     * @return Transaction Id
     * @throws LedgerException
     */
    @Override
    public String processTransaction(String transactionId,
                                     int amount,
                                     int fee,
                                     String note,
                                     String payer,
                                     String receiver) throws LedgerException {

        // create transaction object. Can throw in case duplicate transaction Id
        Transaction transaction = TransactionFactory.create(transactionId, amount, fee, note, payer, receiver);

        // validate, for example payers balance, account validity, min fee requirements
        validateTransaction(transaction);

        // all good, record the transaction
        currentBlock.recordTransaction(transaction);

        // in case block has 10 transaction already
        if( currentBlock.isBlockFull() ) {

            // prepare the block for committing to the block-chain
            // compute and hash to the block
            String blockHash = currentBlock.addHash(hashingAlgorithm);

            // add current block to the map and track it as last committed block
            committedBlock = currentBlock;
            blockMap.put(committedBlock.getBlockNumber(), committedBlock);

            // allocate new block for future transactions
            currentBlock = new Block( currentBlock.getBlockNumber() + 1, blockHash, committedBlock, committedBlock.getAccountBalanceMap());
        }
        return transactionId;
    }

    /**
     * Return the account balance for the Account with a given address based on the most
     * recent completed block.
     * If the Account does not exist, throw a LedgerException.
     * @param address account address, must be valid
     * @return account balance
     * @throws LedgerException
     */
    @Override
    public int getAccountBalance(String address) throws LedgerException {
        // validate input, throw an exception with proper command/reason otherwise
        Utils.assertTrue(committedBlock != null, GET_ACCOUNT_BALANCE, NO_COMMITTED_BLOCK);
        Utils.assertTrue( AccountFactory.exists(address), GET_ACCOUNT_BALANCE, ACCOUNT_NOT_FOUND);
        return committedBlock.getAccountBalance(address);
    }

    /**
     * @return the account balance map for the most recently completed block.
     */
    @Override
    public HashMap<String, Account> getAccountBalances() {
        return committedBlock != null ? committedBlock.getAccountBalanceMap() : null;
    }

    /**
     * Internal method to validate the transaction
     * @param transaction
     * @throws LedgerException
     */
    private void validateTransaction(Transaction transaction) throws LedgerException {

        // Test payer and receiver accounts are valid
        String payer = transaction.getPayer();
        String receiver = transaction.getReceiver();

        int fee = transaction.getFee();
        int amount = transaction.getAmount();

        // validate payer and receiver accounts
        Utils.assertTrue( AccountFactory.exists(payer), PROCESS_TRANSACTION, payer + ACCOUNT_NOT_FOUND);
        Utils.assertTrue( AccountFactory.exists(receiver), PROCESS_TRANSACTION, receiver + ACCOUNT_NOT_FOUND);

        // min fee requirement
        Utils.assertTrue( fee >= 10, PROCESS_TRANSACTION, payer + MIN_FEE);

        // amount must be greater than 0
        Utils.assertTrue( amount > 0, PROCESS_TRANSACTION, payer + INVALID_AMOUNT);

        int currentBalance = currentBlock.getAccountBalance(payer);

        // payer has sufficient funds
        Utils.assertTrue( currentBalance >= transaction.getAmount() + transaction.getFee() ,
                PROCESS_TRANSACTION,
                payer + NOT_SUFFICIENT_BALANCE);
    }

    /**
     * Return the Block for the given block number.
     * return null, in case block number does not exist
     * @param blockNumber
     * @return Block object
     */
    @Override
    public Block getBlock(int blockNumber) {
        Block block = blockMap.get(blockNumber);

        // return clone of the block
        return block == null ? null : block.clone();
    }

    /**
     * Return the Transaction for the given transaction id.
     * @param transactionId
     * @return
     */
    @Override
    public Transaction getTransaction(String transactionId) {
        Transaction transaction = TransactionFactory.get(transactionId);
        return transaction != null ? transaction.clone(): null;
    }

    /**
     * Validate the current state of the blockchain.For each block,
     * check the hash of the previous hash, make sure that the account balances total
     * to the max value. Verify that each completed block has exactly 10 transactions.
     * @throws LedgerException
     */
    @Override
    public void validate() throws LedgerException{

        // starting with 0 balances inb everyone's account, validate the account balances for all the blocks
        // and other block level validations
        HashMap<String, Account> startingBalances = Utils.deepCopyAndResetBalance(AccountFactory.accounts);

        // Master account is an exception, fund it well
        Account account = startingBalances.get(masterAccount);
        account.setBalance(Integer.MAX_VALUE);

        // for Genesis block re-compute the prev-hash
        String prevHash = hashingAlgorithm.generateHash(seed);

        // validate all the block are lined up properly.
        int block_count = blockMap.size();

        for( int i = 1; i <= block_count; i++) {
            Block block = blockMap.get(i);

            // validate blocks, included hashes
            // block validate throws an exception in case of an error or mismatch
            block.validate(startingBalances, i, prevHash, hashingAlgorithm);

            prevHash = block.getHash();
        }
    }

    /**
     *
     * @return String representation of the Ledger
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Ledger: Name[")
                .append(name).append("]")
                .append(" Description[").append(description).append("]");

        return builder.toString();
    }

    ///FREEMAN ADDED THIS TO GET ACCOUNT BALANCES FROM THE CONTROLLER SERVICE
    public static int commandAccountBalance(String address){
        //Block recentBlock = blockMap.get(b);

        return currentBlock.getAccountBalance(address);
    }
}
