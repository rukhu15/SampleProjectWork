package pages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.UtilityClass;

public class SignInPage {
	@FindBy (xpath = "//input[@id='ap_email']") private WebElement  emailOrMobile;
	@FindBy (xpath = "//input[@id='continue']") private WebElement  continueButton;
	@FindBy (xpath = "//input[@id='ap_password']") private WebElement  password;
	@FindBy (xpath = "//input[@id='signInSubmit']") private WebElement  signIn;
	
	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	 
	public void enterEmailOrMobile(String mobileNo) throws EncryptedDocumentException, IOException {
		 emailOrMobile.sendKeys(mobileNo);
	 }
	
	public void clickOnContinueButton() {
		continueButton.click();
	 }
	
	public void enterPassword(String pass) {
		password.sendKeys(pass);
	 }
	
	public void clickOnSignIn() {
		signIn.click();
	 }
}
