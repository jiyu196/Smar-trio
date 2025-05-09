package old.apps;

import java.util.Scanner;

import old.utils.DateTimeUtils;
import old.utils.TrioUtils;

public class UpAndDownAppService {
	public static void main(String[] args) {
        
		int input = 0;
		int count = 0;
		
		int value = (int)(Math.random()*100+1);
		
		do {
			String currentTime = DateTimeUtils.getCurrentDateTime("dd/MM/yyyy hh:mm a");
			System.out.println(currentTime); //시간 뜨는부분
			
//			System.out.println("1에서 100사이의 숫자를 입력하세요 >");
			input = TrioUtils.nextInt("1에서 100사이의 숫자를 입력하세요 >");
			if(value < input) {
				System.out.println("다운입니다");
			}
			else if(value > input) {
				System.out.println("업입니다");
			}
			count++;
		}
		while(value != input);
		
		System.out.println(count + "번째에 당첨! 축하합니다");
		
	}
}
