package main;

import service.MenuService;

public class SmartrioMain {
	public static void main(String[] args) {
		System.out.println("메인 메소드입니다");
		MenuService menu = new MenuService();
		
		menu.menu();

	}
}
