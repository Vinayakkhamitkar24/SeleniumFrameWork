package framework.tests;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.IOException;

import java.util.List;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

import framework.AbstractComponent.BaseComponent;
import framework.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.ConfirmationPage;
import framework.pageObjects.LoginPage;
import framework.pageObjects.PaymentPage;
import framework.pageObjects.ProductCatalogPage;


public class StandAloneTesting extends BaseTest{

	@Test
	public void placeOrder() throws IOException
	{

		String prodName ="ZARA COAT 3";
		String userName = "rastogi@gmail.com";
		String password = "Vck@240397";
		String countryName="India";
		String confirmationMsg="THANKYOU FOR THE ORDER.";


		PaymentPage pmtpage= new PaymentPage(driver);
		BaseComponent basepage = new BaseComponent(driver);
		ConfirmationPage cnfpage = new ConfirmationPage(driver);

		LoginPage lpage=launchApplication();
		ProductCatalogPage productCatalogPage = lpage.logingToApp(userName, password);

		List<WebElement> products= productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(prodName);
		CartPage cpage =productCatalogPage.navigatetoCartpage();

		boolean productmatch=cpage.verifyAddedCartProduct(prodName);
		assertTrue(productmatch);

		cpage.checkOutCart();

		pmtpage.fillPaymentDetails(countryName);
		pmtpage.placeOrder();

		String cnfMsg= cnfpage.verifyOrderPlacedSuccessfully();
		assertTrue(cnfMsg.equalsIgnoreCase(confirmationMsg));

		basepage.closeBrowser();

	}

}
