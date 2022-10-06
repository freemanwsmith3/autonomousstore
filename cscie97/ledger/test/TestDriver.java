package com.cscie97.ledger.test;

import com.cscie97.ledger.CommandProcessor;
import com.cscie97.ledger.CommandProcessorException;


public class TestDriver {

    public static void main(String[] args) {

        CommandProcessor commandProcessor = new CommandProcessor();

        try {
            if (args.length < 1) {
                System.out.println("Script name required..");
                System.exit(1);
            }
            //commandProcessor.processCommandFile("/Users/neerajaggarwal/harvard/design/ledgerservice/src/ledger.script");
            commandProcessor.processCommandFile(args[0]);
        } catch (CommandProcessorException e) {
            System.out.println(e);
        }
    }
}
