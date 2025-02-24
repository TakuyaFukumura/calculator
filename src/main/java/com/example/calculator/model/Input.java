package com.example.calculator.model;

import java.util.regex.Pattern;

public class Input {

    /**
     * 記号ではないことを確認するメソッド
     * （数字であることを確認する）
     */
    public static boolean notSymbol(char c) {
        return Pattern.matches("^[0-9０-９]*$", String.valueOf(c));
    }

    /**
     * char配列の最後の文字を取り出す
     */
    public static char getLastChar(char[] charList) {
        int size = charList.length;
        return charList[size - 1];
    }
}
