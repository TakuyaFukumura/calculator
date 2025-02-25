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
        String[] segments = Calculation.splitFormula(formula); // 分解してまとまりに分ける処理
        String lastSegment = segments[segments.length - 1];
        if (isNum(lastSegment)) {
            count = countDigits(segments[segments.length - 1]);
        }
        return count <= 11;
    }

    /**
     * 与えられた文字列が数字または小数点を含む数字であるかをチェックします。
     *
     * <p>このメソッドは、文字列が以下のいずれかの形式である場合にtrueを返します：</p>
     * <ul>
     *   <li>整数（例: "123"）</li>
     *   <li>小数（例: "123.45"）</li>
     * </ul>
     *
     * @param segment チェック対象の文字列
     * @return 数字または小数点を含む数字であればtrue、それ以外はfalse
     */
    public static boolean isNum(String segment) {
        return segment != null && Pattern.matches("^[0-9]*$|^[0-9]+\\.[0-9]+$", segment);
    }

    /**
     * 指定された文字が数値（0-9）であるかを判定します。
     *
     * @param input 判定対象の文字
     * @return 指定された文字が数値の場合は {@code true}、それ以外は {@code false}
     */
    public static boolean isNumeric(char input) {
        return isNumeric(String.valueOf(input));
    }

    /**
     * 指定された文字列が数値のみで構成されているかを判定します。
     * 空文字列の場合は {@code true} を返します。
     *
     * @param input 判定対象の文字列
     * @return 指定された文字列が数値のみで構成されている場合は {@code true}、それ以外は {@code false}
     */
    public static boolean isNumeric(String input) {
        return !input.isEmpty() && Pattern.matches("^\\d*$", input);
    }

    /**
     * 引数として渡された文字列の末尾の1文字を削除します。
     * 入力が {@code null} または空文字の場合、そのままの文字列を返します。
     *
     * @param str 末尾を削除したい文字列
     * @return 末尾の1文字を削除した文字列。{@code null} または空文字の場合は、入力された文字列をそのまま返す。
     */
    public static String deleteLastChar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    public static String getLastString(String str) {
        return str.substring(str.length() - 1);
    }
}
