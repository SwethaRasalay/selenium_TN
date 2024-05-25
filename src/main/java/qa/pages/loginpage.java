package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {
	WebDriver driver;
//objects
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement myaccount;
	@FindBy(xpath = "//a[normalize-space()='Login']")
	private WebElement login;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement validemail;
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement validpassword;
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginbutton;
	@FindBy(xpath ="//a[contains(text(),'Edit')]")
	private WebElement validationmessge;
	@FindBy(xpath="//div[contains(text(),'Warning:')]")
	private WebElement invaliderrormsg;
	
	public loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void clickMyaccount() {
		myaccount.click();
	}
	public void clickLogin() {
		login.click();
	}
	public void enterMail(String email) {
		validemail.sendKeys(email);
	}
	public void enterpassword(String password) {
		validpassword.sendKeys(password);
	}
	public void loginbutton() {
		loginbutton.click();
	}
	public boolean validationmessage() {
		boolean displaystatus = validationmessge.isDisplayed();
		return displaystatus;
	}
	
	public boolean invaliderormsg()
	{
		boolean errorstatus = invaliderrormsg.isDisplayed();
		return errorstatus;
		
	}
	
}
