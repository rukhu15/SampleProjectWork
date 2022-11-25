package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.OnlineShopping;
import pages.SignInPage;
import pages.YourOrders;
import utils.UtilityClass;

public class CheckItemsOrderedInAYear {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium material\\chromedriver_win32\\chrome92 version\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		Thread.sleep(2000);
		
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
		homePage.clickOnReturnsAndOrders();
		
		YourOrders yourOrders = new YourOrders(driver);
		yourOrders.clickOnYear();
		int ActualOrders=yourOrders.getTextOfAllElements();
		int Expected=5;
		if(ActualOrders==Expected) {
			System.out.println("Passed");
		}
		
	}

}
