package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class searchpage {
	WebDriver driver;
@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchtextbox ;
@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchclick;
@FindBy(xpath="//*[contains(text(),'Products')]")
	private WebElement validproductmessg;
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement invalidproductitem;
	//private WebElement ;

	public searchpage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//action methods
	public void searchtext(String searchitem) {
		searchtextbox.sendKeys(searchitem);
		
	}
public void searchclick() {
	searchclick.click();
}
public String validproductmsg() {
	String validmsg = validproductmessg.getText();
	return validmsg;
}
public String invalidprodctmsg() {
	String invalidmsg = invalidproductitem.getText();
	return invalidmsg;
}
}
