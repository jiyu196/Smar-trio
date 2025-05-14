package domain;

import util.TrioUtils;

public class PhoneUI {
	
	private static final int FRAME_WIDTH = 25;
	
	public static void printWallpaper() {
		
		printBorder();
		System.out.println("" 
				+ "|   *      .   *     .    |\n" 
				+ "|  .     *    .   *    .  |\n"
				+ "|   *   . *       .   *   |\n" 
				+ "|     *      *      .     |\n"
				+ "|  .    *       .   *     |\n" 
				+ "|     *   .   *     .     |");
		printBorder();
		
	}

	public static void printTimeLine() {
		
		printBorder();
		String currentTime = TrioUtils.getCurrentDateTime();
		printLine(currentTime);
		
	}
	
	public static void printBorder() {
		
		System.out.println("+" + "-".repeat(FRAME_WIDTH) + "+");
		
	}
	
	public static void printLine(String text) {
		
		int length = getDisplayLength(text);
		int frame = (FRAME_WIDTH - length) / 2;
		int wall = Math.max(0, FRAME_WIDTH - length - frame);	
		
		String margin = text.length() > FRAME_WIDTH ? text.substring(0, FRAME_WIDTH) : text;
		String line = "|" + " ".repeat(frame) + margin + " ".repeat(wall) + "|";
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
