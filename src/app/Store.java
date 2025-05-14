package app;

import static util.TrioUtils.*;
import java.util.ArrayList;
import java.util.List;

import main.Main;

public class Store extends App {

	public Store(int no) {
		super(no, "스토어", true);
		storeApps();
	}

	public List<App> store = new ArrayList<App>();

	@Override
	public void run() {
		while (true) {
			showAppList();

			int no = nextInt("설치하고 싶은 어플의 번호를 입력해주세요. 0. 종료");
			if (!(no >= 0 && no <= store.size())) {
				throw new IllegalArgumentException("표기된 번호만 입력해주세요");
			}

			if (no == 0) {
				System.out.println("설치를 취소하고 스토어로 되돌아갑니다");
				return;
			} else {
				install(no - 1);
			}
		}
	}

	private void storeApps() {
		store.add(ContactList.getInstance());
		store.add(new MemoPad(generateAppNo()));
		store.add(new Calculator(generateAppNo()));
		store.add(new SimpleCalendar(generateAppNo()));
		store.add(new CountDown(generateAppNo()));
		store.add(new UpandDown(generateAppNo()));
		store.add(new BlackJack(generateAppNo()));
		store.add(new HighLow(generateAppNo()));
		store.add(new Lotto(generateAppNo()));
		store.add(new RSP(generateAppNo()));
	}

	private void showAppList() {
//		for (App a : store) {
//			System.out.println(" (" + (store.indexOf(a) + 1) + ") " + a.getAppName());
//		}

		for (App a : store) {
			if (a != null) {
				System.out.println(" (" + (store.indexOf(a) + 1) + ") " + a.getAppName());
			} else {
				System.out.println(" ⚠️ null 객체 감지됨 - 어플 생성 실패");
			}
		}
	}

	private void install(int no) {
		Main main = Main.getInstance();

		if (no < 0 || no >= store.size()) {
			System.out.println("❌ 없는 번호입니다.");
			return;
		}

		App a = store.get(no);
		if (a == null) {
			throw new NullPointerException("선택된 앱이 null입니다.");
		}

		if (main.installedApps.contains(a)) {
			System.out.println(a.getAppName() + "은 이미 설치되어 있습니다.");
			return;
		}

		main.installedApps.add(a);
		System.out.println("✅ 설치 완료: " + a.getAppName());
		saveData(main.installedApps, "storage/system/Appdata");

	}
}