package app;

import static util.TrioUtils.generateAppNo;
import static util.TrioUtils.loadData;
import static util.TrioUtils.nextInt;
import static util.TrioUtils.saveData;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import domain.Calculation;
import domain.Memo;
import domain.PhoneUI;
import util.TrioUtils;

public class Calculator extends App {

	private List<Calculation> calculationHistory;

	public Calculator(int no) {
		super(no, "계산기");
		this.calculationHistory = new ArrayList<>();
	}

	public void run() {
		System.out.println("계산기 앱을 실행합니다");
		loadHistory();
		while (true) {
			PhoneUI.printTimeLine();
			PhoneUI.printWallpaper();
			System.out.println(" < 계산기 > :");
			System.out.println("" + 
					" (1) 계산 하기\n" + 
					" (2) 기록 보기 \n" + 
					" (0) 돌아가기");
			PhoneUI.printBorder();
			int choice = nextInt("(옵션을 선택하세요) :");
			switch (choice) {
			case 1:
				calculate();
				break;
			case 2:
				showHistory();
				break;
			case 0:
				System.out.println("(홈 화면으로 되돌아갑니다)");
				saveHistory();
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}

	private void calculate() {
		while (true) {
			String operation = TrioUtils.nextLine("어떤 연산을 하시겠습니까? (+, -, *, / 입력)");
			if (!operation.matches("[+\\-*/]")) {
				System.out.println("잘못된 연산자입니다. 다시 시도하세요.");
				continue;
			}
			double number1 = TrioUtils.nextDouble("첫번째 숫자를 입력하세요:");
			double number2 = TrioUtils.nextDouble("두번째 숫자를 입력하세요:");

			double result = 0;
			if (number2 == 0) {
				System.out.println("0으로 나눌 수 없습니다.");
				continue;
			}

			switch (operation) {
			case "+": // 덧셈
				result = number1 + number2;
				break;
			case "-": // 뺄셈
				result = number1 - number2;
				break;
			case "*": // 곱셈
				result = number1 * number2;
				break;
			case "/": // 나눗셈
				result = number1 / number2;
				break;
			}
			String date = TrioUtils.getCurrentDateTime();
			Calculation calc = new Calculation(number1, number2, operation, result, date);
			System.out.println("결과 : " + result);
			calculationHistory.add(calc);
			saveHistory();
		}
	}

	private void saveHistory() {
		saveData(calculationHistory, "storage/calculator/math_log.ser");
	}

	private void loadHistory() {
		List<Calculation> loadHistory = loadData("storage/calculator/math_log.ser");

		if (loadHistory != null) {
			this.calculationHistory = loadHistory;

		} else {
			this.calculationHistory = new ArrayList<>(); // 파일이 없거나 실패한 경우에도 빈 리스트로 초기화
			System.out.println("기록을 불러오는 데 실패했습니다.");
		}
	}

	private void showHistory() {
		if (!calculationHistory.isEmpty()) {
			System.out.println("계산 기록:");
			for (Calculation calc : calculationHistory) {
				System.out.println(calc);
			}
		} else {
			System.out.println("기록이 없습니다.");
		}
	}
}
