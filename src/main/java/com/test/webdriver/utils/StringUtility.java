package com.test.webdriver.utils;

/**
 * @author TY
 * @throws   
 * @category
 * 
 */

public class StringUtility {

	public static boolean isNullOrEmpty(String string) {
		return (string == null) || string.equals("");
	}

	public static boolean isNullOrEmptyOrMatchChar(String string, String matchChar) {
		return (string == null) || string.isEmpty() || string.equals(matchChar);
	}

}
