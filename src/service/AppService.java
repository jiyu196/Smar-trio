package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;

public class AppService { //앱의 설치와 삭제 관리
	
	
	public AppService() {
		
	}
	
	List<App> installedApps = new ArrayList<>();
	
	public void showInstalledApps() {
        System.out.println("설치된 앱 목록:");
        for (App app : installedApps) {
            System.out.println(app.getAppNo() + ": " + app.getAppName());
        }
    }
	
	public void installApp() {
		
	}
	
	
	public void deteleApp() {
		
	}
	
	
	
}
