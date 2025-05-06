package main;

import service.MainMenuService;

public class SmartrioMain {
	public static void main(String[] args) {
		
		MainMenuService MainMenu = MainMenuService.getInstance();
		
		MainMenu.mainMenu();
	}
}