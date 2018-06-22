package com.test.webdriver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author TY
 * @throws   
 * @category
 * 
 */

public class TimeManager {

	private static String DATE_FORMAT_NOW = "yyyy-MM-dd";
	private static String TIME_FORMAT_NOW = "HH:mm:ss";
	public static String DATE_TIME_AMPM__FORMAT = "dd-MMM-yyyy hh:mm:ss a";
	public static String dd_MMM_yy_FORMAT = "dd-MMM-yy";

	public static void waitInMinutes(int min) {
		try {
			Thread.sleep(min * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitInSeconds(int seconds) {
		int millisec = seconds * 1000;
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitInMilliSeconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public static String timeNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	public static String dateNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	public static String dateNow(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static String dateTimeNow(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());

	}

	public static String getDateAndTime() {
		return dateNow() + "  " + timeNow();
	}

	public static String elapsedTime(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_NOW);
		Date date1 = null, date2 = null;
		try {
			date1 = format.parse(startTime);
			date2 = format.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long difference = date2.getTime() - date1.getTime();

		long diffSeconds = difference / 1000 % 60;
		long diffMinutes = difference / (60 * 1000) % 60;
		long diffHours = difference / (60 * 60 * 1000) % 24;
		long diffDays = difference / (24 * 60 * 60 * 1000);

		// String elapsedTime = diffHours + " hrs, " + diffMinutes + " min, " +
		// diffSeconds + " sec" ;
		String elapsedTime = diffMinutes + "m, " + diffSeconds + "s";
		return elapsedTime;
	}

	public static int convertToMilliSeconds(int seconds) {
		return seconds * 1000;
	}

}
