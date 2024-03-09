package basePackage;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilityBrowserSetup.BrowserSetup;



public class BaseTest {

	
	protected WebDriver driver;
	
	
	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;

	@BeforeMethod
	@Parameters({ "Browser" })
	public void setUp(Method method,String browser,ITestContext ctx) {
		
		String testName = ctx.getCurrentXmlTest().getName();
		
		
		BrowserSetup browserSetup = new BrowserSetup(browser);
		driver = browserSetup.createDriver();
		driver.manage().window().maximize();
		
		System.out.println("Before method Thread Id:" + Thread.currentThread().getId());
		this.testMethodName = method.getName();
		this.testName = testName;
		this.testSuiteName = ctx.getSuite().getName();
	
	}
	
	

	@AfterMethod
	public void closeDriver() {
		driver.quit();
	}


}
