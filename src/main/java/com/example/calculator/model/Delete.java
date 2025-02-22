package com.example.calculator.model;

import java.util.regex.Pattern;

public class Delete {

    /**
     * 削除処理
     * ACならゼロを返却
     * Ｃなら最後尾削除メソッド実行後返却
     */
    public static String deleteProcessing(String formula, String clickData) {
        //ACならformulaをゼロにして返却
        //Ｃなら最後尾を削除して返却
        if (formula == null || clickData == null) {
            return formula;
        }

        if (isExactAC(clickData)) {
            return "0";
        }

        if (isSingleC(clickData)) {
            return (isLongerThanOne(formula)) ? deleteLastChar(formula) : "0";
        }

        return formula;
    }

    /**
     * 削除系処理であることを判断する
     * ＣorＡＣ
     */
    public static boolean checkDelete(String c) {
        return c != null && (isSingleC(c) || isExactAC(c));
    }

    public static boolean isSingleC(String c) {
        return Pattern.matches("^Ｃ$", c);
    }

    /**
     * 記号がＡＣであることを確認する
     */
    public static boolean isExactAC(String c) {
        return Pattern.matches("^ＡＣ$", c);
    }

    /**
     * 文字列の長さが2文字以上であることを確認する
     * （長さ１の時にc押されるとゼロにしたいため）
     */
    public static boolean isLongerThanOne(String str) {
        return str.length() > 1;
    }

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
