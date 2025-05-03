package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;
import domain.AppTest;

public class StoreService { //스토어에 기능을 담당.
	
	private StoreService storeService = new StoreService(); //싱글턴 설계
	private StoreService() {}
	public	StoreService getInstance() {
		return storeService;
	}
	
	
	List<App> instalableApps = new ArrayList<>();  //스토어의 있는 어플 관리 리스트
	
	{// 이곳에 스토어에서 사용할 어플들 추가
		int no = 1;
		instalableApps.add(new AppTest(no));
	}
	
	public void showStoreApp() {
		for( App a : instalableApps ) {
			System.out.println(a.getAppNo() +", 어플이름 :" +a.getAppName());
		}
	}
	
	public App selectApp(int appNo) {
		App App = null;
		if(App.getAppNo() == appNo) {
			App = instalableApps.get(appNo);	
		}
		return App;
	}
	
	public int makeAppStoreNo() { //구현중
		
		return 0;
	}
}
