package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actionDriver.ActionDriver;
public class DetailsPage {

	private ActionDriver actionDriver;
	private By hotelBreadcrumbLink = By.cssSelector("a[href*='/hotel/eg/royal-tulip-alexandria.html']");
	private By availabilityHeader = By.id("availability_target");
	private By startDateButton = By.cssSelector("button[data-testid='date-display-field-start']");

	private By endDateButton = By.cssSelector("button[data-testid='date-display-field-end']");
	private By roomSelectDropdown = By.id("hprt_nos_select_bbasic_0");
	private By roomSelectOption1 = By.cssSelector("#hprt_nos_select_bbasic_0 option[value='1']");
	private By reservationButton = By.id("b_tt_holder_4");

    
	public boolean checkAvailabilityDisplayed(){
		actionDriver.scrollWithCoordinateY(availabilityHeader);
		 boolean isDisplayed = actionDriver.isDisplayed(availabilityHeader);
		    if (isDisplayed) {
		        // Perform the text comparison if the element is displayed
		        actionDriver.compareText(availabilityHeader, "availability");
		    }
		    return isDisplayed;
		
}
	
	public boolean validateNavigationToDetailsPage()
	{
		return actionDriver.isDisplayed(hotelBreadcrumbLink);
	}
	

	public void validateCheckInDate() {
		actionDriver.getText(startDateButton).compareTo("Thu, Jan 30");
	}
	
	public void validatecheckOutDate() {
		actionDriver.getText(endDateButton).compareTo("Fri, Jan 31");
	}
	
	public void clickOnRoomDropDown() {
		
		actionDriver.click(roomSelectDropdown);
	}
	
	public void selectFromRoomDropDown() {
		actionDriver.click(roomSelectOption1);
	}
	
	public void clickOnReserveBtn()
	{
		actionDriver.click(reservationButton);
	}
	public DetailsPage(WebDriver driver) {
		this.actionDriver=	new ActionDriver(driver);
		}
}
