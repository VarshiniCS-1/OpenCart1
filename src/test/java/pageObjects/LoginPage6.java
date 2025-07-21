package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage6 extends BasePage1 {

	public LoginPage6(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(name="email")
	WebElement txtEmail;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	public void setemail(String email)
	{
		txtEmail.sendKeys(email);
		
	}
	
	public void setpassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
		
	}
	
	public void clicklogin()
	{
		btnLogin.click();
		
	}

}
