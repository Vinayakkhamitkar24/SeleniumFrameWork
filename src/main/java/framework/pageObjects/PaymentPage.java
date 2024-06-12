package framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponent.BaseComponent;

public class PaymentPage extends BaseComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(css=".ta-item span")
	List<WebElement> countryList;
	
	@FindBy(css="[class*='btnn action__submit']")
	WebElement placeOrderBtn;


	By counrtyDropDown = By.cssSelector(".ta-results");

	public void fillPaymentDetails(String country)
	{
		countryInput.sendKeys(country);
		waitUntillElementVisible(counrtyDropDown);

		for(WebElement ele:countryList)
		{
			if(ele.getText().equals("India"))
			{
				ele.click();
				break;
			}
		}
	}
	
	public ConfirmationPage placeOrder()
	{
		placeOrderBtn.click();
		ConfirmationPage cnfpage = new ConfirmationPage(driver);
		return cnfpage;
	}
	
}
