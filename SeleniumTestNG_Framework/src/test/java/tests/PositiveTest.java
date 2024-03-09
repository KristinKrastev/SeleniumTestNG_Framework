package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import basePackage.TestUtilities;
import pageObject.LoginPage;
import pageObject.SecureAreaPage;
import pageObject.WelcomePage;
import utilityExtentReports.ExtentTestManager;


public class PositiveTest extends TestUtilities {


	@Test
	public void logInTest() {
		//log.info("Starting logIn test");

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver);
		
		welcomePage.openPage();
				
		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthentication();


		// Login with selected credentials
		SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");

		Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

		// log out button is visible
		Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(),
				"logOutButton is not visible.");
		
		ExtentTestManager.getTest().log(Status.INFO, "This is info inside the Test Case");
		// Successful log in message
		String expectedSuccessMessage = "You logged into a secure area!";
		
		
		String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
		
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
	}
}
