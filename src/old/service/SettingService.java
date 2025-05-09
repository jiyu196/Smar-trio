package old.service;

import static old.utils.TrioUtils.*;

public class SettingService {

	AppService appService = AppService.getInstance();
	DeviceInfoService deviceInfoService = DeviceInfoService.getInstance();

	private static SettingService settingService = new SettingService();

	private SettingService() {
	};

	public static SettingService getInstance() {
		return settingService;
	}

	public void settingMenu() {
		System.out.println("세팅 메뉴 호출 테스트");

		while (true) {
			int no = nextInt("사용할 기능을 번호로 입력해주세요 > 1.유저 설정 2.어플 삭제 0. 이전 메뉴");
			switch (no) {
			case 1: {
				System.out.println("유저설정 기능 호출");
				return;
			}
			case 2: {
				System.out.println("어플삭제 기능");
				appService.showInstalledApps();
				appService.deleteApp();
				return;
			}
			case 0: {
				System.out.println("메인메뉴로 되돌아갑니다");
				return;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}
		}

	}
}
