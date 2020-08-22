package SeleniumAndAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseconfig.BaseAutomationMethods;

public class SeleniumTestCase extends BaseAutomationMethods {

	String temperature;

	@Test(priority = 1, dependsOnMethods = "setup")
	public void verifyUserCanUnPinCity() {

		Assert.assertTrue(page.getHeaderOptions().isDisplayed());
		Assert.assertTrue(page.getSubmenu().isDisplayed());
		page.navigateToWeatherPage();
		Assert.assertTrue(page.getMorePanel().isDisplayed());
		page.clearSelectedCity();
		Assert.assertEquals(page.getListOfSelectedLocations().size(), 0, "Some Pinned city(ies) are not removed");
	}

	@Test(priority = 2, dependsOnMethods = "setup")
	public void verifyUserCanSeeDetailedInformationPinCity() {
		final String CITY = "Bengaluru";
		page.pinCity(CITY);
		Assert.assertEquals(page.getPinnedCity().getAttribute("title"), CITY);
		page.openDetailedInformationOfCity(CITY);
		Assert.assertTrue(page.getCityInformationModal().isDisplayed());
		temperature = page.getTemprature();
	}

}
