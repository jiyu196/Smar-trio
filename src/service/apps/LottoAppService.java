package service.apps;

import java.util.Arrays;

import utils.DateTimeUtils;

public class LottoAppService {

	// 25/04/30
	public static void main(String[] args) {
		String currentTime = DateTimeUtils.getCurrentDateTime("dd/MM/yyyy hh:mm a");
		System.out.println(currentTime);
		
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
		System.out.println(Arrays.toString(arr));
	}
}
