package com.example.calculator.model;

public class Delete {

    /**
     * 文字列の最後尾1文字を削除する
     * 分割した文字を再合成
     */
    public static String deleteLastChar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }
}
