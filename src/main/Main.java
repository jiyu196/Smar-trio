package main;

import static util.TrioUtils.*;
import java.util.ArrayList;
import java.util.List;
import app.App;
import app.Info;
import app.Setting;
import app.Store;



public class Main {
	public static void main(String[] args) {
		
		Info info = Info.getInstance();
		try {
//			if(!info.isRegistInfo()) {
//				System.out.println("등록된 사용자가 없습니다 가입하시겠습니까?");
//				info.register();
//			}
//			if(!info.isLoginInfo()) {
//				System.out.println("비밀번호를 입력해주세요");
//				info.logIn();
//			}
			Menu();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	public static List<App> installedApps = new ArrayList<App>();
	
	static{
		installedApps.add(new Store(generateAppNo()));
		installedApps.add(new Setting(generateAppNo()));
	}
	
	public static void Menu() {
		while (true) {
			appList();
			try {
				int no = nextInt("실행할 어플의 번호를 선택해 주세요. 0.종료");
				if (no == 0) {
					System.out.println("기기를 종료합니다");
					return;
				}
				runApp(no-1);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
	
	public static void appList() {
		for(App a : installedApps) {
			System.out.println("(" + (installedApps.indexOf(a)+1)+") " + a.getAppName());
		}
	}
	
	public static void runApp(int no) {
		List<App> tempList = new ArrayList<>(installedApps);
		for(App a: tempList) {
			if(installedApps.indexOf(a) == no) {
				a.run();
			}
		}
	}
	
	
}