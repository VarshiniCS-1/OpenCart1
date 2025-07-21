package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage7  extends BasePage1{

	public MyAccountPage7(WebDriver driver) 
	{
		super(driver);
		
	}
	
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement lnMyAccount;
	
	//use this when step is like Myaccount > logout
	
//	@FindBy(xpath = "//a[@title='My Account']")
//	WebElement ddMyAccount;
//	
//	@FindBy(xpath = "//a[@title='My Account']/..//a[text()='Logout']")
//	WebElement lnlogout;
	
	//use this when step is like logout (in My account page at the bottom of the content section)
	
	@FindBy(xpath = "//div[@class='list-group']/..//a[text()='Logout']")
	WebElement lnlogout;
	
	public boolean verifyMyAccount()
	{
		try {
			
			return(lnMyAccount.isDisplayed());
		}
		
		catch (Exception e)
		{
			return false;
		}
		
	}
	
//	public void myAccountDropDown()
//	{
//		ddMyAccount.click();
//	}

	public void logout()
	{
		lnlogout.click();
	}
}
