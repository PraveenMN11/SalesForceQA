package com.test.salesforce.stepdefs.steps;

import java.util.List;
import java.util.Map;

import com.test.salesforce.stepdefs.BaseSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountSetUp extends BaseSteps {
	
	@When("^I want to create an Account$")
	public void i_want_to_create_an_Account() throws Throwable {
		loginPage = getLoginPage();	
		accountPage=getAccountPage();
		accountPage.clickAccountTab();
		accountPage.createNewRecordBtn();
	}

	@When("^I select the \"([^\"]*)\"$")
	public void i_select_the(DataTable recordTypes) throws Throwable {
		List<List<String>> data = recordTypes.cells(0);
		accountPage.selectEndUserRecordType();
	}
 
	/*@When("^I select the End User Record Type$")
	public void i_select_the_End_User_Record_Type() throws Throwable {
		accountPage.selectEndUserRecordType();	    
	}*/

	@When("^i click to Next Button$")
	public void i_click_to_Next_Button() throws Throwable {
		accountPage.clickNextBtn();
	}

	@When("^I enter the Ansell Account Management Information$")
	public void i_enter_the_Ansell_Account_Management_Information() throws Throwable {
		accountPage.enterAccManagementInfo();
	}

	@When("^I enter the Address and Contact information$")
	public void i_enter_the_Address_and_Contact_information() throws Throwable {
		accountPage.enterAddrAndContactInfo();
	}

	@When("^I enter the Healthcare specific fields information$")
	public void i_enter_the_Healthcare_specific_fields_information() throws Throwable {
		accountPage.enterHealtCareSpecificInfo();
	}
	
	@When("^i click on save$")
	public void i_click_on_save() throws Throwable {
		accountPage.clickSaveBtn();
	}

	@Then("^an Account with end user record type is created successfully$")
	public void an_Account_with_end_user_record_type_is_created_successfully() throws Throwable {
		logoutPage = getLogoutPage();
	    logoutPage.logOut();
	}

}
