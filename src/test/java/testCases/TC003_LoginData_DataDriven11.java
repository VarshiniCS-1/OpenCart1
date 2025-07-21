package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage2;
import pageObjects.LoginPage6;
import pageObjects.MyAccountPage7;
import testBase.BaseClass4;
import utilities.DataProviders10;


public class TC003_LoginData_DataDriven11 extends BaseClass4 {
	
	/*dataProviderClass = DataProviders.class is used to get the DataProviders class methods which is in diff pack.
	this is not required if the dataProvider is in same class */
	
	/*  We cannot extend DataProviders class bcoz DataProviders class has special type of methods, 
	 * this methods will repeat multiple times so we cannot extend it, 
	 * how many times DP class input is passing that many times this class runs.
	 */
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders10.class,groups = {"DataDriven"})
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		HomePage2 hp = new HomePage2(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		
		LoginPage6 lp = new LoginPage6(driver);
		lp.setemail(email);
		lp.setpassword(pwd);
		lp.clicklogin();
				
		MyAccountPage7 ma = new MyAccountPage7(driver);
		boolean verifyMessage = ma.verifyMyAccount();
		

/* data is valid --> login success --> test pass --> logout
 * data is valid --> login failed --> test fail
 * data is invalid --> login success --> test fail --> logout
 * data is invalid --> login failed --> test pass 
 */
		logger.info("***** TC003_LoginData_DataDriven Started *****");
		
		try {
			
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(verifyMessage == true)
			{
				ma.logout();
				Assert.assertTrue(true);	
			}
			
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if (exp.equalsIgnoreCase("Invalid")) 
		{
			if(verifyMessage == true)
			{
				ma.logout();
				Assert.assertTrue(false);	
			}
			else 
			{
				Assert.assertTrue(true);
			}
		} 

		
	}
	
	catch (Exception e)
		{
			Assert.fail();
			logger.error("TC003_LoginData_DataDriven got fail");
		}

		logger.info("***** TC003_LoginData_DataDriven finished *****");
}}
