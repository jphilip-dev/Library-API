package com.training.restLibrary.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodeText(String text) {
        return passwordEncoder.encode(text);
    }

    public static void main(String[] args) {
        String plainText = "yourTextHere";
        String hashedText = encodeText(plainText);
        System.out.println("Hashed text: " + hashedText);
    }
}
