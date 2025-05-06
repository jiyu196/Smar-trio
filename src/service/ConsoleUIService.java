package service;

import java.util.List;
import service.apps.TimeService;

public class ConsoleUIService {

	private static final int FrameWidth = 30;

	public enum UIStyle {
		Full, Top, Bottom, Sides, None
	}

	public static void printFrame(String title, List<String> bodyLines) {
		printFrame(title, bodyLines, UIStyle.Full, true);
	}

	public static void printFrame(String title, List<String> bodyLines, UIStyle style) {
		printFrame(title, bodyLines, style, true);
	}

	public static void printFrame(String title, List<String> bodyLines, UIStyle style, boolean showTime) {
		if (showTime) {
			printTimeLine();
		}

		if (style == UIStyle.Full || style == UIStyle.Top) {
			printBorder();
		}

		if (style != UIStyle.None) {
			printLine(title);
			for (String line : bodyLines) {
				printMenuLine(line);
			}
		}

		if (style == UIStyle.Full || style == UIStyle.Bottom) {
			printBorder();
		}
	}

	public static void printMenu(String title, List<String> menuItems, boolean showWallpaper) {
		printTimeLine();
		if (showWallpaper) {
			printWallpaper();
		}
		printBorder();
		printLine(title);
		for (int i = 0; i < menuItems.size(); i++) {
			printIndexedLine(i + 1, menuItems.get(i));
		}
		printBorder();
	}

	public static void printWallpaper() {
		System.out.println("" 
				+ "|     *       .    *     .     |\n" 
				+ "|  .      *    .     *      .  |\n"
				+ "|   *   .    *     .    *      |\n" 
				+ "|     *           *      .     |\n"
				+ "|  .    *      .   *      .    |\n" 
				+ "|     *      .     *     .     |");
	}

	public static void printTimeLine() {
		String currentTime = TimeService.getCurrentTime();
		printBorder();
		printLine(currentTime);
	}

	private static void printBorder() {
		System.out.println("+" + "-".repeat(FrameWidth) + "+");
	}

	private static void printLine(String text) {
		int length = getDisplayLength(text);
		int padding = (FrameWidth - length) / 2;
		int right = Math.max(0, FrameWidth - length - padding);
		String margin = text.length() > FrameWidth ? text.substring(0, FrameWidth) : text;
		String line = "|" + " ".repeat(padding) + margin + " ".repeat(right) + "|";
		System.out.println(line);
	}

//	private static void printMenuLine(String text) {
//		int length = getDisplayLength(text);
//		String line = "| " + text + " ".repeat(FrameWidth - 2 - length) + " |";
//		System.out.println(line);
//	}
	
	private static void printMenuLine(String text) {
		int maxLength = FrameWidth - 2;
		int textLength = getDisplayLength(text);
//		String displayText = text;

		if (textLength <= maxLength) {
			System.out.println("| " + text + " ".repeat(maxLength - textLength) + " |");
//			displayText = trimLine(text, FrameWidth - 5) + "...";
//			textLength = getDisplayLength(displayText);
		} else {
			// Split text into lines of maxLength width
			StringBuilder line = new StringBuilder();
			int currentLength = 0;

			for (int i = 0; i < text.length(); i++) {
				char ch = text.charAt(i);
				int charLength = getDisplayLength(String.valueOf(ch));
				if (currentLength + charLength > maxLength) {
					System.out.println("| " + line + " ".repeat(maxLength - currentLength) + " |");
					line.setLength(0);
					currentLength = 0;
				}
				line.append(ch);
				currentLength += charLength;
			}

			if (line.length() > 0) {
				System.out.println("| " + line + " ".repeat(maxLength - currentLength) + " |");
			}
		}
//		String line = "| " + displayText + " ".repeat(Math.max(0, FrameWidth - 2 - textLength)) + " |";
//		System.out.println(line);
	}

	private static void printIndexedLine(int index, String text) {
		String prefix = index + ". ";
		int length = getDisplayLength(prefix + text);
		String line = "| " + prefix + text + " ".repeat(FrameWidth - 2 - length) + " |";
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
