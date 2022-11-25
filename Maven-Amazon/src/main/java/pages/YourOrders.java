package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class YourOrders {
	
	@FindBy (xpath = "//select[@id='time-filter']")
	private WebElement Year;
	
	@FindBy (xpath = "//div[@class=\"a-box shipment\"]") private List<WebElement> itemList;
	
	
	public YourOrders(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnYear() {
		Select select = new Select(Year);
		select.selectByValue("year-2019");
	}
	
	public int getTextOfAllElements() {
		System.out.println("Total no. of items ordered in 2019 = "+itemList.size()+" items");
		System.out.println("List of Items ordered in 2019");
		for(int index=0; index<itemList.size(); index++) {
			String item=itemList.get(index).getText();
			System.out.println();
			System.out.println(item);
			System.out.println();
		}
		return itemList.size();
	}
	
}
