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

    public String inputProcessing(String display, String clickData) {
        // 初期値の場合
        if (Constants.INITIAL_VALUE.equals(display)) {
            if (DOT.equals(clickData)) {
                return display + clickData;
            }
            //数字かマイナスで上書きする。それ以外は入らない
            if (MINUS.equals(clickData) || isNumeric(clickData)) {
                return clickData;
            }
        }

        char lastChar = getLastChar(display.toCharArray());
        if (!isNumeric(lastChar)) { //最後尾が記号の時
            if (isNumeric(clickData)) {
                return display + clickData;
            } else if (symbolIsMinus(clickData) && (symbolIsMuD(lastChar))) {
                return display + clickData;
            } else if (symbolIsMinus(clickData) && (symbolIsMinus(lastChar))) {
                return CommonUtil.deleteLastChar(display) + "＋"; //プラスマイナス反転させる
            } else if (symbolIsMinus(clickData) && (symbolIsPlus(lastChar))) {
                return CommonUtil.deleteLastChar(display) + "-"; //プラスマイナス反転させる
            }
        }

        if (isNumeric(lastChar)) { //最後尾が数字の時は、ほぼなんでも入る
            //最後の塊が数字グループでピリオドが含まれるならなにもしない
            if (checkPeriod(display) && ".".equals(clickData)) {
            } else {
                return display + clickData;
            }
        }
        return display;
    }

    /**
     * ピリオドの重複防止
     */
    public boolean checkPeriod(String display) {
        //塊に分ける
        //最後の塊を取り出して、ピリオドが含まれているかチェックする
        if (display == null) {
            return false;
        }
        String[] str = Calculation.splitFormula(display);
        return isPeriod(str[str.length - 1]);
    }

    public boolean isPeriod(String c) {
        return c != null && c.contains(".");
    }

    /**
     * 入力系処理であることを判断する
     * 数字or四則演算子orピリオド
     */
    public boolean checkInput(String c) {
        return isNumeric(c) || Pattern.matches("^\\.$", c) ||
                symbolIsMinus(c) || Pattern.matches("^＋$", c) || Pattern.matches("^×$|^÷$", c);
    }

    /**
     * 記号ではないことを確認するメソッド
     * （数字であることを確認する）
     */
    public boolean isNumeric(char c) {
        return Pattern.matches("^\\d*$", String.valueOf(c));
    }

    public boolean isNumeric(String c) {
        return Pattern.matches("^\\d*$", c);
    }

    /**
     * 記号がマイナスであることを確認する
     */
    public boolean symbolIsMinus(char c) {
        return Pattern.matches("^-$", String.valueOf(c));
    }

    public boolean symbolIsMinus(String c) {
        return Pattern.matches("^-$", c);
    }

    /**
     * 記号がプラスであることを確認する
     */
    public boolean symbolIsPlus(char c) {
        return Pattern.matches("^＋$", String.valueOf(c));
    }

    /**
     * 記号が×,÷であることを確認する
     */
    public boolean symbolIsMuD(char c) { //引数Stringのほうがいいのでは？オーバーロードする？
        return Pattern.matches("^×$|^÷$", String.valueOf(c));
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
