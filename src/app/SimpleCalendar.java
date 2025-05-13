package app;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.TrioUtils;

public class SimpleCalendar extends App{

    public SimpleCalendar(int no) {
		super(no, "달력");
		// TODO Auto-generated constructor stub
	}
    
    Calendar cal = Calendar.getInstance();

	public void run() {
        while (true) {
        	displayCalendar();
        	System.out.println("\n옵션을 선택하세요:");
        	int choice = TrioUtils.nextInt(""
        			+ " (1) 이전 달\n"
        			+ " (2) 다음 달\n"
        			+ " (0) 종료");
        	switch (choice) {
			case 1: {
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.MONTH, -1); // 이전 달
			    displayCalendar();
			    break;
			}
			case 2: {
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.MONTH, 1); // 다음 달
				displayCalendar();
				break;
			}
			case 0: {
				return; // 종료 + 매인으로
			}
			default:
				System.out.println("잘못된 선택입니다.");
				break;
			}
        }
    }
	
	private void displayCalendar() {
		cal.set(Calendar.DAY_OF_MONTH, 1);
        // 시작 날자
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
        // 주일 출력
        System.out.println(" 일 월 화 수 목 금 토");
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 (Sun) to 7 (Sat)
        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 날자 (월당)

        // 빈공간 출력
        for (int i = 1 - firstDayOfWeek; i <= lastDate; i++) {
            if (i < 1) {
                // 월 시작 날자 위해 빈공간 출력
                System.out.print("   ");
            } else {
                // 날자 출력
                System.out.printf("%3d", i);
            }
            // 화요일 이면 다음줄 바꾸기
            if(i % 7 == ((7 - firstDayOfWeek) % 7)) {
                System.out.println();
            }
        }
	}
}