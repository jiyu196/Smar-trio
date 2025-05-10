package main;

import static util.TrioUtils.*;
import java.util.ArrayList;
import java.util.List;
import app.App;
import app.Setting;
import app.Store;



public class Main {
	public static void main(String[] args) {
		
		Menu();
		
	}
	
	
	public static List<App> installedApps = new ArrayList<App>();
	
	static{
		installedApps.add(new Store(getAppNo()));
		installedApps.add(new Setting(getAppNo()));
	}
	
	
	
	public static void Menu() {
		while (true) {
			appList();
			
			int no = nextInt("실행할 어플의 번호를 선택해 주세요. 0 기기 종료");
			if (no == 0) {
				System.out.println("기기를 종료합니다");
				return;
			}
			runApp(no-1);
		}
	}
	
	public static void appList() {
		for(App a : installedApps) {
			System.out.println("실제 인덱스"+installedApps.indexOf(a)+"어플 번호" + a.getAppNo()+"어플 이름 : " + a.getAppName());
		}
	}
	
	public static void runApp(int no) {
		for(App a: installedApps) {
			if(installedApps.indexOf(a) == no) {
				a.run();
			}
		}
	}
	
	
}