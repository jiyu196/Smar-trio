package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;

public class AppService { //설치된 앱의 관리
	
	private List<App> installedApps = new ArrayList<>();
	
	public void showInstalledApps() {
        System.out.println("설치된 앱 목록:");
        for (App app : installedApps) {
            System.out.println(app.getAppNo() + ": " + app.getAppName());
        }
    }
	
}
