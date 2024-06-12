package framework.pageObjects;

//import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponent.BaseComponent;


public class CartPage extends BaseComponent{


	WebDriver driver;

	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProductList;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutCartBtn;
	
	By checkOutbtn = By.cssSelector(".totalRow button");
	
	public boolean verifyAddedCartProduct(String productName)
	{
		boolean productmatch = cartProductList.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return productmatch;
	}
	
	public PaymentPage checkOutCart()
	{
		waitUntillElementVisible(checkOutbtn);
		checkOutCartBtn.click();
		PaymentPage pmtpage= new PaymentPage(driver);
		return pmtpage;
	}
}
