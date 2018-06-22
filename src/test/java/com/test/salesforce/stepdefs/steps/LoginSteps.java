package com.test.salesforce.stepdefs.steps;

import com.test.salesforce.stepdefs.BaseSteps;
import com.test.webdriver.utils.PropertyFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginSteps extends BaseSteps {

	String appURL = PropertyFileReader.getProperty("app.url"); 

	@Given("^I am logged in as \"([^\"]*)\"$")
	public void i_am_logged_in_as(String userType) throws Throwable {
		openBrowser(appURL);
		loginPage = getLoginPage();		
		loginPage.login(userType);
	}
	
	@When("^SalesForce home page appears$")
	public void SalesForce_home_page_appears() throws Throwable {
       System.out.println("Done");
	}
	
	@Then("^i logged out of application$")
	public void i_logged_out_of_application() throws Throwable {
		logoutPage = getLogoutPage();
	    logoutPage.logOut();
	}
}

