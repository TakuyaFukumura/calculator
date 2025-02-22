package com.example.calculator.model;

import java.util.regex.Pattern;

public class Input {
	//入力に応じた処理を実装する
	public static String inputProcessing(String display, String clickData) {
		if(judgment(display)) { //disが初期値0の場合
			if(symbolIsPeriod(clickData)) {
				display += clickData;
			}else if(symbolIsMinus(clickData)||notSymbol(clickData)) { //数か-で上書きそれ以外は入らない
				display = clickData;
			}
		}else if(!notSymbol(getLastChar(Common.split(display)))) { //最後尾が記号の時
			if(notSymbol(clickData)) {
				display += clickData;
			}else if(symbolIsMinus(clickData)&&(symbolIsMuD(getLastChar(Common.split(display))))) {
				display += clickData;
			}else if(symbolIsMinus(clickData)&&(symbolIsMinus(getLastChar(Common.split(display))))) {
				display = Delete.deleteLastChar(display) + "＋"; //プラスマイナス反転させる
			}else if(symbolIsMinus(clickData)&&(symbolIsPlus(getLastChar(Common.split(display))))) {
				display = Delete.deleteLastChar(display) + "-"; //プラスマイナス反転させる
			}
		}else if(notSymbol(getLastChar(Common.split(display)))) { //最後尾が数字の時は、ほぼなんでも入る
			//最後の塊が数字グループでピリオドが含まれるならなにもしない
			if(checkPeriod(display)&&".".equals(clickData)) {
			}else {
				display += clickData;
			}
		}
		return display;
	}
	/**
	 * ピリオドの重複防止
	 * */
	public static boolean checkPeriod(String display) {
		//塊に分ける
		//最後の塊を取り出して、ピリオドが含まれているかチェックする
		boolean flag = false;
		if(display != null){
			String[] str = Calculation.splitFormula(display);
			if(IsPeriod(str[str.length-1])) flag = true;
		}
		return flag;
	}
	public static boolean IsPeriod(String c) {
		boolean flag = false;
		if(c != null) flag = Pattern.matches("^.*[\\.].*$", c);
		return flag;
	}

	/**
	 * 入力系処理であることを判断する
	 * 数字or四則演算子orピリオド
	 * */
	public static boolean checkInput(char c) {
		boolean flag = false;
		if(notSymbol(c) || symbolIsPeriod(c) || symbolIsMinus(c) || symbolIsPlus(c) || symbolIsMuD(c)) {
			flag = true;
		}
		return flag;
	}
	public static boolean checkInput(String c) {
		boolean flag = false;
		if(notSymbol(c) || symbolIsPeriod(c) || symbolIsMinus(c) || symbolIsPlus(c) || symbolIsMuD(c)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 記号ではないことを確認するメソッド
	 *（数字であることを確認する）
	 * */
	public static boolean notSymbol(char c) {
		boolean flag = false;
		flag = Pattern.matches("^[0-9０-９]*$", String.valueOf(c));
		return flag;
	}
	public static boolean notSymbol(String c) {
		boolean flag = false;
		flag = Pattern.matches("^[0-9０-９]*$", c);
		return flag;
	}

	/**
	 * 記号が．であることを確認する
	 * */
	public static boolean symbolIsPeriod(char c) {
		boolean flag = false;
		flag = Pattern.matches("^\\.$", String.valueOf(c));
		return flag;
	}
	public static boolean symbolIsPeriod(String c) {
		boolean flag = false;
		flag = Pattern.matches("^\\.$", c);
		return flag;
	}

	/**
	 * 記号がマイナスであることを確認する
	 * */
	public static boolean symbolIsMinus(char c) {
		boolean flag = false;
		flag = Pattern.matches("^-$", String.valueOf(c));
		return flag;
	}
	public static boolean symbolIsMinus(String c) {
		boolean flag = false;
		flag = Pattern.matches("^-$", c);
		return flag;
	}

	/**
	 * 記号がプラスであることを確認する
	 * */
	public static boolean symbolIsPlus(char c) {
		boolean flag = false;
		flag = Pattern.matches("^＋$", String.valueOf(c));
		return flag;
	}
	public static boolean symbolIsPlus(String c) {
		boolean flag = false;
		flag = Pattern.matches("^＋$", c);
		return flag;
	}

	/**
	 * 記号が×,÷であることを確認する
	 * */
	public static boolean symbolIsMuD(char c) { //引数Stringのほうがいいのでは？オーバーロードする？
		boolean flag = false;
		flag = Pattern.matches("^×$||^÷$", String.valueOf(c));
		return flag;
	}
	public static boolean symbolIsMuD(String c) {
		boolean flag = false;
		flag = Pattern.matches("^×$||^÷$", c);
		return flag;
	}

	/**
	 * char配列の最後の文字を取り出す
	 *
	 * */
	public static char getLastChar(char[] charList) {
		int size = charList.length;
		char tmp = charList[size-1];
		return tmp;
	}

	/**
	 * 文字列の長さが１&&ゼロならばtrueを返す
	 *
	 * */
	public static boolean judgment(String text) {
		boolean flag = false;
		if( text.length() == 1 && Pattern.matches("^0$||^０$", text)) {
			flag = true;
		}
		return flag;
	}

}
