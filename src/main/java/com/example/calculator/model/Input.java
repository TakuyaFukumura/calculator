package com.example.calculator.model;

import com.example.calculator.util.CommonUtil;

import java.util.regex.Pattern;

public class Input {

    /**
     * ピリオドの重複防止
     */
    public static boolean checkPeriod(String display) {
        //塊に分ける
        //最後の塊を取り出して、ピリオドが含まれているかチェックする
        if (display == null) {
            return false;
        }
        String[] str = Calculation.splitFormula(display);
        return isPeriod(str[str.length - 1]);
    }

    public static boolean isPeriod(String c) {
        return c != null && c.contains(".");
    }

    /**
     * 記号ではないことを確認するメソッド
     * （数字であることを確認する）
     */
    public static boolean notSymbol(char c) {
        return Pattern.matches("^[0-9０-９]*$", String.valueOf(c));
    }

    public static boolean notSymbol(String c) {
        return Pattern.matches("^[0-9０-９]*$", c);
    }

    /**
     * 記号が．であることを確認する
     */
    public static boolean symbolIsPeriod(String c) {
        return Pattern.matches("^\\.$", c);
    }

    /**
     * 記号がマイナスであることを確認する
     */
    public static boolean symbolIsMinus(char c) {
        return Pattern.matches("^-$", String.valueOf(c));
    }

    public static boolean symbolIsMinus(String c) {
        return Pattern.matches("^-$", c);
    }

    /**
     * 記号がプラスであることを確認する
     */
    public static boolean symbolIsPlus(char c) {
        return Pattern.matches("^＋$", String.valueOf(c));
    }

    public static boolean symbolIsPlus(String c) {
        return Pattern.matches("^＋$", c);
    }

    /**
     * 記号が×,÷であることを確認する
     */
    public static boolean symbolIsMuD(char c) { //引数Stringのほうがいいのでは？オーバーロードする？
        return Pattern.matches("^×$|^÷$", String.valueOf(c));
    }

    public static boolean symbolIsMuD(String c) {
        return Pattern.matches("^×$|^÷$", c);
    }

    /**
     * char配列の最後の文字を取り出す
     */
    public static char getLastChar(char[] charList) {
        int size = charList.length;
        return charList[size - 1];
    }

    /**
     * 文字列の長さが１&&ゼロならばtrueを返す
     */
    public static boolean judgment(String text) {
        return text.length() == 1 && Pattern.matches("^0$|^０$", text);
    }
}
