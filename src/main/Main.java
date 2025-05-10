package main;

import static util.TrioUtils.*;



public class Main {
	public static void main(String[] args) {
		

		
	}
	
	
	public static void mainMenu() {
		while (true) {
			int no = nextInt("실행할 어플의 번호를 선택해 주세요. 0 기기 종료");
			
			if (no == 0) {
				System.out.println("기기를 종료합니다");
				return;
			}

		}
	}
}