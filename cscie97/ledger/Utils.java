package com.cscie97.ledger;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Static helper functions to avoid code duplication
 */
public class Utils {

    /**
     * Create a new hash map and deep copy
     * @param from
     * @return new hash map
     */
    public static HashMap<String, Account> deepCopy(HashMap<String, Account> from) {
        return deepCopy(from, new HashMap<>());
    }

    /**
     * deep copy
     * @param from source hashmap
     * @param to destination hashmap
     * @return
     */
    public static HashMap<String, Account> deepCopy(HashMap<String, Account> from, HashMap<String, Account> to) {
        for (HashMap.Entry<String, Account> entry : from.entrySet()) {
            to.put(entry.getKey(), new Account(entry.getValue().getAddress(), entry.getValue().getBalance()));
        }
        return to;
    }

    /**
     * create a new hashmap, deep copy and reset balances
     * @param from
     * @return new hashmap
     */
    public static HashMap<String, Account> deepCopyAndResetBalance(HashMap<String, Account> from) {
        return deepCopyAndResetBalance(from, new HashMap<>());
    }

    /**
     * deep copy the hashmap and reset balances
     * @param from source hashmap
     * @param to destination hashmap
     * @return destination hash map
     */
    public static HashMap<String, Account> deepCopyAndResetBalance(HashMap<String, Account> from, HashMap<String, Account> to) {
        for (HashMap.Entry<String, Account> entry : from.entrySet()) {
            to.put(entry.getKey(), new Account(entry.getValue().getAddress(), 0));
        }
        return to;
    }

    /**
     * create a new array list and deep copy transactions
     * @param from
     * @return new copy
     */
    public static ArrayList<Transaction> deepCopy(ArrayList<Transaction> from) {
        return deepCopy(from, new ArrayList<>());
    }

    /**
     * deep copy
     * @param from source
     * @param to destination
     * @return deep copied destination
     */
    public static ArrayList<Transaction> deepCopy(ArrayList<Transaction> from, ArrayList<Transaction> to) {
        for( Transaction transaction : from) {
            to.add(transaction.clone());
        }
        return to;
    }

    /**
     * @param predicate
     * @param command
     * @param reason
     * @throws LedgerException when predicate is not true
     */
    public static void assertTrue(boolean predicate, String command, String reason) throws LedgerException {
        if( !predicate) {
            throw new LedgerException(command, reason);
        }
    }

    public static void prettyPrint(HashMap<String,Account> accountBalances) {
        if (accountBalances != null) {
            for (HashMap.Entry<String, Account> entry : accountBalances.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
}
