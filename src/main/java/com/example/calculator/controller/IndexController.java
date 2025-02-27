package com.example.calculator.controller;

import com.example.calculator.dto.OldCalculatedData;
import com.example.calculator.service.CalculationService;
import com.example.calculator.service.DeleteService;
import com.example.calculator.service.InputService;
import com.example.calculator.util.CommonUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

    private final CalculationService calculationService;
    private final InputService inputService;
    private final DeleteService deleteService;

    @Autowired
    public IndexController(CalculationService calculationService, InputService inputService, DeleteService deleteService) {
        this.calculationService = calculationService;
        this.inputService = inputService;
        this.deleteService = deleteService;
    }

    @GetMapping
    public String view(Model model) {
        model.addAttribute("displayData", "0");
        return "index";
    }

    @PostMapping
    public String process(
            @RequestParam("display") String formula,
            @RequestParam("clickData") String input,
            HttpSession session,
            Model model) {

        // セッションから情報取得、なければ新規作成
        OldCalculatedData oldCalculatedData =
                Optional.ofNullable((OldCalculatedData) session.getAttribute("lastEquation"))
                .orElseGet(OldCalculatedData::new);

        // E(エラー)表示後にACがクリックされた場合はフラグリセット
        if ("ＡＣ".equals(input)) {
            oldCalculatedData.setError(false);
        }

        // エラー発生状態では何も処理しない
        if (oldCalculatedData.isError()) {
            model.addAttribute("displayData", formula);
            return "index";
        }

        // クリックされたボタンによって処理を分岐
        if (inputService.isInput(input)) { // 入力系処理（数字 or 四則演算子 or ピリオド）
            if (CommonUtil.checkNumber(formula) || // 桁数チェック
                    calculationService.isOperatorSymbol(input)) { // 演算記号の場合
                model.addAttribute("displayData", inputService.buildFormula(formula, input));
                return "index";
            }
            // 桁数上限に達している場合はそのまま返す
            model.addAttribute("displayData", formula);
            return "index";
        }

        // 計算結果を出す
        if (calculationService.isCalculation(input)) {
            // イコールボタンが連続で押された場合は前回の計算を再度行う
            if (formula.equals(oldCalculatedData.getOldResult())) {
                formula += oldCalculatedData.getOldOperator();
            }
            // 計算式の最後の演算式を記録する
            oldCalculatedData.setOldOperator(calculationService.getLastArithmetic(formula));
            formula = calculationService.calculationProcessing(formula);
            if (CommonUtil.countDigits(formula) > 12) { // 上限を超えたらエラー
                oldCalculatedData.setError(true);
            }
            oldCalculatedData.setOldResult(formula);
            session.setAttribute("lastEquation", oldCalculatedData);
        }

        // 1つ削除
        if (deleteService.isClear(input)) {
            model.addAttribute("displayData", deleteService.clear(formula));
            return "index";
        }

        // 全削除
        if (deleteService.isAllClear(input)) {
            model.addAttribute("displayData", deleteService.allClear());
            return "index";
        }

        model.addAttribute("displayData", formula);
        return "index";
    }
}