package app;

import java.util.ArrayList;
import java.util.List;
import main.Main;

import static util.TrioUtils.*;

public class Store extends App{

	public Store(int no) {
		super(no, "스토어", true);
		}

	List<App> store = new ArrayList<App>();
	
	{
		store.add(new Calculator(getAppNo()));
	}
	
	@Override
	public void run() {
		
		
		while(true) {
			int no = nextInt("설치하고 싶은 어플의 번호를 입력해주세요. 0은 종료");
			appList();
			if(no == 0) {
				System.out.println("설치를 취소하고 스토어로 되돌아갑니다");
				return;
			}
			no = no -1;
		}
		
		
	}
	
	private void appList() { // 추후 수정
		for(App a: store) {
			System.out.println("인덱스 번호 : "+store.indexOf(a)+a.toString());
		}
	}
	
	
	private void install(App app) {
		if(app == null) {
			System.out.println("선택한 어플이 없습니다");
			return;
		}
		Main.installedApps.add(app);
		System.out.println(app.getAppName() + "을 설치하였습니다");
	}
	
	private App findby(int no) {
		for(App a : store) {
			if(store.indexOf(a) == no) {
				return a;
			}
		}
		return null;
	}
	
	
}
