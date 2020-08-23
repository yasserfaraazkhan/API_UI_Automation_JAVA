package apibase;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.builder.RequestSpecBuilder;

public class WheatherAPIBase extends BaseAPI {
	protected String accesstoken;
	protected String cityName;
	protected String metricSystem;
	protected String schemaFileName;

	protected String apiPath = "/data/2.5/weather";

	public WheatherAPIBase(String baseURI, String accesstoken) {
		super(baseURI);
		this.accesstoken = accesstoken;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMetricSystem() {
		return metricSystem;
	}

	public void setMetricSystem(String metricSystem) {
		this.metricSystem = metricSystem;
	}

	public String getSchemaFileName() {
		return schemaFileName;
	}

	public void setSchemaFileName(String schemaFileName) {
		this.schemaFileName = schemaFileName;
	}

	private RequestSpecBuilder addQueryParam(RequestSpecBuilder requestSpecBuilder) {
		if (this.cityName != null) {
			requestSpecBuilder.addQueryParam("q", cityName);
		}
		requestSpecBuilder.addQueryParam("appid", accesstoken);
		requestSpecBuilder.addQueryParam("units", metricSystem);

		return requestSpecBuilder;
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecification = this.addQueryParam(requestSpecBuilder).build();
		requestSpecification = requestSpecBuilder.build();

	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).get();

	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(expectedStatusCode); // If this fails we dont need to Deserialize into pojo
		responseSpecification = responseSpecBuilder.build();
		apiResponse.then().spec(responseSpecification).assertThat()
				.body(matchesJsonSchemaInClasspath(String.format("schema/%s.json", this.getSchemaFileName())));

	}

}
