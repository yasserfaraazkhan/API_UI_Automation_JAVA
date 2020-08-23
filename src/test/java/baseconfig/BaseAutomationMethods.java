package baseconfig;

import apibase.WheatherAPIBase;

public class BaseAutomationMethods {

	public WheatherAPIBase gitCityTemperature(String cityName, int status, String schemaFileName) {

		WheatherAPIBase weatherRequest = new WheatherAPIBase("https://api.openweathermap.org",
				"7fe67bf08c80ded756e598d6f8fedaea");
		weatherRequest.setExpectedStatusCode(status);
		weatherRequest.setCityName(cityName);
		weatherRequest.setMetricSystem("metric");
		weatherRequest.setSchemaFileName(schemaFileName);

		weatherRequest.perform();
		
		return weatherRequest;
	}
}
