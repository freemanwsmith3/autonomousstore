package com.cscie97.ledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command line interface to test the Ledger API.
 * This class, will parse the commands, validate syntax
 * and return the results back to the tester app.
 */
public class CommandProcessor {

    /* supported CLI commands */
    private static final String CREATE_LEDGER = "create-ledger";
    private static final String CREATE_ACCOUNT = "create-account";
    private static final String GET_ACCOUNT_BALANCE = "get-account-balance";
    private static final String GET_ACCOUNT_BALANCES = "get-account-balances";
    private static final String PROCESS_TRANSACTION = "process-transaction";
    private static final String GET_BLOCK = "get-block";
    private static final String GET_TRANSACTION = "get-transaction";
    private static final String VALIDATE = "validate";
    private static final String COMMENTS = "#";

    /* List of supported commands */
    private final List<Command> commands = new ArrayList<>();

    /* main Ledger, will be initialized using one of the commands*/
    private Ledger ledger = null;

    /* flag to decide if we want to continue after first error. */
    boolean exitOnFirstError = false;

    /**
     * default constructor. Just setup all the supported commands
     */
    public CommandProcessor() {
        // set up the commands
        commands.add(new CreateLedger());
        commands.add(new CreateAccount());
        commands.add(new GetAccountBalances());
        commands.add(new GetAccountBalance());
        commands.add(new ProcessTransaction());
        commands.add(new GetBlock());
        commands.add(new GetTransaction());
        commands.add(new Validate());
        commands.add(new Comments());

        // Catch all command handler.
        commands.add(new CommandNotSupported());
    }

    /**
     * Process the command passed. Will run syntax validation. commands starting
     * with # are considered as commented and handled as comments.
     * Processes one command at a time.
     * @param commandStr
     * @throws CommandProcessorException
     */
    public void processCommand(String commandStr) throws CommandProcessorException {
        for (Command command : commands) {
            if (command.matches(commandStr)) {
                System.out.println("Processing: Command[" + commandStr + "]");
                command.execute(commandStr);
                System.out.println();
                return;
            }
        }
    }

    /**
     * Read a script containing commands and process those one at a time.
     * @param fileScript
     * @throws CommandProcessorException
     */
    public void processCommandFile(String fileScript) throws CommandProcessorException {

        int lineNumber = 0;
        String st = "";

        try {
            // read the file. In case file does not exist function will exit
            BufferedReader br = new BufferedReader(new FileReader(fileScript));

            // every line is a command.
            while ((st = br.readLine()) != null) {
                lineNumber++;

                try {
                    processCommand(st);
                } catch (CommandProcessorException ex) {
                    // command failed, decide to continue or exit
                    if (exitOnFirstError) {
                        throw new CommandProcessorException(ex.getCommand(), ex.getReason(), lineNumber);
                    } else {
                        System.out.println("Error: Line Number[" + lineNumber + "] " + ex + "\n");
                    }
                }
            }
        } catch (Exception ex) {
            throw new CommandProcessorException(st, ex.getMessage(), lineNumber);
        }
    }

    /**
     * Abstract class to handle common handling such as matching the command to specified regex
     * and return the expected number of parameters for the command.
     * Ideally, we could have used a CommandFactory
     */
    abstract class Command {

        /* regex pattern for the concrete command*/
        private final Pattern p;

        /* command to be handled */
        private final String command;

        /**
         * Base class constructor
         * @param pattern
         * @param command
         */
        public Command(String pattern, String command) {
            p = Pattern.compile(pattern);
            this.command = command;
        }

        /**
         * Throws an exception when command does not match regex
         * @param commandStr
         * @param expectCount
         * @return list of parameters specified upon successful command parsing.
         * @throws CommandProcessorException
         */
        public List<String> match(String commandStr, int expectCount) throws CommandProcessorException {

            Matcher m = p.matcher(commandStr);

            // empty list of parameters extracted from the command
            List<String> params = new ArrayList<>();

            if (m.find() && m.groupCount() == expectCount) {
                for (int i = 1; i <= expectCount; i++) {
                    params.add(m.group(i));
                }
            } else {
                // number of args do not match expected count or match not found
                throw new CommandProcessorException(command, "Parsing Error", -1);
            }

            return params;
        }

        /**
         * Identify appropriate Command instance
         * @param commandStr
         * @return true when we think command supplied can be handled by this command instance
         */
        public boolean matches(String commandStr) {
            return commandStr.startsWith(command);
        }

        public abstract void execute(String commandStr) throws CommandProcessorException;
    }

    /**
     * handles create-ledger command
     */
    class CreateLedger extends Command {
        //create-ledger <name> description <description> seed<seed>
        static String pattern = "^" + CREATE_LEDGER + " (.+) description .([a-zA-Z0-9 ]*). seed .([a-zA-Z0-9 ]*).";

        public CreateLedger() {
            super(pattern, CREATE_LEDGER);
        }

        /**]
         * Execute create ledge command
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 3);

            String ledgerName = params.get(0);
            String description = params.get(1);
            String seed = params.get(2);

            ledger = new LedgerImpl(ledgerName, description, seed);

            if (ledger != null) {
                System.out.println("Ledger Created:  " + ledger);
            }
        }
    }

    /**
     * Handle the create account command
     */
    class CreateAccount extends Command {
        //create-account <account-id>
        static String pattern = "^" + CREATE_ACCOUNT + " ([a-zA-Z0-9]*)";

