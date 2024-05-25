package qa.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.base.Base;
import qa.pages.searchpage;

public class SearchTest extends Base{
	searchpage sp;
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		 loadPropertiesFile();
			driver= driverSetupandOpenURL(p.getProperty("browserName"));
			 sp = new searchpage(driver);
	
	}
	
 @Test(priority=1)
 public void searchitem() {
	 	sp.searchtext(datap.getProperty("validsearch"));
		sp.searchclick();
		
		 String phtext = sp.validproductmsg();
		Assert.assertEquals(phtext, "Products meeting the search criteria");
  }
 @Test(priority=2)
public void invalidSearchitem() {
sp.searchtext(datap.getProperty("invalidsearch"));
sp.searchclick();
	String text = sp.invalidprodctmsg();
	Assert.assertEquals(text, "There is no product that matches the search criteria.");
}
 @AfterMethod
	public void tearDown() {
	driver.quit();	
	}

 }
