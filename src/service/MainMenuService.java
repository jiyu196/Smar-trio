package service;

public class MainMenuService { // 이 클래스를 호출하는 것으로 메인에 메뉴를 출력, 메뉴에 직접 기능 호출
	//리스트를 사용해 설치된 어플들의 목록을 호출할 예정, 임시적으로 어플실이 실행 메소드 만으로 실행되는지 알아볼 예정
	

	private static MainMenuService menuService = new MainMenuService();
	private MainMenuService() {}
	public static MainMenuService getInstance() {
		return menuService;
	}
	
	
	public void mainMenu() {
		System.out.println("사용할 기능들을 선택해주세요");
	}

}