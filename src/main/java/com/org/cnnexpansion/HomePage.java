package com.org.cnnexpansion;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends LaunchBrowser{
	public int countpresent,brokenimage =0;
	public int countnotpresent =0;
	public WebDriver WD;
	
	public void launchbrowser(WebDriver W, ExtentReports report){
	String Baseurl = "http://www.cnn.com/";
	W.manage().deleteAllCookies();
	ExtentTest test = report.startTest("Homepage validation");
	
	W.navigate().to(Baseurl);
	test.log(LogStatus.INFO, "homepage load", "Homepage is launched and loading");
	W.manage().window().maximize();
	test.log(LogStatus.INFO, "browser full screen", "browser gone full screen");
	report.endTest(test);
	report.flush();
	WD=W;
}

	
	public void headernav(String browser) throws ClientProtocolException, IOException{
		try{
		WebDriverWait wdw = new WebDriverWait(WD, 10,100);
		wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("Stage_clickthrough")));
		System.out.println("element loaded");
		}catch(Exception e){
			System.out.println("element not loaded");
		}
	    WebElement we = WD.findElement(By.cssSelector("a[data-analytics=header_news_world]"));
		Actions ac = new Actions(WD);
		ac.moveToElement(we).perform();
		JavascriptExecutor jse = (JavascriptExecutor)WD;
	    jse.executeScript("window.scrollBy(0,50)");
	    WebElement we1 = WD.findElement(By.xpath("/html/body/header/nav/div/a"));
	    ac.moveToElement(we1).perform();
	    ac.moveToElement(we).perform();
	    
	    //Creating a collection of webElements for top story validation
	    
	   List <WebElement> ls = WD.findElements(By.xpath("//ul[2]/li/article/div/div[1]/a//img"));
	    for (WebElement we2 : ls){
	    	if (we2.isDisplayed()){
	    		countpresent =countpresent+1;
	    	}else {
	    		countnotpresent =countnotpresent+1;
	    	}
	    }
	    System.out.println("Mega nav elements for " +browser +" loaded " +countpresent);
    	System.out.println("Mega nav elements for " +browser +" not loaded " +countnotpresent);
 	    
    	for (WebElement we2 : ls){
 	    	@SuppressWarnings({ "deprecation", "resource" })
			HttpResponse response = new DefaultHttpClient().execute(new HttpGet(we2.getAttribute("src")));
 	    	if(response.getStatusLine().getStatusCode() !=200){
 	    		brokenimage=brokenimage+1;
 	    	}
 	    }
    	if (brokenimage>0){
    		System.out.println("We have " +brokenimage +" broken images in " + browser);
    	}else{
    		System.out.println("We have " +brokenimage +" broken images in " + browser);
    	}
	    }
}
