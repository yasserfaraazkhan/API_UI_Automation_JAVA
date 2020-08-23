package baseconfig;

import APIBase.WheatherAPIBase;

public class BaseAutomationMethods extends TestHooks {

	public WheatherAPIBase gitCityTemperature(String cityName, int status) {

		WheatherAPIBase weatherRequest = new WheatherAPIBase("https://api.openweathermap.org",
				"7fe67bf08c80ded756e598d6f8fedaea");
		weatherRequest.setExpectedStatusCode(status);
		weatherRequest.setCityName(cityName);
		weatherRequest.setMetricSystem("metric");
		weatherRequest.perform();
		
		return weatherRequest;
	}
}
