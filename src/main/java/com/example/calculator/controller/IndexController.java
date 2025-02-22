package com.example.calculator.controller;

import com.example.calculator.dto.OldCalculatedData;
import com.example.calculator.model.Calculation;
import com.example.calculator.model.Delete;
import com.example.calculator.model.Input;
import com.example.calculator.service.IndexService;
import com.example.calculator.util.CommonUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service; // IndexServiceのインスタンスを使えるようにしている
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", service.getMessage());
        model.addAttribute("displayData", "0");
        return "index";
    }

    @PostMapping
    public String handlePost(
            @RequestParam("display") String display,
            @RequestParam("clickData") String clickData,
            HttpSession session,
            Model model) {

        OldCalculatedData oldCalculatedData = (OldCalculatedData) session.getAttribute("lastEquation");
        if (oldCalculatedData == null) {
            oldCalculatedData = new OldCalculatedData();
        }

        if ("ＡＣ".equals(clickData)) {
            oldCalculatedData.setErrorFlag(true);
        }

        if (!oldCalculatedData.isErrorFlag()) {
            // エラーセット時の処理
        } else if (Input.checkInput(clickData)) {
            if (CommonUtil.checkNumber(display) || Calculation.judgmentSymbolG(clickData)) {
                display = Input.inputProcessing(display, clickData);
            }
        } else if (Delete.checkDelete(clickData)) {
            display = Delete.deleteProcessing(display, clickData);
        } else if (Calculation.checkCalculation(clickData)) {
            if (display.equals(oldCalculatedData.getOldResult())) {
                display += oldCalculatedData.getOldOperator();
            }
            oldCalculatedData.setOldOperator(Calculation.getLastArithmetic(display));
            display = Calculation.calculationProcessing(display);
            if (CommonUtil.checkNumberOfDigits(display) > 12) {
                oldCalculatedData.setErrorFlag(false);
            }
            oldCalculatedData.setOldResult(display);
            session.setAttribute("lastEquation", oldCalculatedData);
        }

        model.addAttribute("displayData", display);
        return "index";
    }
}