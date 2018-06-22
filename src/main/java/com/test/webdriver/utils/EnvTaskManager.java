package com.test.webdriver.utils;

import java.io.IOException;

/**
 * @author TY
 * @throws   
 * @category
 * 
 */

public class EnvTaskManager {

	/**
	 * This method kills the service mentioned in the argument on the command prompt.
	 * @param service to be killed.
	 */
	@SuppressWarnings("unused")
	public static void killProcess(String... serviceName) throws Exception {
		int len = serviceName.length;
		for (int i = 0; i < len; i++) {
			if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
				Process p = Runtime.getRuntime().exec("cmd /c taskkill  /F /IM " + serviceName[i]);
			}
			else {
				Process p = Runtime.getRuntime().exec("kill -9 "+serviceName[i]);
			}

		}
	}
}

/**
 * This method executes the command mentioned in the argument on the command prompt .
 * @param command to be executed.
 */
/*public static void execDosCommand(String cmd) {
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			System.out.println("exec cmd " + cmd);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/


