package com.test.salesforce.stepdefs.steps;

import com.test.salesforce.stepdefs.BaseSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ContactSetUp extends BaseSteps {
	@When("^I want to create a contact$")
	public void i_want_to_create_a_contact() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		contactPage = getContactPage();
		loginPage = getLoginPage();		
		contactPage.clickContactTab();
		contactPage.createNewRecordBtn();
	}

	@When("^I enter the contact information$")
	public void i_enter_the_contact_information() throws Throwable {
		contactPage.contactInfoFields();
		
	}
	
	@When("^I enter the address information$")
	public void i_enter_the_address_information() throws Throwable {
		contactPage.addressInfoFields();
	}
	@When("^I enter the additional information$")
	public void i_enter_the_additional_information() throws Throwable {
	    contactPage.additionalInfoFields();
	}

	@When("^I enter the description information$")
	public void i_enter_the_description_information() throws Throwable {
	    contactPage.descriptionInfoFields();
	}

	@When("^i click to save a record$")
	public void i_click_to_save_a_record() throws Throwable {
		contactPage.clicksave();
	}

	@Then("^an contact is created successfully$")
	public void an_contact_is_created_successfully() throws Throwable {
	 //   logoutPage = getLogoutPage();
	  //  logoutPage.logOut();
	}


}


