package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import actionDriver.ActionDriver;

public class HomePage<JavascriptExecutor> {
	private ActionDriver actionDriver;

	// define locators using By class

	private By searchTextBox = By.id(":rh:");
	
	private By checkInDateButton = By.cssSelector("button[data-testid='date-display-field-start']");

	//private By CheckIndateElement = By.xpath("//span[@aria-label='20 January 2025']");
	private By CheckIndateGridCell = By.xpath("//span[contains(text(),'30')]");

	//private By CheckOutdateElement = By.xpath("//span[@aria-label='21 January 2025']");
	private By CheckOutdateGridCell = By.xpath("//span[contains(text(),'31')]");

	private By closeBtn= By.cssSelector("button[aria-label='Dismiss sign-in info.']");

	private By monthHeader = By.xpath("//h3[@aria-live='polite']");

	private By searchButton = By.xpath("//button[span[text()='Search']]");
	private By exactDatesCheckbox = By.cssSelector("li.b0932df2e7.e375bc2404 input[type='checkbox'][value='exact']");
	private By locationDropDown = By.xpath("//div[@class='ce5ee7d913']/div[@class='abf093bdfe d2f04c9037']");


	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}

//	public void enterDestination(String destination) {
//		actionDriver.dismissPopupIfPresent(closeBtn);
//		actionDriver.enterText(searchTextBox, destination);
//		
//}

	public void enterDestination() {
	    String destination = PropertiesUtil.getProperty("destination"); // Read from properties file
	    actionDriver.dismissPopupIfPresent(closeBtn);
	    actionDriver.enterText(searchTextBox, destination);
	}


	
	public void enterDates() {
		try {
			actionDriver.dismissPopupIfPresent(closeBtn);
			//actionDriver.click(dismissSignInButton);
			actionDriver.click(checkInDateButton);
			System.out.println("check in date btn clicked");
			//actionDriver.scrollToElement(exactDatesCheckbox);
			//actionDriver.scrollToSpecificCoordinate(0,500);
			//System.out.println("scroll done");
			//actionDriver.waitElementToBeClickable(CheckIndateGridCell);
			actionDriver.clickNotClickableElements(CheckIndateGridCell);
			
			System.out.println("CheckIndateGridCell");
			actionDriver.clickNotClickableElements(CheckOutdateGridCell);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSearchBtn() {
		actionDriver.dismissPopupIfPresent(closeBtn);
		actionDriver.click(searchButton);
	}

}
