package framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponent.BaseComponent;

public class ConfirmationPage extends BaseComponent{

	WebDriver driver;

	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;

	public String verifyOrderPlacedSuccessfully()
	{
		String confirmMsg = confirmationMsg.getText(); 
		return confirmMsg;
	}

}
