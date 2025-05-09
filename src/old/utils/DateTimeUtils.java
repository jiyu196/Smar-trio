package old.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateTimeUtils {

	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCurrentDateTime() {
		return DATE_TIME_FORMAT.format(new Date());
	}

	public static String getCurrentDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
}