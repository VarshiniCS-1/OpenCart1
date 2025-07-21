package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


//Step 2. a)Here we have import these 2 log package

import org.apache.logging.log4j.Logger;			//Log4j
import org.apache.logging.log4j.LogManager;		//Log4j

public class BaseClass4 {
	
public WebDriver driver;
public Logger logger;		//Step 2. b)Create variable logger of Logger
public Properties p;		// Step 4. b) Import package to get property file

	@BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		// Step 4. c) give file path and load

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p =  new Properties();
		p.load(file);
		
		//Step 2. c)Log file config.
		//this will load the log4j2.xml file and automatically fetch it.
						
		logger = LogManager.getLogger(this.getClass());
		
		switch (br.toLowerCase()) 
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name"); return;
		}		
		
//		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
				
		
		// Step 4. d) calling the property here from property file instead of hardcoding

		driver.get(p.getProperty("appURL"));
//		driver.get("https://tutorialsninja.com/demo/");	
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	//User Defined Method
		//To Generate Random String
		
		public String randomstring()
			{
				String randomStr = RandomStringUtils.randomAlphabetic(5);
				return randomStr;
			}
		
		//To Generate Random Number
		
		public String randomnumber()
		{
			//even if it is number it will be in string format only
			String randomNum = RandomStringUtils.randomNumeric(10);
			return randomNum;
		}

		//To Generate AlphaNumberic
		
		public String randomalphanumeric()
		{
			String randomStr = RandomStringUtils.randomAlphabetic(3);
			String randomNum = RandomStringUtils.randomNumeric(3);
			return (randomStr+"@"+randomNum);
		}
		
		//To Capture Screenshot and save it in a location with a timestamp
		public String captureScreenshot( String screenshotName) throws IOException {
			
				String timestamp  = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				TakesScreenshot ts = (TakesScreenshot) driver;
				File sourceFile = ts.getScreenshotAs(OutputType.FILE);
				
				String targetFielPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
				File targetFile = new File(targetFielPath);
				
//				sourceFile.renameTo(targetFile);
				FileUtils.copyFile(sourceFile, targetFile);
				logger.info("Screenshot captured and saved at: " + targetFielPath);				
				return targetFielPath;
				
		}

}
