package com.cscie97.ledger;

/**
 * The Account class represents an individual account within the Ledger Service.
 * An account contains an address that provides a unique identity for the Account.
 * The Account also contains a balance that represents the value of the account.
 * The account can only be updated by theLedger Service.
 */
public class Account implements Cloneable {

    /* address or accountId are the same thing */
    private final String address;

    /* current balance in the account */
    private int balance;

    /**
     * Constructor
     * @param address account identifier
     * @param balance account balance
     */
    public Account(String address, int balance) {
        this.address = address;
        this.balance = balance;
    }

    /**
     * @return account identifier
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return account balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * update to new balance
     * @param newBalance
     */
    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }

    /**
     * @return copy of the account object
     */
    @Override
    protected Account clone() {
        try {
            return (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * pretty print version
     * @return string representation of account object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Account: ")
                .append("Address[").append(address).append("] ")
                .append("Balance[").append(balance).append("]");

        return builder.toString();
    }
}
