package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;

import actionDriver.ActionDriver;
public class ConfirmationPage {
	
	private ActionDriver actionDriver;
	private By closeButton = By.className("modal-mask-closeBtn");
	private By bannerMessage = By.cssSelector("div.fe_banner.fe_banner__accessible.fe_banner__green.fe_banner__bp.fe_banner__inherit_font_size.fe_banner__bp_top_banner");
	private By hotelNameTitle = By.cssSelector("div.bp_hotel_name_title");

	public ConfirmationPage(WebDriver driver) {
		this.actionDriver=	new ActionDriver(driver);
		}

 public boolean checkHotelNameDisplayedAndVerifyText() {
	 actionDriver.scrollToElement(hotelNameTitle);
	    boolean isDisplayed = actionDriver.isDisplayed(hotelNameTitle);
	    if (isDisplayed) {
	        // Perform the text comparison if the element is displayed
	     return actionDriver.compareText(hotelNameTitle, "Tolip Hotel Alexandria");
	    }
	    return isDisplayed;
	    
	    
 }
}
