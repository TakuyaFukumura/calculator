package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculation {
	/**
	 * 数値文字変換
	 * String→BigDecimal
	 * 元String→double
	 * ピリオド＆マイナス対応可
	 * */
	public static BigDecimal conversions(String str) {
		BigDecimal num = new BigDecimal(str);
		return num;
	}
//	public static double conversions(String str) {
//		double num = Double.valueOf(str);
//		return num;
//	}
	/**
	 * 計算式の最後の演算式を返す。例：「÷5」
	 * 式が存在しない時は空文字を返す
	 * */
	public static String getLastArithmetic(String display) {
		String lastArithmetic = "";
		String[] str = splitFormula(display);
		if(str.length > 2) {
			lastArithmetic = str[str.length - 2] + str[str.length - 1];
		}
		return lastArithmetic;
	}
	/**
	 * 実際の計算処理
	 * */
	public static String calculationProcessing(String display) {
		if(!Input.notSymbol(Input.getLastChar(Common.split(display)))) return display;//最後尾が記号の場合はそのまま返す処理
		String[] str = splitFormula(display);//分解してまとまりに分ける処理
		display = calculate(str);//まとまりを３つづつ計算していく処理
		return display;
	}
	/**
	 * まとまりを３つづつ計算していく処理
	 * */
	public static String calculate(String[] str) {
		//宣言部
		String result = "";
		int i = 0;
		boolean flag = false;
		//先頭が記号ならばマイナスの数値にする処理が必要
		if(judgmentSymbolG(str[i])) {
			str[i+1] = str[i] + str[i+1]; //ここで２番目の配列操作してしまえばいい
			i++;
		}
		//ループ開始 while
		while(i < str.length) {
			if(i == str.length) break;
			//数値文字変換
			BigDecimal num1 = new BigDecimal("0");

			num1 = conversions(str[i]);

			if(i == str.length - 1) {
				result = str[i];
				break;
				//return result;
			}else {
				i++;
			}
			int type = judgmentOperator(str[i]); //演算子判定
			i++;
			BigDecimal num2 = conversions(str[i]); //数値文字変換
			str[i] = "" + calculateSwitch(num1, type, num2); //演算実行して
			if(i == str.length) result = str[i];
		}//ループ終了
		return result;
	}

	/**
	 * 演算スイッチ処理
	 * */
	public static double calculateSwitch(double num1, int type, double num2) {
		double result = 0.0;
		switch (type){
		case 1:
			result = addition(num1,num2);
			break;
		case 2:
			result = subtraction(num1,num2);
		    break;
		case 3:
			result = multiplication(num1,num2);
		    break;
		case 4:
			result = division(num1,num2);
		    break;
		case 5:
			result = multiplicationAndMinus(num1,num2);
		    break;
		case 6:
			result = divisionAndMinus(num1,num2);
			break;
		}
		return result;
	}
	public static BigDecimal calculateSwitch(BigDecimal num1, int type, BigDecimal num2) {
		BigDecimal result = new BigDecimal("0");
		switch (type){
		case 1:
			result = addition(num1,num2);
			break;
		case 2:
			result = subtraction(num1,num2);
		    break;
		case 3:
			result = multiplication(num1,num2);
		    break;
		case 4:
			result = division(num1,num2);
		    break;
		case 5:
			result = multiplicationAndMinus(num1,num2);
		    break;
		case 6:
			result = divisionAndMinus(num1,num2);
			break;
		}
		return result;
	}
	/**
	 * 足し算
	 * */
	public static double addition(double num1, double num2) {
		double result = num1 + num2;
		return result;
	}
	public static BigDecimal addition(BigDecimal num1, BigDecimal num2) {
		BigDecimal result = num1.add(num2);
		return result;
	}
	/**
	 * 引き算
	 * */
	public static double subtraction(double num1, double num2) {
		double result = num1 - num2;
		return result;
	}
	public static BigDecimal subtraction(BigDecimal num1, BigDecimal num2) {
		BigDecimal result = num1.subtract(num2);
		return result;
	}
	/**
	 * 掛け算
	 * */
	public static double multiplication(double num1, double num2) {
		double result = num1 * num2;
		return result;
	}
	public static BigDecimal multiplication(BigDecimal num1, BigDecimal num2) {
		BigDecimal result = num1.multiply(num2);
		return result;
	}
	/**
	 * 割り算
	 * */
	public static double division(double num1, double num2) {
		double result = num1 / num2;
		return result;
	}
	public static BigDecimal division(BigDecimal num1, BigDecimal num2) {
		BigDecimal result = new BigDecimal("0");
		try{
			if(num2.compareTo(result) == 0) {
				result = new BigDecimal("1234567891023");
			}else {
				result = num1.divide(num2);
			}
		}catch (Exception e){
			//最も近い数に丸め込んだ
			result = num1.divide(num2,11,RoundingMode.DOWN);//HALF_DOWN);//result = num1.divide(num2).setScale(2, RoundingMode.DOWN);
			//上の桁数取得して再リザルト//マイナス対応のため絶対値使用
			result = num1.divide(num2,12 - figureLengthUpPoint((result.abs()).toString()).length(),RoundingMode.DOWN);
		}
		return result;
	}
	/**
	 * 小数点除いたの上側の数字を取得する
	 * */
	public static String figureLengthUpPoint(String str) {
		String regex = "(^[0-9]+)";
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(str);
		if (m.find()){
		  str = "" + m.group(1);
		}
		return str;
	}


	/**
	 * 掛け算&マイナス
	 * */
	public static double multiplicationAndMinus(double num1, double num2) {
		num2 = num2 - num2 -num2;
		double result = num1 * num2;
		return result;
	}
	public static BigDecimal multiplicationAndMinus(BigDecimal num1, BigDecimal num2) {
		BigDecimal tmp = new BigDecimal("0");
		num2 = tmp.subtract(num2);
		BigDecimal result = num1.multiply(num2);
		return result;
	}
	/**
	 * 割り算&マイナス
	 * */
	public static double divisionAndMinus(double num1, double num2) {
		num2 = num2 - num2 -num2;
		double result = num1 / num2;
		return result;
	}
	public static BigDecimal divisionAndMinus(BigDecimal num1, BigDecimal num2) {
		BigDecimal tmp = new BigDecimal("0");
		num2 = tmp.subtract(num2);
		BigDecimal result = new BigDecimal("0");
		//BigDecimal result = num1.divide(num2,11,RoundingMode.HALF_UP);
		try{
			if(num2.compareTo(result) == 0) {
				result = new BigDecimal("1234567891023");
			}else {
				result = num1.divide(num2);
			}
		}catch (Exception e){
			//最も近い数に丸め込んだ
			result = num1.divide(num2,11,RoundingMode.DOWN);//RoundingMode.HALF_DOWN);
			result = num1.divide(num2,12 - figureLengthUpPoint((result.abs()).toString()).length(),RoundingMode.DOWN);
		}
		return result;
	}


	/**
	 * 計算式を分解して纏りに分ける処理
	 * */
	public static String[] splitFormula(String display) {
		char tmp[] = Common.split(display); //分解
		String[] str = new String[tmp.length];
		//どっちのグループなのか比較判定して格納していく
		int j = 0;
		str[j] = String.valueOf(tmp[0]);
		for(int i = 1; i < tmp.length; i++) {
			if((judgmentNumG(tmp[i])&&judgmentNumG(tmp[i-1]))||(judgmentSymbolG(tmp[i])&&judgmentSymbolG(tmp[i-1]))) { //同じグループだったら
				str[j] += String.valueOf(tmp[i]);
			}else {
				j++;
				str[j] = String.valueOf(tmp[i]);
			}
		}
		String[] str2 = new String[j + 1]; //nullの無い配列にしたい
		for(int i = 0; i < j + 1; i++) {
			str2[i] = str[i];
		}
		return str2;
	}

	/**
	 * 演算子判定処理
	 * -、＋、×、÷、×-、÷-
	 * １、２、３、４、５　、６
	 * */
	public static int judgmentOperator(String str) {
		int num = 0;
		if("＋".equals(str)) {
			num = 1;
		}else if("-".equals(str)) {
			num = 2;
		}else if("×".equals(str)||"×＋".equals(str)) {
			num = 3;
		}else if("÷".equals(str)||"÷＋".equals(str)) {
			num = 4;
		}else if("×-".equals(str)) {
			num = 5;
		}else if("÷-".equals(str)) {
			num = 6;
		}
		return num;
	}


	/**
	 * 計算処理であることを判断する「＝」
	 * */
	public static boolean checkCalculation(String c) {
		boolean flag = false;
		if(c != null) if(symbolIsEqual(c)) flag = true;
		return flag;
	}

	/**
	 * 記号が＝であることを確認する
	 * */
	public static boolean symbolIsEqual(String c) {
		boolean flag = false;
		if(c != null) flag = Pattern.matches("^＝$", c);
		return flag;
	}

	/**
	 * 数字グループであることを確認する
	 * */
	public static boolean judgmentNumG(String c) {
		boolean flag = false;
		if(c != null) flag = Pattern.matches("^[0-9０-９]$||^\\.$", c);
		return flag;
	}
	public static boolean judgmentNumG(char c) {
		boolean flag = false;
		flag = Pattern.matches("^[0-9０-９]$||^\\.$", String.valueOf(c));
		return flag;
	}
	/**
	 * 記号グループであることを確認する
	 * */
	public static boolean judgmentSymbolG(String c) {
		boolean flag = false;
		if(c != null) flag = Pattern.matches("^＋$||^-$||^×$||^÷$", c);
		return flag;
	}
	public static boolean judgmentSymbolG(char c) {
		boolean flag = false;
		flag = Pattern.matches("^＋$||^-$||^×$||^÷$", String.valueOf(c));
		return flag;
	}
}
