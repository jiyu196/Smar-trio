package service.apps;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {
	public static void main(String[] args) {
		String currentTime = TimeService.getCurrentTime();
        System.out.println(currentTime);
	}
	
	// 시간 기능
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTime() {
        return sdf.format(new Date());
    }
    
    
}