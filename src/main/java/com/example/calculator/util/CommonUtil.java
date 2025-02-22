package com.example.calculator.util;

import com.example.calculator.model.Calculation;

import java.util.regex.Pattern;

public class CommonUtil {

    private CommonUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 指定された文字列内の数字の個数をカウントする
     *
     * <p>このメソッドは、入力文字列の各文字をチェックし、数値（0～9）の
     * 文字の個数を返す</p>
     *
     * @param input チェック対象文字列
     * @return 数字の個数
     * @throws NullPointerException 引数が null の場合
     */
    public static int countDigits(String input) {
        return (int) input.chars().filter(Character::isDigit).count();
    }

    /**
     * 桁数をチェックして、入力継続（指定桁数以内であれば）できるならtrueを返す
     *
     * <p>このメソッドは、文字列を数式として分解し、最後のセグメントが数字であればその桁数を数え、桁数が11桁以下であればtrueを返します。</p>
     *
     * @param formula チェック対象の文字列（数式として分解される）
     * @return 最後のセグメントが数字で、かつその桁数が11桁以下であればtrue、それ以外はfalse
     */
    public static boolean checkNumber(String formula) {
        int count = 0;
        //桁数チェック(分解して、左塊とって、数字ならば桁チェックして返す)
        String[] segments = Calculation.splitFormula(formula);//分解してまとまりに分ける処理
        String lastSegment = segments[segments.length - 1];
        if (isNumeric(lastSegment)) {
            count = countDigits(segments[segments.length - 1]);
        }
        return count <= 11;
    }

    /**
     * 与えられた文字列が数字または小数点を含む数字であるかをチェックします。
     *
     * <p>このメソッドは、文字列が以下のいずれかの形式である場合にtrueを返します：</p>
     * <ul>
     *   <li>整数（例: "123", "０１２３"）</li>
     *   <li>小数（例: "123.45", "１２３．４５"）</li>
     * </ul>
     *
     * @param segment チェック対象の文字列
     * @return 数字または小数点を含む数字であればtrue、それ以外はfalse
     */
    public static boolean isNumeric(String segment) {
        return segment != null && Pattern.matches("^[0-9０-９]*$|^[0-9０-９]+\\.[0-9０-９]+$", segment);
    }
}
