package com.cscie97.ledger;

/**
 * The CommandProcessorException is returned from the CommandProcessor methods in response to an error condition.
 * The CommandProcessorException captures the command that was attempted and the reason for the failure.
 */
public class CommandProcessorException extends Throwable {

    private final String command;
    private final String reason;
    private final int lineNumber;

    /**
     * Constructor
     * @param command
     * @param reason
     */
    public CommandProcessorException(String command, String reason) {
        this(command, reason, -1);
    }

    /**
     * Constructor
     * @param command
     * @param reason
     * @param lineNumber
     */
    public CommandProcessorException(String command, String reason, int lineNumber) {
        this.command = command;
        this.reason = reason;
        this.lineNumber = lineNumber;
    }

    /* Accessors */
    public String getCommand() { return command; }

    public String getReason() { return reason; }

    public int getLineNumber() { return lineNumber; }

    /**
     * Pretty Print
     * @return String representation
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (lineNumber > 0) {
            stringBuilder.append(" Line[").append(lineNumber).append("]");
        }
        stringBuilder.append(" Command[").append(command).append("]");
        stringBuilder.append(" Reason[").append(reason).append("]");

        return stringBuilder.toString();
    }
}
