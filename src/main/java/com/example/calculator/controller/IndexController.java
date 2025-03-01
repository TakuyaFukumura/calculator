package com.example.calculator.controller;

import com.example.calculator.constants.Constants;
import com.example.calculator.dto.CalculationHistory;
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
    public String process(@RequestParam("display") String formula,
                          @RequestParam("clickData") String input, HttpSession session, Model model) {

        CalculationHistory history = Optional.ofNullable((CalculationHistory) session.getAttribute("lastEquation"))
                .orElseGet(CalculationHistory::new);

        if (Constants.ALL_CLEAR.equals(input)) {
            history.setError(false); // 全削除の場合はエラー状態も解除
        }

        if (history.isError()) {
            return setModelAndReturn(model, formula); // エラー状態なら何もせず返す
        }

        if (inputService.isInput(input)) {
            return setModelAndReturn(model, inputService.buildFormula(formula, input)); // 式構築
        }

        if (calculationService.isCalculation(input)) { // 計算処理
            if (formula.equals(history.getOldResult())) {
                formula += history.getOldOperator(); // イコールが連続して押された場合は同じ計算を繰り返す
            }
            history.setOldOperator(calculationService.getLastArithmetic(formula)); // 歴管理
            formula = calculationService.calculationProcessing(formula);
            if (CommonUtil.countDigits(formula) > 12) {
                history.setError(true); // 桁数オーバーならエラーにする
            }
            history.setOldResult(formula);
            session.setAttribute("lastEquation", history);
            return setModelAndReturn(model, formula);
        }

        if (deleteService.isClear(input)) {
            return setModelAndReturn(model, deleteService.clear(formula)); // 1つ削除
        }

        if (deleteService.isAllClear(input)) {
            return setModelAndReturn(model, deleteService.allClear()); // 全削除
        }

        return setModelAndReturn(model, formula);
    }

    private String setModelAndReturn(Model model, String displayData) {
        model.addAttribute("displayData", displayData);
        return "index";
    }
}
