package com.example.calculator.service;

import com.example.calculator.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteService {

    private static final String INITIAL_VALUE = "0";

    private static final String ALL_CLEAR = "ＡＣ";
    private static final String CLEAR = "Ｃ";

    /**
     * 入力された文字列が全削除を表すか確認します。
     *
     * @param input 入力された文字列
     * @return 全削除の場合はtrue、それ以外はfalse
     */
    public boolean isAllClear(String input) {
        return ALL_CLEAR.equals(input);
    }

    /**
     * 入力された文字列が1文字削除を表すか確認します。
     *
     * @param input 入力された文字列
     * @return 1文字削除の場合はtrue、それ以外はfalse
     */
    public boolean isClear(String input) {
        return CLEAR.equals(input);
    }

    /**
     * 全削除を実行した場合の初期値を返します。
     *
     * @return 初期値
     */
    public String allClear() {
        return INITIAL_VALUE;
    }

    /**
     * 引数として渡された式をクリアします。
     * 式が1文字の場合は全削除の結果を返し、1文字以上の場合は最後の文字を削除します。
     *
     * @param formula クリア対象の式
     * @return クリア後の式
     * @throws IllegalArgumentException 引数がnullまたは空の場合
     */
    public String clear(String formula) {
        if (formula == null || formula.isEmpty()) {
            throw new IllegalArgumentException("引数が不正です。formula=" + formula);
        }
        if (formula.length() == 1) {
            return allClear();
        }
        return CommonUtil.deleteLastChar(formula);
    }
}
