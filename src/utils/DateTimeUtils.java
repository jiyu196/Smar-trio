package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateTimeUtils {

	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCurrentDateTime() {
		return dateTimeFormat.format(new Date());
	}

	public static String getCurrentDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
}