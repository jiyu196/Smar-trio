package app;

import java.util.ArrayList;
import java.util.List;
import main.Main;

import static util.TrioUtils.*;

public class Store extends App{

	public Store(int no) {
		super(no, "스토어", true);
		}

	public static List<App> store = new ArrayList<App>();
	
	{
		store.add(new Calculator(getAppNo()));
	}
	
	@Override
	public void run() {
		while(true) {
			appList();
			int no = nextInt("설치하고 싶은 어플의 번호를 입력해주세요. 0. 종료");
			if(no == 0) {
				System.out.println("설치를 취소하고 스토어로 되돌아갑니다");
				return;
			}
			install(no -1);
		}
	}
	
	private void appList() {
		for(App a: store) {
			System.out.println("(" + a.getAppNo()+") " + a.getAppName());
		}
	}

	//   ConcurrentModificationException 의 발생, 리스트의 순회중 run 메소드를 실행중에 리스으틔 내용을 바꾼것으로 취급하여 예외가 발생
	private void install(int no) {
		List<App> tempList = new ArrayList<>(store);
		for(App a : tempList) {
			if(Main.installedApps.contains(a)) {
				System.out.println("이미"+ a.getAppName()+ "은 설치되어있습니다");
				return;
			}
			if(store.indexOf(a)== no) {
				Main.installedApps.add(a);
				System.out.println(a.getAppName()+"을 설치했습니다");
			}
		}

	}

	
	
}
