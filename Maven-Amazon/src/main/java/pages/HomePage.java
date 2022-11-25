package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy (xpath = "//input[@id='twotabsearchtextbox']")
	 private WebElement searchBar;
	
	@FindBy (xpath = "//input[@id='nav-search-submit-button']")
	 private WebElement searchButton;
	
	@FindBy (xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
	 private List<WebElement> searchResult;
	
	 @FindBy (xpath = "//span[text()='Returns']")
	 private WebElement returnsAndOrders;
	
	 @FindBy (xpath = "//div[@id='nav-cart-count-container']") 
	 private WebElement cart;
	 
	 
	 @FindBy (xpath = "//span[@class='icp-nav-language']") 
	 private WebElement language;
	 
	 @FindBy (xpath = "//span[text()='Account & Lists']") //span[@class='nav-line-2 ']
	 private WebElement accountAndList;
	 
	 @FindBy (xpath = "(//span[text()='All'])[2]") 
	 private WebElement all;
	 
	 @FindBy (xpath = "//a[text()='Coupons']") 
	 private WebElement coupons;
	 
	 @FindBy (xpath = "//a[text()='Gift Cards']") 
	 private WebElement giftCards;
	 
	 @FindBy (xpath = "//span[@id='nav-link-accountList-nav-line-1']") 
	 private WebElement helloUser;
	
	 @FindBy (xpath = "//span[@id='glow-ingress-line1']") 
	 private WebElement deliverToUser;
	 
	 @FindBy (xpath = "//span[@id='glow-ingress-line2']") 
	 private WebElement deliveryAdress;
	
	 @FindBy (xpath = "//span[text()='Sign Out']") 
	 private WebElement signOut;
	 
	 public void clickOnSignOut() {
		 signOut.click();
		}
	 public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	 
	 public String getTextOfHelloUser() {
		String username= helloUser.getText();
		return username;
	 }
	 
	 public String getTextOfDeliveryAdress() {
			String userAdress= deliveryAdress.getText();
			return userAdress;
		 }
	 
	public void clickOnReturnsAndOrders() {
		returnsAndOrders.click();
	}
	
	public void enterItemInSearchBar() {
		searchBar.sendKeys("laptop");
	}
	
	public void clickOnDesiredItem() {
		searchResult.get(1).click();	
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	public void moveToAccountsAndLists() {
		accountAndList.click();
	}
}
