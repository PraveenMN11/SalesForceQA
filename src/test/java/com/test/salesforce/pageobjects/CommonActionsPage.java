package com.test.salesforce.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;
import com.test.webdriver.utils.TimeEntity;

import org.junit.Assert;

public class CommonActionsPage extends PageObject {

	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	public String genaratedId;

	final By lablRoleAssgnmt = By.xpath("//div[contains(text(),'Role Assignment')]");
	final By btnAddRoles = By.xpath("//button[@ ng-click='addRole()']");
	final By btnRolePageNext=By.id("gotoAddressbtnId");
	final By btnRolePageBack =By.id("backtoCardOnebtnId");
	final By stusTitle=By.id("successPageTitleId");
	final By valGenaratedId=By.id("valueIdlabelId");
	final By valStatus=By.id("statuslabelId");
	final By alertBox = By.xpath("//md-dialog-content");
	final By altFailureMessage=By.xpath("//h2/following-sibling::div ");
	final By alrtOk=By.xpath("//span[text()='Ok']");

	@Override
	protected void isLoaded() throws Error {
		logger.debug("CommonPage {} isLoaded() ", this.getClass().getName());
		this.init();
	}

	@Override
	protected void load() {
		logger.debug("CommonPage {} load() ", this.getClass().getName());
		String lableRole=getText(lablRoleAssgnmt);
		waitUntilLoadedAndTextPresentInElement(lablRoleAssgnmt,lableRole);
	}

	public void roleAssignment(List<String> lstRoles) throws InterruptedException{

		//Select list of roles	check boxes
		for (String role : lstRoles) {
			String strXpathRole = "//label[text()='";
			strXpathRole = strXpathRole + role + "']";
			By xpathRole = By.xpath(strXpathRole);  
			waitUntilElementPresent(xpathRole);
			jsClick(xpathRole);
		}
		//Add all the selected roles
		waitUntilElementPresent(btnAddRoles);
		jsClick(btnAddRoles);
		//Validate List of Roles added to Assigned Roles
		for (String role : lstRoles) {
			String strXpathRole = "//label[text()='";
			strXpathRole = strXpathRole + role + "']";
			By xpathRole = By.xpath(strXpathRole);  
			waitUntilElementPresent(xpathRole);
			String roleLabel=getText(xpathRole);
			Assert.assertEquals("Assigned Roles"+" "+"<"+ role+">"+" "+"is added" ,role, roleLabel);


		}
		click(btnRolePageNext,TimeEntity.SEC_10.getSeconds());
	}

	public String messageValidation(){
		waitUntilElementPresent(stusTitle);
		String msg=getText(stusTitle);
		return msg;
	}
	public boolean statusValidation(String strStatus){
		//Get the Agent ID value
		genaratedId=getText(valGenaratedId);
		//Get the Status value
		waitUntilElementPresent(valStatus);
		strStatus=getText(valStatus);
		return true;

	}
	//Get the failure message from alert box 
	public String failureMessage(){
		waitUntilElementPresent(alertBox);
		String failureMsg= getText(altFailureMessage);
		click(alrtOk,TimeEntity.SEC_10.getSeconds());
		return failureMsg;

	}
}


