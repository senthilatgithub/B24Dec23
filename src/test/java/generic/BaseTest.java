package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConst{
	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert softAssert;
	public static ExtentReports report;
	static 
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
	}
	

	@BeforeSuite
	public void initReport() {
		report=new ExtentReports();
		ExtentSparkReporter format=new ExtentSparkReporter(REPORT_PATH);
		report.attachReporter(format);
	}
	
	@AfterSuite
	public void endReport() {
		report.flush();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void openApp() throws Exception {
	//this is for local system
//		driver=new ChromeDriver();
		
		URL remoteURL=new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities capability=new DesiredCapabilities();
		capability.setBrowserName("chrome");
		driver=new RemoteWebDriver(remoteURL, capability);
		
		long ETO=Long.parseLong(Property.getProperty(PPT_PATH,"ETO"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Setting ETO:"+ETO,true);
		
		long ITO=Long.parseLong(Property.getProperty(PPT_PATH,"ITO"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		
		String App_URL=Property.getProperty(PPT_PATH,"URL");
		driver.get(App_URL);//if url is diff for each build
//		driver.get(APP_URL); from interface when all Build has same URL
		
		
		driver.manage().window().maximize();
		
		softAssert=new SoftAssert();
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeApp() {
		Reporter.log("Closing the Browser",true);
		driver.quit();
	}
	
}
