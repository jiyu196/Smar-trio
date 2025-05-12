package main;

import static util.TrioUtils.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import app.App;
import app.Info;
import app.Setting;
import app.Store;
import util.TrioUtils;

public class Main {

	{

	}

	public static void main(String[] args) {
		Menu();

	}

	public static List<App> installedApps = new ArrayList<App>();

	static {
		
		installedApps.add(new Store(generateAppNo()));
		installedApps.add(new Setting(generateAppNo()));

	}

	public static void Menu() {

		Info info = Info.getInstance();

		while (true) {

			try {

//				if (!info.isRegistInfo()) {
//					System.out.println("등록된 사용자가 없습니다 가입하시겠습니까?");
//					info.register();
//				}
//
//				if (!info.isLoginInfo()) {
//					info.logIn();
//				} else {
					appList();
					int no = nextInt("실행할 어플의 번호를 선택해 주세요. 0.종료");
					if (no == 0) {
						System.out.println("기기를 종료합니다");
						return;
					}
					
					runApp(no - 1);
//				}
			} catch (NumberFormatException e) {
				System.out.println("실행할 메뉴의 숫자를 정확히 입력해주세요");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void appList() {
		for (App a : installedApps) {
			System.out.println(
					"( appno" + a.getAppNo() + "  인덱스" + (installedApps.indexOf(a) + 1) + ") " + a.getAppName());
		}
	}

	public static void runApp(int no) {
		List<App> tempList = new ArrayList<>(installedApps);
		for (App a : tempList) {
			if (installedApps.indexOf(a) == no) {
				a.run();
			}
		}
	}
	
	public static void saveInstalledApps() {
	    TrioUtils.save("storage", "system", "installed_apps.ser", installedApps);
	}
	
	public static void loadInstalledApps() {

	}
}