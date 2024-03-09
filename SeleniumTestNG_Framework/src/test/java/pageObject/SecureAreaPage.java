package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {

	private String pageUrl = "https://the-internet.herokuapp.com/secure";
	private By logOutButton = By.xpath("//a[@class='button secondary radius']");
	private By logInMessage = By.xpath("//div[@id='flash']");
	
	public SecureAreaPage(WebDriver driver) {
		super(driver);
	}
	
	public String getPageUrl() {
		return pageUrl;
	}
	
	public boolean isLogOutButtonVisible() {
		return isElementDisplayed(logOutButton);
	}
	
	public String getSuccessMessageText() {
		return getText(logInMessage);
	}

}
