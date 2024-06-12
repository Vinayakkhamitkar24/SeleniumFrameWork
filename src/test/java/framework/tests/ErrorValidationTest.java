package framework.tests;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import framework.AbstractComponent.BaseComponent;
import framework.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.ProductCatalogPage;

public class ErrorValidationTest extends BaseTest{
	
	@Test
	public void loginErrorValidation()
	{
		
		lpage.logingToApp("anshika@gmail.com","Iamki000");
		Assert.assertEquals("Incorrect email  password.",lpage.getErrorMsg());
	}
	
	@Test
	public void productErrorValidation()throws IOException
	{

		String prodName ="ZARA COAT 3";
		String userName = "rastogi@gmail.com";
		String password = "Vck@240397";

		BaseComponent basepage = new BaseComponent(driver);
		ProductCatalogPage productCatalogPage = lpage.logingToApp(userName, password);

		List<WebElement> products= productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(prodName);
		CartPage cpage =productCatalogPage.navigatetoCartpage();

		boolean productmatch=cpage.verifyAddedCartProduct("ZARA COAT 333");
		Assert.assertFalse(productmatch);

	}

}
