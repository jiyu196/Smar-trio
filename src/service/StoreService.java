package service;

import static utils.TrioUtils.*;

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
	
	AppService appService = AppService.getInstance();
	
	public List<App> instalableApps = new ArrayList<>();  //스토어의 있는 어플 관리 리스트
	
	{// 이곳에 스토어에서 사용할 어플들 추가
		int no = 1;
		if(!instalableApps.isEmpty()) {
			no = instalableApps.get(instalableApps.size()-1).getAppNo()+1;
		}
		instalableApps.add(new AppTest(no));
	}
	
	public void storeMenu() {
		System.out.println("스토어의 어플 목록");
		showStoreAppList();
		int no = nextInt("설치할 어플을 번호로 골라주세요 > 0은 취소");
		if(no == 0) {
			System.out.println("취소하였습니다.");
			return;
		} else {
			appService.installApp(selectStoreApp(no)); //installApp에 null 예외 처리
		}
		
	}
	
	private void showStoreAppList() { // 스토어의 모든 리스트 확인
		if(instalableApps.isEmpty()) {
			System.out.println("스토어가 비어있습니다.");
			return;
		}
		
		for( App a : instalableApps ) {
			System.out.println("("+a.getAppNo() +"), 앱의 고유 넘버, 어플이름 :" +a.getAppName());
		}
	}
	
	private App selectStoreApp(int appNo) {//어플의 고유 번호를 기준으로 스토어 리스트 내의 어플 하나 선택
	    for (App a : instalableApps) {
	        if (a.getAppNo() == appNo) {
	        	System.out.println(a.getAppName()+"을 선택하셨습니다");
	            return a;
	        }
	    }
	    return null;
	}
	
	
}
