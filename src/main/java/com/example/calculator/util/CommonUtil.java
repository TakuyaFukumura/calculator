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
     * 桁数をチェックして、
     * 入力継続（指定桁数以内であれば）できるならTrue
     */
    public static boolean checkNumber(String display) {
        boolean flag = true;
        int count = 0;
        //桁数チェック(分解して、左塊とって、数字ならば桁チェックして返す)
        String[] str = Calculation.splitFormula(display);//分解してまとまりに分ける処理
        if (judgmentNumG(str[str.length - 1])) {
            count = countDigits(str[str.length - 1]);
        }
        if (count > 11) flag = false;
        return flag;
    }

    public static boolean judgmentNumG(String c) {
        boolean flag = false;
        if (c != null) flag = Pattern.matches("^[0-9０-９]*$|^[0-9０-９]+\\.[0-9０-９]+$", c);
        return flag;
    }
}//各メソッドを整備したい
