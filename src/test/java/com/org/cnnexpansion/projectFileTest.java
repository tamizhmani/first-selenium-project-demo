package com.org.cnnexpansion; 

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

public class projectFileTest{

    public WebDriver WFF,WGC;
	HomePage hp = new HomePage();
	ArticleLeafPage alp = new ArticleLeafPage();
	Driverinitialize Di = new Driverinitialize();
	socialLogin sl= new socialLogin();
	ExtentReports report= new ExtentReports("D:\\reports\\advancereport.html", true);;
	
	@BeforeClass (alwaysRun = true)
	public void getdrivers(){
		
		WFF=Di.FFDriverinitialize();
		WGC=Di.ChromeDriverinitialize();
	}
	
	@Test(dataProvider="loginpage" ,groups={"a","b"})
	public void launchurlfirefox(String username, String password) throws ClientProtocolException, IOException, InterruptedException{
		alp.launchbrowser(WFF,report);
	   //hp.headernav("Firefox");
		//  alp.loadarticlepage(report);
	      //alp.expandablegallery();
	    //  sl.fbLogin(WFF,username, password);

	}
	@Test (dataProvider="loginpage", groups={"b","c"} )
	public void launchurlchrome(String username, String password) throws ClientProtocolException, IOException, InterruptedException{
		alp.launchbrowser(WGC,report);
		//hp.headernav("Chrome");
       alp.loadarticlepage(report);
       alp.expandablegallery();
		sl.fbLogin(WGC,username, password, report);
	}
      
	@DataProvider(name= "loginpage")
	public Object[][] testdata(){
		Object cr[][] = new Object[1][2];
		cr[0][0] = "tamizhmani313@yahoo.com";
		cr[0][1] = "**********";
		return cr;	
	}

}
