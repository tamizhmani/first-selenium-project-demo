package com.org.cnnexpansion;
import library.*;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class socialLogin extends HomePage{
	
	@FindBy(xpath="//form/div[1]/p/a")
    public WebElement e;
	 
	
	public void fbLogin(WebDriver W, String user, String pass, ExtentReports report) throws InterruptedException{


		String color_beforehover;
        WD=W;
		WD.navigate().to("http://www.cnn.com/login.html");
		
		String parent = WD.getWindowHandle();
		System.out.println("parent window: " +parent);
		WebElement we = WD.findElement(By.xpath("//div[4]/div[2]/div[1]/div/div[1]/div/div"));
		we.click();
		Thread.sleep(10000);
		Set<String> ls = WD.getWindowHandles();
		for (String s : ls) {
			if (!s.equals(parent)) {
				WD.switchTo().window(s);
			} else {
				System.out.println("finding window in progress....");
			}
	}

		WD.findElement(By.id("email")).sendKeys(user);
		WD.findElement(By.id("pass")).sendKeys(pass);
		WebElement we1 = WD.findElement(By.xpath("//input[@type='submit']"));
		
		
		PageFactory.initElements(WD,this);
		color_beforehover=e.getCssValue("color");
		System.out.println(color_beforehover);
		
		//JavascriptExecutor js = (JavascriptExecutor)WD;
		//js.executeScript("arguments[0].hover" , e);
		
		Actions ac1 = new Actions (WD);
		Action ac = ac1.moveToElement(e).build();
		ac.perform();
		
		String s1 = e.getCssValue("text-decoration");
		if (s1.equalsIgnoreCase("underline")){
			System.out.println(s1);
			System.out.println("Hover effect works");
		}else{
			
			System.out.println("Hover effect not works");
		}
		


			we1.click();
			Thread.sleep(10000);
			
			ExtentTest test = report.startTest("Login information");
			
			if (WD.getTitle().equals("CNN page")){
				System.out.println("login succesful");
			}
			else{
	        test.log(LogStatus.FAIL, "trying to login","login failed");
			library.utitity.getscreenshot(WD, "Loginstatus");
			//test.addScreenCapture("D:\\seleniumproject\\xpanxion\\Screenshots\\Loginstatus.png");
			test.addScreenCapture("./Screenshots/Loginstatus.png");
			report.endTest(test);
			report.flush();
			}
	}
}
