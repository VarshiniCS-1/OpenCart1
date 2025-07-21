package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage2 extends BasePage1{

	public HomePage2(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement lnMyAccount;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement lnRegister;
	
	@FindBy(xpath = "//a[@title='My Account']/..//a[text()='Login']")
	WebElement lnLogin;
	
	public void ClickMyAccount()
	{
		lnMyAccount.click();
	}
	
	public void ClickRegister()
	{
		lnRegister.click();
	}

	public void ClickLogin()
	{
		lnLogin.click();
	}

}
