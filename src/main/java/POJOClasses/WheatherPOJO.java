package POJOClasses;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "coord", "weather", "name", "cod", "message", "main" })

public class WheatherPOJO {

	@JsonProperty("coord")
	private Object coord;

	@JsonProperty("weather")
	private Object weather;

	@JsonProperty("message")
	private String message;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cod")
	private String cod;

	@JsonProperty("main")
	private ObjectNode main;

	@JsonProperty("cod")
	public String getCod() {
		return cod;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("name")
	public String getCityName() {
		return name;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("coord")
	public Object getCoord() {
		return coord;
	}

	@JsonProperty("weather")
	public Object getWeather() {
		return weather;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("main")
	public String getMain() {
		return main.get("temp").asText();
	}

//	public static class Main {
//		private String temperature;
//
//		public String getTemperature() {
//			return temperature;
//		}
//
//		public void setTemperature(String temperature) {
//			this.temperature = temperature;
//		}
//	}
}