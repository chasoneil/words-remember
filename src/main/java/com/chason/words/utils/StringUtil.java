package com.chason.words.utils;

public class StringUtil {

    public static boolean isEmpty (String string) {

        if (string == null || string.equals("")) {
            return true;
        }

        return false;
    }

}
