package domain;

import service.TestappService;

public class AppTest extends App {

	TestappService app = TestappService.getInstance();

	public AppTest(int appNo) {
		super(appNo, "테스트앱이름");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		app.runAppTest();

	}

}
