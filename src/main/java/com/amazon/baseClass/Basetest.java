package com.amazon.baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import com.amazon.pageObjectRepository.PageClass;
import com.amazon.utilClass.Utilclass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

public class Basetest {

	public static AndroidDriver<MobileElement> driver;
	public DesiredCapabilities cap;
	public static Properties prop;
	public static Utilclass Utilobj;
	public static PageClass pageobj;

	@BeforeTest
	public void setup() throws IOException {
		cap = new DesiredCapabilities();
		prop = new Properties();
		Utilobj = new Utilclass();
		try {
			FileInputStream f = new FileInputStream("./src/main/java/com/amazon/data/configu.properties");
			prop.load(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("DEVICE_NAME"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("PLATFORM_NAME"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, Utilobj.Androidversion);
		cap.setCapability("appPackage", prop.getProperty("appPackage"));
		cap.setCapability("appActivity", prop.getProperty("appActivity"));
		cap.setCapability("autoLaunch", true);
		cap.setCapability("noReset", true);
		driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("URL")), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		pageobj = new PageClass();
		PageFactory.initElements(new AppiumFieldDecorator(driver), pageobj);

	}
}
