package framework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import framework.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.ConfirmationPage;
import framework.pageObjects.LoginPage;
import framework.pageObjects.PaymentPage;
import framework.pageObjects.ProductCatalogPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseOrderTest extends BaseTest {
	
	public ProductCatalogPage productCatalogPage;
	public ConfirmationPage cnfpage;
	public PaymentPage pmtpage;
	public LoginPage lpage;
	public CartPage cpage;
	public BaseTest bt;
	
	
	
	
	@Given("^I landed on Ecommerce page$")
    public void i_landed_on_ecommerce_page() throws IOException   {
		
		lpage = launchApplication();
		
    }
	
	@Given("^Logged in with username(.+)and password(.+)$")
    public void logged_in_with_username_and_password(String name, String password) throws Throwable {
        
		productCatalogPage = lpage.logingToApp(name,password);
    }
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_zara_coat_to_cart(String ProductName) {
	   
		List<WebElement> products= productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(ProductName);
	}

    @Then("^\"([^\"]*)\" message displayed on confirmation page$")
    public void something_message_displayed_on_confirmation_page(String confirmationMsg) throws Throwable {
        
    	String cnfMsg= cnfpage.verifyOrderPlacedSuccessfully();
		Assert.assertTrue(cnfMsg.equalsIgnoreCase(confirmationMsg));
    }

    @And("^Checkout (.+) and submit order$")
    public void checkout_and_submit_order(String productname) throws Throwable {
        
    	cpage =productCatalogPage.navigatetoCartpage();

		boolean productmatch=cpage.verifyAddedCartProduct(productname);
		Assert.assertTrue(productmatch);

		pmtpage=cpage.checkOutCart();
		pmtpage.fillPaymentDetails("India");
		cnfpage=pmtpage.placeOrder();
    }
}
