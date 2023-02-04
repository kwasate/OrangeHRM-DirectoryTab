package reusableFunctions;
import java.text.ParseException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.prolifics.ProlificsSeleniumAPI;

import utility.Generic;
import utility.ScreenShot;

public class SterlingUI {
	Generic gen = new Generic();
	ProlificsSeleniumAPI oPSelFW = null;	
	public void sterlingPaymentFailure(HashMap<String, String> envTestData,HashMap<String, String> XLTestData,String orderNumber,ProlificsSeleniumAPI oPSelFW) throws Exception {

		try {
			System.setProperty("webdriver.ie.driver","./resources/IEDriverServer.exe");
			DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
			ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, envTestData.get("SterlingURL"));
			RemoteWebDriver  driver = new InternetExplorerDriver(ieCaps);
			//Thread.sleep(10000);			
			driver.findElementByXPath("//input[@class='logininput']").sendKeys(envTestData.get("CCUIUserName"));
            //Thread.sleep(10000);
			driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(envTestData.get("CCUIPasword"));
			Thread.sleep(35000);
			driver.findElement(By.xpath("//input[contains(@title,'Click To Sign In To IBM Sterling Selling and Fulfillment Suite')]")).click();
			Thread.sleep(30000);
			WebElement appConsole = driver.findElementByXPath("//td[@class='tablecolumn']//a[contains(@href,'order') and contains(text(),'OrderSearch')]");
			boolean displayed = appConsole.isDisplayed();
			if(displayed == true)
			{
				oPSelFW.reportStepDetails("user is successfully Logged in Sterling" ,"Pass", envTestData.get("CCUIUserName"));
			}else
			{
				oPSelFW.reportStepDetails("user is not logged in Sterling" ,"Fail", envTestData.get("CCUIUserName"));
			}			
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement element = driver.findElement(By.xpath("//td[@class='tablecolumn']//a[contains(@href,'order') and contains(text(),'OrderSearch')]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor. executeScript("arguments[0]. click();", element);
			Thread.sleep(35000);
			driver.findElement(By.xpath("//input[@name='xml:/Order/@OrderNo']")).sendKeys(orderNumber);
			driver.findElement(By.xpath("//input[@name='xml:/Order/@OrderNo']")).sendKeys(Keys.ENTER);
			Thread.sleep(35000);			
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//img[@name ='Payment_Information']")).click();
			Thread.sleep(2000);
			Set <String> set = driver.getWindowHandles();
			Iterator<String> it = set.iterator();
			String parentWindowId = it.next();
			String childWindowId = it.next();
			System.out.println(set);
			driver.switchTo().window(childWindowId);
			Thread.sleep(50000);
			driver.switchTo().frame("yfcRootFrame");
			System.out.println("iframe   "+driver.getTitle());			
			WebElement ele = driver.findElement(By.xpath("//table[contains(@class,'scButtonBar')][1]//tbody//tr//td[3][contains(text(),'Create Debit Memo')]"));
			driver.findElement(By.xpath("//table[contains(@class,'scButtonBar')][1]//tbody//tr//td[3][contains(text(),'Create Debit Memo')]")).click();
			executor. executeScript("arguments[0]. click();", ele);
			executor. executeScript("arguments[0]. click();", ele);
			Set <String> strHandles = driver.getWindowHandles();
			for(String handle: strHandles)
			{
				driver.switchTo().window(handle);
		        String strTitle = driver.getTitle();
		    }
			driver.switchTo().frame("yfcRootFrame");			
			driver.findElement(By.xpath("//input[@name='xml:/RecordExternalCharges/Memo/@Reference1']")).sendKeys("Reference Amount");
			 Thread.sleep(15000);
			//driver.findElement(By.xpath("//input[@name='xml:/RecordExternalCharges/Memo/@ChargeAmount']")).sendKeys("1.05");
			driver.findElement(By.xpath("//input[@class='button' and @value='Save']")).click();
		    Thread.sleep(15000);
		    Set <String> strHandles1 = driver.getWindowHandles();
			for(String handle: strHandles1)
			{
				driver.switchTo().window(handle);
		        String strTitle = driver.getTitle();
		    }
			driver.switchTo().frame("yfcRootFrame");	
			System.out.println("+++++++   "+driver.findElements(By.xpath("//table[@class='view']//tbody//tr[3]//td[2]//input[1]input[@calss='button' and contains(@onclick,'setOKClickedAttribute')]")).size());
			System.out.println("+++++++   "+driver.findElements(By.xpath("//table[@class='view']//tbody//tr[3]//td[2]//input[1][@calss='button']")).size());
			System.out.println("+++++++   "+driver.findElements(By.xpath("//table[@class='view']//tbody//tr[3]//td[2]//input[@value='OK']")).size());
			//driver.findElement(By.xpath("//table[@class='view']//tbody//tr[3]//td[2]//input[1]//input[@value='OK']")).click();
		    Thread.sleep(3000);
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
		}
	}
}
