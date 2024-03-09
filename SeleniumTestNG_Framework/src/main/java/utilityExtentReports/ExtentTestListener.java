package utilityExtentReports;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestListener implements ITestListener {

//	static Date d = new Date();
//	static String fileName = "ExtentReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
//
//	private static ExtentReports extent = ExtentManager.createInstance(
//			System.getProperty("user.dir") + File.separator + "Reports" + File.separator + fileName, "1.0.2");
//
//	
//	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 String methodName=result.getMethod().getMethodName();
		 
	        String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " - PASSED"+"</b>";  
	        
	        ExtentTestManager.getTest().log(Status.PASS, "Test Case successful");
	        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
	        ExtentTestManager.getTest().pass(markup);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		 String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		 
		 ExtentTestManager.getTest().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
	                + "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
	         
	        try {
	 
	          //  ExtentManager.captureScreenshot();
	        	ExtentTestManager.getTest().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>");
	        } catch (Exception e) {
	 
	        }
	         
	        String failureLogg="TEST CASE FAILED";
	        Markup markup = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
	        ExtentTestManager.getTest().log(Status.FAIL, markup);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("*** Test "+ context.getName() + " started ***");
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("*** Test  "+ context.getName() + " ended ***");
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

}
