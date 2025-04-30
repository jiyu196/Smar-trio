package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;
import domain.appTest;

public class storeService { //스토어에 기능을 담당.
	
	private List<App> storeApps = new ArrayList<>();
	
	{
		storeApps.add(new appTest());
	}
	
	
	public storeService() { // 생성자
		
    }
	
	public List<App> installatableApps(){ //호출을 통해 스토어에 앱 목록 호출
		
		return storeApps;
	}
}
