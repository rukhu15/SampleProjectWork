package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import pages.HomePage;
import pages.OnlineShopping;
import pages.ProductDetailTab;
import pages.SignInPage;
import utils.UtilityClass;

public class VarifyItemAddedToCart {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium material\\chromedriver_win32\\chrome92 version\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		OnlineShopping onlineShopping = new OnlineShopping(driver);
		onlineShopping.clickOnAccountAndLists();
		Thread.sleep(2000);
		
		SignInPage signInPage = new SignInPage(driver);
		String exelData = UtilityClass.getDataFromExelSheet("Amazon", 0, 0);
		signInPage.enterEmailOrMobile(exelData);
		signInPage.clickOnContinueButton();
		
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 4, 2);
		signInPage.enterPassword(exelData);
		signInPage.clickOnSignIn();
		signInPage.clickOnSignIn();
		
		HomePage homePage = new HomePage(driver);
		homePage.enterItemInSearchBar();
		homePage.clickOnSearchButton();
		homePage.clickOnDesiredItem();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		
		Set<String> tabs=driver.getWindowHandles();
		ArrayList<String> list= new ArrayList<String>(tabs);
		driver.switchTo().window(list.get(1));
		
		ProductDetailTab productDetailTab = new ProductDetailTab(driver);
		productDetailTab.checkNameOfSelectedProdut();
		Thread.sleep(3000);
		productDetailTab.checkPriceOfSelectedProdut();
		productDetailTab.clickOnAddToCart();
		productDetailTab.checkNoOfItemsInCart();
		Thread.sleep(3000);
		productDetailTab.checkSubtotalOfItemsInCart();
		
	}
}
