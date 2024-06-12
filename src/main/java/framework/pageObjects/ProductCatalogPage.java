package framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponent.BaseComponent;

public class ProductCatalogPage extends BaseComponent {
	
	WebDriver driver;
	
	public ProductCatalogPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By productList = By.cssSelector(".mb-3");
	By addProduct = By.cssSelector(".card-body button:last-of-type");
	By toastMsg=By.cssSelector("#toast-container");
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-tns-c31-1.ng-star-inserted")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartLink;
	
	public List<WebElement> getProductList()
	{
		waitUntillElementVisible(productList);
		return products;
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductName(productName);
		prod.findElement(addProduct).click();
		waitUntillElementVisible(productList);
		waitTillElementToDisappear(spinner);
	}
	
	public CartPage navigatetoCartpage()
	{
		cartLink.click();
		CartPage cpage = new CartPage(driver);
		return cpage;
	}

}
