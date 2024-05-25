package qa.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.base.Base;
import qa.pages.loginpage;
import qa.utils.Utility;

public class LoginTest extends Base {
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		 driver = driverSetupandOpenURL(p.getProperty("browserName"));
		 System.out.println(driver);
		 loginpage loginpage = new loginpage(driver);
		 loginpage.clickMyaccount();
		 loginpage.clickLogin();
				
	}
	
	@Test(priority=1,dataProvider ="testm" )
public void login(String email,String password) {
		loginpage loginpage = new loginpage(driver);
		loginpage.enterMail(email);
		loginpage.enterpassword(password);
		loginpage.loginbutton();
	
	Assert.assertTrue(loginpage.validationmessage());
	tearDown();
}
	@DataProvider(name ="testm")
	public Object[][] dataintomethods(){
		Object[][] data = Utility.readExcel("sheet1");
		return data ;
		
	}
	@Test(priority=2)
public void loginwithInvalidCredentials() {
		loginpage loginpage = new loginpage(driver);
		loginpage.enterMail(Utility.genrateTimestamp());
		loginpage.enterpassword(datap.getProperty("invalid-email"));
		
		loginpage.loginbutton();
		Assert.assertTrue(loginpage.invaliderormsg());
		tearDown();
}
	@AfterMethod
	public void tearDown() {
	driver.quit();	
	}	
}
