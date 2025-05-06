package main;

import static utils.TrioUtils.nextInt;

import java.util.List;
import domain.CalculatorApp;
import domain.ContactList;
import service.ConsoleUIService;
import service.apps.BlackJackApp;

public class TestMain {
	public static void main(String[] args) { // 각 어플들의 객체를호출해서 동작 확인 하는 메인입니다
		TestMain TestMenu = TestMain.getInstance();
		TestMenu.testMenu();
	}

	private static TestMain testMain = new TestMain();

	private TestMain() {
	}

	public static TestMain getInstance() {
		return testMain;
	}


	CalculatorApp calculatorApp = CalculatorApp.getInstance();
	BlackJackApp blackJackApp = BlackJackApp.getInstance();
	ContactList contactList = ContactList.getInstance();

	public void testMenu() {
		while (true) {
			displayMainScreen(); // UI 나오기, 주석 하며는 해제.
			int no = nextInt("실행할 기능 선택: ");
			switch (no) {
			case 1: {
				calculatorApp.run();
				break;
			}
			case 2: {
				blackJackApp.run();
				break;
			}
			case 3: {
				contactList.run();
				break;
			}
			case 4: {
				return;
			}
			default:
				throw new IllegalArgumentException("잘못된 입력: " + no);
			}
		}
	}

	private void displayMainScreen() {

		ConsoleUIService.printMenu("< 메인 매뉴 >", List.of("계산기", "블랙잭", "연락처", "종료"), true);

	}
}
