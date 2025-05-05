package service;

import static utils.TrioUtils.*;

import service.apps.TimeService;

public class MainMenuService { // 이 클래스를 호출하는 것으로 메인에 메뉴를 출력, 메뉴에 직접 기능 호출
	// 리스트를 사용해 설치된 어플들의 목록을 호출할 예정, 임시적으로 어플실이 실행 메소드 만으로 실행되는지 알아볼 예정

	private static MainMenuService menuService = new MainMenuService();

	private MainMenuService() {
	}

	public static MainMenuService getInstance() {
		return menuService;
	}

	StoreService storeService = StoreService.getInstance();
	AppService appService = AppService.getInstance();

	public void mainMenu() {
		while (true) {
			displayMainScreen(); // UI 나오기, 주석 하며는 해제.
			int no = nextInt("실행할 기능 선택: ");
			switch (no) {
			case 1: {
//				System.out.println("설정 기능");
				break;
			}
			case 2: {
//				System.out.println("앱스토어 오픈");
				storeService.storeMenu();
				break;
			}
			case 3: {
//				System.out.println("앱 서랍 열기");
				appService.showInstalledApps();
				break;
			}
			case 4: {
//				System.out.println("기기를 종료합니다");
				return;
			}
			default:
				throw new IllegalArgumentException("잘못된 입력: " + no);
			}
		}
	}

	private static final int frameWidth = 30; //넓이 조정

	private void displayMainScreen() {
		printTime();	
		
		System.out.println("+" + "-".repeat(frameWidth) + "+");
		printPageName("매인 매뉴"); //나중에 각 패이지 마다 이름 지정후, 이렇게 출력.
		System.out.println("+" + "-".repeat(frameWidth) + "+");
		
		String[] menuChoice = { "설정", "앱스토어 오픈", "앱 서랍 열기", "기기를 종료합니다" };
		for (int i = 0; i < menuChoice.length; i++) {
			printMenuLine(i, menuChoice[i]);
			System.out.println("|" + " ".repeat(frameWidth) + "|"); // 빈칸 출력
		}
		System.out.println("+" + "-".repeat(frameWidth) + "+");
	}

	private void printTime() {
		String currentTime = TimeService.getCurrentTime();

		System.out.println("+" + "-".repeat(frameWidth) + "+");
		int textArea = (frameWidth - currentTime.length()) / 2;
		String line = "|" + " ".repeat(Math.max(0, textArea)) + currentTime //시간 표시
				+ " ".repeat(Math.max(0, frameWidth - currentTime.length() - textArea)) + "|";
		System.out.println(line);
//		System.out.println("+" + "=".repeat(frameWidth) + "+");
	}
	
	private void printPageName(String text) {
		int textLength = getCharacterLength(text);
		int textArea = (frameWidth - textLength) / 2;
		String line = "|" + " ".repeat(Math.max(0, textArea)) + text
                + " ".repeat(Math.max(0, frameWidth - textLength - textArea)) + "|";
		System.out.println(line);
	}

	private void printMenuLine(int index, String item) {
		String prefix = (index + 1) + ". ";
		int textLength = prefix.length() + getCharacterLength(item); // 한글 넓이 처리
		int textArea = frameWidth - textLength - 2;
		String padding = " ".repeat(Math.max(0, textArea));
		System.out.println("| " + prefix + item + padding + " |");
	}

	private int getCharacterLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i); // V 한글인지 확인
			if ((ch >= '가' && ch <= '힣') || (Character.getType(ch) == Character.OTHER_LETTER)) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length;
	}
}