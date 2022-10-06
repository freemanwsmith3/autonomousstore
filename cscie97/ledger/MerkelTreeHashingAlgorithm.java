package com.cscie97.ledger;

import java.util.ArrayList;

/**
 * Merkel Root calculations for a set of transactions
 */
public class MerkelTreeHashingAlgorithm {

    /**
     * Calculate Merkel root for the transactions. Use the provided hashing algorithm
     * @param transactions - Could be any length
     * @param hashingAlgorithm for example SHA-256.
     * @return
     */
    public static String generateHash(ArrayList<Transaction> transactions, HashingAlgorithm hashingAlgorithm) {

        // first step - calculate hash for each transaction i.e. find the leaf hashes
        ArrayList<String> transactionHashes = new ArrayList<>();

        for( Transaction transaction: transactions) {
            transactionHashes.add(hashingAlgorithm.generateHash(transaction.toString()));
        }

        // calculate Merkel root
        return getMerkelRoot(transactionHashes, hashingAlgorithm);
    }

    /**
     * Recurive implementation to calculate the Merkel Root based on design spec.
     * @param inputHashes
     * @param hashingAlgorithm
     * @return
     */
    private static String getMerkelRoot(ArrayList<String> inputHashes, HashingAlgorithm hashingAlgorithm) {

        //System.out.println("Input Hash = " + inputHashes);
        int size = inputHashes.size();

        // base case, only one elem, promote the hash to a level up
        if (size == 1) {
            return inputHashes.get(0);
        }

        // make space for next up-level
        ArrayList<String> nextLevelHashes = new ArrayList<>();

        // compute the next level hashes
        for( int i = 0; i < inputHashes.size(); i += 2 ){
            if( i+1 < size) {
                String levelUpHash = hashingAlgorithm.generateHash(inputHashes.get(i) + inputHashes.get(i+1));
                nextLevelHashes.add(levelUpHash);
            } else {
                nextLevelHashes.add(inputHashes.get(i));
            }
        }
        //System.out.println("Next level Hash = " + nextLevelHashes);
        return getMerkelRoot(nextLevelHashes, hashingAlgorithm);
    }
}
