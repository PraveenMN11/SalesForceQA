package com.test.webdriver.main;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.test.webdriver.utils.TimeEntity;
import com.test.webdriver.utils.TimeManager;

/**
 * @author TY
 * @throws   
 * @category
 * 
 */

public abstract class PageObject extends LoadableComponent<PageObject> {
	final static Logger logger = LoggerFactory.getLogger(PageObject.class);

	DriverInitializer driverInitializer = DriverInitializer.getDriverInitializerInstance();

	public WebDriver driver;
	public void init() {
		this.driver = driverInitializer.getDriver();
		PageFactory.initElements(driver, this);
	}

	// ================================================================================
	// Start of Selenium core wrapper methods
	// ================================================================================
	
	public void closeAlert() {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		if (driver.switchTo().alert() != null) {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}
	}

	public void click(By byLocator) {
		WebElement element = findElement(byLocator);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.click();
	}

	public void click(WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement();
			return;
		}
		element.click();
	}

	public void click(By byLocator, int maxWaitTime) {
		WebElement element = findElement(byLocator, maxWaitTime);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.click();
	}

	public void submit(By byLocator) {
		WebElement element = findElement(byLocator);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.submit();
	}

	public void submit(By byLocator, int maxWaitTime) {
		WebElement element = findElement(byLocator, maxWaitTime);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.submit();
	}

	public void type(String inputText, By byLocator) {
		WebElement element = findElement(byLocator);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.sendKeys(inputText);
	}

	public void type(String inputText, WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement(element);
			return;
		}
		element.sendKeys(inputText);
	}

	public void clearAndType(String inputText, By byLocator) {
		clear(byLocator);
		type(inputText, byLocator);
	}

	public void clearAndType(String inputText, WebElement element) {
		clear(element);
		type(inputText, element);
	}

	public void clear(By byLocator) {
		WebElement element = findElement(byLocator);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.clear();
	}

	public void clear(WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement(element);
			return;
		}
		element.clear();
	}

	public boolean isDisplayed(By byLocator) {
		return isElementCurrentlyDisplayed(byLocator);
	}

	public boolean isDisplayed(By byLocator, int maxWaitTime) {
		for (int waitSoFar = 1; waitSoFar < maxWaitTime; waitSoFar++) {
			if (isElementCurrentlyDisplayed(byLocator)) {
				return true;
			}
			TimeManager.waitInSeconds(TimeEntity.SEC_1.getSeconds()); // Wait 1 second and try again.
		}
		if (isElementCurrentlyDisplayed(byLocator)) {
			return true;
		}
		return false;
	}

	private boolean isElementCurrentlyDisplayed(By byLocator) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if ((element != null) && element.isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isEnabled(By byLocator) {
		return isElementCurrentlyEnabled(byLocator);
	}

	public boolean isEnabled(By byLocator, int maxWaitTime) {
		for (int waitSoFar = 1; waitSoFar < maxWaitTime; waitSoFar++) {
			if (isElementCurrentlyEnabled(byLocator)) {
				return true;
			}
			TimeManager.waitInSeconds(TimeEntity.SEC_1.getSeconds()); // Wait 1 second and try again.
		}
		if (isElementCurrentlyEnabled(byLocator)) {
			return true;
		}
		return false;
	}

	private boolean isElementCurrentlyEnabled(By byLocator) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if ((element != null) && element.isEnabled()) {
			return true;
		}
		return false;
	}

	public boolean isSelected(By byLocator) {
		WebElement element = findElement(byLocator, 0);
		if ((element != null) && element.isSelected()) {
			return true;
		}
		return false;
	}

	public String getAttribute(By byLocator, String attribute) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return "";
		}
		return element.getAttribute(attribute);
	}

	public String getText(By byLocator) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return "";
		}
		return element.getText();
	}

	public String getText(WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement();
			return "";
		}
		return element.getText();
	}

	public WebElement findElement(By byLocator) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		WebElement element;
		if (!isElementEnabledWithinWait(byLocator, 0)) {
			return null;
		}
		element = driver.findElement(byLocator);
		return element;
	}

	public WebElement findElement(By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		WebElement element;
		if (!isElementEnabledWithinWait(byLocator, maxWaitTime)) {
			return null;
		}
		element = driver.findElement(byLocator);
		return element;
	}

	private boolean isElementEnabledWithinWait(By byLocator, int maxWaitTime) {
		if (isWaitForSuccessful(ExpectedConditions.elementToBeClickable(byLocator), maxWaitTime)) {
			return true;
		}
		return false;
	}

	public WebElement findElementThatIsVisible(By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		WebElement element;
		if (!isElementVisibleWithinWait(byLocator, maxWaitTime)) {
			return null;
		}
		element = driver.findElement(byLocator);
		return element;
	}

	public WebElement findElementThatIsPresent(final By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxWaitTime, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS);

		try {
			return wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriver) {
					List<WebElement> elems = driver.findElements(byLocator);
					if (elems.size() > 0) {
						return elems.get(0);
					} else {
						return null;
					}
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Element found via byLocator can be seen on the page.
	 */
	private boolean isElementVisibleWithinWait(By byLocator, int maxWaitTime) {
		if (isWaitForSuccessful(ExpectedConditions.visibilityOfElementLocated(byLocator), maxWaitTime)) {
			return true;
		}
		return false;
	}

	public List<WebElement> findElements(By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		List<WebElement> result;
		findElement(byLocator, maxWaitTime); // Look for the default first one
												// of the collection.
		result = driver.findElements(byLocator);
		return result;
	}

	public List<WebElement> findElements(By byLocator) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		List<WebElement> result;
		findElement(byLocator); // Look for the default first one of the
								// collection.
		result = driver.findElements(byLocator);
		return result;
	}

	private boolean isWaitForSuccessful(ExpectedCondition<WebElement> condition, Integer maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		if (maxWaitTime == null) {
			maxWaitTime = 3;
		}
		WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
		try {
			wait.until(condition);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	// ================================================================================
	// End of Selenium core wrapper methods
	// ================================================================================

	// ================================================================================
	// Start of wait methods
	// ================================================================================

	public void waitUntilLoadedAndElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, TimeEntity.SEC_30.getSeconds());
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

	public void waitUntilLoadedAndElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, TimeEntity.SEC_30.getSeconds());
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitUntilElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, TimeEntity.SEC_30.getSeconds());
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitUntilLoadedAndTextPresentInElement(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, TimeEntity.SEC_30.getSeconds());
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	// ================================================================================
	// End of wait methods
	// ================================================================================
		
	// ================================================================================
	// Start of page action methods
	// ================================================================================

	public void hoverAndClick(WebElement mainElement, WebElement subElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(mainElement).moveToElement(subElement).click().build().perform();
	}

	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).doubleClick().perform();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	// ================================================================================
	// End of page action methods
	// ================================================================================	
		
	// ================================================================================
	// Start of page get/set data methods
	// ================================================================================

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	// ================================================================================
	// End of page get/set data methods
	// ================================================================================
		
	// ================================================================================
	// Start of js helper methods
	// ================================================================================
	
	public void jsClick(By byLocator){
		WebElement element = findElement(byLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	
	
	// ================================================================================
	// End of js helper methods
	// ================================================================================
	
	// Logging
	// ================================================================================

	protected void logErrorForNotFindingElement(By byLocator) {
		logger.error("Could not find element based on locator: " + byLocator.toString());
	}

	protected void logErrorForNotFindingElement(WebElement element) {
		//logger.error("Could not find element based on locator: " + getBy("element").toString());
	}

	protected void logErrorForNotFindingElement() {
		logger.error("Element is null and can not be acted upon");
	}

	protected void throwNullPointerExeptionForNullDriver() {
		throw new NullPointerException(
				"The Driver object you are using is null.  Please make sure you are passing the correct driver instance into the PageObject.");
	}
	public void switchToFrame(int Index) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are " + numberOfFrames);

		//By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		System.out.println("The total number of iframes are " + iframeElements.size());
		driver.switchTo().frame(0);
}
 
	public void switchToWindowURL(String expectedURL) {
		Set<String> handleID = driver.getWindowHandles();
		Iterator<String> it=handleID.iterator();
		String actualURL=null;
		
		first:
		while(it.hasNext())
		{
			driver.switchTo().window(it.next());
			actualURL=driver.getCurrentUrl();
			
			if(actualURL.contains(expectedURL))
			{
				break first;
			}
		}
	}
		

}
