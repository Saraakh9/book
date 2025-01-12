package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actionDriver.ActionDriver;

public class ResultPage {
	private ActionDriver actionDriver;

	// define locators using By class
	private By searchResultsHeader = By.xpath("//div[@class='abf093bdfe']");
	private By seeAvailabilityLink = By.xpath("//div[contains(text(), 'Tolip Hotel Alexandria')]//following::a[@data-testid='availability-cta-btn']");
	private By HoteltitleLink = By.cssSelector("a[data-testid='title-link']");
	private By hotelTitleElement = By.xpath("//div[@data-testid='title' and text()='Tolip Hotel Alexandria']");
	private By closeBtn= By.cssSelector("button[aria-label='Dismiss sign-in info.']");
	
	public ResultPage(WebDriver driver) {
		this.actionDriver=	new ActionDriver(driver);
		}

	// method to check "search results header"
	public boolean checkResultsHeaderDisplayed(){
		
		return actionDriver.isDisplayed(searchResultsHeader);
		
	}
	
	public boolean checkTolipHotelTitleDisplayed() {
		//actionDriver.scrollToElement(hotelTitleElement);
		//actionDriver.scrollToSpecificCoordinate(0, 100);
		actionDriver.dismissPopupIfPresent(closeBtn);
		actionDriver.scrollWithCoordinateY(hotelTitleElement);
	    boolean isDisplayed = actionDriver.isDisplayed(hotelTitleElement);
	    if (isDisplayed) {
	        // Perform the text comparison if the element is displayed
	        actionDriver.compareText(hotelTitleElement, "Tolip Hotel Alexandria");
	    }
	    return isDisplayed;
	}

	public boolean clickSeeAvailabilityBtn()
	{
		 boolean isDisplayed = actionDriver.isDisplayed(seeAvailabilityLink);
		    if (isDisplayed) {
		    	actionDriver.click(seeAvailabilityLink);
		    }
		    return isDisplayed;
		
	}

	
  


}
