package framework.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseComponent {
	
	public WebDriver driver;
	public BaseComponent(WebDriver driver) {
		this.driver=driver;
	}

	public void waitUntillElementVisible(By webElement)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
	}
	
	public void waitUntillElementAppear(WebElement webElement)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void waitTillElementToDisappear(WebElement webElement)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}


}
