package com.test.salesforce.pageobjects;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.salesforce.dataobjects.LoginUserData;
import com.test.salesforce.resources.User;
import com.test.webdriver.main.PageObject;
import com.test.webdriver.utils.TimeEntity;


public class LoginPage extends PageObject {

	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	final static By txtUsername = By.id("username");
	final static By txtPassword = By.id("password");
	final static By btnSubmit = By.id("Login");
	final static By valHome = By.xpath("//span[@class='breadcrumbDetail uiOutputText']");

	@Override
	protected void isLoaded() throws Error {
		logger.debug("LoginPage {} isLoaded() ", this.getClass().getName());
		this.init();
	}

	@Override
	protected void load() {
		logger.debug("LoginPage {} load() ", this.getClass().getName());
		// ASSERT - CHECK WHETHER THE PAGE HAS LOADED
	}

	public void login(String userType) {
		LoginUserData loginUserData=User.getUser(userType);
		waitUntilElementPresent(txtUsername);
		type(loginUserData.getUserName(),txtUsername);
		waitUntilElementPresent(txtPassword);
		type(loginUserData.getPassword(),txtPassword);
		click(btnSubmit,TimeEntity.SEC_10.getSeconds());
	}
}
