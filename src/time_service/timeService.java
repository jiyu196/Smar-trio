package time_service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class timeService {
	// 시간 기능
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTime() {
        return sdf.format(new Date());
    }
}