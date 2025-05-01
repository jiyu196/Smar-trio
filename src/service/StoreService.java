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
	
	public List<Apps> installatableApps(){ //호출을 통해 스토어에 앱 목록 호출
		
		return storeApps;
	}
}
