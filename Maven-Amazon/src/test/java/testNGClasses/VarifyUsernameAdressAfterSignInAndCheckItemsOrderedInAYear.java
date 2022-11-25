package testNGClasses;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.HomePage;
import pages.OnlineShopping;
import pages.ProductDetailTab;
import pages.SignInPage;
import pages.YourOrders;
import setup.Base;
import utils.UtilityClass;

public class VarifyUsernameAdressAfterSignInAndCheckItemsOrderedInAYear extends Base {
	
	WebDriver driver;
	OnlineShopping onlineShopping;
	SignInPage signInPage;
	HomePage homePage;
	private int testId;
	static ExtentTest test;
	static ExtentReports extent;
	static ExtentHtmlReporter reporter;
	private String exelData;
	
	@Parameters("browser")
	@BeforeTest
	public void launchBrowser(String BrowserName) {
		reporter = new ExtentHtmlReporter("test-output/extentReporter/Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		if(BrowserName.equals("Chrome")) {
			driver=openChromeBrowser();
		}
		
		if(BrowserName.equals("Edge")) {
			driver=openEdgeBrowser();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void createPOMObjects() {
		onlineShopping = new OnlineShopping(driver);
		signInPage = new SignInPage(driver);
		homePage = new HomePage(driver);
	}

	
	@BeforeMethod
	public void login() throws InterruptedException, EncryptedDocumentException, IOException {
		driver.get("https://www.amazon.in/");
		
		onlineShopping.clickOnAccountAndLists();
		
		Thread.sleep(2000);
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 0, 0);
		signInPage.enterEmailOrMobile(exelData);
		signInPage.clickOnContinueButton();
		
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 4, 2);
		signInPage.enterPassword(exelData);
		signInPage.clickOnSignIn();
		//signInPage.clickOnSignIn();
		
	}
	
	@Test
	public void VarifyUsernameAfterLogin() throws IOException {
		testId=2000;
		String username = homePage.getTextOfHelloUser();
		String expected="Hello, Rukhmini";
		UtilityClass.captureScreenshot(driver,testId);
		Assert.assertEquals(username, expected);
	}
	
	@Test(priority=-1)
	public void checkItemsOrderedInAYear() throws IOException {
		homePage.clickOnReturnsAndOrders();
		testId=2500;
		YourOrders yourOrders = new YourOrders(driver);
		yourOrders.clickOnYear();
		int ActualOrders=yourOrders.getTextOfAllElements();
		int Expected=5;
		UtilityClass.captureScreenshot(driver,testId);
		Assert.assertEquals(ActualOrders, Expected);
		
	}
	
	@AfterMethod
	public void logOut(ITestResult result) throws IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			UtilityClass.captureScreenshot(driver, testId);
		}
		onlineShopping.moveToAccountAndLists(driver);
		homePage.clickOnSignOut();
		extent.flush();
	}
	
	@AfterClass
	public void nullobjectReferences() {
		onlineShopping = null;
		signInPage = null;
		homePage = null;	
	}
	
	@AfterTest
	public void closeTheBrowser() {
		driver.close();
	}
}
