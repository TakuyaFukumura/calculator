package com.example.calculator.model;

import com.example.calculator.util.CommonUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculation {
    // 定数化
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final String EQUAL = "＝";
    private static final String PLUS = "＋";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "×";
    private static final String DIVIDE = "÷";
    private static final BigDecimal OVERFLOW_VALUE = new BigDecimal("1234567891023"); // オーバーフローを表す値
    private static final int MAX_SCALE = 12; // 最大桁数

    /**
     * 計算式の最後の演算式を返します。例：「÷5」
     * 式が存在しない時は空文字を返します
     *
     * @param formula 計算式
     * @return 最後の演算式
     */
    public static String getLastArithmetic(String formula) {
        String[] parts = splitFormula(formula);
        if (parts.length < 3) {
            return "";
        }
        return parts[parts.length - 2] + parts[parts.length - 1];
    }

    /**
     * 実際の計算処理を行います。
     *
     * @param formula 計算式
     * @return 計算結果
     */
    public static String calculationProcessing(String formula) {
        if (!CommonUtil.isNumeric(CommonUtil.getLastString(formula))) {
            return formula; // 最後尾が記号の場合はそのまま返す
        }
        String[] parts = splitFormula(formula); // 分解してまとまりに分ける処理
        return calculate(parts); // まとまりを３つづつ計算していく処理
    }

    /**
     * まとまりを３つづつ計算していく処理
     */
    public static String calculate(String[] str) {
        //宣言部
        String result;
        int i = 0;
        //先頭が記号ならばマイナスの数値にする処理が必要
        if (judgmentSymbolG(str[i])) {
            str[i + 1] = str[i] + str[i + 1]; //ここで２番目の配列操作してしまえばいい
            i++;
        }
        //ループ開始 while
        while (true) {
            //数値文字変換
            BigDecimal num1 = new BigDecimal(str[i]);

            if (i == str.length - 1) {
                result = str[i];
                break;
            } else {
                i++;
            }
            int type = judgmentOperator(str[i]); //演算子判定
            i++;
            BigDecimal num2 = new BigDecimal(str[i]); //数値文字変換
            str[i] = "" + calculateSwitch(num1, type, num2); //演算実行して
        }//ループ終了
        return result;
    }

    /**
     * 演算スイッチ処理
     */
    public static BigDecimal calculateSwitch(BigDecimal num1, int type, BigDecimal num2) {
        return switch (type) {
            case 1 -> num1.add(num2);
            case 2 -> num1.subtract(num2);
            case 3 -> num1.multiply(num2);
            case 4 -> division(num1, num2);
            case 5 -> multiplicationAndMinus(num1, num2);
            case 6 -> divisionAndMinus(num1, num2);
            default -> BigDecimal.ZERO;
        };
    }

    /**
     * マイナスの掛け算メソッド
     *
     * @param num1 掛けられる数
     * @param num2 掛ける数（マイナス値）
     * @return マイナスで掛けた数
     */
    public static BigDecimal multiplicationAndMinus(BigDecimal num1, BigDecimal num2) {
        num2 = ZERO.subtract(num2); //符号反転
        return num1.multiply(num2); //掛け算実行して返す
    }

    /**
     * 割り算
     *
     * @param num1 割られる数
     * @param num2 割る数
     * @return 割り算結果
     */
    public static BigDecimal division(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(ZERO) == 0) { // ゼロ除算時の特別な処理
            return new BigDecimal("1234567891023"); //あえて大きい数を入れてオーバーフローさせる
        }

        try {
            return num1.divide(num2); //普通の割り算
        } catch (ArithmeticException e) {
            // 例外時、最も近い数に丸め込む
            BigDecimal result = num1.divide(num2, 11, RoundingMode.DOWN);
            //上の桁数取得して再リザルト
            //マイナス対応のため絶対値使用
            int scale = 12 - figureLengthUpPoint(result.abs().toString()).length();
            return num1.divide(num2, scale, RoundingMode.DOWN); // 桁数を調整して再計算
        }
    }

    /**
     * マイナスの割り算
     *
     * @param num1 割られる数
     * @param num2 割る数(マイナス値)
     * @return 割り算結果
     */
    public static BigDecimal divisionAndMinus(BigDecimal num1, BigDecimal num2) {
        num2 = ZERO.subtract(num2);
        return division(num1, num2);
    }

    /**
     * 少数の整数部を取得する
     *
     * @param str 小数
     * @return 整数部
     */
    public static String figureLengthUpPoint(String str) {
        Matcher matcher = Pattern.compile("(^[0-9]+)").matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

    /**
     * 計算式を分解して纏りに分ける処理
     */
    public static String[] splitFormula(String display) {
        char[] tmp = display.toCharArray();
        List<String> strList = new ArrayList<>();

        StringBuilder current = new StringBuilder(Character.toString(tmp[0]));

        for (int i = 1; i < tmp.length; i++) {
            if ((judgmentNumG(tmp[i]) && judgmentNumG(tmp[i - 1])) ||
                    (judgmentSymbolG(tmp[i]) && judgmentSymbolG(tmp[i - 1]))) {
                current.append(tmp[i]); // 同じグループなら追加
            } else {
                strList.add(current.toString()); // 現在の文字列をリストに追加
                current.setLength(0); // StringBuilderをリセット
                current.append(tmp[i]);
            }
        }
        strList.add(current.toString()); // 最後の要素を追加

        return strList.toArray(new String[0]);
    }

    /**
     * 演算子判定処理
     * -、＋、×、÷、×-、÷-
     * １、２、３、４、５　、６
     */
    public static int judgmentOperator(String str) {
        Map<String, Integer> operatorMap = Map.of(
                "＋", 1,
                "-", 2,
                "×", 3,
                "×＋", 3,
                "÷", 4,
                "÷＋", 4,
                "×-", 5,
                "÷-", 6
        );
        return operatorMap.getOrDefault(str, 0);
    }

    /**
     * 計算処理であることを判断する「＝」
     */
    public static boolean checkCalculation(String c) {
        return symbolIsEqual(c);
    }

    /**
     * 記号が＝であることを確認する
     */
    public static boolean symbolIsEqual(String c) {
        return c != null && Pattern.matches("＝", c);
    }

    /**
     * 数字グループであることを確認する
     */
    public static boolean judgmentNumG(char c) {
        return Pattern.matches("^[0-9]$|^\\.$", String.valueOf(c));
    }

    /**
     * 記号グループであることを確認する
     */
    public static boolean judgmentSymbolG(String c) {
        return c != null && Pattern.matches("^＋$|^-$|^×$|^÷$", c);
    }

    public static boolean judgmentSymbolG(char c) {
        return Pattern.matches("^＋$|^-$|^×$|^÷$", String.valueOf(c));
    }
}
