package qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import qa.utils.ExtentReport;
import qa.utils.Utility;

public class Mylisteners implements ITestListener{
	ExtentReports extentrep;
	ExtentTest extentest;
	@Override
	public void onStart(ITestContext context) {
		System.out.println("started");
		  extentrep = ExtentReport.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {
		 
	String testName=	result.getName();
	  extentest = extentrep.createTest(testName);
	 extentest.log(Status.INFO,testName+"started executing");

	System.out.println(testName+"started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentest.log(Status.PASS, testName+"sucessfully executed");
		System.out.println(testName+"success");
	}

//	@Override
//	public void onTestFailure(ITestResult result) {
//		
//	String testName=	result.getName();
//	System.out.println(result.toString());
//	 WebDriver driver = null;
//	try {
//		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//File screenshot=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//String destinationpath = System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\"+testName+".png";
//try {
//	FileHandler.copy(screenshot, new File(destinationpath));
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//extentest.addScreenCaptureFromPath(destinationpath);
//extentest.log(Status.INFO, result.getThrowable());
////extentest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(destinationpath).build());
//	}
//
	
	@Override
	public void onTestFailure(ITestResult result) {
	    String testName = result.getName();
	    System.out.println(result.toString());
	    
	    WebDriver driver = null;
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	    }
	String destinationPath   =   Utility.captureScreenshot(driver, testName);
	    
	        try {
	            extentest.addScreenCaptureFromPath(destinationPath);
	            extentest.log(Status.INFO, result.getThrowable());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}	@Override
	public void onTestSkipped(ITestResult result) {
		 String testName = result.getName();
		 extentest.log(Status.SKIP, result.getThrowable());
		 extentest.log(Status.SKIP, testName+"skipped testcase");
		 System.out.println(testName +"skipped");
		 System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		extentrep.flush();	
	String text = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html";
	File f = new File(text);	
	try {
		Desktop.getDesktop().browse(f.toURI());
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

}
