package com.example.calculator.model;

import com.example.calculator.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;


public class Calculation {
    private static final String PLUS = "＋";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "×";
    private static final String DIVIDE = "÷";

    private Calculation(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 計算式を分解し、数値と演算子の塊に分割します。
     *
     * @param formula 計算式
     * @return 数値と演算子の塊に分割された配列
     */
    public static String[] splitFormula(String formula) {
        List<String> result = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        char[] chars = formula.toCharArray();
        final char DOT = '.';

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            boolean isNumberOrDot = CommonUtil.isNumeric(c) || c == DOT;

            if (i > 0) {
                char prevChar = chars[i - 1];
                boolean isPrevNumberOrDot = CommonUtil.isNumeric(prevChar) || prevChar == DOT;
                boolean isSameType = (isNumberOrDot && isPrevNumberOrDot) || (isOperator(c) && isOperator(prevChar));

                if (!isSameType) {
                    addToken(result, token);
                }
            }

            token.append(c);
        }
        addToken(result, token);

        return result.toArray(new String[0]);
    }

    /**
     * トークンをリストに追加し、StringBuilderをリセットする。
     *
     * @param result トークンのリスト
     * @param token  現在のトークン
     */
    private static void addToken(List<String> result, StringBuilder token) {
        if (!token.isEmpty()) {
            result.add(token.toString());
            token.setLength(0);
        }
    }

    /**
     * 演算子であるかどうかをチェックする。
     *
     * @param input チェック対象の文字
     * @return 演算子であればtrue
     */
    public static boolean isOperator(char input) {
        return isOperator(String.valueOf(input));
    }

    /**
     * 演算子であるかどうかをチェックする。
     *
     * @param input チェック対象の文字
     * @return 演算子であればtrue
     */
    public static boolean isOperator(String input) {
        return PLUS.equals(input) || MINUS.equals(input) || MULTIPLY.equals(input) || DIVIDE.equals(input);
    }
}
