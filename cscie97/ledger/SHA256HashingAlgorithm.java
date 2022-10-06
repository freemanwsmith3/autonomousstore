package com.cscie97.ledger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-256 hashing.
 */
public class SHA256HashingAlgorithm implements HashingAlgorithm {

    private MessageDigest md = null;

    /**
     * default constructor.
     */
    public SHA256HashingAlgorithm() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data
     * @return hash for the input data in hex format
     */
    @Override
    public String generateHash(String data) {
       md.update(data.getBytes());
       return bytesToHex(md.digest());
    }

    /**
     * return hex value for byte[]
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();

        for (byte b : bytes)
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

        return result.toString();
    }
}
