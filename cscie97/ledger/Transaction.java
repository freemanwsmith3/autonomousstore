package com.cscie97.ledger;

/**
 * The Transaction class represents a transaction in the Ledger System.  A transaction contains a
 * transaction id, an amount, a fee, a note, and references a payer account and a receiver account.
 * The transaction amount is transferred from the payer’s account balance to the receiver’s account balance.
 * The transaction fee is transferred from the payer’s account to the master account.  Transactions are aggregated
 * with in blocks.
 */
public class Transaction implements Cloneable {

    /* unique transaction Id*/
    private final String transactionId;

    /* transfer amount involved*/
    private final int amount;

    /* fee for the transaction. Must be greater than 10 */
    private final int fee;

    /* notes about the transaction */
    private final String note;

    /* payer */
    private final String payerAddress;

    /* receiver account */
    private final String receiverAddress;

    /* keep track of transaction being recorded as the transaction may get rejected */
    private boolean recorded;

    /**
     * Constructor
     * @param transactionId
     * @param amount
     * @param fee
     * @param note
     * @param payerAddress
     * @param receiverAddress
     */
    public Transaction( String transactionId, int amount, int fee, String note, String payerAddress, String receiverAddress) {
        this.transactionId = transactionId;
        this.fee = fee;
        this.amount = amount;
        this.note = note;
        this.payerAddress = payerAddress;
        this.receiverAddress = receiverAddress;
        this.recorded = false;
    }

    /* Accessors */
    public String getTransactionId() {
        return transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public int getFee() {
        return fee;
    }

    public String getNote() {
        return note;
    }

    public String getPayer() {
        return payerAddress;
    }

    public String getReceiver() {
        return receiverAddress;
    }

    public void setRecorded() { this.recorded = true; }

    /**
     * @return clone of transaction object
     */
    public Transaction clone() {

        // no fields to deep copy.
        try {
            return (Transaction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Pretty print
     * @return
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Transaction :")
                .append(" TransactionId[").append(transactionId).append("]")
                .append(" Amount[").append(amount).append("]")
                .append(" Fee[").append(fee).append("]")
                .append(" Note[").append(note).append("]")
                .append(" Payer[").append(payerAddress).append("]")
                .append(" Receiver[").append(receiverAddress).append("]")
                .append(" Recorded[").append(recorded ? "True" : "False").append("]")
        ;

        return builder.toString();
    }
}
