package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject{

	private By usernameLocator = By.id("username");
	private By passwordLocator = By.name("password");
	private By logInButtonLocator = By.tagName("button");
	private By failLoginMessage = By.xpath("//div[@id='flash']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		}
	
	

	/** Execute log in */
	public SecureAreaPage logIn(String username, String password) {
		//log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
		type(username, usernameLocator);
		type(password, passwordLocator);
		click(logInButtonLocator);
		return new SecureAreaPage(driver);
	}
	
	public void negativeLogIn (String username, String password) {
		//log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
		type(username, usernameLocator);
		type(password, passwordLocator);
		click(logInButtonLocator);		
	}
	
	public void waitForVisibiltyOfErrorMessage() {
		waitForVisibilityOf(failLoginMessage, Duration.ofSeconds(5));
	}
	public String getFailLoginMessageText() {
		return find(failLoginMessage).getText();
	}
}
