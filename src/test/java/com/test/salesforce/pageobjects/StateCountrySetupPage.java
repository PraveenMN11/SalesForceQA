package com.test.salesforce.pageobjects;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.webdriver.main.PageObject;
import com.test.webdriver.utils.TimeEntity;

/**
 * 
 * @author TY
 *
 */

public class StateCountrySetupPage extends PageObject {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public String labelKeys=null;
	public String btnName=null;
	public String countryName=null;
	
	final By lnkConfigureStates = By.xpath("//a[contains(text(),'Configure states and countries')]");
	By lnkEditBtn;
	final By btnNewState = By.xpath("//input[@value='New State']");
	final By lnkTerritories = By.xpath("//a[text()='Configure States and Countries and Territories']");
	final By labelCountry = By.xpath("//label[contains(text(),'Country')]/../..//span");
	By inputCountryName=By.xpath("//label[contains(text(), 'Country/Territory Name')]/../../..//input");
	By inputStateName=By.xpath("//label[contains(text(), 'State Name')]/../../..//input");
	By inputStateCode=By.xpath("//label[contains(text(), 'State Code')]/../../..//input");
	By chkBoxActive = By.xpath("//label[contains(text(), 'Active')]/../../..//input");
	By chkBoxVisible = By.xpath("//label[contains(text(), 'Visible')]/../../..//input");
	final By btnAdd = By.xpath("//input[@value='Add']");
	final By btnCancel = By.xpath("//input[@value='Cancel']");
	final By newStateText = By.xpath("//h2[text()='New State']");
	
	@Override
	protected void isLoaded() throws Error {
		logger.debug("StateCountrySetup {} isLoaded() ", this.getClass().getName());
		this.init();
		
	}
	@Override
	protected void load() {
		logger.debug("StateCountrySetup {} load() ", this.getClass().getName());
	}
	
	
	/**
	 * <b>performs the following tasks</b><br>
	 * takes country name from application<br>
	 * matches country name from application with excel values<br>
	 * 
	 * 
	 */
	
	//to click on configure states and countries
	public void clickConfigureStateCountries() throws InterruptedException
	{
		TimeEntity.SEC_2.getSeconds();
		driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='State and Country/Territory Picklists ~ Salesforce - Enterprise Edition']")));
		waitUntilLoadedAndElementClickable(lnkConfigureStates);
		click(lnkConfigureStates);
	}
	
	public void clickEditBtn(String countryName)
	{
		try {
			this.countryName=countryName;
			System.out.println(this.countryName);
			lnkEditBtn=By.xpath("//table[@class='list']/tbody/tr/td[4]/span[text()='"+countryName+"']/../preceding-sibling::td[3]/a");
			
			driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='Configure States and Countries and Territories ~ Salesforce - Enterprise Edition']")));
			waitUntilElementPresent(lnkEditBtn);
			click(lnkEditBtn);
			
		} catch (Exception e) {
			System.out.println("Country fetched from excel is not present in application");
			e.printStackTrace();
		}
		
	}
	
	public void stateCountryMapping()
	{
		FileInputStream fis=null;
		Workbook wb=null;
		try {
			fis=new FileInputStream("./config/LAC_States.xlsx");
			wb=WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet=wb.getSheet("Sheet1");
		
		//saving all country names in arraylist present in excel
		ArrayList<String> countriesName=new ArrayList<>();
		int rowNum=sheet.getLastRowNum();
		System.out.println("row num = "+rowNum);
		for(int i=0; i<=rowNum;i++)
		{
			countriesName.add(sheet.getRow(i).getCell(2).toString());
		}
		logger.info(Arrays.toString(countriesName.toArray()));
		
		//tasks
		for(int i=0;i<=rowNum;i++)
		{
			//clicking on edit button based on the country name from excel
			clickEditBtn(countriesName.get(i));
			logger.info("outer loop country name= "+countriesName.get(i));
			
			driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='Configure States and Countries and Territories ~ Salesforce - Enterprise Edition']")));
			waitUntilLoadedAndElementClickable(btnNewState);
			
			//fetching the country name
			String countryName=getAttribute(inputCountryName, "value");
			
			while(i<=rowNum && countryName.equals(countriesName.get(i)))
			{
					click(btnNewState);
					
					driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='Configure States and Countries and Territories ~ Salesforce - Enterprise Edition']")));
					waitUntilLoadedAndElementClickable(newStateText);
					
					//typing state name
					waitUntilLoadedAndElementVisible(inputStateName);
					type(sheet.getRow(i).getCell(0).toString(), inputStateName);
					
					//typing state code
					waitUntilLoadedAndElementVisible(inputStateCode);
					type(sheet.getRow(i).getCell(1).toString(), inputStateCode);
					
					//Click on active checkbox
					waitUntilLoadedAndElementClickable(chkBoxActive);
					click(chkBoxActive);
					
					//Click on visible checkbox
					TimeEntity.SEC_2.getSeconds();
					waitUntilLoadedAndElementClickable(chkBoxVisible);
					click(chkBoxVisible);
					
					//click on add button
					click(btnAdd);
					
					
					try {
						driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='Configure States and Countries and Territories ~ Salesforce - Enterprise Edition']")));
						waitUntilLoadedAndElementClickable(btnNewState);
					}
					catch (Exception e) {
						logger.info("State is already added");
						click(btnCancel);
					}
					try {
						driver.switchTo().frame((WebElement) driver.findElement(By.xpath(".//iframe[@title='Configure States and Countries and Territories ~ Salesforce - Enterprise Edition']")));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					waitUntilLoadedAndElementClickable(btnNewState);
					//fetching the country name
					countryName=getAttribute(inputCountryName, "value");
					
					i++;
			}
			i--;
			click(lnkTerritories);	
			
		}
		
		
	}

}
