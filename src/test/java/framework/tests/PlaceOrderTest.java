package framework.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import framework.AbstractComponent.BaseComponent;
import framework.TestComponents.BaseTest;
import framework.pageObjects.CartPage;
import framework.pageObjects.ConfirmationPage;
import framework.pageObjects.PaymentPage;
import framework.pageObjects.ProductCatalogPage;

public class PlaceOrderTest extends BaseTest{

	@Test(dataProvider="getData" , groups={"PlaceOrder"})
	public void placeOrder(HashMap<String,String> input)throws IOException
	{
		String countryName="India";
		String confirmationMsg="THANKYOU FOR THE ORDER.";

		BaseComponent basepage = new BaseComponent(driver);
		ProductCatalogPage productCatalogPage = lpage.logingToApp(input.get("email"),input.get("pass"));

		List<WebElement> products= productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(input.get("prod"));
		CartPage cpage =productCatalogPage.navigatetoCartpage();

		boolean productmatch=cpage.verifyAddedCartProduct(input.get("prod"));
		Assert.assertTrue(productmatch);

		PaymentPage pmtpage=cpage.checkOutCart();
		pmtpage.fillPaymentDetails(countryName);
		ConfirmationPage cnfpage=pmtpage.placeOrder();

		String cnfMsg= cnfpage.verifyOrderPlacedSuccessfully();
		Assert.assertTrue(cnfMsg.equalsIgnoreCase(confirmationMsg));

	}


	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\resources\\framework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	/*
	@DataProvider
	public Object[][] getData() throws IOException
	{

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "rastogi@gmail.com");
		map.put("pass", "Vck@240397");
		map.put("prod", "ZARA COAT 3");

		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("pass", "Iamking@000");
		map1.put("prod", "ADIDAS ORIGINAL");
    }*/
	/*
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"rastogi@gmail.com","Vck@240397","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
}
