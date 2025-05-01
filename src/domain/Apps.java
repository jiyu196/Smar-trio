package domain;

import java.io.Serializable;

public abstract class Apps implements Serializable{ // 해당 클래스를 상속 받아주세요
	String name;
	
	public Apps() {}
	
	public void setName(String name) { //각 어플의 이름을 등록할 추상 클래스입니다
		this.name = name;
	}
	
	public String getName() { // 해당 메서드를 호출하면 이름이 출력될 기능입니다
        return name;
    }
	
	public abstract void run(); // 메서드 호출시 어플이 작동할 부분입니다. 
	
	public abstract void svaeData();// 각 어플의 데이터 저장
	
	public abstract void loadData(); //각 어플의 데이터 로드
	
	public abstract void exitApp(); // 어플의 정상 종료 기능
	
	

}
