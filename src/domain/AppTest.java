package domain;

public class AppTest extends Apps{

	public AppTest() {
		
	}

	@Override
	public String getName() {
		return "설정";
	}
	
	@Override
	public void run() {
		System.out.println("테스트 기능 실행");
	}

	@Override
	public int appNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
