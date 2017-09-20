package ch.swissbytes.ewallet.util;

import java.text.Normalizer;

/**
 * Created by eliana on 20-10-15.
 */
public class TMStringUtils {

    /**
     * Inner Function which replace a char in string
     * @param str, string to update
     * @param indexInDB, position in which they update.
     * @param replace, new value to set in the position index.
     * @return new string with update
     */
    public static String replace(String str, int indexInDB, char replace) {
        if (str == null) {
            return str;
        } else if (indexInDB < 0 || indexInDB > str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[indexInDB - 1] = replace;
        return String.valueOf(chars);
    }

    public static String subString(String str, int length){
        if(str ==null)
            return str;

        if(str.length()>=length)
            return str.substring(0,length);

        if(str.length()<length)
            return str;
        return str;
    }

    public static String normalizeAccents(String str){
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String subStringNormalizeAccents(String str, int length){
        return subString(normalizeAccents(str), length);
    }


    public static void main(String[] args) {
        // Test function normalizeAccents
        System.out.println(TMStringUtils.normalizeAccents("àèé"));
        System.out.println(TMStringUtils.normalizeAccents("àáèéíóúÁÉÍÓÚ"));
        System.out.println(TMStringUtils.normalizeAccents("ÄËÏÖÜäëïöü"));
        System.out.println(TMStringUtils.normalizeAccents("$%+-*"));

    }
}
