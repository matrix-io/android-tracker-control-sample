package com.admobilize.tracker.sample_lib;

/**
 * Created by Antonio Vanegas @hpsaturn on 12/7/18.
 */
public class StringUtils {
    public static String formatString(String text, int start, int finish) {

        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = start; i < finish; i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n" + indentString + letter + "\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n" + indentString + letter);
                    break;
                case ',':
                    json.append(letter + "\n" + indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }
}
