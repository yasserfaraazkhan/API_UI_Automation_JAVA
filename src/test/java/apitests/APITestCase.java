package apitests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import APIBase.WheatherAPIBase;
import POJOClasses.WheatherPOJO;
import baseconfig.BaseAutomationMethods;

public class APITestCase extends BaseAutomationMethods {

	@Test
	public void verifyUserIsAbleToSearchAValidCity() throws IOException {
		String CITY_NAME = "Bengaluru";
		int STATUS = 200;
		WheatherAPIBase weatherRequest = this.gitCityTemperature(CITY_NAME, STATUS);

		WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
		Assert.assertFalse(wheatherResponse.getMain().isBlank());
		Assert.assertEquals(wheatherResponse.getCityName(), CITY_NAME);
	}

	@Test
	public void verifyUserGetsCorrectMessageOnInvalidSearch() throws IOException {
		String CITY_NAME = "Dummy";
		String ERROR_MESSAGE = "city not found";
		int STATUS = 404;

		WheatherAPIBase weatherRequest = this.gitCityTemperature(CITY_NAME, STATUS);

		WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
		Assert.assertEquals(wheatherResponse.getMessage(), ERROR_MESSAGE);

	}
}
