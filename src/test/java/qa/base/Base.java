package qa.base;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import qa.utils.Utility;

public class Base {
	WebDriver driver;
	public Properties p;
	public Properties datap;
	public void loadPropertiesFile()  {
		 p= new Properties();
		 File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			p.load(fis);
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		datap = new Properties();
		File ff = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\testdataconfig.properties");
	try {
		FileInputStream fo= new FileInputStream(ff);
		datap.load(fo);
	} catch(Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	public WebDriver driverSetupandOpenURL(String browserName) {
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.Implicit_wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.PageLoad_Time));
		driver.get(p.getProperty("url"));
return driver;
		
		
	}
	
	
}
