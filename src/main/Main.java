package main;

import old.service.MainMenuService;

public class Main {
	public static void main(String[] args) {

		MainMenuService MainMenu = MainMenuService.getInstance();

		MainMenu.mainMenu();
	}
}