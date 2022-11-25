package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailTab {
	@FindBy (xpath = "//span[@id='productTitle']")
	private WebElement productTitle;
	
	@FindBy (xpath = "(//span[@class='a-offscreen'])[6]")
	private WebElement productPrice;
	
	@FindBy (xpath = "//input[@id='add-to-cart-button']")
	private WebElement addToCart;
	
	@FindBy (xpath = "//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")
	private WebElement addedToCart;

	@FindBy (xpath = "//span[contains(text(), 'item')]")
	private WebElement noOfItemsInCart;
	
	@FindBy (xpath = "//span[@id='attach-accessory-cart-subtotal']")
	private WebElement cartSubtotal;
	
	@FindBy (xpath = "(//span[contains(text(), ' more')])[1]")
	private WebElement seeMore;
	
	@FindBy (xpath = "//div[@class='a-section a-spacing-none sopp-offer-container']")
	private List<WebElement> offers;
	
	
	public ProductDetailTab(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void checkNameOfSelectedProdut() {
		System.out.println(productTitle.getText());
	}
	
	public void checkPriceOfSelectedProdut() {
		System.out.println(productPrice.getText());
	}
	
	public void clickOnAddToCart() {
		addToCart.click();
	}
	
	public void getOffers() {
		for(int index=0; index<offers.size()-1; index++) {
			System.out.println(offers.get(index).getText());
		}
	}
	
	public void clickOnSeeMore(){
		seeMore.click();	
	}
	
	public void checkNoOfItemsInCart() {
		noOfItemsInCart.getText();	
	}
	
	public void checkSubtotalOfItemsInCart(){
		cartSubtotal.getText();
	}
}
