package framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponent.BaseComponent;

public class LoginPage extends BaseComponent{
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="toast-message")
	WebElement errorMsg;
	
	public void navigateToAppUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogPage logingToApp(String user , String pass)
	{
		userEmail.sendKeys(user);
		password.sendKeys(pass);
		loginBtn.click();
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		return productCatalogPage;
	}
	
	public String getErrorMsg()
	{
		waitUntillElementAppear(errorMsg);
		return errorMsg.getText();
	}

}
