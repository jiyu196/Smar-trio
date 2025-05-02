package service;

import java.util.ArrayList;
import java.util.List;

import domain.Apps;
import domain.AppTest;

public class StoreService { //스토어에 기능을 담당.
	
	private List<Apps> storeApps = new ArrayList<>();
	
	{
		storeApps.add(new AppTest());
	}
	
	
	public StoreService() { // 생성자
		
    }
	
	public void showStoreAppsList() {
		System.out.println("스토어의 앱의 목록들");
	}
	
	public List<Apps> getInstallatableApps(){ // 스토어의 앱 리스트 하나 선택 
		
		return storeApps;
	}
}
