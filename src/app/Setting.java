package app;

import static util.TrioUtils.*;

import main.Main;

public class Setting extends App {

	
	
	public Setting(int appNo) {
		super(appNo, "설정", true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		while (true) {
			
			int no = nextInt("실행할 설정을 골라주세요 1.계정설정 2.어플 삭제 3 0.종료");

			switch (no) {
			case 1:
				Info.getInstance().accountMenu();
				if(!Info.getInstance().isLoginInfo()) {
					return;
				}
				break;
			case 2:
				deletApp();
				return;
			case 0:
				System.out.println("설정을끝내고 되돌아갑니다");
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}
		}

	}

	public void deletApp() {
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
			return;
		}
		
	}
}
