package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration3;
import pageObjects.HomePage2;
import testBase.BaseClass4;

public class TC001_AccountRegistration5 extends BaseClass4
{
	//In order to execute all test cases we need to run Master group
	@Test(groups = {"Regression","Master"})
	public void account_registration() throws InterruptedException
	{
		//Step 2. d)Add log statements.
		
		logger.info("***** Starting TC001_AccountRegistration5 *****");
				
		//Step 2. e)Add Try and Catch block if any statement fails.
		try
		{	
		
		HomePage2 hp= new HomePage2(driver);
		hp.ClickMyAccount();
		
		logger.info("Clicked on MyAccount Link");
		
		hp.ClickRegister();
		
		logger.info("Clicked on Register Link");
		
		AccountRegistration3 ar = new AccountRegistration3(driver);
		
		logger.info("Providing customer details");
		
		//This is Hardcoded
//		ar.Setfn("Varshini");		
		ar.Setfn(randomstring().toUpperCase()); //To convert name to upper case
		
//		ar.setln("CS");
		ar.setln(randomstring().toUpperCase());
		
		//This is Hardcoded
//		ar.setemail("sbaudhaiudi@gmail.com");
		ar.setemail(randomstring()+"@gmail.com");
		
//		ar.Settel("1234567890");
		ar.Settel(randomnumber());
		
//		ar.Setpwd("Pammy@1998");
//		ar.Setcpwd("Pammy@1998");		
		String password = randomalphanumeric(); //here we have to a variable to store random password, otherwise it will generate 2 different pwds for password and confirm password text boxs
		ar.Setpwd(password);
		ar.Setcpwd(password);
		
		
		ar.Clickag();
		ar.Clickcn();
		
		logger.info("Validated Expected Message");
		
		String Confirm_Message = ar.getConfirmMsg();
		
		if(Confirm_Message.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("AccountRegistration_Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(Confirm_Message, "Your Account Has Been Created!");
	
	}
	catch (Exception e)
	{
		Assert.fail();
	}
	
		logger.info("***** Finished TC001_AccountRegistration5 *****");
	
}}
