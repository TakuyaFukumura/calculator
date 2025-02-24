package com.example.calculator.service;

import com.example.calculator.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
     * @param formula   現在の数式
     * @param input ユーザーがクリックした削除操作（"AC" または "C"）
     * @return 更新後の数式。ACの場合は "0"、C の場合は最後の文字を削除したものを返す。
     * ただし、formula が 1 文字のみの場合、"0" を返す。
     */
    public String handleFormulaResetOrDelete(String formula, String input) {
        if (formula == null || input == null) {
            return formula;
        }
        return switch (input) {
            case "ＡＣ" -> "0";
            case "Ｃ" -> formula.length() > 1 ? CommonUtil.deleteLastChar(formula) : "0";
            default -> formula;
        };
    }
}
