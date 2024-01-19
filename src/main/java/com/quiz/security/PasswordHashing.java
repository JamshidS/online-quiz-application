package com.quiz.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashing {
    public static String hashPassword(String password) {
        try {
            byte[] salt = generateSalt();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(salt);

            byte[] hashedPassword = messageDigest.digest(password.getBytes());

            byte[] combined = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, combined ,0, salt.length);
            System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

            return Base64.getEncoder().encodeToString(combined);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {

        try {
            byte[] combined = Base64.getDecoder().decode(hashedPassword);

            byte[] salt = new byte[16];
            System.arraycopy(combined, 0, salt, 0, salt.length);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(salt);

            byte[] hashedPasswordBytes = messageDigest.digest(password.getBytes());

            for (int i = 0; i < hashedPasswordBytes.length; i++) {
                if (hashedPasswordBytes[i] != combined[i + salt.length]) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
