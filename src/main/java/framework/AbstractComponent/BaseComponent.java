package framework.AbstractComponent;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseComponent {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public BaseComponent(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	public void waitUntillElementVisible(By webElement)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
	}
	
	public void waitUntillElementAppear(WebElement webElement)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void waitTillElementToDisappear(WebElement webElement)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}
	

	public void closeBrowser()
	{
		driver.quit();
	}
	
	// Element Locator Functions
    public WebElement findElementByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement findElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    // Element Interaction Functions
    public void click(WebElement element) {
        element.click();
    }

    public void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    // Wait Functions
    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Dropdown Handling Functions
    public void selectOptionByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public void selectOptionByValue(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public void selectOptionByVisibleText(WebElement dropdownElement, String text) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(text);
    }

    public WebElement getSelectedOption(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption();
    }

    public List<WebElement> getAllOptions(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getOptions();
    }

    // Other Functions
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
 // Other Wait Functions
    public void waitForTextPresent(String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(text(),'" + text + "')]"), text));
    }

    // Navigation Functions
    public void goToUrl(String url) {
        driver.get(url);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    // Alert Handling Functions
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void typeTextInPrompt(String text) {
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
    }

    // Screenshot Function
    public void takeScreenshot(String filename) {
        // Implement screenshot capture code here using libraries like WebDriver's TakesScreenshot interface
    }

    // Clear Text Function
    public void clearText(WebElement element) {
        element.clear();
    }


    // Switch to Frame by Frame Index Function
    public void switchToFrameByIndex(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    // Switch to Frame by Frame Name or ID Function
    public void switchToFrameByNameOrId(String frameNameOrId) {
        driver.switchTo().frame(frameNameOrId);
    }
    
 // Generic Wait Function
    public <T> T waitFor(ExpectedCondition<T> condition) {
        return wait.until(condition);
    }

    // Wait Function with Custom Timeout
    public <T> T waitFor(ExpectedCondition<T> condition, Duration timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, timeoutInSeconds);
        return customWait.until(condition);
    }

    // Implicit Wait Function
    public void setImplicitWait(long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }
    
    // Method to retry finding element
    public static WebElement retryFindElement(WebDriver driver, By locator ) {
        int attempts = 0;
        final int maxAttempts = 3; // Max number of retry attempts
        
        while (attempts < maxAttempts) {
            try {
                // Wait for the element to be present
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (StaleElementReferenceException e) {
                // Increment attempt count and retry
                attempts++;
            }
        }
        
        throw new RuntimeException("Failed to locate element after " + maxAttempts + " attempts");
    }

}
