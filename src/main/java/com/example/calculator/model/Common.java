package com.example.calculator.model;

import java.util.regex.Pattern;

public class Common {
	/**
	 * 文字列を分割してchar型の配列を返す
	 *
	 * */
	public static char[] split(String str) {
		char charList[] = new char[str.length()];

		// 変数strの長さ分回す
		for (int i = 0; i < str.length(); i++) {
		    // strの先頭から1文字ずつString型にして取り出す
		    char tmp = str.charAt(i);
		    // 配列に順番に格納する
		    charList[i] = tmp;
		}

		return charList;
	}
	/**
	 * 桁数を調べる
	 * */
	public static int checkNumberOfDigits(String display) {
		int count = 0;
		int i = 0;
		char charList[] = Common.split(display);
		//if("0".equals(String.valueOf(charList[i]))) i++;
		while(i < charList.length) {
			if(Pattern.matches("^[0-9]*$", String.valueOf(charList[i]))) count++;
			i++;
		}
		return count;
	}

	/**
	 * 桁数をチェックして、
	 * 入力継続（指定桁数以内であれば）できるならTrue
	 * */
	public static boolean checkNumber(String display) {
		boolean flag = true;
		int count = 0;
		//桁数チェック(分解して、左塊とって、数字ならば桁チェックして返す)
		String[] str = Calculation.splitFormula(display);//分解してまとまりに分ける処理
		if( judgmentNumG(str[str.length - 1]) ) {
			count = checkNumberOfDigits(str[str.length - 1]);
		}
		if( count > 11 ) flag = false;
		return flag;
	}

	public static boolean judgmentNumG(String c) {
		boolean flag = false;
		if(c != null) flag = Pattern.matches("^[0-9０-９]*$||^[0-9０-９]+\\.[0-9０-９]+$", c);
		return flag;
	}
}
