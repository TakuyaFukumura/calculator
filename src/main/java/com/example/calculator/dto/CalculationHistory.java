package com.example.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationHistory implements Serializable {
    private String oldResult = "";
    private String oldOperator = "";
    private boolean isError = false;

    public CalculationHistory(String oldResult, String oldOperator) {
        this.oldResult = oldResult;
        this.oldOperator = oldOperator;
    }
}
