package com.example.calculator.dto;

public class OldCalculatedData {
	private String oldResult = "";
	private String oldOperator = "";
	private boolean errorFlag = true;

	public OldCalculatedData() {

	}
	public OldCalculatedData(String oldResult,String oldOperator) {
		this.setOldResult(oldResult);
		this.setOldOperator(oldOperator);
	}
	public OldCalculatedData(String oldResult,String oldOperator,boolean errorFlag) {
		this.setOldResult(oldResult);
		this.setOldOperator(oldOperator);
		this.setErrorFlag(errorFlag);
	}
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
	public boolean isErrorFlag() {
		return this.errorFlag;
	}
	public String getOldResult() {
		return oldResult;
	}
	public void setOldResult(String oldResult) {
		this.oldResult = oldResult;
	}
	public String getOldOperator() {
		return oldOperator;
	}
	public void setOldOperator(String oldOperator) {
		this.oldOperator = oldOperator;
	}
}
