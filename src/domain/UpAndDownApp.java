package domain;

import java.util.Scanner;

public class UpAndDownApp {
	public static void main(String[] args) {
        
		int input = 0;
		int count = 0;
		
		int value = (int)(Math.random()*100+1);
		Scanner scanner = new Scanner(System.in);
		
		do {
			String currentTime = service.timeService.getCurrentTime();
			System.out.println(currentTime); //시간 뜨는부분
			
			System.out.println("1에서 100사이의 숫자를 입력하세요 >");
			input = scanner.nextInt();
			if(value < input) {
				System.out.println("다운입니다");
			}
			else if(value > input) {
				System.out.println("업입니다");
			}
			count++;
		}
		while(value != input);
		
		System.out.println(count + "번째에 당첨");
		
	}
}
