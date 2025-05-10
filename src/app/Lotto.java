package app;

import java.util.Arrays;

import util.TrioUtils;


public class Lotto extends App{
	
//	public static void main(String[] args) {
//		Lotto app = new Lotto(1);
//		app.run();
//	}
	
	 public Lotto(int no) {
	        super(no, "로또");
	    }
	 
	public void run() {
		System.out.println("로또 앱을 실행합니다");
		if (!TrioUtils.nextConfirm("로또 번호를 뽑으시겠습니까? ")) {
			return;
	}
		while(true) {
		int[] lotto = new int[6];
		int idx = 0;
		while (true) {
			int number = (int) (Math.random() * 45) + 1;
			boolean insert = true; // 중복 체크. insert
			
			for (int i = 0; i <= idx; i++) { // 배열 내에 들어있는 값이 중복인지 아닌지
				if (lotto[i] == number) {
					insert = false;
					break;
				}
			}
			if (insert) {
				lotto[idx] = number;
				idx++;
			}
			if (idx == 6)
				break;
		}
		int[] arr = lotto;
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		System.out.println("이번 랜덤 번호는 "+ Arrays.toString(arr)+ "입니다");
		
			if (!TrioUtils.nextConfirm("로또 번호를 다시 뽑으시겠습니까? ")) {
			System.out.println("게임이 종료되었습니다.");
			return;
			}
			continue;
		}
	}
}
