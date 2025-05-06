package service.apps;

import java.util.Calendar;
import static java.util.Calendar.*;

import java.text.SimpleDateFormat;

public class CalenderService {

		public static void main(String[] args) {

			
			Calendar cal = getInstance();
			
			
			
			cal.set(DATE, 1);  //date값을 1로 지정을 해애 넣어서 가져옴 1일의 요일-2월임
			cal.add(MONTH, -1);
			
			int lastDate = cal.getActualMaximum(DATE);
			int startDay = cal.get(DAY_OF_WEEK);
			int d = startDay-1;
//			System.out.println(startDay-1); //startDay가 핵심단서. 
			System.out.println(new SimpleDateFormat("yyyy/MM 달력").format(cal.getTime()));
			System.out.println(" 월 화 수 목 금 토 일");
			for(int i =1-d; i <=lastDate; i++) {
				if(i < 1) {
					System.out.printf("%3c",' ');
				}
				else {
					System.out.printf("%3d",i);
				}
				
				if(i % 7 == ((7 - d) % 7)) {
					System.out.println();
				
					
				
		}
			}

	}

	
		
}
