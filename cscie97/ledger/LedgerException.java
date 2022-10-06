package com.cscie97.ledger;

/**
 * Ledger will use the exception mechanism to notify the user on an error.
 */
public class LedgerException extends Throwable {

    // various ledger operations
    public static class Commands {
        static final String CREATE_ACCOUNT = "CreateAccount";
        static final String CREATE_TRANSACTION = "CreateTransaction";
        static final String GET_ACCOUNT_BALANCE = "GetAccountBalance";
        static final String PROCESS_TRANSACTION = "ProcessTransaction";
        static final String HASHING_BLOCK = "ProcessTransaction";
        static final String VALIDATE = "ProcessTransaction";
    }

    // error codes/reasons
    public static class Reason {
        static final String DUPLICATE_ACCOUNT = " Duplicate Account";
        static final String ACCOUNT_NOT_FOUND = " Account Not Found";
        static final String DUPLICATE_TRANSACTION = " Duplicate Transaction Id";
        static final String NOT_SUFFICIENT_BALANCE = " Not Sufficient Balance";
        static final String NO_HASHING_ALGORITHM = " No Such Algorithm Found";
        static final String MIN_FEE = " Fee must be greater than 10";
        static final String INVALID_AMOUNT = " Amount must be greater than 0";
        static final String BALANCE_MISMATCH = " Balance mismatch after applying transaction in the block";
        static final String INVALID_TRANSACTION_COUNT = " Number of transactions is less than 10 on a committed block";
        static final String HASH_MISMATCH = " Hash does not match";
        static final String PREV_HASH_MISMATCH = " Prev Hash does not match";
        static final String BLOCK_NUMBER_MISMATCH = " Block Number does not match";
        static final String NO_COMMITTED_BLOCK = " No Committed block. Can not verify account";
    }

    private final String command;

    private final String reason;

    /**
     * Constructor
     * @param command
     * @param reason
     */
    public LedgerException(String command, String reason) {
        this.command = command;
        this.reason = reason;
    }

    // accessors
    public String getCommand() { return command; }

    public String getReason() { return reason; }

    // pretty print
    public String toString() {
        return "Command[" + command + "] failed. Reason[" + reason + "]";
    }
}
