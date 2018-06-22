package com.test.salesforce.cukes;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.test.salesforce.resources.TestConstants;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
		tags = { "@StateSetUp" },
		features = { "src/test/resources"}, 
		plugin = {"html:target/test-report"}, 
		glue = { "com.test.salesforce.stepdefs" })
public class RunCukesTest {

	@BeforeClass
	public static void init() {
		System.setProperty("driverLibDir", System.getProperty("user.dir").concat("/lib"));
		System.setProperty("browserType", "chrome");
		//Reports
		System.setProperty("customReportDir", System.getProperty("user.dir").concat("/customReports"));
		System.setProperty("log.path", System.getProperty("user.dir").concat("/target/log4j"));
		System.setProperty("log.level", "DEBUG");
						
	}

	@AfterClass
	public static void finish() {

	}

}
