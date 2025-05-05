package service;

import java.util.List;
import service.apps.TimeService;

public class ConsoleUIService {
	
	private static final int frameWidth = 30;

	public static void printFrame(String title, List<String> bodyLines) {
		printTimeLine();
		printBorder();
		printLine(title);
		for (String line : bodyLines) {
			printMenuLine(line);
		}
		printBorder();
	}

	public static void printMenu(String title, List<String> menuItems) {
		printTimeLine();
		printBorder();
		printLine(title);
		for (int i = 0; i < menuItems.size(); i++) {
			printIndexedLine(i + 1, menuItems.get(i));
		}
		printBorder();
	}

	private static void printTimeLine() {
		String currentTime = TimeService.getCurrentTime();
		printBorder();
		printLine(currentTime);
	}

	private static void printBorder() {
		System.out.println("+" + "-".repeat(frameWidth) + "+");
	}

	private static void printLine(String text) {
		int length = getDisplayLength(text);
		int padding = (frameWidth - length) / 2;
		String line = "|" + " ".repeat(padding) + text + " ".repeat(frameWidth - length - padding) + "|";
		System.out.println(line);
	}

	private static void printMenuLine(String text) {
		int length = getDisplayLength(text);
		String line = "| " + text + " ".repeat(frameWidth - 2 - length) + " |";
		System.out.println(line);
	}

	private static void printIndexedLine(int index, String text) {
		String prefix = index + ". ";
		int length = getDisplayLength(prefix + text);
		String line = "| " + prefix + text + " ".repeat(frameWidth - 2 - length) + " |";
		System.out.println(line);
	}

	public static int getDisplayLength(String text) {
		int length = 0;
		for (char ch : text.toCharArray()) {
			length += (ch >= '가' && ch <= '힣') || (Character.getType(ch) == Character.OTHER_LETTER) ? 2 : 1;
		}
		return length;
	}
}

