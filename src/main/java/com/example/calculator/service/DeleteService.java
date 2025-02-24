package com.example.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class DeleteService {

    /**
     * 指定された文字列が削除系処理を表すかを判定する。
     * 判定基準は、文字列が "Ｃ" または "ＡＣ" であるかどうか。
     *
     * @param input 判定対象の文字列
     * @return c が "Ｃ" または "ＡＣ" の場合は {@code true}、それ以外の場合は {@code false}
     */
    public boolean isDeleteOperation(String input) {
        return "Ｃ".equals(input) || "ＡＣ".equals(input);
    }

    /**
     * 指定された削除処理を実行する。
     *
     * <p>クリックデータが "AC" の場合、数式を "0" にリセットする。</p>
     * <p>クリックデータが "C" の場合、数式の最後の文字を削除する。</p>
     *
     * @param formula    現在の数式
     * @param clickData  ユーザーがクリックした削除操作（"AC" または "C"）
     * @return 更新後の数式。ACの場合は "0"、C の場合は最後の文字を削除したものを返す。
     *         ただし、formula が 1 文字のみの場合、"0" を返す。
     */
    public String deleteProcessing(String formula, String clickData) {
        //ACならformulaをゼロにして返却
        //Ｃなら最後尾を削除して返却
        if (formula == null || clickData == null) {
            return formula;
        }

        if (Pattern.matches("^ＡＣ$", clickData)) {
            return "0";
        }

        if (Pattern.matches("^Ｃ$", clickData)) {
            return (formula.length() > 1) ? deleteLastChar(formula) : "0";
        }

        return formula;
    }

    /**
     * 文字列の最後尾1文字を削除する
     * 分割した文字を再合成
     */
    private String deleteLastChar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }
}
