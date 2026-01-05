package com.example.finance_server.Util;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private static final String ALPHANUMERIC =
            "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }
        return sb.toString();
    }
}
