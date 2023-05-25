package org.example;

import java.util.Arrays;

public class Anagram {

    public static boolean isAnagram(String str1, String str2) {
        // Sanitize the strings by removing spaces and converting to lower case
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Base case: strings are the same length
        if (str1.length() != str2.length()) {
            return false;
        }

        // Sort the arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Compare
        return Arrays.equals(arr1, arr2);
    }
}
