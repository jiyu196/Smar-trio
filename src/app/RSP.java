package app;

import java.util.Scanner;

import domain.PhoneUI;
import util.TrioUtils;

public class RSP extends App{

//	public static void main(String[] args) {
//		RSP app = new RSP(1);
//		app.run();
//	}

	public RSP(int no) {
		super(no, "가위바위보");
	}

	public void run() {
		PhoneUI.printTimeLine();
		PhoneUI.printWallpaper();
		System.out.println(" < 가위바위보 앱 > :");
		if (!TrioUtils.nextConfirm("컴퓨터와 대결하시겠습니까? ")) {
			System.out.println("가위바위보 게임이 종료되었습니다.");
			return;
		}
		while (true) {
//			System.out.println("-------------------------------------------------");
			PhoneUI.printBorder();
			System.out.print(" (1) 가위\n (2) 바위\n (3) 보\n중 하나를 선택하세요 >\n");
			PhoneUI.printBorder();
//			System.out.println("-------------------------------------------------");
			Scanner scanner = new Scanner(System.in);

			int me = scanner.nextInt();
			//System.out.println(me);
			int com = (int) (Math.random() * 3 + 1);
			//System.out.println(com);
			// System.out.println(me - com);

			// 승리, 패배, 무승부 출력
			String[] values = { "가위", "바위", "보" };
			System.out.println("당신은 " + "<" + values[me - 1] + ">" + "를 냈습니다");
			System.out.println("컴퓨터는 " + "<" + values[com - 1] + ">" + "를 냈습니다");

			String result = "";
			switch (me - com) {
			case -2:
				result = "승리";
				System.out.println(">> 결과 : 승리했습니다!");
				break;
			case -1:
				result = "패배";
				System.out.println(">> 결과 : 패배입니다...\n다시 도전 하시겠습니까? ");
				break;
			case 0:
				result = "비김";
				System.out.println(">> 결과 : 비겼습니다");
				break;
			case 1:
				result = "승리";
				System.out.println(">> 결과 : 승리했습니다!");
				break;
			case 2:
				result = "패배";
				System.out.println(">> 결과 : 패배입니다...\n다시 도전 하시겠습니까? ");
				break;
			}
			System.out.println("=================================================");
			if (!TrioUtils.nextConfirm("가위바위보 게임을 다시 시작하시겠습니까?")) {
				System.out.println("가위바위보 게임이 종료되었습니다.");
				return;
			}
			continue;
		}
	}
}
