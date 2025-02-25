package com.example.calculator.controller;

import com.example.calculator.dto.OldCalculatedData;
import com.example.calculator.model.Calculation;
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

        // セッション情報取得、なければ新規作成
        OldCalculatedData oldCalculatedData = (OldCalculatedData) session.getAttribute("lastEquation");
        if (oldCalculatedData == null) {
            oldCalculatedData = new OldCalculatedData();
        }

        // E(エラー)表示後にACがクリックされた場合はフラグリセット
        if ("ＡＣ".equals(clickData)) {
            oldCalculatedData.setError(false);
        }

        // エラー発生状態では何も処理しない
        if (oldCalculatedData.isError()) {
            model.addAttribute("displayData", display);
            return "index";
        }

        // クリックされたボタンによって処理を分岐
        if (inputService.isInput(clickData)) { // 入力系処理（数字 or 四則演算子 or ピリオド）
            if (CommonUtil.checkNumber(display) || // 桁数チェック
                    calculationService.isOperatorSymbol(clickData)) { // 演算記号の場合
                display = inputService.inputProcessing(display, clickData);
            }

        } else if (deleteService.isAllClear(clickData)) { // 全削除
            display = deleteService.allClear();
        } else if (deleteService.isClear(clickData)) { // 1つ削除
            display = deleteService.clear(display);

        } else if (Calculation.checkCalculation(clickData)) { // 計算結果を出す
            if (display.equals(oldCalculatedData.getOldResult())) {
                display += oldCalculatedData.getOldOperator();
            }
            oldCalculatedData.setOldOperator(Calculation.getLastArithmetic(display));
            display = Calculation.calculationProcessing(display);
            if (CommonUtil.countDigits(display) > 12) { // 上限を超えたらエラー
                oldCalculatedData.setError(true);
            }
            oldCalculatedData.setOldResult(display);
            session.setAttribute("lastEquation", oldCalculatedData);
        }

        model.addAttribute("displayData", display);
        return "index";
    }
}