package service;

public class TestappService {
	
	private static TestappService TestappService = new TestappService();
	private TestappService() {}
	public static TestappService getInstance() {
		return TestappService;
	}
	
	public void runAppTest() {
		System.out.println("테스트어플의 실행을 확인했습니다");
	}
}
