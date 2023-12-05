package com.chason.test01.utils;

public class StringUtil {

    public static boolean isEmpty (String string) {

        if (string == null || string.equals("")) {
            return true;
        }

        return false;
    }

}
