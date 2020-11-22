/*
 * Filename: DeweyDecimalUtility.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * A Utility class for the Dewey Decimal System of Classification.
 * @author Scott
 */
public final class DeweyDecimalUtility {

    private DeweyDecimalUtility() { }

    public static String parseCode(String code) {
        if (matchesPattern(code)) {
            return String.valueOf(code.charAt(0)) + String.valueOf(code.charAt(0)) + String.valueOf(code.charAt(0));
        }
        return "";
    }

    /**
     * Checks if a String matches the format of the Dewey Decimal Sytem using Regex.
     * @param s The String that will be checked for formatting.
     * @return The boolean value for whether it was a match.
     */
    public static boolean matchesPattern(String s) {
        return s.matches("^\\d{3}((?=\\.\\d)(\\.\\d*))?$");
    }

}
