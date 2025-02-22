package com.example.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OldCalculatedData {
    private String oldResult = "";
    private String oldOperator = "";
    private boolean errorFlag = true;

    public OldCalculatedData(String oldResult, String oldOperator) {
        this.oldResult = oldResult;
        this.oldOperator = oldOperator;
    }
}
