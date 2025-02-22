package com.example.calculator.model;

import com.example.calculator.util.CommonUtil;

import java.util.regex.Pattern;

public class Delete {


    /**
     * 実際の削除処理
     * ACなら０を返却
     * Ｃなら最後尾削除メソッド実行後返却
     */
    public static String deleteProcessing(String display, String clickData) {
        //ACならdisを０にして返却
        //Ｃなら最後尾削除メソッド実行後返却
        if (display != null && clickData != null) {
            if (symbolIsAC(clickData)) {
                display = "0";
            } else if (symbolIsC(clickData) && !charaOrMore2(display)) {
                display = "0";
            } else if (symbolIsC(clickData)) {
                display = deleteLastChar(display);
            }
        }
        return display;
    }

    /**
     * 削除系処理であることを判断する
     * ＣorＡＣ
     */
    public static boolean checkDelete(String c) {
        boolean flag = false;
        if (c != null) {
            if (symbolIsC(c) || symbolIsAC(c)) flag = true;
        }
        return flag;
    }

    /**
     * 記号がＣであることを確認する
     */
    public static boolean symbolIsC(char c) {
        return Pattern.matches("^Ｃ$", String.valueOf(c));
    }

    public static boolean symbolIsC(String c) {
        return Pattern.matches("^Ｃ$", c);
    }

    /**
     * 記号がＡＣであることを確認する
     */
    public static boolean symbolIsAC(String c) {
        return Pattern.matches("^ＡＣ$", c);
    }

    /**
     * 文字列の長さが2文字以上であることを確認する
     * （長さ１の時にc押されるとゼロにしたいため）
     */
    public static boolean charaOrMore2(String str) {
        return str.length() > 1;
    }

    /**
     * 文字列の最後尾1文字を削除する
     * 分割した文字を再合成
     */
    public static String deleteLastChar(String str) {
        StringBuilder tmp = new StringBuilder();
        char[] charList = CommonUtil.split(str);
        for (int i = 0; i < (charList.length - 1); i++) {
            tmp.append(charList[i]);
        }
        return tmp.toString();
    }

}
