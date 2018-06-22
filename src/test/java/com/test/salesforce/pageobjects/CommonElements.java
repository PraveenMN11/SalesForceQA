package com.test.salesforce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;

public class CommonElements extends PageObject {

final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public String labelKeys=null;
	public String btnName=null;
	public String quickFindStateText="State";
	
	final By wheelBaring = By.xpath("//span[text()='Setup']/../lightning-icon/lightning-primitive-icon");
	final By lnkSetUp = By.xpath("(//span[text()='Setup'])[2]");
	final By inputQuickFindBox = By.xpath("//input[@placeholder='Quick Find']");
	final By lnkQuickFind = By.xpath("//mark[text()='"+quickFindStateText+"']");
	
	
	@Override
	protected void isLoaded() throws Error {
		logger.debug("CommonElements {} isLoaded() ", this.getClass().getName());
		this.init();
		
	}
	@Override
	protected void load() {
		logger.debug("CommonElements {} load() ", this.getClass().getName());	
	}
	
	public void clickWheelBaring() {
		waitUntilLoadedAndElementClickable(wheelBaring);
		click(wheelBaring);
	}
	
	public void clickSetup() {
		waitUntilLoadedAndElementClickable(lnkSetUp);
		click(lnkSetUp);
		switchToWindowURL("SetupOneHome");
	} 
	
	public void inputQuickFind(String QuickFindText) {
		quickFindStateText=QuickFindText;
		waitUntilLoadedAndElementClickable(inputQuickFindBox);
		type(QuickFindText, inputQuickFindBox);
		waitUntilLoadedAndElementClickable(lnkQuickFind);
		click(lnkQuickFind);
	} 
	
}



