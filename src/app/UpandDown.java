package app;

import static util.TrioUtils.generateAppNo;

import domain.PhoneUI;
import util.TrioUtils;

public class UpandDown extends App{

//	public static void main(String[] args) {
//		UpandDown app = new UpandDown(1);
//		app.run();
//	}
	
	public UpandDown(int no) {
		super(no, "업앤다운");
	}


	public void run() {
		
		System.out.println("업앤다운 앱을 실행합니다");
		PhoneUI.printTimeLine();
		PhoneUI.printWallpaper();
		
		if (!TrioUtils.nextConfirm(" 게임을 시작하시겠습니까? ")) {
			System.out.println("업앤다운 앱이 종료되었습니다.");
			return;
		}
		while (true) {
			int input = 0;
			int count = 0;

			int value = (int) (Math.random() * 100 + 1);

			do {
				System.out.println("-------------------------------------------------");
				input = TrioUtils.nextInt("1에서 100사이의 숫자를 입력하세요 >");
				
				if (value < input) {
					System.out.println("<다운>입니다");
				} else if (value > input) {
					System.out.println("<업>입니다");
				}
				count++;
			} while (value != input);

			System.out.println(count + "번째에 당첨! 축하합니다");
			System.out.println("=================================================");

			if (!TrioUtils.nextConfirm("게임을 다시 시작하시겠습니까? ")) {
				System.out.println("(홈 화면으로 되돌아갑니다)");
				return;
			}
			continue;
		}
	}
}
