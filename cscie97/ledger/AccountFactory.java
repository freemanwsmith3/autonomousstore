package com.cscie97.ledger;

import java.util.HashMap;

import static com.cscie97.ledger.LedgerException.Reason.*;
import static com.cscie97.ledger.LedgerException.Commands.*;

/**
 * Account factory, responsible for creating tracking new accounts.
 */
public class AccountFactory {

    // keep track of existing accounts
    public static HashMap<String, Account> accounts = new HashMap<>();

    public static Account create(String address, int balance) throws LedgerException {
        // ensure that we do not have an account with the same address already
        Utils.assertTrue(!accounts.containsKey(address), CREATE_ACCOUNT, DUPLICATE_ACCOUNT);

        Account account =  new Account(address, balance);
        accounts.put(address, account);
        return account;
    }

    /**
     * @param address
     * @return is account exists
     */
    public static boolean exists(String address) {
        return accounts.containsKey(address);
    }
}
