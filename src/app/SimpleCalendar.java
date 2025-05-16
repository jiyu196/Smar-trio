package app;

import static util.TrioUtils.generateAppNo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import domain.PhoneUI;
import util.TrioUtils;

public class SimpleCalendar extends App{

	 public SimpleCalendar(int no) {
		super(no, "달력");
	}
    
    
    Calendar cal = Calendar.getInstance();

	public void run() {
        while (true) {
        	PhoneUI.printTimeLine();
        	PhoneUI.printWallpaper();
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
				System.out.println("(홈 화면으로 되돌아갑니다)");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		System.out.println(" [" + sdf.format(cal.getTime()) + "]");
        System.out.println(" 일 월 화 수 목 금 토");
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //첫 주
        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막주 

        // 빈공간 출력
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("   ");
        }
        // 날짜 출력
        int dayOfWeek = firstDayOfWeek;
        for (int i = 1; i <= lastDate; i++) {
            System.out.printf("%3d", i);
            if (dayOfWeek % 7 == 0) System.out.println();
            dayOfWeek++;
        }
	}
}