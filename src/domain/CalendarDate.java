package domain;

import java.io.Serializable;
import java.util.Calendar;

public class CalendarDate implements Serializable {

	private int year;
	private int month;

	public CalendarDate(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "CalendarDate [year=" + year + ", month=" + month + "]";
	}
}
