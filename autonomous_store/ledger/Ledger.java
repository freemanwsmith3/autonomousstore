package com.cscie97.ledger;

import java.util.HashMap;

/**
 * Ledger API
 */
public interface Ledger {

    /**
     * Create a new account, assign a unique identifier, and set the balance to 0.
     * Return the account identifier.
     * @param address
     * @return
     * @throws LedgerException
     */
    Account createAccount(String address) throws LedgerException;

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
    String processTransaction(String transactionId,
                              int amount,
                              int fee,
                              String note,
                              String payer,
                              String receiver) throws LedgerException;

    /**
     * Return the account balance for the Account with a given address based on the most
     * recent completed block.
     * If the Account does not exist, throw a LedgerException.
     * @param address
     * @return
     * @throws LedgerException
     */
    int getAccountBalance(String address) throws LedgerException;

    /**
     * Return the account balance map for the most recently completed block.
     * @return
     */
    HashMap<String, Account> getAccountBalances();

    /**
     * Return the Block for the given block number.
     * return null, in case block number does not exist
     * @param blockNumber
     * @return Block object
     */
    Block getBlock(int blockNumber);

    /**
     * Return the Transaction for the given transaction id.
     * @param transactionId
     * @return
     */
    Transaction getTransaction(String transactionId);

    /**
     * Validate the current state of the blockchain.For each block,
     * check the hash of the previous hash, make sure that the account balances total
     * to the max value. Verify that each completed block has exactly 10 transactions.
     * @throws LedgerException
     */
    void validate() throws LedgerException;
}
