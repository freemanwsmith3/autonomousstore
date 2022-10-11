package com.cscie97.ledger;

import java.util.HashMap;

import static com.cscie97.ledger.LedgerException.Reason.*;
import static com.cscie97.ledger.LedgerException.Commands.*;

/**
 * Factory to create transaction object.
 * We want to keep track of rejected transactions as well.
 */
public class TransactionFactory {

    /* Keep track of all transactions */
    private static HashMap<String, Transaction> transactionsMap = new HashMap<>();

    /**
     * @param transactionId
     * @param amount
     * @param fee
     * @param note
     * @param payer
     * @param receiver
     * @return Transaction object
     * @throws LedgerException when TransactionId is already used
     */
    public static Transaction create(String transactionId,
                                     int amount,
                                     int fee,
                                     String note,
                                     String payer,
                                     String receiver) throws LedgerException {

        Utils.assertTrue(!transactionsMap.containsKey(transactionId), CREATE_TRANSACTION, DUPLICATE_TRANSACTION);

        Transaction transaction =  new Transaction(transactionId, amount, fee, note, payer, receiver);
        transactionsMap.put(transactionId, transaction);
        return transaction;
    }

    /**
     * Return transaction. External users can not access this method.
     * @param transactionId
     * @return
     */
    public static Transaction get(String transactionId) {
        return transactionsMap.get(transactionId);
    }
}
