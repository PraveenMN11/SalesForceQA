package com.test.salesforce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;
import com.test.webdriver.utils.TimeEntity;


public class ContactPage extends PageObject {
	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	final static By clkTabContact = By.xpath("//a[@title='Contacts']/span");
	final static By btnNew = By.xpath("//div[@title='New']");
	final static By drpDownSalutation = By.xpath("(//a[@class='select'])[1]");
	final static By valueSalutation = By.xpath("(//div[@class='select-options']/ul/li[2]/a)");
	final static By txtFirstname = By.xpath("//input[@placeholder='First Name']");
	final static By txtLastName = By.xpath("//input[@placeholder='Last Name']");
	final static By txtMailingStreet = By.xpath("//textarea[@placeholder='Mailing Street']");
	final static By txtLanuages = By.xpath("//h3[span[text()='Additional Information']]/following-sibling::div/div/div/div/div/div/div/label/following-sibling::input");
	final static By txtDesc = By.xpath("//h3[span[text()='Description Information']]/following-sibling::div/div/div/div/div/div/div/label/following-sibling::textarea");
	final static By btnsave = By.xpath("//button[@title='Save']");
	final static By validateContact = By.xpath("//div[@class='testonly-outputNameWithHierarchyIcon sfaOutputNameWithHierarchyIcon']/span[1]");

	@Override
	protected void isLoaded() throws Error {
		logger.debug("ContactPage {} isLoaded() ", this.getClass().getName());
		this.init();
	}

	@Override
	protected void load() {
		logger.debug("ContactPage {} load() ", this.getClass().getName());
		// ASSERT -  CHECK WHETHER THE PAGE HAS LOADED
	}
	public void clickContactTab()
	{
		waitUntilLoadedAndElementClickable(clkTabContact);
		click(clkTabContact);
	}
	public void createNewRecordBtn()
	{
		waitUntilLoadedAndElementClickable(btnNew);
		click(btnNew);
	}
	public void contactInfoFields() throws InterruptedException
	{
		click(drpDownSalutation,TimeEntity.SEC_2.getSeconds());
		click(valueSalutation,TimeEntity.SEC_2.getSeconds());
		waitUntilElementPresent(txtFirstname);
		type("Priyanka", txtFirstname);
		waitUntilElementPresent(txtLastName);
		type("tester", txtLastName);
		TimeEntity.SEC_2.getSeconds();
	}
	
	
	public void addressInfoFields() throws InterruptedException
	{
		waitUntilElementPresent(txtMailingStreet);
		type("AVBVBBC", txtMailingStreet);
	}
	
	public void additionalInfoFields() throws InterruptedException
	{
		waitUntilElementPresent(txtLanuages);
		type("English", txtLanuages);
	}
	
	public void descriptionInfoFields() throws InterruptedException
	{
		waitUntilElementPresent(txtDesc);
		type("Description", txtDesc);
	}
	public void clicksave() throws InterruptedException
	{
		click(btnsave,TimeEntity.SEC_2.getSeconds());
	}
	public void validateContact() throws InterruptedException
	{
		Thread.sleep(5000);
		//validateContact.getText();
		
	}
	
}