        public CreateAccount() {
            super(pattern, CREATE_ACCOUNT);
        }

        /**
         * execute the command, print the status on console after successful completion.
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 1);

            String address = params.get(0);

            try {
                Account account = ledger.createAccount(address);
                System.out.println("Account Created: " + account);
            } catch (LedgerException e) {
                throw new CommandProcessorException(e.getCommand(), e.getReason());
            }
        }
    }

    /**
     * Get Account Balance
     */
    class GetAccountBalance extends Command {
        //get-account-balance <account-id>
        static String pattern = "^" + GET_ACCOUNT_BALANCE + " ([a-zA-Z0-9]*)";

        public GetAccountBalance() {
            super(pattern, GET_ACCOUNT_BALANCE);
        }

        /**
         * Print account balance to the console.
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 1);

            String address = params.get(0);

            try {
                int balance = ledger.getAccountBalance(address);
                System.out.println("Account balance for account[" + address + "] is " + balance);
            } catch (LedgerException ex) {
                throw new CommandProcessorException(ex.getCommand(), ex.getReason());
            }
        }
    }

    /**
     * get Block information from the Ledger
     */
    class GetBlock extends Command {
        //get-block <block-number>
        static String pattern = "^" + GET_BLOCK + " ([0-9]*)";

        public GetBlock() {
            super(pattern, GET_BLOCK);
        }

        /**
         * Print Block details on successful lookup
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 1);

            int blockNumber = Integer.parseInt(params.get(0));

            Block block = ledger.getBlock(blockNumber);

            if (block == null) {
                System.out.println("Block Not Found");
            } else {
                System.out.println("Block details: " + block);
            }
        }
    }

    /**
     * Get Transaction
     */
    class GetTransaction extends Command {
        //get-transaction <transaction-id>
        static String pattern = "^" + GET_TRANSACTION + " ([a-zA-Z0-9]*)";

        public GetTransaction() {
            super(pattern, GET_TRANSACTION);
        }

        /**
         * Print Transaction details on the console
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 1);

            String transactionId = params.get(0);

            Transaction transaction = ledger.getTransaction(transactionId);

            if (transactionId == null) {
                System.out.println("Transaction Not Found");
            } else {
                System.out.println("Transaction details: " + transaction);
            }
        }
    }

    /**
     * Process a transaction. No semantic validation are done at this level.
     */
    class ProcessTransaction extends Command {
        //process-transaction <transaction-id> amount <amount> fee <fee> note <note>
        //payer <account-address> receiver <account-address>
        static String pattern = "^" + PROCESS_TRANSACTION +
                " ([0-9]*) amount ([0-9]*) fee ([0-9]*) note .([a-zA-Z0-9 ]*)." +
                " payer ([a-zA-Z0-9]*) receiver ([a-zA-Z0-9]*)";

        public ProcessTransaction() {
            super(pattern, PROCESS_TRANSACTION);
        }

        /**
         * extract all the params required for processing a transaction.
         * Pass thew detail to ledger using API
         * Status or exception is reported back.
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            List<String> params = super.match(command, 6);

            String transactionId = params.get(0);
            int amount = Integer.parseInt(params.get(1));
            int fee = Integer.parseInt(params.get(2));
            String note = params.get(3);
            String payer = params.get(4);
            String receiver = params.get(5);

            try {
                ledger.processTransaction(transactionId, amount, fee, note, payer, receiver);
                System.out.println("Transaction Submitted");
            } catch (LedgerException e) {
                throw new CommandProcessorException(e.getCommand(), e.getReason());
            }
        }
    }

    /**
     * Get Account balances. Will print balances for
     * all the accounts from last committed block.
     */
    class GetAccountBalances extends Command {
        //get-account-balances
        static String pattern = "^" + GET_ACCOUNT_BALANCES;

        public GetAccountBalances() {
            super(pattern, GET_ACCOUNT_BALANCES);
        }

        /**
         * Get balances from the ledger and print.
         * @param command
         */
        public void execute(String command) {
            HashMap<String, Account> accountBalances = ledger.getAccountBalances();
            Utils.prettyPrint(accountBalances);
        }
    }

    /**
     * handle validate command and invoke function on ledger service to validate
     */
    class Validate extends Command {
        //validate
        static String pattern = "^" + VALIDATE;

        public Validate() {
            super(pattern, VALIDATE);
        }

        /**
         * validate the Ledger
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            try {
                ledger.validate();
                System.out.println("Validation Completed");
            } catch (LedgerException e) {
                // Ledger will throw an exception in case validation fails
                throw new CommandProcessorException(e.getCommand(), e.getReason());
            }
        }
    }

    /**
     * handle comments
     */
    class Comments extends Command {

        public Comments() {
            super("", COMMENTS);
        }

        /**
         * no-op, just ignore the comments for now
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            // no-op
        }
    }

    /**
     * Catch all command handler to match anything not supported
     * and throw an exception
     */
    class CommandNotSupported extends Command {

        public CommandNotSupported() {
            super("", "");
        }

        /**
         * Always matches the input.
         * @param commandStr
         * @return
         */
        public boolean matches(String commandStr) {
            return true;
        }

        /**
         * Always throw an exception.
         * @param command
         * @throws CommandProcessorException
         */
        public void execute(String command) throws CommandProcessorException {
            throw new CommandProcessorException(command, "Command Not Supported");
        }
    }
}
