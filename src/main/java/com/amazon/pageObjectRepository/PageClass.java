package com.amazon.pageObjectRepository;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.baseClass.Basetest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageClass extends Basetest {
	String costofProduct;
	String NameofProduct;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Search\")")
	MobileElement searchoption;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"Filter\")]")
	MobileElement filteroption;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Add to Cart')]")
	MobileElement AddCartbutton;

	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV\"))")
	MobileElement particularTv;

	@AndroidFindBy(xpath = "//android.view.View[contains(@text,\"Samsung 163 cm (65 Inches)\")]")
	MobileElement productName;

	@AndroidFindBy(xpath = "//android.view.View[@id='buybox.addToCart']")
	MobileElement ProductAddedtoCart;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.amazon.mShop.android.shopping:id/chrome_action_bar_cart\")")
	MobileElement selectedIteamCount;

	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'rupees')]")
	MobileElement productPrice;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\")")
	List<MobileElement> productDetailsAfteraddingtoCart;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed to Buy']")
	MobileElement ProceedToBuy;

	/******* To Search Particular product ************/
	public String searchingAnIteam(String iteamName) {
		searchoption.sendKeys(iteamName);
		return filteroption.getText();
	}

	/***** To Select Product randomly ********/
	public String selectingParticularIteam() {
		particularTv.click();
		return productName.getText();
	}

	public MobileElement selectingIteamCountEle() {
		return selectedIteamCount;
	}

	/******** To add selected product to Cart *********/
	public String selectedIteamtoAddToCart() {
		costofProduct = productPrice.getText();
		NameofProduct = productName.getText();

		System.out.println("Product Name:::: " + NameofProduct);
		System.out.println("Producet Price:::" + productPrice.getText());

		/****** Scrolling Screen until Add Cart button is visible ******/
		Utilobj.scrolling();

		try {
			Utilobj.waitUnntilParticularElementTobeClickable(AddCartbutton);
			AddCartbutton.click();
		} catch (Exception e) {
			Utilobj.scrolling();
			AddCartbutton.click();
		}

		/*********** To Navigate to proceed to buy screen *******************/
		selectedIteamCount.click();

		return ProceedToBuy.getText();
	}

	/**********
	 * To get product details in proceeding to buy screen
	 *********************/
	public List<MobileElement> scBuyBoxList() {
		return productDetailsAfteraddingtoCart;
	}

	/******************* To get product name **********/
	public String getProductName() {
		return NameofProduct;
	}

	/**************** To get product cost ******************/
	public String getProductCost() {
		return costofProduct;
	}

	/************** To proceed to buy product *************/
	public MobileElement proceedToBuyEle() {
		return ProceedToBuy;
	}

}
