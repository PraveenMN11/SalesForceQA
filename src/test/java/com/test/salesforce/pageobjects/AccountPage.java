package com.test.salesforce.pageobjects;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;
import com.test.webdriver.utils.TimeEntity;

public class AccountPage extends PageObject{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	final static By clkTabAccount = By.xpath(".//a[@title='Accounts']/span");
	final static By newAccBtn = By.xpath("//div[@title='New']");
	final static By selectStdRecordType = By.xpath("//span[contains(text(),'Standard Account')]/ancestor::div/ancestor::label/div[1]/span");
	final static By selectDistributorRecordType = By.xpath("//span[contains(text(),'Distributors')]/ancestor::div/ancestor::label/div[1]/span");
	final static By selectEndUserRecordType = By.xpath("//span[contains(text(),'End-Users')]/ancestor::div/ancestor::label/div[1]/span");
	final static By selectGlobalRecordType = By.xpath("//span[contains(text(),'Global Account')]/ancestor::div/ancestor::label/div[1]/span");
	final static By clkNextBtn = By.xpath("//div[@class='modal-footer slds-modal__footer']/div/button[2]/span");
	final static By drpDownType = By.xpath("(.//span[contains(text(),'Type')]/ancestor::span/following-sibling::div//a[@class='select'])[1]");
	final static By  typeValue = By.xpath("//div[@class='select-options']/ul/li[3]/a[contains(text(),'Healthcare')]");
	final static By accName = By.xpath("(.//span[contains(text(),'Account Name')]/ancestor::label/following-sibling::input)[1]");
	final static By drpDownStatus = By.xpath(".//span[contains(text(),'Status')]/ancestor::span/following-sibling::div//a[@class='select']");
	final static By statusValue = By.xpath("//div[@class='select-options']/ul/li[2]/a[contains(text(),'Active')]");
	final static By phoneNoInput = By.xpath(".//span[contains(text(),'Phone')]/ancestor::label/following-sibling::input");
	final static By drpdownbillingCtryCode = By.xpath(".//span[contains(text(),'Billing Country Code')]/ancestor::span/following-sibling::div//a[@class='select']");
	final static By billCtryCodeValue = By.xpath("//div[@class='select-options']/ul/li[5]/a[contains(text(),'Algeria')]");
	final static By billStreetInput = By.xpath(".//span[contains(text(),'Billing Street')]/ancestor::label/following-sibling::textarea");
	final static By billZipCodeInput = By.xpath(".//span[contains(text(),'Billing Zip/Postal Code')]/ancestor::label/following-sibling::input");
	final static By btnSave = By.xpath("//button[@title='Save']");
	final static By noOfOperatingRooms = By.xpath("(.//span[contains(text(),'No. of Operating Rooms')]/ancestor::label/following-sibling::input)[1]");
	final static By drpdownIndustry = By.xpath("(.//span[contains(text(),'Industry')]/ancestor::span/following-sibling::div//a[@class='select'])[1]");		
	final static By industryValue = By.xpath("//div[@class='select-options']/ul/li[3]/a[contains(text(),'HEALTHCARE (HLT)')]");
	final static By drpdownSubIndustry = By.xpath("(.//span[contains(text(),'Sub-Industry')]/ancestor::span/following-sibling::div//a[@class='select'])[1]");
	final static By subIndustryValue = By.xpath("//div[@class='select-options']/ul/li[4]/a[contains(text(),'Blood Bank')]");
	
	@Override
	protected void isLoaded() throws Error {
		logger.debug("AccountPage {} isLoaded() ", this.getClass().getName());
		this.init();
	}

	@Override
	protected void load() {
		logger.debug("AccountPage {} load() ", this.getClass().getName());
		// ASSERT -  CHECK WHETHER THE PAGE HAS LOADED
	}
	
	public void clickAccountTab()
	{
		
		waitUntilLoadedAndElementClickable(clkTabAccount);
		click(clkTabAccount,TimeEntity.SEC_10.getSeconds());
	}
	//Click on New Button
	public void createNewRecordBtn()
	{
		waitUntilLoadedAndElementClickable(newAccBtn);
		click(newAccBtn);
	}
	//Select Standard Record type
	public void selectStdRecordType() {
		waitUntilLoadedAndElementClickable(selectStdRecordType);
		click(selectStdRecordType);
	}
	//Select Distributors Record type
	public void selectDistributorRecordType() {
		waitUntilLoadedAndElementClickable(selectDistributorRecordType);
		click(selectDistributorRecordType);
	}
	//Select End-Users Record type
	public void selectEndUserRecordType() {
		waitUntilLoadedAndElementClickable(selectEndUserRecordType);
		click(selectEndUserRecordType);
	}
	//Select Global Account Record type
	public void selectGlobalAccountRecordType() {
		waitUntilLoadedAndElementClickable(selectGlobalRecordType);
		click(selectGlobalRecordType);
	}
	
	public void clickNextBtn()
	{
		waitUntilLoadedAndElementClickable(clkNextBtn);
		//jsClick(clkNextBtn);
		click(clkNextBtn,TimeEntity.SEC_2.getSeconds());
	}
	//ENter Anse Account Management Information
	public void enterAccManagementInfo() throws InterruptedException
	{
		type("15/6 Standard Acc", accName);
		Thread.sleep(2000);
		click(drpDownType);
		waitUntilLoadedAndElementClickable(typeValue);
		click(typeValue);
		
		click(drpDownStatus);
		waitUntilLoadedAndElementClickable(statusValue);
		click(statusValue);
		
		click(drpdownIndustry);
		waitUntilLoadedAndElementClickable(industryValue);
		click(industryValue);
		
		click(drpdownSubIndustry);
		waitUntilLoadedAndElementClickable(subIndustryValue);
		click(subIndustryValue);
		
		
		
	}
	
	public void enterAddrAndContactInfo() throws InterruptedException{
		
		type("4578963", phoneNoInput);
		Thread.sleep(2000);
		click(drpdownbillingCtryCode);
		Thread.sleep(2000);
		click(billCtryCodeValue);
		Thread.sleep(2000);
		type("Bakers Street", billStreetInput);
		Thread.sleep(2000);
		type("741258", billZipCodeInput);
	}
	
	public void enterHealtCareSpecificInfo() throws InterruptedException{
		
		type("741", noOfOperatingRooms);
		
	}
	
	public void clickSaveBtn()
	{
		waitUntilLoadedAndElementClickable(btnSave);
		click(btnSave);
	}
	
	

}
