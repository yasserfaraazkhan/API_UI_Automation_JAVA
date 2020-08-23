package apitests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import apibase.WheatherAPIBase;
import baseconfig.BaseAutomationMethods;
import pojoclasses.WheatherPOJO;

public class APITestCase extends BaseAutomationMethods {

	@Test
	public void verifyUserIsAbleToSearchAValidCity() throws IOException {
		String CITY_NAME = "Bengaluru";
		String SCHEMA_FILE_NAME = "wheatherschema";
		int STATUS = 200;
		WheatherAPIBase weatherRequest = this.gitCityTemperature(CITY_NAME, STATUS, SCHEMA_FILE_NAME);
		WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
		Assert.assertFalse(wheatherResponse.getMain().isBlank());
		Assert.assertEquals(wheatherResponse.getCityName(), CITY_NAME);
	}

	@Test
	public void verifyUserGetsCorrectMessageOnInvalidSearch() throws IOException {
		String CITY_NAME = "Dummy";
		String ERROR_MESSAGE = "city not found";
		int STATUS = 404;
		String SCHEMA_FILE_NAME = "wheatherErrorSchema";

		WheatherAPIBase weatherRequest = this.gitCityTemperature(CITY_NAME, STATUS, SCHEMA_FILE_NAME);

		WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
		Assert.assertEquals(wheatherResponse.getMessage(), ERROR_MESSAGE);

	}
}
