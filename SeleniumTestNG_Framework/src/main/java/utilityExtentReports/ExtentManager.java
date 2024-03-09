package utilityExtentReports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;
	

	//Set the report paths variables
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String reportFilepath = System.getProperty("user.dir") + File.separator + "Reports";
	private static String reportFileLocation = reportFilepath + File.separator + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return reportFileLocation;
	}

	public static ExtentReports createInstance() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(getReportPath(reportFilepath));

		reporter.config().setReportName("Report name in setReportName");
		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Application Version","1.0.2");
		extent.setSystemInfo("Environment", "Test Environment");

		return extent;
	}
}
