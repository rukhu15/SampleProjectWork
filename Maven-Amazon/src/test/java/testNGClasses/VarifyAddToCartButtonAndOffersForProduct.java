package testNGClasses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
import setup.Base;
import utils.UtilityClass;

public class VarifyAddToCartButtonAndOffersForProduct extends Base {
	
	WebDriver driver;
	OnlineShopping onlineShopping;
	SignInPage signInPage;
	HomePage homePage;
	ProductDetailTab productDetailTab;
	ArrayList<String> list;
	JavascriptExecutor js;
	static ExtentReports extent;
	int testID;
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	private String exelData;
	
	@Parameters("browser")
	
	@BeforeTest
	public void launchBrowser(String browserName) {
		reporter = new ExtentHtmlReporter("test-output/extentReporter/Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);

		if(browserName.equals("Chrome")) {
			driver=openChromeBrowser();
		}
		
		if(browserName.equals("Edge")) {
			driver=openEdgeBrowser();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void createPOMObjects() {
		onlineShopping = new OnlineShopping(driver);
		signInPage = new SignInPage(driver);
		homePage = new HomePage(driver);
		productDetailTab = new ProductDetailTab(driver);
		
	}
	
	@BeforeMethod
	public void selectProductYouWantToAdd() throws InterruptedException, IOException {
		
		driver.get("https://www.amazon.in/");
		
		onlineShopping.clickOnAccountAndLists();
		Thread.sleep(2000);
		
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 0, 0);
		signInPage.enterEmailOrMobile(exelData);
		signInPage.clickOnContinueButton();
		
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 4, 2);
		signInPage.enterPassword(exelData);
		signInPage.clickOnSignIn();
		
		homePage.enterItemInSearchBar();
		homePage.clickOnSearchButton();
		homePage.clickOnDesiredItem();
	//	UtilityClass.captureScreenshot(driver);
		
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		
		//Set<String> tabs=driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(1));
	}
	
	@Test(groups= {"Sanity"})
	public void VarifyAddToCartButton() throws InterruptedException, IOException {
		testID=1000;
		productDetailTab.checkNameOfSelectedProdut();
		Thread.sleep(3000);
		productDetailTab.checkPriceOfSelectedProdut();
		productDetailTab.clickOnAddToCart();
		productDetailTab.checkNoOfItemsInCart();
		Thread.sleep(3000);
		productDetailTab.checkSubtotalOfItemsInCart();
		UtilityClass.captureScreenshot(driver,testID);
		
	}
	
	@Test(groups= {"Smoke"})
	public void VarifyOffersForProducts() throws InterruptedException, IOException {
		testID=1500;
		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(2000);
		productDetailTab.clickOnSeeMore();
		productDetailTab.getOffers(); 
		UtilityClass.captureScreenshot(driver,testID);
		
	}
	
	@AfterMethod()
	public void closeTheTabAndLogOut(ITestResult result) throws InterruptedException, IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			UtilityClass.captureScreenshot(driver, testID);
		}
		driver.close();
		list = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(0));
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -700)");
		Thread.sleep(2000);
		onlineShopping.moveToAccountAndLists(driver);
		homePage.clickOnSignOut();
		extent.flush();
	}
	
	@AfterClass
	public void closeTheBrowser() {
		driver.close();
		
	}
}
