package library;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class utitity {

	public static void getscreenshot(WebDriver driver, String screenshotname){
		
		try {
			File file_destination = new File ("./Screenshots/"+screenshotname+".png");
			TakesScreenshot ts = (TakesScreenshot)driver;
			File file_source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file_source, file_destination);
		} 
		catch (Exception e) 
		{
			System.out.println("Screenshot capture not done:" +e.getMessage());
		} 
	}
}
