package old.apps;

import java.util.Scanner;

//import old.utils.DateTimeUtils;

public class RPSAppService {
	public static void main(String[] args) {
//		String currentTime = DateTimeUtils.getCurrentDateTime("dd/MM/yyyy hh:mm a");
//		System.out.println(currentTime);

		System.out.print("가위(1), 바위(2), 보(3) 중 하나를 선택하세요 >");
		Scanner scanner = new Scanner(System.in);
		int me = scanner.nextInt();
//			System.out.println(me);

		int com = (int) (Math.random() * 3 + 1); // 0이상 1미민의 값 > 0이상 3미만 > 1이상 4미만 > 정수화
//			System.out.println(com);

//		1	System.out.println(me - com);
		// switch를 활용해 승리, 패배, 무승부 출력

		String[] values = { "가위", "바위", "보" };
		System.out.println("내가 낸것 : " + values[me - 1]);
		System.out.println("컴퓨터 : " + values[com - 1]);

		String result = "";
		switch (me - com) {
		case -2:
			result = "승리";
			System.out.println("결과 : 승리!");
			break;
		case -1:
			result = "패배";
			System.out.println("결과 : 패배");
			break;
		case 0:
			result = "비김";
			System.out.println("결과 : 비김");
			break;
		case 1:
			result = "승리";
			System.out.println("결과 : 승리!");
			break;
		case 2:
			result = "패배";
			System.out.println("결과 : 패배");
			break;
		}
	}
}
