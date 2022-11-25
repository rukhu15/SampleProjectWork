package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlineShopping {
	
	@FindBy (xpath = "(//div[@id='nav-tools']//a)[2]")
	private WebElement accountAndLists;
	
	@FindBy (xpath = "//span[text()='Returns']") 
	private WebElement returnsAndOrders;
	
	@FindBy (xpath = "//div[@id='nav-cart-count-container']") 
	private WebElement cart;
	
	@FindBy (xpath = "//span[@id='glow-ingress-line1']") 
	private WebElement user;
	
	@FindBy (xpath = "//span[@id='glow-ingress-line2']") 
	private WebElement deliveryAdress;
	
	@FindBy (xpath = "//span[@class='icp-nav-language']") 
	private WebElement language;
	
// @FindBy (xpath = "(//div[@id='nav-tools']//a)[2]") private WebElement signIn;
// @FindBy (xpath = "(//div[@id='nav-tools']//a)[2]") private WebElement signIn;
 	
 
 	public OnlineShopping(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
 	
 	public void clickOnAccountAndLists() {
 		accountAndLists.click();
 	}
 	
 	public void moveToAccountAndLists(WebDriver driver) {
 		Actions act = new Actions(driver);
 		act.moveToElement(accountAndLists).perform();
 	}
}
