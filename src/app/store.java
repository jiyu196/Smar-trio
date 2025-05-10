package app;

import java.util.ArrayList;
import java.util.List;
import static util.TrioUtils.*;

public class store extends App{

	public store(int no) {
		super(no, "스토어", true);
		}

	List<App> store = new ArrayList<App>();
	
	{
		store.add(new Calculator(getAppNo()));
	}
	
	@Override
	public void run() {
		
		install();
		while(true) {
			int no = nextInt("실행하고싶은 기능의 번호를 입력해주세요");
		}
		
		
	}
	
	private void install() {
		
	}
	
	private void appList() {
		
	}
	
	
}
