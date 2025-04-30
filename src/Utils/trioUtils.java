package Utils;

import java.util.Scanner;

public class trioUtils {
	private static final Scanner scanner = new Scanner(System.in);// final : 상수
	
	public static String nextLine(String msg) { //메서드
		System.out.println(msg);
		return scanner.nextLine();
	}
	
	public static int nextInt(String msg) {
		return Integer.parseInt(nextLine(msg));
	}
	
	public static boolean nextConfirm(String msg) {
		String s = nextLine(msg);
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
		
	}

}
