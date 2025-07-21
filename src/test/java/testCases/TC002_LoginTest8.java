package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage2;
import pageObjects.LoginPage6;
import pageObjects.MyAccountPage7;
import testBase.BaseClass4;

public class TC002_LoginTest8 extends BaseClass4 {
	
	@Test(groups = {"Sanity","Master"})
	public void login_test()
	{
		logger.info("***** TC002_LoginTest Started *****");
		try {
		HomePage2 hp = new HomePage2(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		
		LoginPage6 lp = new LoginPage6(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
		
		
		MyAccountPage7 ma = new MyAccountPage7(driver);
		boolean verifyMessage = ma.verifyMyAccount();
		
//		Assert.assertEquals(verifyMessage, true,"Login Failed");
		Assert.assertTrue(verifyMessage);
		
	}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** TC002_LoginTest Finished *****");
	}


}
