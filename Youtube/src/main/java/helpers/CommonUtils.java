package helpers;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUtils {

	protected WebDriver driver;
	private static final int TIMEOUT_IN_SECONDS = 60;
	private static final int POLL_INTERVAL = 500;

	
	public <U> U wait(ExpectedCondition<U> condition) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(this.driver).ignoring(RuntimeException.class)
				.withTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).pollingEvery(POLL_INTERVAL, TimeUnit.MILLISECONDS);
		try {
			return wait.until(condition);
		} catch (TimeoutException err) {
			String errMessage = "Bot encountered a timeout while waiting for a condition,  "
					+ err.getLocalizedMessage();
			throw new Exception(errMessage);
		}
	}

	protected void clearText(String elementLocator) throws Exception {
	    By locator = ObjectLocators.getBySelector(elementLocator);
	    WebElement element = driver.findElement(locator);
	    if (element.isDisplayed()) { 
	      element.clear();
	    }
	  }


	private boolean isElementPresent(By by) {
		try {
			WebElement element = driver.findElement(by);
			if (element != null) {
				System.out.println("Element is present: " + by.toString());
				return true;
			}
			System.out.println("Element is NOT present: " + by.toString());
			return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean isElementPresent(String propKey) throws Exception {
		By locator = ObjectLocators.getBySelector(propKey);
		System.out.println("Checking the presence of the Element: " + propKey + " : " + propKey);
		return isElementPresent(locator);
	}
	
	protected List<WebElement> getSimilarElements(String elementLocator) throws Exception {
		By locator = ObjectLocators.getBySelector(elementLocator);
		System.out.println("Getting Similar elements with locator \" + elementLocator + \" )");
		return driver.findElements(locator);
	}
	

	protected boolean isElementClickable(String elementLocator) {
		try {
			By locator = ObjectLocators.getBySelector(elementLocator);
			WebElement element = driver.findElement(locator);
			String result = element.getCssValue("cursor");
			if (result.equals("pointer")) {
				System.out.println("Element is Clickable");
				return true;
			}
			System.out.println("Element is NOT Clickable");
			return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected void clickOnElement(String elementLocator) throws Exception {
		By locator = ObjectLocators.getBySelector(elementLocator);
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			element.click();
			System.out.println(("Clicked on element: " + locator));
		}
	}

	public static ExpectedCondition<WebElement> elementToBeClickable(final String elementLocator) throws Exception {
		final By locator = ObjectLocators.getBySelector(elementLocator);
		System.out.println("Waiting until the element " + locator + " becomes clickable");
		return ExpectedConditions.elementToBeClickable(locator);
	}

	protected void type(String elementLocator, String text) {
		System.out.println("Entering Text");
		WebElement element;
		try {
			element = wait(elementToBeClickable(elementLocator));
			Thread.sleep(1000);
			element.sendKeys(text);
			Thread.sleep(1000);
			System.out.println("Entered " + text + " into the " + elementLocator + " text field");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToUrl(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("Loading the URL:" + url);
		this.driver.get(url);
	}
	
	@AfterTest
	public void closeBowser() {
		driver.quit();
	}
	
}
