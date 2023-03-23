package com.ntneik15.selflearning.retailerapp.ultils;

public class CheckNumGenerator {
    final static String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final static String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    // Generate a unique check number for each payment with the following format: 2 letters + 5-6 numbers
public static String generateCheckNumber() {
        StringBuilder checkNumber = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            checkNumber.append(letters[(int) (Math.random() * 26)]);
        }
        for (int i = 0; i < (int) (Math.random() * 2) + 5; i++) {
            checkNumber.append(numbers[(int) (Math.random() * 10)]);
        }
        return checkNumber.toString();
    }
}
