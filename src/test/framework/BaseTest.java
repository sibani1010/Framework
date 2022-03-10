package framework;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
   
	
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtenReports extent;
	public static ExtentReports logger;
	
	
	
	@BeforeTest
	
	public void beforeTestMethod(){
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"report"+File.separator+"AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Details");
		htmlReporter.config().setReportName("Auutomation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Sibani Nayak");
		
	}
	
	@BeforeMethod
	@Parameter(value={"browserName"})
	public void beforeMethodMethod(String browserName, Method testMethod){
		logger = extent.createTest(testMethod.getName());
		setUpDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	 public void afterMethodMethod(ITestResult result){
		
		if(result.getStatus() == ITestResult.SUCCESS)
		{
		  String methodName = result.getMethod().getMethodName();
		  String logText = "Test Case" + methodName + "Passed";
		  Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		  logger.log(Status.PASS,m);
		}
		
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			
		String methodName = result.getMethod().getMethodName();
		String logText = "Test Case" + methodName + "Failed";
	    Markup m= MarkupHelper.createLabel(logText, ExtentColor.RED);
	    logger.log(Status.FAIL,m);
			
		}
		
		driver.quit();
	}
	
	
	@AfterTest
  
	public void afterTestMethod{
		
		extent.flush();
		
	}
	
	public void setUpDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator + "drivers" + File.separator +"chromedriver");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ File.separator + "drivers" + File.separator +"geckodriver");
			driver = new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator + "drivers" + File.separator +"chromedriver");
			driver = new ChromeDriver();
		}
	}
}
