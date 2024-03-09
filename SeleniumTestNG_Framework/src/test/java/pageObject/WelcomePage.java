package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {



	private String pageUrl = "https://the-internet.herokuapp.com/";
	
	// ***** Elements ***** //
	
	private By formAuthenticatorLocator = By.linkText("Form Authentication");
	
	
	
	//Constructor
	public WelcomePage(WebDriver driver) {
		super(driver);

	}

	//******Methods*****//
	
	// Open Welcome Page with url
	public void openPage() {
		//log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		//log.info("Page is opened!");
		
	}
	
	//Click on Form Auth link to open Login Page
	public LoginPage clickFormAuthentication() {
	//	log.info("Clicking Form Auth link on Welcome Page");
		click(formAuthenticatorLocator);
		return new LoginPage(driver);
	}
	
	
}