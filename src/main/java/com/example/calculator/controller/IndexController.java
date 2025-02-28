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

        CalculationHistory history = getCalculationHistory(session);

        if (Constants.ALL_CLEAR.equals(input)) {
            history.setError(false);
        }

        if (history.isError()) {
            return setModelAndReturn(model, formula);
        }

        if (inputService.isInput(input)) {
            return setModelAndReturn(model, inputService.buildFormula(formula, input));
        }

        if (calculationService.isCalculation(input)) {
            return handleCalculation(model, session, formula, history);
        }

        if (deleteService.isClear(input)) {
            return setModelAndReturn(model, deleteService.clear(formula));
        }

        if (deleteService.isAllClear(input)) {
            return setModelAndReturn(model, deleteService.allClear());
        }

        return setModelAndReturn(model, formula);
    }

    private CalculationHistory getCalculationHistory(HttpSession session) {
        return Optional.ofNullable((CalculationHistory) session.getAttribute("lastEquation"))
                .orElseGet(CalculationHistory::new);
    }

    private String handleCalculation(Model model, HttpSession session, String formula, CalculationHistory history) {
        if (formula.equals(history.getOldResult())) {
            formula += history.getOldOperator();
        }
        history.setOldOperator(calculationService.getLastArithmetic(formula));
        formula = calculationService.calculationProcessing(formula);
        if (CommonUtil.countDigits(formula) > 12) {
            history.setError(true);
        }
        history.setOldResult(formula);
        session.setAttribute("lastEquation", history);
        return setModelAndReturn(model, formula);
    }

    private String setModelAndReturn(Model model, String displayData) {
        model.addAttribute("displayData", displayData);
        return "index";
    }
}