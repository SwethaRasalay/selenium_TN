package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public static ExtentReports generateExtentReport() {
	ExtentReports extent = new ExtentReports();
	File f = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(f);
	spark.config().setTheme(Theme.DARK);
	spark.config().setDocumentTitle("TN Automation Report");
	spark.config().setReportName("TN Results");
	spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
extent.attachReporter(spark);
Properties ep = new Properties();
File fp = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\config.properties");
FileInputStream fip;
try {
	fip = new FileInputStream(fp);
			ep.load(fip);
	
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


extent.setSystemInfo("App url",ep.getProperty("url"));
extent.setSystemInfo("BrowserName", ep.getProperty("browserName"));
extent.setSystemInfo("email", ep.getProperty("validEmail"));
extent.setSystemInfo("pass", ep.getProperty("validPassword"));
extent.setSystemInfo("osversion", System.getProperty("os.version"));
extent.setSystemInfo("osname" , System.getProperty("os.name"));
extent.setSystemInfo("username", System.getProperty("user.name"));
extent.setSystemInfo("javaversion", System.getProperty("java.vm.version"));
return extent;
	}

}
