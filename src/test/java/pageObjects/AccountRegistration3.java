package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration3  extends BasePage1{

	public AccountRegistration3(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id="input-firstname")
	WebElement txtFistname;
	
	@FindBy(id="input-lastname")
	WebElement txtLastname;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtConfirmpassword;
	
	@FindBy(name="agree")
	WebElement chkAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnSubmit;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement confirmmessage;
	
	public void Setfn(String fname)
	{
		txtFistname.sendKeys(fname);
	}
	
	public void setln(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setemail(String Email)
	{
		txtEmail.sendKeys(Email);
	}
	
	public void Settel(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void Setpwd(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void Setcpwd(String cpwd)
	{
		txtConfirmpassword.sendKeys(cpwd);
	}
	
	public void Clickag()
	{
		chkAgree.click();
	}
	
	public void Clickcn() throws InterruptedException
	{
		btnSubmit.click();
		Thread.sleep(3000);
	}
	
	public String getConfirmMsg()
	{
		try {
			return (confirmmessage.getText());
		}
		
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	

}
