package domain;

import java.io.Serializable;

public abstract class Apps implements Serializable{
	
	
	public Apps() {};
	
	public abstract int appNo();
	
	public abstract String getName(); // 해당 메서드를 호출하면 이름이 출력될 기능입니다

	public abstract void run();
}
