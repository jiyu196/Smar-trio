package main;

import static util.TrioUtils.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import app.App;
import app.ContactList;
import app.Info;
import app.JavaMailSender;
import app.Setting;
import app.Store;
import domain.Contacts;
import domain.Memo;
import domain.PhoneUI;
import util.TrioUtils;

public class Main {

	public static void main(String[] args) {
		main.menu();
	}
	private static Main main = new Main();
	private Main() {
		loadApp();
		
	}

	public static Main getInstance() {
		return main;
	}

	public List<App> installedApps = new ArrayList<>();
	Info info = Info.getInstance();
	
	public void menu() {

		while (true) {

//			try {

//				if (info.getloginInfo() == null) {
//					guestMenu();
//				} else {
					appList();
					int no = nextInt("실행할 어플의 번호를 선택해 주세요. 0.종료");
					if (!(no >= 0 && no <= main.installedApps.size())) {
						throw new IllegalArgumentException("표기된 번호만 입력해주세요");
					}
					if (no == 0) {
						System.out.println("기기를 종료합니다");
						return;
					}
					runApp(no - 1);
//				}
//			} catch (NumberFormatException e) {
//				System.out.println("실행할 메뉴의 숫자를 정확히 입력해주세요");
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage());
//			} catch (NullPointerException e) {
//				System.out.println(e.getMessage());
//			}
		}
	}

	public void guestMenu() {
		PhoneUI.printTimeLine();
		PhoneUI.printWallpaper();
		int no = nextInt(""
				+ " (1) 비밀번호 입력\n"
				+ " (2) 사용자 등록\n"
				+ " (0) 기기 종료");
		switch (no) {
		case 1:
			if (!info.loadUserData()) {
				System.out.println("회원가입이 필요합니다");
			} else {
				info.logIn();
			}
			break;
		case 2:
			if (!info.loadUserData()) {
				info.register();
			}
			break;
		case 0:
			System.out.println("기기를 종료합니다");
			return;
		default:
			throw new IllegalArgumentException("정해진 번호를 입력해주세요");
		}
	}

	public void appList() {
		int index = 1;
		for (App a : installedApps) {
			System.out.println(" (" + index + ") " + a.getAppName());
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

	private void loadApp() {
		List<App> loadApp = loadData("storage/system/Appdata");

		if (loadApp != null) {
			this.installedApps = loadApp;
		} else {
//			this.installedApps = new ArrayList<>(); // 파일이 없거나 실패한 경우에도 빈 리스트로 초기화
			System.out.println(" 앱  기록을 불러오는 데 실패했습니다.");
		}

		if (installedApps.isEmpty()) {
			installedApps.add(new Store(generateAppNo()));
			installedApps.add(new Setting(generateAppNo()));
			installedApps.add(ContactList.getInstance());
			installedApps.add(new JavaMailSender(generateAppNo()));
		}
	}

}