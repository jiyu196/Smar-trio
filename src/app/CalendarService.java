package app;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarService {
//	public static void main(String[] args) {
//		CalendarService app = new CalendarService();
//        app.run();
//    }

    public void run() {
        Calendar cal = Calendar.getInstance();
        // 마지막 날자 (월당)
        int lastDate = cal.getActualMaximum(Calendar.DATE);
        // 시작 날자
        int startDate = cal.get(Calendar.DAY_OF_WEEK);
        int weekEndSet = startDate - 1;

        // 날자 출력
        System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
        // 주일 출력
        System.out.println("Su Mo Tu We Th Fr Sa");
        // 빈공간 출력
        for (int i = 1 - weekEndSet; i <= lastDate; i++) {
            if (i < 1) {
                // 월 시작 날자 위해 빈공간 출력
                System.out.print("   ");
            } else {
                // 날자 출력
                System.out.printf("%2d ", i);
            }
            // 화요일 이면 다음줄 바꾸기
            if(i % 7 == ((7 - weekEndSet) % 7)) {
                System.out.println();
            }
        }
    }
}