package com.example.calculator.service;

import com.example.calculator.constants.Constants;
import com.example.calculator.model.Calculation;
import com.example.calculator.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class InputService {

    private static final String DOT = ".";
    private static final String PLUS = "＋";
    private static final String MINUS = "-";

    public String buildFormula(String formula, String input) {
        // 式が初期値ゼロの場合
        if (Constants.INITIAL_VALUE.equals(formula)) {
            if (DOT.equals(input)) {
                return formula + input;
            }
            // 数字かマイナスで上書きする。それ以外は入らない
            if (MINUS.equals(input) || CommonUtil.isNumeric(input)) {
                return input;
            }
        }

        char lastChar = getLastChar(formula.toCharArray());
        String lastWord = CommonUtil.getLastString(formula);

        if (CommonUtil.isNumeric(lastChar)) { //最後尾が数字の時は、ほぼなんでも入る
            // 最後の塊が数字グループでピリオドが含まれるならなにもしない
            if (hasPeriodAtEnd(formula) && ".".equals(input)) {
                return formula;
            } else {
                return formula + input;
            }
        } else { // 式の最後尾が記号の時
            // 入力が数字の場合
            if (CommonUtil.isNumeric(input)) {
                return formula + input;
            }
            // 式の最後が×か÷で、入力がマイナスの場合
            if (isMinus(input) && (isMultiplicationOrDivision(lastChar))) {
                return formula + input;
            }
            // 式の最後がマイナスで、入力がマイナスの場合
            if (isMinus(input) && (isMinus(lastChar))) {
                return CommonUtil.deleteLastChar(formula) + PLUS; // プラスへ反転させる
            }
            // 式の最後がプラスで、入力がマイナスの場合
            if (isMinus(input) && (isPlus(lastChar))) {
                return CommonUtil.deleteLastChar(formula) + MINUS; // マイナスへ反転させる
            }
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
    public boolean hasPeriodAtEnd(String formula) {
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
        return input.equals("×") || input.equals("÷");
    }

    /**
     * char配列の最後の文字を取り出す
     */
    public char getLastChar(char[] chars) {
        return chars[chars.length - 1];
    }
}
