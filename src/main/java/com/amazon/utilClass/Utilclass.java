package com.amazon.utilClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.baseClass.Basetest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utilclass extends Basetest {

	public String Androidversion;

	/****************** To get device platform version ****************************/
	public String plaformVersion() {
		try {
			Process p1 = Runtime.getRuntime().exec("adb shell getprop ro.build.version.release");
			BufferedReader version = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			while ((Androidversion = version.readLine()) != null) {
				System.out.println("Android Version is::::" + Androidversion);
			}
			version.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Androidversion;
	}

	/******************** To scroll the Screen ****************************/
	public void scrolling() {
		Dimension d = driver.manage().window().getSize();
		int starty = (int) (d.height * 0.99);
		int endy = (int) (d.height * 0.10);
		int startx = d.width / 2;

		for (int i = 0; i <= 2; i++) {
			new TouchAction(driver)
					.longPress(new LongPressOptions().withPosition(PointOption.point(startx, starty))
							.withDuration(Duration.ofMillis(4000)))
					.moveTo(PointOption.point(100, endy)).release().perform();
		}
	}

	/******************
	 * To wait until element to be click
	 **************************/
	public void waitUnntilParticularElementTobeClickable(MobileElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

}
