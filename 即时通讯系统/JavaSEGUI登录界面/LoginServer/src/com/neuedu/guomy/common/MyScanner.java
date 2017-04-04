package com.neuedu.guomy.common;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScanner {
	public String inputString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	public String inputSex() {
		String sex = "";
		do {
			sex = inputString();
			if (sex.compareTo("男") != 0 && sex.compareTo("女") != 0)
				System.out.print("请输入“男”或“女”：");
		} while (sex.compareTo("男") != 0 
				&& sex.compareTo("女") != 0);
		return sex;
	}

	public float inputFloat() {
		float f = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			f = scanner.nextFloat();
			return f;
		} catch (InputMismatchException ex) {
			System.out.print("输入格式错误！请输入浮点数：");
			return inputFloat();
		}
	}
	public float inputFloat(float min, float max) {
		float f = 0;
		do {
			f = inputFloat();
			if (f < min || f > max)
				System.out.printf("请输入%d-%d之间的整数：", min, max);
		} while (f < min || f > max);
		return f;
	}
	
	public int inputInteger() {
		int i = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			i = scanner.nextInt();
			return i;
		} catch (InputMismatchException ex) {
			System.out.print("输入格式错误！请输入整数：");
			return inputInteger();
		}
	}
	public int inputInteger(int min, int max) {
		int i = 0;
		do {
			i = inputInteger();
			if (i < min || i > max)
				System.out.printf("请输入%d-%d之间的整数：", min, max);
		} while (i < min || i > max);
		return i;
	}
	
	public char inputChar() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().charAt(0);
	}
	
	public Date inputDate() {
		Date date = null;
		Scanner scanner = new Scanner(System.in);
		try {
			date = Date.valueOf(scanner.nextLine());
			return date;
		} catch (IllegalArgumentException ex) {
			System.out.print("输入格式错误！请输入日期（YYYY-MM-DD）：");
			return inputDate();
		}
	}
}









