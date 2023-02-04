package utility;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
	import org.openqa.selenium.OutputType;
	import org.apache.commons.io.FileUtils;

	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.Test;


public class ScreenShot{
	
		
		 public static String getScreenshot(WebDriver webdriver) throws Exception{
	 TakesScreenshot scrShot =((TakesScreenshot)webdriver);//Convert web driver object to TakeScreenshot
	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);//Call getScreenshotAs method to create image file
    String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
    Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy hh mm ss");
	String time = dateFormat.format(now);
	time = time.replace(" ", "");
	
    
  
    String path =System.getProperty("user.dir")+"\\target\\ScreenShots\\"+time+".png";
    
   
	File DesFile =new File(path); //Move image file to new destination
	try {
		FileUtils.copyFile(SrcFile, DesFile);
	}catch(IOException e) {
		System.out.println("Capture Failed"+e.getMessage());
	}
	        
	return time;
		 }

		 

}
