package domain;

import java.util.Arrays;

public class LottoApp {

	//25/04/30
		public static void main(String[] args) {
			int[] lotto = new int[6];
			int idx = 0;  //무한루프 구간. 항상 참. break없으면 무한임
			while(true) {
				int number = (int)(Math.random()*45)+1;  //0부터 45인데 1더해서 1부터
				
				boolean insert = true; //중복 체크. insert
				
				for(int i = 0; i <= idx ; i++) {   //배열 내에 들어있는 값이 중복인지 아닌지
					if(lotto[i] == number) {
						insert = false;
						break;  //for문 브레이크
					}
					
				}
				
				if(insert) {
					lotto[idx] = number; //idx에 number을 넣을거냐
					idx++;
				}
				if(idx == 6) break;  //while문 브레이크
			}
//			System.out.println(Arrays.toString(lotto));
			int[]arr = lotto;
			for(int i = 0 ; i < arr.length - 1 ; i++) {
				System.out.println(i + 1 + "회차");
				for(int j = 0 ; j < arr.length - 1 -i ; j++) {
					if(arr[j] > arr[j+1]) {
						int tmp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = tmp;
					}
					System.out.println(Arrays.toString(arr));
				
			}
		}
	}
}
