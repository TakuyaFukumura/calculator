package com.example.calculator.service;

import com.example.calculator.constants.Constants;
import com.example.calculator.model.Calculation;
import com.example.calculator.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InputService {

    private static final String DOT = ".";
    private static final String PLUS = "＋";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "×";
    private static final String DIVIDE = "÷";

    /**
     * 計算式の文字列を構築する。
     *
     * @param formula 現在の計算式
     * @param input   入力された文字
     * @return 更新された計算式
     */
    public String buildFormula(String formula, String input) {
        // 式が初期値の場合の処理
        if (Constants.INITIAL_VALUE.equals(formula)) {
            return handleInitialValue(input);
        }

        // 空の場合は処理を中断
        if (formula.isEmpty()) {
            return formula;
        }

        char lastChar = getLastChar(formula);

        // 最後が数字の場合の処理
        if (CommonUtil.isNumeric(lastChar)) { //最後尾が数字の時は、ほぼなんでも入る
            return handleNumericLastChar(formula, input);
        }

        // 最後が記号の場合の処理
        return handleSymbolLastChar(formula, input, lastChar);
    }

    /**
     * 式が初期値の場合の処理を行う
     *
     * @param input   入力された文字
     * @return 更新された計算式
     */
    private String handleInitialValue(String input) {
        if (DOT.equals(input)) {
            return Constants.INITIAL_VALUE + DOT;
        }
        if (MINUS.equals(input) || CommonUtil.isNumeric(input)) {
            return input;
        }
        return Constants.INITIAL_VALUE;
    }

    /**
     * 式の最後尾が数字の場合の処理を行う
     *
     * @param formula 現在の計算式
     * @param input   入力された文字
     * @return 更新された計算式
     */
    private String handleNumericLastChar(String formula, String input) {
        if (hasPeriod(formula) && DOT.equals(input)) {
            return formula;
        }
        return formula + input;
    }

    /**
     * 式の最後尾が記号の場合の処理を行う
     *
     * @param formula  現在の計算式
     * @param input    入力された文字
     * @param lastChar 最後の文字
     * @return 更新された計算式
     */
    private String handleSymbolLastChar(String formula, String input, char lastChar) {
        if (CommonUtil.isNumeric(input)) {
            return formula + input;
        }
        if (isMinus(input) && isMultiplicationOrDivision(lastChar)) {
            return formula + input;
        }
        if (isMinus(input) && isMinus(lastChar)) {
            return CommonUtil.deleteLastChar(formula) + PLUS;
        }
        if (isMinus(input) && isPlus(lastChar)) {
            return CommonUtil.deleteLastChar(formula) + MINUS;
        }
        return formula;
    }

    /**
     * 指定された式がピリオドを含んでいるかどうかを確認します。
     * <p>
     * 式がnullの場合、または式の最後の部分にピリオドが含まれていない場合は、falseを返します。
     * 式の分解には、{@link Calculation#splitFormula(String)} メソッドを使用します。
     * </p>
     *
     * @param formula 確認対象の式。nullの場合はfalseを返します。
     * @return 最後の部分にピリオドが含まれていればtrue、それ以外はfalse。
     */
    public boolean hasPeriod(String formula) {
        // 式を分解して塊に分ける。最後の塊を取り出して、ピリオドが含まれているか確認する
        if (formula == null) {
            return false;
        }
        String[] parts = Calculation.splitFormula(formula);
        int last = parts.length - 1;
        return isPeriod(parts[last]);
    }

    /**
     * 入力された文字列にピリオドが含まれているかを確認する
     *
     * @param input チェック対象文字列
     * @return ピリオドを含んでいる場合は `true`、それ以外の場合は `false` を返す
     */
    public boolean isPeriod(String input) {
        return input != null && input.contains(DOT);
    }

    /**
     * 入力系処理であることを判断する
     * 数字 or 四則演算子 or ピリオド
     */
    public boolean isInput(String input) {
        return CommonUtil.isNumeric(input) || DOT.equals(input) ||
                isMinus(input) || isPlus(input) || isMultiplicationOrDivision(input);
    }

    /**
     * 記号がマイナスであることを確認する
     */
    public boolean isMinus(char input) {
        return isMinus(String.valueOf(input));
    }

    public boolean isMinus(String input) {
        return MINUS.equals(input);
    }

    /**
     * 記号がプラスであることを確認する
     */
    public boolean isPlus(char input) {
        return PLUS.equals(String.valueOf(input));
    }

    public boolean isPlus(String input) {
        return PLUS.equals(input);
    }

    /**
     * 記号が×,÷であることを確認する
     */
    public boolean isMultiplicationOrDivision(char input) {
        return isMultiplicationOrDivision(String.valueOf(input));
    }

    public boolean isMultiplicationOrDivision(String input) {
        return MULTIPLY.equals(input) || DIVIDE.equals(input);
    }

    /**
     * 文字列の最後の文字を取り出す
     * 例外処理を追加する
     *
     * @param str 　対象の文字列
     * @return 最後の文字
     * @throws IllegalArgumentException 空文字を渡したとき
     */
    public char getLastChar(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("入力された文字列が不正です str=" + str);
        }
        return str.charAt(str.length() - 1);
    }
}
