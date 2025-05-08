package service.apps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import utils.TrioUtils;

//extends App
public class CalculatorApp {
	public static void main(String[] args) {
		CalculatorApp app = new CalculatorApp();
		app.run();
	}

	private static final Path CALC_PATH = Path.of("storage", "calculator", "math_log.txt");

	private static final CalculatorApp calculatorApp = new CalculatorApp();

	private CalculatorApp() {
	}

	public static CalculatorApp getInstance() {
		return calculatorApp;
	}

	public void run() {
		System.out.println("계산기 앱을 실행합니다.");

		boolean running = true;
		while (running) {
			String str = TrioUtils.nextLine("1: 계산, 2: 기록 보기, 0: 종료");

			if (str.equals("0")) {
				System.out.println("종료");
				return;

			} else if (str.equals("2")) {
				showHistory();

			} else if (str.equals("1")) {
				String math = TrioUtils.nextLine("어떤 연산을 하시겠습니까? (+, -, *, / 입력)");
				double num1 = TrioUtils.nextDouble("첫번째 숫자를 입력하세요:");
				double num2 = TrioUtils.nextDouble("두번째 숫자를 입력하세요:");
				double result = 0;
				boolean valid = true;
				String write = "";

				switch (math) {
				case "+":
					result = num1 + num2;
					write = num1 + " + " + num2 + " = " + result;
					break;
				case "-":
					result = num1 - num2;
					write = num1 + " - " + num2 + " = " + result;
					break;
				case "*":
					result = num1 * num2;
					write = num1 + " * " + num2 + " = " + result;
					break;
				case "/":
					if (num2 == 0) {
						System.out.println("0으로 나눌 수 없습니다.");
						valid = false;
					} else {
						result = num1 / num2;
						write = num1 + " / " + num2 + " = " + result;
					}
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					valid = false;
				}

				if (valid) {
					System.out.println("결과 : " + result);
					try {
						Files.createDirectories(CALC_PATH.getParent());
						try (BufferedWriter writer = new BufferedWriter(new FileWriter(CALC_PATH.toFile(), true))) {
							writer.write(write);
							writer.newLine();
						}
					} catch (IOException e) {
						System.out.println("기록 저장 중 오류 발생: " + e.getMessage());
					}
				}

				if (!TrioUtils.nextConfirm("계속하시겠습니까? (y / n)")) {
					System.out.println("종료");
					running = false;
				}

			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private void showHistory() {
		System.out.println("< 기록 >");
		try {
			if (!Files.exists(CALC_PATH)) {
				System.out.println("기록이 없습니다.");
				return;
			}

			try (BufferedReader reader = Files.newBufferedReader(CALC_PATH)) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			System.out.println("기록 불러오기 실패: " + e.getMessage());
		}
	}
}
