package main;

import static util.TrioUtils.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
		main.menu();
	}
	
	static Main main = new Main();

	
	public List<App> installedApps = new ArrayList<App>();

	{
		
		installedApps.add(new Store(generateAppNo()));
		installedApps.add(new Setting(generateAppNo()));

	}

	public void menu() {

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

	public void appList() {
//		for (App a : installedApps) {
//			System.out.println(
//					"("+ (installedApps.indexOf(a) + 1) + ") " + a.getAppName());
//		}
		int index = 1;
		for (App a : installedApps) {
			System.out.println("(" + index + ") " + a.getAppName());
			index++;
		}
	}

	public void runApp(int no) {
		List<App> tempList = new ArrayList<>(installedApps);
		for (App a : tempList) {
			if (installedApps.indexOf(a) == no) {
				a.run();
			}
		}
	}
	
	public void saveInstalledApps() {
        save("storage", "system", "installed_apps.ser", installedApps); // 파일료 저장
    }
	
	public void loadInstalledApps() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage/system/installed_apps.ser"))) {
            List<App> loadedApps = (List<App>) ois.readObject(); // Read installed apps from file
            installedApps.clear();
            installedApps.addAll(loadedApps);
            System.out.println("앱 목록을 성공적으로 불러왔습니다.");
        } catch (Exception e) {
            System.out.println("앱 목록을 불러오는 데 실패했습니다.");
            e.printStackTrace();
        }
    }
}