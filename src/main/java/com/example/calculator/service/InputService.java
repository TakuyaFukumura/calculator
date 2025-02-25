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
    private static final String MINUS = "-";

    public String inputProcessing(String formula, String input) {
        // 初期値の場合
        if (Constants.INITIAL_VALUE.equals(formula)) {
            if (DOT.equals(input)) {
                return formula + input;
            }
            //数字かマイナスで上書きする。それ以外は入らない
            if (MINUS.equals(input) || isNumeric(input)) {
                return input;
            }
        }

        char lastChar = getLastChar(formula.toCharArray());
        if (!isNumeric(lastChar)) { //式の最後尾が記号の時
            // 入力が数字の場合
            if (isNumeric(input)) {
                return formula + input;
            }
            // 式の最後が×か÷で、入力がマイナスの場合
            if (isMinus(input) && (isMuD(lastChar))) { // symbolIsMinusとかを見直したい。isMinusとか
                return formula + input;
            }
            // 式の最後がマイナスで、入力がマイナスの場合
            if (isMinus(input) && (isMinus(lastChar))) {
                return CommonUtil.deleteLastChar(formula) + "＋"; //プラスマイナス反転させる
            }
            // 式の最後がプラスで、入力がマイナスの場合
            if (isMinus(input) && (isPlus(lastChar))) {
                return CommonUtil.deleteLastChar(formula) + "-"; //プラスマイナス反転させる
            }
        }

        if (isNumeric(lastChar)) { //最後尾が数字の時は、ほぼなんでも入る
            //最後の塊が数字グループでピリオドが含まれるならなにもしない
            if (checkPeriod(formula) && ".".equals(input)) {
            } else {
                return formula + input;
            }
        }
        return formula;
    }

    /**
     * ピリオドの重複防止
     */
    public boolean checkPeriod(String formula) {
        //塊に分ける
        //最後の塊を取り出して、ピリオドが含まれているかチェックする
        if (formula == null) {
            return false;
        }
        String[] str = Calculation.splitFormula(formula);
        return isPeriod(str[str.length - 1]);
    }

    public boolean isPeriod(String input) {
        return input != null && input.contains(".");
    }

    /**
     * 入力系処理であることを判断する
     * 数字 or 四則演算子 or ピリオド
     */
    public boolean checkInput(String input) {
        return isNumeric(input) || Pattern.matches("^\\.$", input) ||
                isMinus(input) || Pattern.matches("^＋$", input) || Pattern.matches("^×$|^÷$", input);
    }

    /**
     * 記号ではないことを確認するメソッド
     * （数字であることを確認する）
     */
    public boolean isNumeric(char input) {
        return Pattern.matches("^\\d*$", String.valueOf(input));
    }

    public boolean isNumeric(String input) {
        return Pattern.matches("^\\d*$", input);
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
        return Pattern.matches("^＋$", String.valueOf(input));
    }

    /**
     * 記号が×,÷であることを確認する
     */
    public boolean isMuD(char input) { //引数Stringのほうがいいのでは？オーバーロードする？
        return Pattern.matches("^×$|^÷$", String.valueOf(input));
    }

    /**
     * char配列の最後の文字を取り出す
     */
    public char getLastChar(char[] charList) {
        int size = charList.length;
        return charList[size - 1];
    }

    public String getLastString(String str) {
        return str.substring(str.length() - 1);
    }
}
