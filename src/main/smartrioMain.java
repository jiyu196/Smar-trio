package main;

import service.menuService;

public class smartrioMain {
	public static void main(String[] args) {
		System.out.println("메인 메소드입니다");
		menuService menu = new menuService();
		
		menu.menu();

	}
}
