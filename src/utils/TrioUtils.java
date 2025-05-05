package utils;

import java.util.Scanner;

public class TrioUtils {
	private static final Scanner scanner = new Scanner(System.in);// final : 상수
	
	public static String nextLine(String msg) { //메서드
		System.out.println(msg);
		return scanner.nextLine();
	}
	
	public static int nextInt(String msg) {
	    while (true) {
	        try {
	            return Integer.parseInt(nextLine(msg));
	        } catch (NumberFormatException e) {
	            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
	        }
	    }
	}
	
	public static double nextDouble(String msg) {
	    while (true) {
	        try {
	            return Double.parseDouble(nextLine(msg));
	        } catch (NumberFormatException e) {
	            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
	        }
	    }
	}
	
	public static boolean nextConfirm(String msg) {
		String s = nextLine(msg);
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");		
	}
}