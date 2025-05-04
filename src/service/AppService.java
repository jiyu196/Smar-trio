package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;

public class AppService { //앱의 설치와 삭제 관리
	
	private static AppService appService = new AppService();
	public AppService() {};
	public static AppService getInstance() {
		return appService;
		
	}
	
	List<App> installedApps = new ArrayList<>();
	
	public void showInstalledApps() {
        System.out.println("설치된 앱 목록:");
        for (App app : installedApps) {
        	int index = installedApps.indexOf(app);
            System.out.println("실제 인덱스 : "+index+", 앱 이름:"+app.getAppNo() + ": 부여한 앱 번호" + app.getAppName());
        }
    }
	
	public void installApp() { //앱 설치
		
	}
	
	
	public void deteleApp() { // 앱 삭제
		
	}
	
	
	
}
