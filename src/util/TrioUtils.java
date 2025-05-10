package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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
		return Integer.parseInt(nextLine(msg));
	}

	public static double nextDouble(String msg) {
		return Double.parseDouble(nextLine(msg));
	}

	public static long nextLong(String msg) {
		return Long.parseLong(nextLine(msg));
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

	public static int getAppNo() {
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
	public static void writeLog(String appName, String fileName, Object content) {
		Path logPath = Path.of("storage", appName, fileName); // 기본 폴더 + 서브 폴더 + 파일 이름
		try {
			Files.createDirectories(logPath.getParent());
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(logPath.toFile()))) {
				oos.writeObject(content);
			}
		} catch (IOException e) {
			System.out.println("기록 저장 실패: " + e.getMessage());
		}
	}

	// 기록 보기
	public static void readLog(String appName, String fileName) {
		Path logPath = Path.of("storage", appName, fileName); // 기본 폴더 + 서브 폴더 + 파일 이름
		System.out.println("< 기록 보기 >");
		if (!Files.exists(logPath)) {
			System.out.println("기록이 없습니다.");
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(logPath.toFile()))) {
			Object obj = ois.readObject();
			if (obj instanceof List) {
				List<?> history = (List<?>) obj;
				for (Object item : history) {
					System.out.println(item);
				}
			} else if (obj != null) {
				System.out.println(obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("기록 읽기 실패: " + e.getMessage());
		}
	}
}
