package com.test.salesforce.stepdefs.steps;

import com.test.salesforce.stepdefs.BaseSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StateCountrySetUp extends BaseSteps{

	@When("^I want to Add States in SetUp$")
	public void i_want_to_Add_States_in_SetUp() throws Throwable {
		commomElementsPage = getCommonElements();
		stateCountrySetUpPage = getStateCountrySetupPage();
	}

	@When("^I will navigate to SetUp$")
	public void i_will_navigate_to_SetUp() throws Throwable {
		commomElementsPage.clickWheelBaring();
		commomElementsPage.clickSetup();
	}

	@When("^I will navigate to \"([^\"]*)\"$")
	public void i_will_navigate_to(String QuickFindText) throws Throwable {
		commomElementsPage.inputQuickFind(QuickFindText);
	}

	@When("^i will configure the data for states$")
	public void i_will_configure_the_data_for_states() throws Throwable {
		stateCountrySetUpPage.clickConfigureStateCountries();
		stateCountrySetUpPage.stateCountryMapping();
	}

	@Then("^the states are added successfully$")
	public void the_states_are_added_successfully() throws Throwable {
	}

	
	/*@When("^I want to Add States in SetUp$")
	public void i_want_to_Add_States_in_SetUp() throws Throwable {
		commomElementsPage = getCommonElements();
		stateCountrySetUpPage = getStateCountrySetupPage();
	}*/

	/*@When("^I will navigate to SetUp$")
	public void i_will_navigate_to_SetUp() throws Throwable {
		commomElementsPage.clickWheelBaring();
		commomElementsPage.clickSetup();
	}*/

	/*@When("^I will navigate to \"([^\"]*)\"$")
	public void i_will_navigate_to(String QuickFindText) throws Throwable {
		commomElementsPage.inputQuickFind(QuickFindText);
	}*/	

	/*@When("^i will configure the data for states$")
	public void i_will_configure_the_data_for_states() throws Throwable {
		stateCountrySetUpPage.clickConfigureStateCountries();
		stateCountrySetUpPage.stateCountryMapping();
	}*/

	/*@Then("^the states are added successfully$")
	public void the_states_are_added_successfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}*/
}
