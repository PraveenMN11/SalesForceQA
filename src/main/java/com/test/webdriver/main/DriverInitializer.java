package com.test.webdriver.main;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.utils.EnvTaskManager;
import com.test.webdriver.utils.TimeManager;

/**
 * @author TY
 * @throws   
 * @category
 * 
 */

public class DriverInitializer {
	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private static DriverInitializer driverInitializerInstance = null;
	private static OS os = OS.OTHER;
	public enum OS {
        WINDOWS,
        MAC,
        OTHER;

        private String version;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
	
	private DriverInitializer() {}

	/** The WebDriver object */
	protected WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

		public static DriverInitializer getDriverInitializerInstance(){
			if(driverInitializerInstance==null){
				driverInitializerInstance = new DriverInitializer();
			}
			return driverInitializerInstance;
		}

		public WebDriver getAppropriateDriver(String browsertype, String location) throws Exception {
			// WebDriver driver = null;
			if (browsertype.equalsIgnoreCase("firefox")) {
				EnvTaskManager.killProcess("geckodriver.exe");
				EnvTaskManager.killProcess("firefox.exe");
				TimeManager.waitInSeconds(5);
				System.setProperty("webdriver.gecko.driver", location + "/geckodriver.exe");
				DesiredCapabilities caps= new DesiredCapabilities();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk" , "application/octet-stream;application/csv;text/csv;application/vnd.ms-excel;application/zip;application/x-zip;application/x-zip-compressed;application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"); 
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.showWhenStarting",false);
				profile.setPreference("browser.download.folderList", 1); 
				
			} else if (browsertype.equalsIgnoreCase("Chrome")) {
				/*EnvTaskManager.killProcess("chromedriver.exe");
				EnvTaskManager.killProcess("chrome.exe");*/
				TimeManager.waitInSeconds(5);
				
				try {
		            String osName = System.getProperty("os.name");
		            if (osName == null) {
		                throw new IOException("os.name not found");
		            }
		            osName = osName.toLowerCase(Locale.ENGLISH);
		            if (osName.contains("windows")) {
		                os = OS.WINDOWS;
		                System.setProperty("webdriver.chrome.driver", location + "/chromedriver.exe");
		            } else if (osName.contains("mac os")) {
		                os = OS.MAC;
		                System.setProperty("webdriver.chrome.driver", location + "chromedriver 3");
		            }
		            else {
		                os = OS.OTHER;
		            }
		        } finally {
		            os.setVersion(System.getProperty("os.version"));
		        }
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				
				//driver = new HtmlUnitDriver();
			} else if (browsertype.equalsIgnoreCase("IE")) {
				EnvTaskManager.killProcess("iexplore.exe", "mshta.exe", "IEDriverServer.exe");
				TimeManager.waitInSeconds(5);
			}
			

			if (! browsertype.equalsIgnoreCase("firefox")) {
				driver.manage().window().maximize();
			}
			//driver.manage().deleteAllCookies();

			//logger.debug("is the browser session id null " + ((RemoteWebDriver) driver).getSessionId().toString());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

			return driver;
		}

		public void triggerURL(String URL) {
			if (driver != null) {
				try {
					driver.get(URL);
					System.out.println("Trigger URL: URL is loaded successfully");
					logger.debug("Trigger URL: URL is loaded successfully");
				} catch (Exception e) {
					System.out.println("Trigger URL: URL load failed");
					logger.debug("Trigger URL: URL load failed");
					e.printStackTrace();
				}

			} else {

			}
		}

		public void closeAllBrowsers() {
			try {
				if (driver != null) {
					driver.quit();
					logger.debug("Close Browser : Closing the browser");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void deleteAllCookies() {

			if (driver != null) {
				driver.manage().deleteAllCookies();
			} else {

			}
		}

	}
