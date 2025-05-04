package main;

import service.StoreService;
import service.AppService;
import service.SettingService;
import service.apps.TimeService;
import utils.TrioUtils;

public class PhoneUI {
	public static void main(String[] args) {
		new PhoneUI().run();
	}

	private void run() {
		StoreService storeService = StoreService.getInstance();
		AppService appService = AppService.getInstance();

		while (true) {
			MainScreen();
			int choice = TrioUtils.nextInt("실행할 기능 선택: ");
			menuChoice(choice, storeService, appService);
		}
	}
	
	private static final int frameWidth = 34;

	private void MainScreen() {
		printTime();
		String[] menuChoice = { "Open App Store", "Open App Drawer", "Settings", "Exit" };
		for (int i = 0; i < menuChoice.length; i++) {
			printMenuLine(i, menuChoice[i]);
		}
		System.out.println("+" + "-".repeat(frameWidth) + "+");
	}

	private void printTime() { // 시간 위에 나오기
		String now = TimeService.getCurrentTime();

		System.out.println("+" + "=".repeat(frameWidth) + "+");
		int textArea = (frameWidth - now.length()) / 2;
		String line = "|" + " ".repeat(Math.max(0, textArea)) + now
				+ " ".repeat(Math.max(0, frameWidth - now.length() - textArea)) + "|";
		System.out.println(line);
		System.out.println("+" + "=".repeat(frameWidth) + "+");
	}

	private void printMenuLine(int index, String item) {
		String prefix = (index + 1) + ". ";
		int textLength = prefix.length() + item.length();
		int textArea = frameWidth - textLength - 2;
		System.out.println("| " + prefix + item + " ".repeat(Math.max(0, textArea)) + " |");
	}

	private void menuChoice(int choice, StoreService storeService, AppService appService) {
		switch (choice) {
		case 1:
			storeService.storeMenu();
			break;
		case 2:
			appService.showInstalledApps();
			break;
		case 3:
//			System.out.println("Test");
			SettingService.settingMenu();
			break;
		case 4:
			System.out.println("Exit");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid choice. Please try again.");
		}
	}
}