package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TrioUtils {
	private static final Scanner scanner = new Scanner(System.in);

	// 출/입력
	public static String nextLine(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}

	public static int nextInt(String msg) {
		while (true) {
			try {
				String input = nextLine(msg);
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("유효한 숫자를 입력해주세요.");
			}
		}
	}

	public static double nextDouble(String msg) {
		while (true) {
			try {
				String input = nextLine(msg);
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("유효한 숫자를 입력해주세요.");
			}
		}
	}

	public static long nextLong(String msg) {
		while (true) {
			try {
				String input = nextLine(msg);
				return Long.parseLong(input);
			} catch (NumberFormatException e) {
				System.out.println("유효한 숫자를 입력해주세요.");
			}
		}
	}

	public static boolean nextConfirm(String msg) {
//		String s = nextLine(msg + " [y/n]");
//		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
		while (true) {
			String s = nextLine(msg + " [y/n]");
			if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes"))
				return true;
			if (s.equalsIgnoreCase("n") || s.equalsIgnoreCase("no"))
				return false;
		}
	}

	private static int count = 1;

	public static int generateAppNo() {
		return count++;
	}

	// 날짜, 시간
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCurrentDateTime() {
		return DATE_TIME_FORMAT.format(new Date());
	}

	public static String getCurrentDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	// 각 어플 기록 기능

	// 기록 하기
	public static void save(String dirName, String subDirName, String fileName, Object content) {
		try {
			File dir = new File(dirName, subDirName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, fileName);
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(content);
			}
		} catch (IOException e) {
			System.out.println("파일 저장 실패: " + e.getMessage());
		}
	}
}
