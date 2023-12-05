package com.chason.words.utils;

import java.util.Random;

public class NumberUtil {

    /**
     * 生成8位随机数
     * @return
     */
    public static String random8Number() {

        Random random = new Random();

        // 生成一个8位随机数
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }


}
