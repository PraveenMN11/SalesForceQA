package com.test.salesforce.stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.salesforce.pageobjects.AccountPage;
import com.test.salesforce.pageobjects.CommonActionsPage;
import com.test.salesforce.pageobjects.CommonElements;
import com.test.salesforce.pageobjects.ContactPage;
import com.test.salesforce.pageobjects.LoginPage;
import com.test.salesforce.pageobjects.LogoutPage;
import com.test.salesforce.pageobjects.StateCountrySetupPage;
import com.test.salesforce.resources.TestConstants;
import com.test.webdriver.main.DriverInitializer;

public class BaseSteps {

	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	DriverInitializer driverInitializer = DriverInitializer.getDriverInitializerInstance();
	public LoginPage loginPage;
	public CommonActionsPage commonActionsPage;
	public ContactPage contactPage;
	public LogoutPage logoutPage;
	public CommonElements commomElementsPage;
	public StateCountrySetupPage stateCountrySetUpPage;
	public AccountPage accountPage;

	public LoginPage getLoginPage() {
		return (LoginPage) new LoginPage().get();
	}	
	
	public ContactPage getContactPage() {
		return (ContactPage) new ContactPage().get();
	}
	
	public AccountPage getAccountPage() {
		return (AccountPage) new AccountPage().get();
	}

	public CommonActionsPage getCommonActionsPage(){
		return (CommonActionsPage) new CommonActionsPage().get();
	}
	
	public CommonElements getCommonElements(){
		return (CommonElements) new CommonElements().get();
	}
	
	public StateCountrySetupPage getStateCountrySetupPage(){
		return (StateCountrySetupPage) new StateCountrySetupPage().get();
	}
	
	public LogoutPage getLogoutPage(){
		return (LogoutPage) new LogoutPage().get();
	}
	
	public void triggerURL(String URL) {
		logger.debug("driver init :: {}", driverInitializer);
		driverInitializer.triggerURL(URL);
	}

	public void openBrowser(String URL) {
		try {
			// driverInitializer.getAppropriateDriver(browserType);
			logger.info("Launching App on browser type:: {}", TestConstants.BROWSER_TYPE);
			logger.info("URL:: {}", URL);
			driverInitializer.getAppropriateDriver(TestConstants.BROWSER_TYPE, TestConstants.DRIVER_LIB_DIR);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		triggerURL(URL);
	}

}