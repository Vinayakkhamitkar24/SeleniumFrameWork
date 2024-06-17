/*
1.Navigate to the Flipkart application: Open the Flipkart website or application and navigate to the page where you can search for products and add them to the cart.

2.Search for the pen: Use the search functionality to search for the pen that you want to buy.

3.Add the pen to the cart twice: Simulate adding the pen to the cart two times by clicking the "Add to Cart" button twice.

4.Navigate to the cart: Go to the cart page where you can view the items added to the cart.

5.Verify the cart contents:

6.Check that only one entry for the pen is displayed in the cart.
7. Verify that the quantity of the pen is displayed correctly as 2.
8. Perform assertions: Write assertions to verify that the cart displays the correct item with the correct quantity.
 */

package framework.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import framework.TestComponents.BaseTest;

public class FlipkartCartTest extends BaseTest{
	public static void main(String[] args) throws InterruptedException {

		int count=3;
		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// Navigate to Flipkart
		driver.get("https://www.flipkart.com");

		driver.manage().window().maximize();

		// Search for the pen
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("pen");
		searchBox.submit();

		//Click on Random product from available list
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@data-id,'PEN')]/div/a[2]"));
		int randomIndex = new Random().nextInt(products.size());

		WebElement randomProduct = products.get(randomIndex);

		Actions actions = new Actions(driver);
		actions.moveToElement(randomProduct);
		actions.build().perform();
		String productTitle = randomProduct.getText();

		System.out.println(productTitle);
		
		

		//String productTitle = randomProduct.findElement(By.xpath("/a[2]")).getText();
		randomProduct.click();

		// Navigate to selected product page
		Thread.sleep(1000);

		String parentPage =driver.getWindowHandle();

		// Switch between windows
		for (String childPage : driver.getWindowHandles()) {

			if(!parentPage.equals(childPage))
			{
				driver.switchTo().window(childPage);
				Thread.sleep(2000);

				String title =driver.getTitle();
				System.out.println(title);

				Thread.sleep(2000);
				// Check if it is the desired window
				if (title.contains(productTitle)) {

					WebElement goToCartBtn=driver.findElement(By.cssSelector(".In9uk2"));
					goToCartBtn.click();

					Thread.sleep(2000);

					WebElement cartItemIncrement = driver.findElement(By.xpath("//div[@class='p2uyH2']//button[2]"));
					int counter=1;
					for(int i=0;i<count;i++)
					{
						cartItemIncrement.click();
						Thread.sleep(2000);
						counter++;
						String toastMessage = driver.findElement(By.xpath("//div[@class='eIDgeN']")).getText();
						Thread.sleep(2000);
						System.out.println(toastMessage);
						assertTrue(toastMessage.contains("QUANTITY to '"+counter+"'"));

					}

					break;
				}
			}

		}

		driver.quit();
	}
}
