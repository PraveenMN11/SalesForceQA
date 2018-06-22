package com.test.webdriver.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author TY
 * @throws @category
 * 
 */

public class DataManager {

	/**
	 * This method generates a random string [A-Za-z] of random length
	 * 
	 * @return String - random string.
	 */
	public static String randomIdentifier() {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is
		// being used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++)
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			if (identifiers.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}

	public static String randomIdentifier(int length) {
		final String lexicon = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rand = new SecureRandom();
		final Set<String> identifiers = new HashSet<String>(length);
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		
		return builder.toString();
	}

	/**
	 * This method generates a random 9 digits between[0-9]
	 * 
	 * @return String - random 9 digits string.
	 */
	public static String randomNumberGenerator() {
		int max = 999999999; // e.g. 714053349 (9 digits)
		Random rand = new Random();
		int min = 0;
		int randomNum = rand.nextInt(max - min + 1) + min;
		return Integer.toString(randomNum);
	}

	public static String randomNumberGenerator(int length) {
		StringBuilder builder = new StringBuilder(length);
		final java.util.Random rand = new java.util.Random();
		for (int i = 0; i < length; i++) {
			builder.append((char) ('0' + rand.nextInt(10)));
		}
		return builder.toString();

	}

}
