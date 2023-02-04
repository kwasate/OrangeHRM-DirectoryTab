package PIM_Tab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.prolifics.ProlificsSeleniumAPI;

import bsh.util.Util;
import reusableFunctions.OrangeHRM;
import utility.EmailReport;
import utility.Excel_Reader;

public class TS_Add_Employee_inEmployeeList {
	public static Excel_Reader excelReader;
	public static Excel_Reader envDetails;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public HashMap<String, String> XLTestData, envTestData;
	WebDriver driver;
	OrangeHRM objOrangeHRM = new OrangeHRM();
	EmailReport objEmail = new EmailReport();
	ProlificsSeleniumAPI oPSelFW = new ProlificsSeleniumAPI();

	@BeforeClass
	public void Test()
			throws IOException, InterruptedException, SQLException, ParserConfigurationException, SAXException {
		oPSelFW = new ProlificsSeleniumAPI("PIM Tab", "Add Employee in OrangeHRM Application", "Chrome", "Windows 10",
				"QA", "Automation", "TestAutomation", "Regression");
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
		Util util = new Util();
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\DataSheet.xlsx";
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		envDetails.cFileNameWithPath = TestDataPath;
		envDetails.cSheetName = "Environment";
		envDetails.cTcID = "Execution";
		envTestData = new HashMap<String, String>();
		envTestData = envDetails.readExcel("Y");

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// System.out.println(System.getProperty("UserID"));
		driver.manage().window().maximize();
	}

	@DataProvider(name = "RequireData")
	public static Object[][] createOrderProvider() throws FileNotFoundException, IOException {
		excelReader = new Excel_Reader(System.getProperty("user.dir") + "\\Data\\DataSheet.xlsx");
		return asTwoDimensionalArray();

	}

	private static Object[][] asTwoDimensionalArray() throws FileNotFoundException, IOException {
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\DataSheet.xlsx";
		System.out.println(TestDataPath);
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		int RowCount = excelReader.GetRowCount("TestData");
		HashMap<String, String> XLTestData = new HashMap<String, String>();
		System.out.println("row Count " + RowCount);
		int k = 0;
		for (int j = 1; j <= RowCount; j++) {
			XLTestData = excelReader.readExcel("TS_Add_Employee_inEmployeeList_" + Integer.toString(j));
			if (!XLTestData.get("TestCaseID").toString().contains("TestCaseID"))
				k++;
		}
		Object[][] results = new Object[k][1];
		k = 0;
		for (int j = 1; j <= RowCount; j++) {
			XLTestData = excelReader.readExcel("TS_Add_Employee_" + Integer.toString(j));
			if (!XLTestData.get("TestCaseID").toString().contains("TestCaseID")) {
				results[k] = new Object[] { XLTestData };
				k++;

			}
		}

		return results;
	}

	@Test(dataProvider = "RequireData")
	public void orderSearchWCC(HashMap<String, String> XLTestData) throws Exception {
		try {
			oPSelFW.reportStepDetails("Start the New Steps for TestCase",
					"Add Employee in OrangeHRM Application - " + XLTestData.get("Description").toString(), "Pass");
			objOrangeHRM.orangeHRMLogin(driver, envTestData, XLTestData, oPSelFW);
			objOrangeHRM.addEmployee(driver, oPSelFW, XLTestData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void ExtentReport(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			FailCount = FailCount + 1;
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			PassCount = PassCount + 1;
		}
	}

	@AfterClass(alwaysRun = true)
	public void Display() throws InterruptedException, IOException, ParseException {
		String ClassName = this.getClass().getName();
		Thread.sleep(1000);
		oPSelFW.stopReport();
		objEmail.afterSuite();
		driver.close();
	}


}
