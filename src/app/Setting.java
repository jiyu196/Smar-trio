package app;

import static util.TrioUtils.*;

import domain.PhoneUI;
import main.Main;

public class Setting extends App {

	Info info = Info.getInstance();
	
	public Setting(int appNo) {
		super(appNo, "설정", true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		while (true) {
			PhoneUI.printTimeLine();
			PhoneUI.printWallpaper();
			int no = nextInt(" < 설정 > :\n"
					+ " (1) 계정설정\n"
					+ " (2) 어플 삭제\n"
					+ " (0) 돌아가기");

			switch (no) {
			case 1:
				info.accountMenu();
				if(info.getloginInfo() == null) {return;}
				break;
			case 2:
				deleteApp();
				return;
			case 0:
				System.out.println("(홈 화면으로 되돌아갑니다)");
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}
		}

	}

	public void deleteApp() {
		Main main = Main.getInstance();
		for (App a : main.installedApps) {
			System.out.println("(" + (main.installedApps.indexOf(a) + 1) + ")" + a.getAppName());
		}

		int no = nextInt("삭제할 어플을 골라주세요");
		if(!(no>=0 && no<=main.installedApps.size())) {
			throw new IllegalArgumentException("표기된 번호만 입력해주세요");
		}
		if (no == 0) {
			System.out.println("삭제를 취소합니다");
			return;
		}
		
		App app = main.installedApps.get(no - 1);
		if (app.isSystemApp()) {
			System.out.println("시스템앱은 삭제할수없습니다");
			return;
		}
		if(nextConfirm(app.getAppName()+"을 정말 삭제시겠습니까?")) {
			System.out.println(app.getAppName() + "을 삭제했습니다");
			main.installedApps.remove(app);
			saveData(main.installedApps, "storage/system/Appdata");
			return;
		}
	}
}
