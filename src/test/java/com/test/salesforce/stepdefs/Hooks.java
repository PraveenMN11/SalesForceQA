package com.test.salesforce.stepdefs;


import java.util.Collection;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.DriverInitializer;
import com.test.webdriver.utils.PropertyFileReader;
import com.test.webdriver.utils.TimeManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	DriverInitializer driverInitializer = DriverInitializer.getDriverInitializerInstance();
	public WebDriver driver;
	public String testStartTime, testEndTime;

	@Before("@web")
	public void setUpWeb(Scenario scenario) {
		logger.debug("_____________  BEFORE");
		logger.info("_____________  START of Scenario : {} ", scenario.getName());
		testStartTime = TimeManager.timeNow();
		 driver = driverInitializer.getDriver();
	}
	@After("@web")
	public void webTearDown(Scenario scenario) {
		logger.debug("_____________  AFTER @Web");
		

		if (PropertyFileReader.flagSet("screenshots") && scenario.isFailed()) {
			logger.error("Scenario: {} failed ", scenario.getName());
			try {
				byte[] screenshot = ((TakesScreenshot) driverInitializer.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException wde) {
				System.err.println(wde.getMessage());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (PropertyFileReader.flagSet("closeBrowserAfterTest")) {
			this.driverInitializer.closeAllBrowsers();
		}
	}

	private void printScenarioData(Scenario scenario) {
		System.out.println("_______SCENARIO DATA ");
		System.out.println("CHECKPOINT: " + scenario.toString());
		System.out.println("SCENARIO ID: " + scenario.getId());
		System.out.println("SCENARIO NAME: " + scenario.getName());
		Collection<String> scenarioTags = scenario.getSourceTagNames();
		System.out.println("SCENARIO TAGS: " + scenarioTags.toString());
		System.out.println("STATUS: " + scenario.getStatus());
		System.out.println("SCENARIO CLASS: " + scenario.getClass());
		System.out.println("SCENARIO isFailed: " + scenario.isFailed());

	}

}