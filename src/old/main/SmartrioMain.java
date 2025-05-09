package old.main;

import old.service.MainMenuService;

public class SmartrioMain {
	public static void main(String[] args) {

		MainMenuService MainMenu = MainMenuService.getInstance();

		MainMenu.mainMenu();
	}
}