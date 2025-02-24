package com.example.calculator.controller;

import com.example.calculator.dto.OldCalculatedData;
import com.example.calculator.model.Calculation;
import com.example.calculator.model.Delete;
import com.example.calculator.model.Input;
import com.example.calculator.service.CalculationService;
import com.example.calculator.service.DeleteService;
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

    private final CalculationService calculationService;
    private final DeleteService deleteService;

    @Autowired
    public IndexController(CalculationService calculationService, DeleteService deleteService) {
        this.calculationService = calculationService;
        this.deleteService = deleteService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("displayData", "0");
        return "index";
    }

    @PostMapping
    public String handlePost(
            @RequestParam("display") String display,
            @RequestParam("clickData") String clickData,
            HttpSession session,
            Model model) {

        // セッション情報取得
        OldCalculatedData oldCalculatedData = (OldCalculatedData) session.getAttribute("lastEquation");

        // セッション情報がなければ新規作成
        if (oldCalculatedData == null) {
            oldCalculatedData = new OldCalculatedData();
        }

        // ACがクリックされた場合はフラグを立てる
        if ("ＡＣ".equals(clickData)) {
            oldCalculatedData.setErrorFlag(true);
        }

        // クリックされたボタンによって処理を分岐
        if (!oldCalculatedData.isErrorFlag()) {
            // エラーセット時の処理
        } else if (Input.checkInput(clickData)) { // 入力系処理
            if (CommonUtil.checkNumber(display) || calculationService.isOperatorSymbol(clickData)) {
                display = Input.inputProcessing(display, clickData);
            }

        } else if (deleteService.checkDelete(clickData)) { // 削除系処理
            display = deleteService.deleteProcessing(display, clickData);

        } else if (Calculation.checkCalculation(clickData)) { // 計算結果を出す
            if (display.equals(oldCalculatedData.getOldResult())) {
                display += oldCalculatedData.getOldOperator();
            }
            oldCalculatedData.setOldOperator(Calculation.getLastArithmetic(display));
            display = Calculation.calculationProcessing(display);
            if (CommonUtil.countDigits(display) > 12) {
                oldCalculatedData.setErrorFlag(false);
            }
            oldCalculatedData.setOldResult(display);
            session.setAttribute("lastEquation", oldCalculatedData);
        }

        model.addAttribute("displayData", display);
        return "index";
    }
}