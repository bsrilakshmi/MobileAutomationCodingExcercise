package TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.baseClass.Basetest;
import com.amazon.pageObjectRepository.PageClass;

import io.appium.java_client.MobileElement;

public class OrderingTv extends Basetest {

	@Test(priority = 0)
	public void searchingIteamTest() {

		System.out.println("Searching   " + prop.getProperty("IteamName") + "  product");

		String filteropt = pageobj.searchingAnIteam(prop.getProperty("IteamName") + "\\n");

		if (filteropt.equalsIgnoreCase("Sponsored")) {
			Assert.assertEquals("Sponsored", filteropt);

		} else if (filteropt.equalsIgnoreCase("Filter")) {
			Assert.assertEquals("Filter", filteropt);

		} else {
			Assert.assertTrue(filteropt.contains("TCL"));
		}
	}

	@Test(priority = 1)
	public void selectingParticularIteamTest() {
		String productName = pageobj.selectingParticularIteam();
		Assert.assertEquals(productName.contains("Samsung 163 cm (65 Inches"), true);
		System.out.println("Selected Product Randomly ");
	}

	@Test(priority = 2)
	public void selectingIteamAddingToCart() {
		String proceed = pageobj.selectedIteamtoAddToCart();
		pageobj.scBuyBoxList();
		Assert.assertEquals("Proceed to Buy", proceed);

		System.out.println("Selected product added to CART ");
	}

	@Test(priority = 3)
	public void proceedingToBuyTest() {

		List<MobileElement> productdetails = pageobj.scBuyBoxList();
		String productName = pageobj.getProductName();
		String productCost = pageobj.getProductCost();
		for (MobileElement ele : productdetails) {
			String details = ele.getText();
			if (details.contains(productName) || details.contains(productCost)) {
				pageobj.proceedToBuyEle().click();
				break;
			}

		}
		System.out.println("Product proceed to Buy ");
	}

}
