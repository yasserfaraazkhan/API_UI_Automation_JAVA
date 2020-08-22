package SeleniumAndAPI;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	public static final Logger logger = Logger.getLogger(Page.class);

	@FindBy(how = How.ID, id = "h_sub_menu")
	private WebElement submenu;

	@FindBy(how = How.LINK_TEXT, linkText = "WEATHER")
	private WebElement weatherLink;

	@FindBy(how = How.ID, id = "searchBox")
	private WebElement searchBox;

	@FindBy(how = How.ID, id = "more")
	private WebElement more;

	@FindBy(how = How.ID, id = "header2")
	private WebElement headerOptions;

	@FindBy(how = How.CLASS_NAME, className = "leaflet-popup-content-wrapper")
	private WebElement cityInformationModal;

	@FindBy(how = How.CLASS_NAME, className = "tempRedText")
	private WebElement temperature;

	@FindBy(how = How.CLASS_NAME, className = "message")
	private WebElement searchResult;

	@FindBy(how = How.CLASS_NAME, className = "outerContainer")
	private WebElement pinnedCity;

	@FindBy(how = How.CSS, css = "input:checked[type='checkbox']")
	private List<WebElement> listOfSelectedLocations;

	@FindBy(how = How.CSS, css = "div.outerContainer:not([style*=\"display: none\"])")
	private List<WebElement> listOfLocationsPins;

	@FindBy(how = How.XPATH, xpath = "//*[@type='checkbox']")
	private List<WebElement> locationCheckbox;

	@FindBy(how = How.XPATH, xpath = "//*[@type='cityText']")
	private List<WebElement> cityText;

	public WebDriver driver;

	public WebDriverWait wait;

	public WebDriverWait getWait() {
		return wait;
	}

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		PageFactory.initElements(driver, this);
	}

	private WebElement setLocatorForSearchResult(String locator) {

		return driver.findElement(By.id(locator));
	}

	public List<WebElement> getListOfSelectedLocations() {
		return listOfSelectedLocations;
	}

	public List<WebElement> getListOfLocationsPins() {
		return listOfLocationsPins;
	}

	public WebElement getCityInformationModal() {
		return cityInformationModal;
	}

	public WebElement getSearchResult() {
		return searchResult;
	}

	public WebElement getSubmenu() {
		return submenu;
	}

	public WebElement getHeaderOptions() {
		return headerOptions;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getPinnedCity() {
		return pinnedCity;
	}

	public WebElement getWeatherLink() {
		return weatherLink;
	}

	public List<WebElement> getLocationCheckbox() {
		return locationCheckbox;
	}

	public List<WebElement> getCityText() {
		return cityText;
	}

	public WebElement getMorePanel() {
		return more;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	private void waitForVisiblityOfElement(WebElement elementToWaitFor) {
		this.getWait().until(ExpectedConditions.visibilityOf(elementToWaitFor));

	}

	public void navigateToWeatherPage() {
		logger.info("Navigate to Wheather Page");

		this.waitForVisiblityOfElement(getSubmenu());

		this.getSubmenu().click();

		this.getWeatherLink().click();
		this.waitForVisiblityOfElement(getSearchBox());
	}

	public List<WebElement> clearSelectedCity() {
		logger.info("Clearing Location pinned on Wheather Page");
		for (WebElement checkbox : this.getListOfSelectedLocations()) {
			checkbox.click();
		}

		return this.getListOfSelectedLocations();
	}

	public void pinCity(String cityToPin) {
		logger.info("Search Location");
		this.getSearchBox().sendKeys(cityToPin);
		this.setLocatorForSearchResult(cityToPin).click();
		this.waitForVisiblityOfElement(getPinnedCity());

	}

	public void openDetailedInformationOfCity(String cITY) {
		this.getPinnedCity().click();

	}

	public String getTemprature() {
		return this.temperature.getText();
	}

	public void tearDown() {
		try {
			this.driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigate(String url) {
		driver.navigate().to(url);
	}

}
