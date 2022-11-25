package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindAllconten {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src//test//resources//browsers//chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		String url=driver.getCurrentUrl();
		System.out.println(url);
		int count=0;
		List<WebElement> linkTagname=driver.findElements(By.tagName("a"));
		System.out.println(linkTagname.size());
		String urlLink="";
		Thread.sleep(2000);
		int missingLinkCount=0;
		try {
			for(int index=0; index<linkTagname.size(); index++) {
				urlLink=linkTagname.get(index).getAttribute("href");
				try {
					if(urlLink.equals("") || urlLink.isEmpty() || urlLink.equals(null)) {
						
					}
				}catch(NullPointerException np) {
					missingLinkCount++;
				}
			}
			System.out.println(missingLinkCount);
			URL urlconnect = new URL(urlLink);
			
			HttpURLConnection httpURLConnection= (HttpURLConnection) urlconnect.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.connect();
			if(httpURLConnection.getResponseCode()>=400) {
				count++;
				System.out.println(httpURLConnection.getResponseCode());
				System.out.println("broken links "+url);
			}else{
				
			}
			}catch(Exception e){
				
			}finally {
				
			}
		System.out.println(count);
		}
	}

