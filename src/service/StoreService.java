package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;
import domain.AppTest;

public class StoreService { //스토어에 기능을 담당.
	
	private static final StoreService storeService = new StoreService(); //싱글턴 설계
	private StoreService() {}
	public static StoreService getInstance() {
		return storeService;
	}
	
	
	List<App> instalableApps = new ArrayList<>();  //스토어의 있는 어플 관리 리스트
	
	{// 이곳에 스토어에서 사용할 어플들 추가
		int no = 1; // appno 생성 메서드 추가 예정
		instalableApps.add(new AppTest(no));
	}
	
	public void showStoreApp() { // 스토어의 모든 리스트 확인
		for( App a : instalableApps ) {
			System.out.println(a.getAppNo() +", 어플이름 :" +a.getAppName());
		}
	}
	
	public App selectApp(int appNo) {//어플의 고유 번호를 기준으로 스토어 리스트 내의 어플 하나 선택
	    for (App a : instalableApps) {
	        if (a.getAppNo() == appNo) {
	            return a;
	        }
	    }
	    return null;
	}
	
	public int makeAppStoreNo() { //구현중
		
		return 0;
	}
}
