package actionDriver;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait= Integer.parseInt(BasePage.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, explicitWait); // Pass timeout as an integer or long
	}

	// Click element
	public void click(By by) {
		try {
			waitElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("unable to click element: " + e.getMessage());
		}
	}

	
	public void clickNotClickableElements(By by)
	{
		 WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	// enter text into text field
	public void enterText(By by, String value) {
		try {
			waitElementToBeVisible(by);
			// driver.findElement(by).clear();
			// driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("unable to enter text: " + e.getMessage());
		}
	}

	// get text of element
	public String getText(By by) {
		try {
			waitElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("unable to get text: " + e.getMessage());
		}
		return null;
	}

	// Compare text
	public boolean compareText(By by, String expectedText) {
		try {
			waitElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equalsIgnoreCase(actualText)) {
				System.out.println(" Texts matching " + actualText + " equals " + expectedText);
			return true;
			} else {
				System.out.println(" Texts not matching " + actualText + " not equals " + expectedText);
			return false;
			}
		} catch (Exception e) {
			System.out.println("unable to compare text: " + e.getMessage());
		}
		return false;
	}


	
	// check if element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("Elemnet is not displayed: " + e.getMessage());
			return false;
		}

	}

	// Scroll to element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0],scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println(" Unable to locate element " + e.getMessage());
		}
	}
	
	public void scrollToSpecificCoordinate(int xCoordinate, int yCoordinate) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(arguments[0], arguments[1]);", xCoordinate, yCoordinate);
	}

	public void scrollWithCoordinateY(By by) {
		WebElement element = driver.findElement(by);
		int elementPosition = element.getLocation().getY();
		 ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + elementPosition + ");");
	}
	// wait for page to load
	public void waitForPageToLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			System.out.println("Page loaded successfully ");
		} catch (Exception e) {
			System.out.println("Page did not load within " + timeOutInSec + " second. Exception" + e.getMessage());
		}
	}

	// wait element to be clickable
	public void waitElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Elemnet is not clickable: " + e.getMessage());
		}
	}

	// Wait for element to be visible
	private void waitElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Elemnet is not visible: " + e.getMessage());
		}
	}
	
	// By.cssSelector("button[aria-label='Dismiss sign-in info.']");
	
	// method to dismiss genius popup that intrrupts the flow
	public void dismissPopupIfPresent(By closeButtonLocator) {
	    try {
	        // Check if the close button of the popup is present
	        List<WebElement> closeButtons = driver.findElements(closeButtonLocator);

	        // If the button is present, click to dismiss the popup
	        if (!closeButtons.isEmpty()) {
	            closeButtons.get(0).click(); // Click the first close button found
	            System.out.println("Popup dismissed.");
	        } else {
	            System.out.println("No popup displayed.");
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred while dismissing the popup: " + e.getMessage());
	    }
	}
	
	public static FileInputStream getFile(String filePath) throws FileNotFoundException {

		File file= new File(filePath);

		FileInputStream FIS = new FileInputStream(file);

		return FIS;

		}
	
	
	
}
