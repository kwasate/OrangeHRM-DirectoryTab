package utility;

import java.io.FileInputStream ;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.prolifics.ProlificsSeleniumAPI;

import io.github.sukgu.Shadow;

public class Generic {
	// BaseTest basetest = new BaseTest();
	/*
	 * @Descriptions -- Sets the text in the Web Element
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * 
	 * @param Val2Set -- Value to set in the Web Element
	 */

	// ProlificsSeleniumAPI oPSelFW
	public void setText(WebDriver driver, String xpathExpress, String Val2Set, ProlificsSeleniumAPI oPSelFW) {
		setText(driver, xpathExpress, Val2Set, "", oPSelFW);
	}

	public boolean sizeOftheLocater(WebDriver driver, String xpathExpress, ProlificsSeleniumAPI oPSelFW) {

		boolean size = driver.findElements(By.xpath(xpathExpress)).size() > 0;
		System.out.println("Size of the Locator  " + size);
		return size;

	}
	
	public void scroll(WebDriver driver,ProlificsSeleniumAPI oPSelFW, String direction) throws InterruptedException, IOException {
		try {
		if (direction.contains("UP")) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(""
					+ "(0, -500);");
		} else if (direction.contains("DOWN")) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, +500);");
		}
		
		Thread.sleep(4000);

	}catch(Exception e) {
		oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");

		String ExpectedResult = "Order should be Set to the Text Box";

		Assert.assertEquals("Verify if exception has occured", ExpectedResult);
	}
	}

	public void setText(WebDriver driver, String xpathExpress, String Val2Set, String msg, ProlificsSeleniumAPI oPSelFW) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			// WebElement element = driver.findElement(By.xpath(xpathExpress));
			element.clear();
			element.sendKeys(Val2Set);
			if (msg.length() > 0) {
				System.out.println(" ");
			}
			//oPSelFW.reportStepDetails("Set Text to " + msg, Val2Set + " is set to " + msg, "Pass");
			 System.out.println(Val2Set + " is set to " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void click(WebDriver driver, String xpathExpress) throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpress)));

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			executor.executeScript("arguments[0].click();", element);

			Thread.sleep(1500);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setText(WebDriver driver, By xpathExpress, String Val2Set, String msg, ProlificsSeleniumAPI oPSelFW) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathExpress));
			// WebElement element = driver.findElement(By.xpath(xpathExpress));
			element.clear();
			element.sendKeys(Val2Set);
			if (msg.length() > 0) {
				System.out.println(" ");
			}
			//oPSelFW.reportStepDetails("Set Text to " + msg, Val2Set + " is set to " + msg, "Pass");
			 System.out.println(Val2Set + " is set to " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	
	
	public void clickElement(WebDriver driver, String xpathExpress, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		clickElement(driver, xpathExpress, "", oPSelFW);
	}

	public void clickElement(WebDriver driver, String xpathExpress) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpress)));
			driver.findElement(By.xpath(xpathExpress)).click();
			Thread.sleep(4000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void clickElement(WebDriver driver, String xpathExpress, String NameOfObject, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpress)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//oPSelFW.reportStepDetails("Click on " + NameOfObject, NameOfObject + " element is clicked ", "Pass");
		System.out.println(NameOfObject + "Element is clicked ");
	}
	
	public void clickElement(WebDriver driver, By xpathExpress, String NameOfObject, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(xpathExpress));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//oPSelFW.reportStepDetails("Click on " + NameOfObject, NameOfObject + " element is clicked ", "Pass");
		System.out.println(NameOfObject + " element is clicked ");
	}

	/* Clicks by using javascript executor */

	public void click(WebDriver driver, String xpathExpress, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpress)));

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			executor.executeScript("arguments[0].click();", element);

			Thread.sleep(4000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * @Descriptions -- Gets the value displayed on the UI for the Web Element
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 */
	public String getValueOfElement(WebDriver driver, String xpathExpress) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
		return (element.getAttribute("Value"));
	}

	/*
	 * @Descriptions -- Gets the text of the Web Element
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 */
	public String getTextOfElement(WebDriver driver, String xpathExpress) {
		return (driver.findElement(By.xpath(xpathExpress)).getText());
	}

	/*
	 * @Descriptions -- Converts Integer to Dae
	 * 
	 * @param DateInInt -- Date value in the form of Integer
	 */
	




	/**
	 * @Descriptions  -- Launch the URL 
	 * @param driver
	 * @param URL
	 * @param oPSelFW
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public boolean launchURL(WebDriver driver, String URL,ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		driver.get(URL);
		By txtUserName = By.xpath("//input[@id='idx_form_TextBox_0']");
		try
		{
			Thread.sleep(3000);
			if(existsElement(driver, By.xpath("//*[@id='main-message']/h1/span")))
			{
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", "Create Order", "Pass");
				driver.close();
				Thread.sleep(100);
				driver.quit();
				oPSelFW.reportStepDetails("WCC URL should launch "  , URL + "is down", "Fail");
				Assert.assertEquals("WCC URL should launch "  , URL + "is down");
			}
			WebDriverWait wait = new WebDriverWait(driver, 500);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(txtUserName));
			if(driver.getTitle().contains("Williams-Sonoma Call Center UI")) 
			{
				wait = new WebDriverWait(driver, 300);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(txtUserName));
				//driver.manage().window().maximize();
				oPSelFW.reportStepDetails("WCC URL should launch successfully"  , URL + "is successfully Launched ", "Pass");
				return true;
			}
			else if(driver.getTitle().contains("Application Console"))
			{
				wait = new WebDriverWait(driver, 500);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(txtUserName));
				driver.manage().window().maximize();
				//basetest.test.log(Status.PASS, URL + " is successfully Launched");
				oPSelFW.reportStepDetails("WCC URL should launch "  , URL + "is successfully Launched ", "Pass");
				return true;
			}
			else
			{
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", "Create Order", "Pass");
				driver.close();
				Thread.sleep(100);
				driver.quit();
				oPSelFW.reportStepDetails("WCC URL should launch "  , URL + "is not Launched ", "Fail");
				Assert.assertEquals("WCC URL should launch "  , URL + "is not Launched ");
				return false;
			}
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("WCC URL should launch "  , URL + "is not Launched ", "Fail");
			Assert.assertEquals("WCC URL should launch "  , URL + "is not Launched ");
			return false;
		}
	}
	
	/*
	 * @Descriptions -- Select the value in the Dropdown
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param CSSSelctor -- CSSSelctor of the Web Element
	 * 
	 * @param Val2Select - Value to select in the Web Element
	 */
	public void selectShadowElementByVal(WebDriver driver, String CSSSelctor, String value, String msg,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Shadow shadow = new Shadow(driver);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(shadow.findElement(CSSSelctor)));
			Select dropdown = new Select(element);
			dropdown.selectByValue(value);
			Thread.sleep(2000);
			if (msg.length() > 0)
				System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Descriptions -- clickShadowElement
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param CSSSelctor -- of the Web Element
	 */
	public void clickShadowElement(WebDriver driver, String CSSSelctor, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Shadow shadow = new Shadow(driver);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(shadow.findElement(CSSSelctor)));
			text = element.getText();
			// WebElement element = shadow.findElement(CSSSelctor);
			element.click();
			if (msg.length() > 0)
				System.out.println("");

		} catch (Exception e) {

			e.printStackTrace();
			oPSelFW.reportStepDetails(" element to be Clicked", text, "Fail");
		}
	}

	/*
	 * @Descriptions -- Sets the text in the Web Element for Shadow Element
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- CSSSelctor of the Web Element
	 * 
	 * @param Val2Set -- Value to set in the Web Element
	 */
	public void setTextShadowElement(WebDriver driver, String CSSSelector, String Val2Set, String msg,
			ProlificsSeleniumAPI oPSelFW) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Shadow shadow = new Shadow(driver);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(shadow.findElement(CSSSelector)));
			// WebElement element = shadow.findElement(CSSSelector);
			element.clear();
			element.sendKeys(Val2Set);
			if (msg.length() > 0)
				System.out.println(" ");
		} catch (Exception e) {

			oPSelFW.reportStepDetails(" Enter Text to be Text Box", Val2Set, "Fail");
			e.printStackTrace();
		}
	}

	public String getPropertyValue(String propName, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		String propValue = "";
		try{
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			propValue = prop.getProperty(propName);
			return(propValue);
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
			
		}
		return(propValue);
	}
	public String getPropertyValue(String propName) throws IOException
	{
		String propValue = "";
		try{
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			propValue = prop.getProperty(propName);
			return(propValue);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return(propValue);
	}
/*	public void setTextAndHitTab(WebDriver driver, String xpathExpress, String Val2Set, String FieldName,ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			driver.findElement(By.xpath(xpathExpress)).clear();
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Val2Set);
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Keys.TAB);
			oPSelFW.reportStepDetails("should set " +Val2Set ,  "is set to"+FieldName, "Pass");
			System.out.println(xpathExpress + " exists");
		}
		else
		{		
			oPSelFW.reportStepDetails("should set " +Val2Set ,  "does not exists"+FieldName, "Pass");
			driver.close();
			driver.quit();
			System.out.println(xpathExpress + " not exists");
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());			
		}
	}*/
	public WebElement expandShadowRootElement(WebDriver driver, WebElement element) {
		WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", element);
		return shadowRoot;
	}
		
	public boolean existsElement(WebDriver driver, By id) {
	    try {

	    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        driver.findElement(id);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	public boolean existsElement(WebElement element, By id) {
	    try {
	    	element.findElement(id);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	public void updatePropertyFile(String PropName, String PropValue) throws ConfigurationException
	{
		PropertiesConfiguration properties = new PropertiesConfiguration(System.getProperty("user.dir")+"\\resources\\config.properties");
        properties.setProperty(PropName, PropValue);
        properties.save();
	}
	public void waitnclickElement(WebDriver driver, By xPathOfElement, String NameOfField,ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		System.out.println("wait for element"+NameOfField);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(!driver.findElements(xPathOfElement).isEmpty())
		{
			executor.executeScript("arguments[0].click()", element);
			//oPSelFW.reportStepDetails("Click on "  + NameOfField, NameOfField + " is clicked", "Pass");
		}
		else
		{
			//oPSelFW.reportStepDetails("Click on "  + NameOfField, xPathOfElement + "element doesn't exists", "Fail");
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
		}
	}
	public void shortwaitnclickElement(WebDriver driver, By xPathOfElement, String FieldName, ProlificsSeleniumAPI oPSelFW)
	{
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElements(xPathOfElement).size() > 0)
			executor.executeScript("arguments[0].click()", element);
		else
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
	}
	public void closeTheOrder(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws InterruptedException, Exception
	{
		try
		{
			if(driver.findElements(By.xpath("//span[contains(text(),'OK')]")).size() > 0)
			{
				waitnclickElement(driver, By.xpath("//span[contains(text(),'OK')]"), "OK", oPSelFW);
				Thread.sleep(500);
			}
			if(driver.findElements(By.xpath("//*[@uid='isccsContainer']/div/div/div[4]/div/div[3]/span[3]")).size() > 0)
			{
				waitnclickElement(driver, By.xpath("//*[@uid='isccsContainer']/div/div/div[4]/div/div[3]/span[3]"), "Order Close", oPSelFW);
				Thread.sleep(1000);
				if(driver.findElements(By.xpath("((//*[@uid='Popup_btnNext']/span/span)[1]/span)[3]")).size() > 0)
				{
					waitnclickElement(driver, By.xpath("((//*[@uid='Popup_btnNext']/span/span)[1]/span)[3]"), "OK", oPSelFW);
					Thread.sleep(5000);
				}
			}
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + "is displayed", "Fail");
			
		}
	}
	
	public void waitnclickElement(WebDriver driver, By xPathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElements(xPathOfElement).size() > 0)
			executor.executeScript("arguments[0].click()", element);
		else
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
	}
	
	public void getElementSize(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
	}
	
	public int getElementSize(WebDriver driver, By by) {
		getElementSize(driver);
		return driver.findElements(by).size();
	}
	
	public int getElementSize(WebDriver driver, String xpath) {
		getElementSize(driver);
		return driver.findElements(By.xpath(xpath)).size();
	}
}