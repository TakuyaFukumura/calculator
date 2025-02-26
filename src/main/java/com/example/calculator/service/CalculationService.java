package com.example.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class CalculationService {

    /**
     * 与えられた文字列が、有効な演算子記号（＋、－、×、÷）であるかを判定します。
     *
     * @param symbol 判定対象の文字列
     * @return 文字列が有効な演算子記号の場合は {@code true}、それ以外の場合は {@code false}
     */
    public boolean isOperatorSymbol(String symbol) {
        return symbol != null && Pattern.matches("^＋$|^-$|^×$|^÷$", symbol);
    }
}
