package com.org.cnnexpansion;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;

public class ArticleLeafPage extends HomePage{

	public void loadarticlepage(ExtentReports report) throws IOException, InterruptedException {
		String articleurl;
		Properties prop = new Properties();
		InputStream isr = ArticleLeafPage.class.getClassLoader().getResourceAsStream("data.properties");
		prop.load(isr);
		articleurl= prop.getProperty("articleleafpage");
		System.out.println("the url is: " +articleurl);
		WD.manage().deleteAllCookies();
		ExtentTest test = report.startTest("Article leaf page validation");
		WD.navigate().to(articleurl);
		test.log(LogStatus.INFO, "article page load", "Article is launched and loading");
		Thread.sleep(10000);
		WD.findElement(By.xpath("//body/div[3]/div[1]/div[2]")).click();
		test.log(LogStatus.INFO, "TOS Banner colapse", "Banner is accepted and closed");
		test.log(LogStatus.PASS, "Article page loaded succesfully");
		report.endTest(test);
		report.flush();
	}
	
	public void expandablegallery() throws IOException{
		//WD.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement we = WD.findElement(By.xpath("//div[1]/div/div[2]/div[1]/div/img"));
		we.click();
		we=WD.findElement(By.xpath("//div[1]/div[5]/div[1]/div/div[3]"));
		String st = we.getAttribute("isextended");
		System.out.println("isextended: " +st);
		if(st.equals("true")){
			System.out.println("the gallery is expanded");
		}
		else {
			System.out.println("the gallery is not expanded");
		}
		
	/*	File file = new File("C:\\datadriven\\Sel_Data_Driven.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Sheet1");
		int row = ws.getLastRowNum()+1;
		int column = ws.getRow(1).getLastCellNum();
		
		String [][]str = new String[3][2];
		
		for (int i=0; i<row; i++){
			XSSFRow roww = ws.getRow(i);
			for (int j=0; j<column; j++){
				XSSFCell cell = roww.getCell(j);
				str[i][j]= cell.toString();
			}
		}
		wb.close();
		*/
		
		//for (int i=0; i<row; i++){
		//	for (int j=0; j<column; j++){
		//System.out.println ("The values are : " +"str[" +i +"] " +"[" +j +"] :" +str[i][j]);
		//	}
		//}
		        WebElement w = WD.findElement(By.xpath("//div/div[3]/div[1]/div[1]/div/div[@class='owl-item active']"));
				WebElement w1 = w.findElement(By.xpath("div/div/div/img"));
				String src1= w1.getAttribute("src");
				WD.findElement(By.xpath("//div[1]/div/div[3]/div[1]/div[2]/div[2]")).click();
				WebElement w2= WD.findElement(By.xpath("//div/div[3]/div[1]/div[1]/div/div[@class='owl-item active']"));
				WebElement w3 = w2.findElement(By.xpath("div/div/div/img"));
                String src2=w3.getAttribute("src");
                
                System.out.println("SRC 1: " +src1);
                System.out.println("SRC 2: " +src2);
                
                if (src1.equals(src2)){
                	System.out.println ("Navigation notworks proper");
                }else{
                	System.out.println ("Navigation works proper");
                }

			
	}
	
}
