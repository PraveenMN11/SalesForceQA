package com.test.salesforce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;


public class LogoutPage extends PageObject {

	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	
	@FindBy(css=".bare.branding-userProfile-button")
	private WebElement iconProfile;
	
	@FindBy(xpath="//div[@class='oneUserProfileCard']/div[2]/div/a[@href='/secur/logout.jsp']")
	private WebElement logoutTab;
	
	@Override
	protected void isLoaded() throws Error {
		logger.debug("LogoutPage {} isLoaded() ", this.getClass().getName());
		this.init();
	}

	@Override
	protected void load() {
		logger.debug("LogoutPage {} load() ", this.getClass().getName());
		// ASSERT -  CHECK WHETHER THE PAGE HAS LOADED
	}
	
	public void logOut() throws InterruptedException {
		Thread.sleep(2000);
		iconProfile.click();
		Thread.sleep(3000);
		logoutTab.click();
	}

}

