package service;

import java.util.ArrayList;
import java.util.List;
import static utils.TrioUtils.*;

import domain.App;

public class AppService { //앱의 설치와 삭제 관리
	
	private static AppService appService = new AppService();
	public List<App> installedApps = new ArrayList<>();
	
	private AppService() {};
	public static AppService getInstance() {
		return appService;
	}
	
	public void showInstalledApps() {
        System.out.println("설치된 앱 목록:");
        if(installedApps.isEmpty()) {
        	System.out.println("설치된 어플이 없습니다");
        }else {
        	for (App app : installedApps) {
            	int index = installedApps.indexOf(app);
                System.out.println("어플 번호 "+ (index+1) +", 앱 이름:" + app.getAppName());
            }
        }
        
    }
	
	public void runApp() {
		int no = nextInt("실행할 어플의 번호를 입력해주세요, 0 되돌아가기");
		if(no == 0) {
			return;
		}
		for(App app : installedApps) {
			int index = installedApps.indexOf(app);
			if(index == (no-1)) {
				app.run();
				return;
			}
		}
		System.out.println("실행 할 수 있는 어플이 아닙니다.");
		
	}
	
	
	public void installApp(App app) {//앱 설치, 중복케스 할것.
		
		if(app == null) {
			System.out.println("설치를 위해서는 어플을 선택해 주세요");
			return;
		}
		
		for(App a : installedApps) {
			if( a.equals(app)) {
				System.out.println("이미 설치된 어플입니다");
				return;
			}
		}
		installedApps.add(app);
		System.out.println(app.getAppName()+"이 설치되었습니다.");
	}
	

	
	public void deleteApp() {// 앱 삭제
		int no = nextInt("삭제할 어플의 번호를 선택해주세요");
		for (App app : installedApps) {
			int index = installedApps.indexOf(app);
			if(index == (no-1)) {
				System.out.println(app.getAppName()+"을 삭제하였습니다");
				installedApps.remove(app);
				return;
			}
		}
		
		System.out.println("선택한 어플이 없습니다");
	}
	
	
	
}
