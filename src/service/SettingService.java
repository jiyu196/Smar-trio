package service;

public class SettingService {
	
	
	private static SettingService settingService = new SettingService();
	private SettingService() {};
	public static SettingService getInstance() {
		return settingService;
	}
	
	public static void settingMenu() {
		System.out.println("세팅 메뉴 호출 테스트");
	}
}
