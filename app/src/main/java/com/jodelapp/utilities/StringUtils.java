package com.jodelapp.utilities;

import android.support.annotation.Nullable;
import android.widget.TextView;

public class StringUtils {

    /**
     * Check if the string is either null or empty.
     *
     * @param string The string to check
     * @return True if the string is null or empty. False otherwise.
     */
    public boolean isBlank(CharSequence string) {
        return (string == null || string.toString().trim().length() == 0);
    }

    /**
     * Get the passed string or the default one if the first string is blank - see {@link
     * StringUtils#isBlank(CharSequence)}.
     *
     * @param string        The string to check
     * @param defaultString The default value to return
     * @return The string if not blank. The default value otherwise
     */
    public String valueOrDefault(String string, String defaultString) {
        return isBlank(string) ? defaultString : string;
    }

    /**
     * Check if the text view is either null or empty.
     *
     * @param textView The text view to check
     * @return True if the text view is null or empty. False otherwise.
     */
    public boolean isBlank(TextView textView) {
        return (textView == null || isBlank(textView.getText()));
    }

    /**
     * Get the value in the text view or the default one if the text view is blank - see {@link
     * StringUtils#isBlank(TextView)}. The text in the text view is trimmed.
     *
     * @param textView      The text view to check
     * @param defaultString The default value to return
     * @return The text in the text view if not blank. The default value otherwise
     */
    public String valueOrDefault(TextView textView, String defaultString) {
        return isBlank(textView) ? defaultString : textView.getText().toString().trim();
    }

    /**
     * Replaces a String with another String inside a larger String.
     *
     * @param text         to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement  the String to replace it with, may be null
     * @return the text with any replacements, {@code null} if null String input
     */
    @Nullable
    public String replace(@Nullable final String text, @Nullable final String searchString,
                          @Nullable final String replacement) {
        if (isBlank(text) || isBlank(searchString) || replacement == null) return text;

        return text.replace(searchString, replacement);
    }

    /**
     * Returns true if a and b are equal, including if they are both null.
     * <p><i>Note: In platform versions 1.1 and earlier, this method only worked well if
     * both the arguments were instances of String.</i></p>
     * @param a first CharSequence to check
     * @param b second CharSequence to check
     * @return true if a and b are equal
     */
    public boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }
}
