package service;

import java.util.ArrayList;
import java.util.List;

import Utils.TrioUtils;
import domain.AppTest;
import domain.Apps;

public class MenuService { // 이 클래스를 호출하는 것으로 메인에 메뉴를 출력, 메뉴에 직접 기능 호출
	//리스트를 사용해 설치된 어플들의 목록을 호출할 예정, 임시적으로 어플실이 실행 메소드 만으로 실행되는지 알아볼 예정
	
	
	List<Apps> installedApps = new ArrayList<Apps>(); //설치된 앱의 리스트
	
	{//초기화블럭
		installedApps.add(new AppTest());
	}
	
	
	public void menu() {};
}