package baseconfig;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;

import SeleniumAndAPI.Page;



public abstract class TestHooks {
	public static final Logger logger = Logger.getLogger(TestHooks.class);

	protected Page page;


	// This way if initializing ensured one chrome driver instance is used over all
	// and saves a lot of space and time
//	@BeforeTest
	@Parameters({ "url" })
	public void setup(String url) {

		String chromeDriverPath = "/Users/yasserkhan/Desktop/yasProj/API-Selenium-FrameWork/com.seleniumandapi/drivers/chromedriver" ;
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		page = new Page(driver);
		page.navigate(url);
	}

//	@AfterTest
	public void afterTest() {
		page.tearDown();
	}
}
