package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.OnlineShopping;
import pages.SignInPage;
import utils.UtilityClass;

public class VarifyUserNameAfterSignIn {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium material\\chromedriver_win32\\chrome92 version\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		OnlineShopping onlineShopping = new OnlineShopping(driver);
		onlineShopping.clickOnAccountAndLists();
		
		SignInPage signInPage = new SignInPage(driver);
		String exelData = UtilityClass.getDataFromExelSheet("Amazon", 0, 0);
		signInPage.enterEmailOrMobile(exelData);
		signInPage.clickOnContinueButton();
		
		exelData=UtilityClass.getDataFromExelSheet("Amazon", 4, 2);
		signInPage.enterPassword(exelData);
		signInPage.clickOnSignIn();
		signInPage.clickOnSignIn();
		
		HomePage homePage = new HomePage(driver);
		String username = homePage.getTextOfHelloUser();
		System.out.println(username);
		
		onlineShopping.moveToAccountAndLists(driver);
		homePage.clickOnSignOut();
		
		driver.close();
	}
}
