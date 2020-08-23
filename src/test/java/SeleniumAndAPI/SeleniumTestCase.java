package SeleniumAndAPI;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import APIBase.WheatherAPIBase;
import POJOClasses.WheatherPOJO;
import baseconfig.BaseAutomationMethods;

public class SeleniumTestCase extends BaseAutomationMethods {

	public static final Logger logger = Logger.getLogger(SeleniumTestCase.class);

	@Test(priority = 1)
	public void verifyUserCanUnPinCity() {

		Assert.assertTrue(page.getHeaderOptions().isDisplayed());
		Assert.assertTrue(page.getSubmenu().isDisplayed());
		page.navigateToWeatherPage();
		Assert.assertTrue(page.getMorePanel().isDisplayed());
		page.clearSelectedCity();
		Assert.assertEquals(page.getListOfSelectedLocations().size(), 0, "Some Pinned city(ies) are not removed");
	}

	@Test(priority = 2)
	public void verifyUserCanSeeDetailedInformationPinCity() throws IOException {
		final String CITY = "Bengaluru";
		int STATUS = 200;
		float temperatureFromAPI;
		float temperatureFromUI;

		WheatherAPIBase weatherRequest = this.gitCityTemperature(CITY, STATUS);
		WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
		temperatureFromAPI = Float.parseFloat(wheatherResponse.getMain());

		page.pinCity(CITY);
		Assert.assertEquals(page.getPinnedCity().getAttribute("title"), CITY);
		page.openDetailedInformationOfCity(CITY);
		Assert.assertTrue(page.getCityInformationModal().isDisplayed());
		temperatureFromUI = Float.parseFloat(page.getTemprature());

		logger.info("Temperature difference from API and UI is " + Math.abs(temperatureFromUI - temperatureFromAPI));

	}

}
