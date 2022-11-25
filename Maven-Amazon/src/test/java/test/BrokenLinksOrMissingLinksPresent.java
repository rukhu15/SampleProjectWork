package test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import pages.OnlineShopping;
import pages.SignInPage;
import utils.UtilityClass;


public class BrokenLinksOrMissingLinksPresent {
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium material\\chromedriver_win32\\chrome92 version\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(4000, 5000));
		driver.get("https://www.amazon.in/");
		
		Thread.sleep(2000);
		List<WebElement> linkTagname=driver.findElements(By.tagName("a"));
		System.out.println(linkTagname.size());
		int count=0;
		String urlLink="";
		Thread.sleep(2000);
		try {
			for(int index=0; index<linkTagname.size(); index++) {
				urlLink=linkTagname.get(index).getAttribute("href");
			}
			
			URL url = new URL(urlLink);
			
			HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.connect();
			if(httpURLConnection.getResponseCode()>=400) {
				count++;
				System.out.println("broken links "+url);
			}else{
				
			}
			}catch(Exception e){
				
			}finally {
				
			}
		System.out.println(count);
		}
	}

