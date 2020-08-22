package APIBase;

import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.given;

public class WheatherAPIBase extends BaseAPI {
	protected String accesstoken;
	protected String cityName;
	String apiPath = "/data/2.5/weather";

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

	private RequestSpecBuilder addQueryParam(RequestSpecBuilder requestSpecBuilder) {
		if (this.cityName != null) {
			requestSpecBuilder.addQueryParam("q", cityName);
		}
		requestSpecBuilder.addQueryParam("appid", accesstoken);
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
		apiResponse.then().spec(responseSpecification);

	}

}
