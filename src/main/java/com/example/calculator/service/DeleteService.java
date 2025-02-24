package com.example.calculator.service;

import com.example.calculator.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteService {

    public boolean isAllClear(String input) {
        return "ＡＣ".equals(input);
    }

    public boolean isClear(String input) {
        return "Ｃ".equals(input);
    }

    public String allClear() {
        return "0";
    }

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
