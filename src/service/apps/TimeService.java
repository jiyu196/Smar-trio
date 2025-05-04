package service.apps;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd yyyy HH:mm:ss");
    public static String getCurrentTime() {
        return sdf.format(new Date());
    }
    
    public static void main(String[] args) {
        System.out.println(getCurrentTime());
    }
}
