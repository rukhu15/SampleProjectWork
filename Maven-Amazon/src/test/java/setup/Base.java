package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Base {
	
	public static WebDriver openChromeBrowser() {
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium material\\chromedriver_win32\\chrome92 version\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "src//test//resources//browsers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver openEdgeBrowser() {
		//System.setProperty("webdriver.edge.driver", "D:\\Selenium material\\chromedriver_win32\\edgedriver_win64\\msedgedriver.exe");
		System.setProperty("webdriver.edge.driver", "src//test//resources//browsers//msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		return driver;
	}
}
