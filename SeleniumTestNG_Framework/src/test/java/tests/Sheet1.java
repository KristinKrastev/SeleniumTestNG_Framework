package tests;

import java.util.Map;

import org.testng.annotations.Test;

import basePackage.BaseTest;
import pageObject.LoginPage;
import pageObject.WelcomePage;
import basePackage.ExcelDataProvider;

public class Sheet1 extends BaseTest {

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void negativeTest(Map<String, String> data) {

		

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver);
		welcomePage.openPage();

		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthentication();

		// enter username and password
		loginPage.negativeLogIn(data.get("username"), data.get("password"));

		System.out.println(data.get("username"));
		System.out.println(data.get("password"));
	}
}
