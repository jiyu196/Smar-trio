package util;

import java.util.Scanner;

public class TrioUtils {
	private static final Scanner scanner = new Scanner(System.in);

	public static String nextLine(String msg) { // 값 입력시 사용
		System.out.println(msg);
		return scanner.nextLine();
	}

	public static int nextInt(String msg) {
		return Integer.parseInt(nextLine(msg));
	}

	public static long nextLong(String msg) {
		return Long.parseLong(nextLine(msg));
	}

	public static boolean nextConfirm(String msg) { // y/n 선택 여부 질문
		String s = nextLine(msg + " [y/n]");
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");// 문자열이 같은경우 true 리턴, 대소문 구별 안함
	}

	public static int getAppNo() { // appNo 부여
		int count = 1;
		return count++;
	}
}