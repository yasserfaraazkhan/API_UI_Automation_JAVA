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
        WheatherAPIBase weatherRequest = new WheatherAPIBase("https://api.openweathermap.org", "7fe67bf08c80ded756e598d6f8fedaea");
        weatherRequest.setExpectedStatusCode(200);
        weatherRequest.setCityName(CITY_NAME);
        weatherRequest.perform();
        
        WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
        System.out.println(wheatherResponse.getMain());
        Assert.assertEquals(wheatherResponse.getCityName(), CITY_NAME);
	}

	@Test
	public void verifyUserGetsCorrectMessageOnInvalidSearch() throws IOException {
		String CITY_NAME = "Dummy";
		String ERROR_MESSAGE = "city not found";
        WheatherAPIBase weatherRequest = new WheatherAPIBase("https://api.openweathermap.org", "7fe67bf08c80ded756e598d6f8fedaea");
        weatherRequest.setExpectedStatusCode(404);
        weatherRequest.setCityName(CITY_NAME);
        weatherRequest.perform();
        
        WheatherPOJO wheatherResponse = weatherRequest.getAPIResponseAsPOJO(WheatherPOJO.class);
        Assert.assertEquals(wheatherResponse.getMessage(), ERROR_MESSAGE);
	}
}
