package service;

import static utils.TrioUtils.*;
import java.util.List;

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
	SettingService settingService = SettingService.getInstance();

	public void mainMenu() {
		while (true) {
			int no = nextInt("실행할 기능 선택: ");
			switch (no) {
			case 1: {
				System.out.println("설정 기능");
				settingService.settingMenu();
				break;
			}
			case 2: {
				System.out.println("앱스토어 오픈");
				storeService.storeMenu();
				break;
			}
			case 3: {
				System.out.println("앱 서랍 열기");
				appService.showInstalledApps();
				appService.runApp();
				break;
			}
			case 4: {
				System.out.println("기기를 종료합니다");
				return;
			}
			default:
				throw new IllegalArgumentException("잘못된 입력: " + no);
			}
		}
	}
}