package com.org.cnnexpansion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driverinitialize {
	public WebDriver WDf,WDc;
	//constructor initializing drivers
	public WebDriver FFDriverinitialize(){
	 WDf = new FirefoxDriver();
	 return WDf;
	}
	public WebDriver ChromeDriverinitialize(){
	System.setProperty("Webdriver.chrome.driver", "C:\\browserdrivers\\chrome");
	 WDc = new ChromeDriver();
	 return WDc;
	}
}
