package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.DetailsPage;
import pages.HomePage;
import pages.ResultPage;
import pages.ConfirmationPage;
public class TC01_CheckDatesDisplayedCorrectlyInDetailsPageAndHotelNameShownInReservation extends BasePage {
	private HomePage homePage;
	private ResultPage resultPage;
	private DetailsPage detailsPage;
    private ConfirmationPage confrimationPage;
	@BeforeMethod
	public void setupPages() {
		homePage = new HomePage(getDriver());
		resultPage = new ResultPage(getDriver());
		detailsPage = new DetailsPage(getDriver());
		confrimationPage = new ConfirmationPage(getDriver());
	}
	
	@Test 
	public void CheckDatesDisplayedCorrectlyInDetailsPageAndHotelNameShownInReservation()
	{
		//homePage.enterDestination("Alexandria");
		homePage.enterDestination();
		homePage.enterDates();
		homePage.clickSearchBtn();
		System.out.println("search btn clicked");
		Assert.assertTrue(resultPage.checkResultsHeaderDisplayed());
		resultPage.checkTolipHotelTitleDisplayed();
		System.out.println("check tolip title in result page");
		resultPage.clickSeeAvailabilityBtn();
		System.out.println("availability btn clicked");
		//Assert.assertTrue(detailsPage.checkAvailabilityDisplayed());
		//System.out.println("availability title displayed");
		detailsPage.validateNavigationToDetailsPage();
		System.out.println("navigated to details page");
		detailsPage.validateCheckInDate();
		System.out.println("checkIn date validated");
		detailsPage.validatecheckOutDate();
		System.out.println("checkOut date validated");
		detailsPage.clickOnRoomDropDown();
		System.out.println("room drop down clicked");
		detailsPage.selectFromRoomDropDown();
		System.out.println("number of room selected");
		detailsPage.clickOnReserveBtn();
		System.out.println("reserve btn clicked");
		confrimationPage.checkHotelNameDisplayedAndVerifyText();
		System.out.println("confrmation page");
		
	}

}
