package utilityBrowserSetup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	
	private String osType = System.getProperty("os.name", "generic").toLowerCase();

	public BrowserSetup(String browser) {
		this.browser = browser.toLowerCase();
		
	}

	public WebDriver createDriver() {
		// Create driver
	

		switch (browser) {
		case "chrome":
			if(osType.contains("windows")) {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
				System.out.println("windows OS used!");
				driver.set(new ChromeDriver());
				break;
			}
			else if(osType.contains("linux")){
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driverFolder/chromedriverLinux");
				driver.set(new ChromeDriver());
				break;
			}
	
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
		}

		return driver.get();
	}
}
