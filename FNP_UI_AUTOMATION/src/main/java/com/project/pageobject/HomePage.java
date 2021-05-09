package com.project.pageobject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.helper.ui.UIHelperClass;
import com.project.utils.EnvironmentConfig;

public class HomePage {

	WebDriver driver;
	UIHelperClass helperClass;
	SoftAssert softAssert=new SoftAssert();
	Assertion HAssert = new Assertion();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver , this);
		helperClass =new UIHelperClass(driver);
	}
	
	@FindBy(xpath= "//input[@id='fnpsearch']")
	public WebElement searchBox;
	
	@FindBy(xpath= "//form[@id= 'searchform']//child::button")
	public WebElement searchButton;
	
	@FindBy(xpath= "//form[@id= 'searchhintlist']")
	public WebElement searchSuggestionList;
	
	@FindBy(xpath= "(//ul[@id='searchhintlist']//a)[1]")
	public WebElement firstSearchSuggestion;
	
	@FindBy(xpath= "//iframe[contains(@name, 'notification-frame')]")
	public WebElement iframe;
	
	@FindBy(xpath= "//i[@class= 'wewidgeticon we_close']")
	public WebElement widgetCloseButton;
	
	@FindBy(xpath= "//span[@id='pincode-value']")
	public WebElement selectDeliveryLocation;
	
	@FindBy(xpath= "//div[@id= 'location-container']//div[@class = 'location-subheading']//preceding-sibling::div")
	public WebElement deliveryLocationHeader;
	
	@FindBy(xpath= "//label[@id= 'optn-indian-catalog']//input[@id = 'indian-catalog']")
	public WebElement withinIndia;
	
	@FindBy(xpath= "//label[@id= 'optn-not-india-catalog']//input[@id = 'not-india-catalog']")
	public WebElement outsideIndia;
	
	@FindBy(xpath= "//input[@id='pincode-suggestions']")
	public WebElement pincodeTextBox;
	
	@FindBy(xpath= "//li[@class='ui-menu-item']/div[1]")
	public WebElement firstPincodeSuggestion;
	
	@FindBy(xpath= "//span[@id='selectedPincode']")
	public WebElement selectedPincode;
	
	@FindBy(xpath= "//span[@class='continue-content']")
	public WebElement continueButton;
	
	@FindBy(id= "subscribeemailId")
	public WebElement subscribeToMail;
	
	@FindBy(xpath= "//input[@id='subscribeemailId']/..//preceding-sibling::h6")
	public WebElement subscribeMailHeader;
	
	@FindBy(xpath= "//span[text()= 'Cosmetics Hampers']")
	public WebElement cosmeticHampers;
	
	public void searchItem() {
		searchBox.sendKeys("Flowers");
		helperClass.explicitWait(firstSearchSuggestion);
		firstSearchSuggestion.click();
		//closeAlert();
	}
	
	
	public void closeAlert() {
		helperClass.explicitWait(iframe);
		driver.switchTo().frame(iframe);
		widgetCloseButton.click();
	}
	
	public void verifyDeliveryAddress() {
		helperClass.explicitWait(selectDeliveryLocation);
		selectDeliveryLocation.click();
		
		helperClass.explicitWait(deliveryLocationHeader);
		String popupHeader=deliveryLocationHeader.getText();
		
		softAssert.assertEquals(popupHeader, "Choose your Delivery Location");
		System.out.println(withinIndia.getAttribute("value"));
		
		String userPincode=EnvironmentConfig.getInstance().getProperty("pincode");
		pincodeTextBox.sendKeys(EnvironmentConfig.getInstance().getProperty("pincode"));
		helperClass.explicitWait(firstPincodeSuggestion);
		firstPincodeSuggestion.click();
		
		helperClass.explicitWait(selectedPincode);
		String selectedPinodeUI=selectedPincode.getText();
		softAssert.assertEquals(selectedPinodeUI.contains(userPincode), "Pincode selected is not correct");
		helperClass.explicitWait(continueButton);
		helperClass.fluentWait("CONTINUE");
		continueButton.click();
		
		helperClass.scrollTillVisibleText(subscribeToMail);
		try {
			helperClass.takeScreenshot();
		} catch(IOException e) {
			System.out.println("Error while storing image");
		}
		
		//helperClass.scrollToTop();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//helperClass.scrollDownTillBottomOfPage();
		
		helperClass.keyBoardAndMouseAction(cosmeticHampers);
		
	}
	
}
