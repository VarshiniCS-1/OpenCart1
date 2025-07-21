package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass4;

public class ExtentReportManager implements ITestListener {
	
	String repname;
	public ExtentSparkReporter SparkReporter;
	public ExtentReports extent;
	public ITestContext testContext;
	public ExtentTest test;
	
	
	//testcontext will capture the context of the test.
	public void onstart(ITestContext testcontext) 
	{
		//here i'm setting the report name based on the current timestamp. 
		// we create date using  Date() to get the current timestamp in "yyyy-MM-dd HH:mm:ss" format.
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		// creating report with timestamp as name.html file in reports folder.	
		repname = "Test-Report-" + timestamp + ".html";	
		//Instantiating the ExtentSparkReporter with the report name and path.	
		SparkReporter= new ExtentSparkReporter(".\\reports\\" + repname);
		
		//Configuring the extent report with the necessary details.
		
		SparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of the report.
		SparkReporter.config().setReportName("Opencart Functional Testing"); //Name of the report.	
		SparkReporter.config().setTheme(Theme.DARK); //Theme of the report.
		
		//Starting the report with Hardcoded and Dynamic values in the report.
		
		extent = new ExtentReports();
		extent.attachReporter(SparkReporter); //Attaching the reporter to extent reports.
		extent.setSystemInfo("Application", "opencart"); //Hardcoded values for application and module.
		extent.setSystemInfo("Module", "Admin"); //Hardcoded values for application and module.
		extent.setSystemInfo("Sub Module", "Customers"); //Hardcoded values for sub module.
		//System.getProperty("user.name") is a predefined method that holds the user's name. this not from property file
		extent.setSystemInfo("User Name", System.getProperty("user.name")); //who executed the test.
		extent.setSystemInfo("Environment", "QA"); //hardcoded values for environment.
		
		String os =testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		//Grouping the tests based on their groups. If there are no groups, it will consider all the tests as one group.	
		// Grouping the test from xml file.	
		List<String> includedGroup = testContext.getCurrentXmlTest().getIncludedGroups();
		
		//if checks if there are any groups included in the xml file.	
		//if the groups are included, it will print the groups in the report.	
		//if not, it will print "All groups" in the report.	
		if(!includedGroup.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroup.toString());
		}		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		//Creating new entry in the report using the class name, method name, and group.	
		 test = extent.createTest(result.getTestClass().getRealClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS,result.getName()+"got executed successfully");
		
	}
	
	public void onTestFailure(ITestResult result) {
		 test = extent.createTest(result.getTestClass().getRealClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, result.getName()+ "got failed with the below reason: " + result.getThrowable().getMessage());
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 
		 try {
			 String imgpath = new BaseClass4().captureScreenshot(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }}
	
	public void onTestSkipped(ITestResult result) {
		 test = extent.createTest(result.getTestClass().getRealClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName()+ "got skipped");
		 test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		 extent.flush(); // this will consolidate all the test results and generate the final report.	
		 
		 // After all tests are finished, it  automatically  opens the report in default browser.
		 String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repname;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		 
		 try 
		 {
			 URL url = new URL("file:///" + System.getProperty("user.dir") + "//reports//" + repname);
			 
			 //Create the email message
			 
			 ImageHtmlEmail email = new ImageHtmlEmail();
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.gmail.com");
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("Varshinics1@gmail.com", "your_password"));
			 email.setSSLOnConnect(true);
			 email.setFrom("Varshinivedhacs@gmail.com");		//sender
			 email.setSubject("Test Result");
			 email.setMsg("Please find the attached report....");
			 email.addTo("Varshinics1@gmail.com");				//receiver
			 email.attach(url, "extent_report.html", "please check the report");
			 email.send();
			 System.out.println("Email sent successfully");
			 ;
		} catch (Exception e) {
			e.printStackTrace(); 
			
			

		}
	}

}
