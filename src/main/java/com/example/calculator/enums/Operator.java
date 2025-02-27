package com.example.calculator.enums;

import lombok.Getter;

@Getter
public enum Operator {
    PLUS("＋"),
    MINUS("-"),
    MULTIPLY("×"),
    DIVIDE("÷");

    // シンボルを取得するメソッド
    private final String symbol;

    // コンストラクタ
    Operator(String symbol) {
        this.symbol = symbol;
    }
}
