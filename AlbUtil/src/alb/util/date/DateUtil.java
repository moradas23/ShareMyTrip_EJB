package alb.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {

	public static Date fromString(String string) {
		String dateString[] = string.split("/");
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString[0]));
		c.set(Calendar.MONTH, Integer.parseInt(dateString[1]) - 1); // base 0
		c.set(Calendar.YEAR, Integer.parseInt(dateString[2]));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static String toString(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}

	public static boolean isDateInWindow(Date date, Date from, Date until) {
		return date.compareTo(from) >= 0 && date.compareTo(until) <= 0;
	}

	public static boolean isNotAfter(Date date, Date reference) {
		return date.compareTo(reference) <= 0;
	}

	public static boolean isNotBefore(Date date, Date reference) {
		return date.compareTo(reference) >= 0;
	}

	public static boolean isAfter(Date date, Date reference) {
		return date.compareTo(reference) > 0;
	}

	public static boolean isBefore(Date date, Date reference) {
		return date.compareTo(reference) < 0;
	}

	public static boolean sameMonth(Date date, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime(date);
		c2.setTime(date2);

		return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
	}

	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static Date subDays(Date date, int days) {
		return addDays(date, -days);
	}

	/**
	 * Truncs a date by setting hh:mm:ss to 00:00:00. 
	 * For example for date "12/02/2012 13:24:34" returns "12/02/2012 00:00:00.000"
	 * It is useful for comparing dates in the same day. 
	 * @param date
	 * @return 
	 */
	public static Date trunc(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static boolean isFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH) == 1;
	}

	public static String stringStamp() {
		return new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format( new Date() );
	}

	public static Integer month(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	public static Date yesterday() {
		return addDays( trunc(new Date()), -1);
	}

	public static Date today() {
		return trunc( new Date() );
	}
	
	public static Date rndDateFrom(Date baseDate, int dias) {
		return addDays(baseDate, rnd(0, dias));
	}

	public static Date rndDateBefore(Date baseDate, int dias) {
		return subDays(baseDate, rnd(1, dias));
	}

	private static Random rnd = new Random();
	private static int rnd(int min, int max) {
		return rnd.nextInt(max - min) + min;
	}

}
