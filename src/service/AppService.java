package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;

public class AppService { //앱의 설치와 삭제 관리
	
	private static AppService appService = new AppService();
	private AppService() {};
	public static AppService getInstance() {
		return appService;
	}
	
	public List<App> installedApps = new ArrayList<>();

	
	public void showInstalledApps() {
        System.out.println("설치된 앱 목록:");
        if(installedApps.isEmpty()) {
        	System.out.println("설치된 어플이 없습니다");
        }else {
        	for (App app : installedApps) {
            	int index = installedApps.indexOf(app);
                System.out.println("실제 인덱스 : "+index+", 앱 이름:"+app.getAppNo() + ": 고유 앱 번호" + app.getAppName());
            }
        }
        
    }
	
	public void installApp(App app) {//앱 설치, 중복케스 할것.
		for(App a : installedApps) {
			if( a.equals(app)) {
				System.out.println("이미 설치된 어플입니다");
				return;
			}
		}
		installedApps.add(app);
		System.out.println(app.getAppName()+"이 설치되었습니다.");
	}
	
	
	public void deteleApp() { // 앱 삭제
		
	}
	
	
	
}
