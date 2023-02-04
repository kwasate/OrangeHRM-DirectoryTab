package reusableFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.prolifics.ProlificsSeleniumAPI;

import utility.Generic;
import utility.ScreenShot;

public class OrangeHRM {
	Generic gen = new Generic();
	Logger log;
	ScreenShot screen = new ScreenShot();
	ProlificsSeleniumAPI oPSelFW = null;
	// Login to OrangeHRM
	String txtUserName = "//*[@id=\"txtUsername\"]";
	String txtPassword = "//*[@id=\"txtPassword\"]";
	String btnLogin = "//*[@id=\"btnLogin\"]";
	String tabDirectory = "//a[@id='menu_directory_viewDirectory']";
	String txtEmpName = "//*[contains(@id,'empName')]";
	String btnSearch = "//input[@id = 'searchBtn']";
	String PIMtab = "//*[@id=\"menu_pim_viewPimModule\"]";
	String addEmployee = "//*[@id=\"menu_pim_addEmployee\"]";
	String btnAddEmployee = "//*[@id='btnAdd']";
	String firstName = "//*[@id=\"firstName\"]";
	String lastName = "//*[@id=\"lastName\"]";
	String employeeId = "//*[@id=\"employeeId\"]";
	String id = "//*[@id='empsearch_id']";
	String uploadPhoto = "//*[@id=\"photofile\"]";
	String employeeList = "//*[@id='menu_pim_viewEmployeeList']";
	String saveBtn = "//*[@id=\"btnSave\"]";
	String btnDelete = "//*[@id='btnDelete']";
	String btnReset = "//*[@id='resetBtn']";
	String dltDisabled = " //input[@disabled='disabled']";
	String deleteAlertText = "//*[text()='OrangeHRM - Confirmation Required']";
	String btnDeleteConfirm = "//*[@id='dialogDeleteBtn']";
	String btnDeleteCancel = "//*[@class='btn cancel']";
	String closeDeletePopoup = "//*[@class='close']";
	String empName = "//*[contains(@id,'empName')]";

	public void orangeHRMLogin(WebDriver driver, HashMap<String, String> envTestData,
			HashMap<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception {

		try {

			String url = envTestData.get("ApplicationURL").toString();
			String userName = envTestData.get("UserName").toString();
			String pwd = envTestData.get("Pasword").toString();
			driver.get(url);
			gen.setText(driver, txtUserName, userName, oPSelFW);
			gen.setText(driver, txtPassword, pwd, oPSelFW);
			gen.clickElement(driver, btnLogin, oPSelFW);
			Thread.sleep(14000);
			if (driver.findElements(By.xpath("//li/a[@id='menu_dashboard_index']")).size() != 0) {
				System.out.println("User logged in Sucessfully");
				oPSelFW.reportStepDetails("User is Successfully Logged in OrangeHRM", userName, "Pass");
			} else {
				oPSelFW.reportStepDetails("User Login", "user is not successfully Logged in OrangeHRM", "Fail");
				Assert.assertEquals("user is not successfully Logged in OrangeHRM", "Login failed");
			}
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Exception", e + " is displayed", "Fail");
			Assert.assertEquals("Exception", "Throwing Exception");
		}
	}

	byte counter;

	public void directorySearch(WebDriver driver, HashMap<String, String> excelData, ProlificsSeleniumAPI oPSelFW)
			throws IOException {
		try {
			gen.clickElement(driver, tabDirectory);
			String[] empNames = excelData.get(enumToString(ExcelDataFields.Name)).split(";");
			for (String empName : empNames) {
				oPSelFW.reportStepDetails("Verify Employee Details", " Employee Details -  " + (counter + 1), "Info");
				gen.setText(driver, txtEmpName, empName, "Name #", oPSelFW);
				gen.clickElement(driver, btnSearch, "submit", oPSelFW);
				Thread.sleep(20000);
				boolean isPresent = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr")).size() > 1;
				if (isPresent) {
					oPSelFW.reportStepDetails("Verify the Directory Search with Name display in directory search ",
							"Employee name are displayed for " + empName, "Pass");
					oPSelFW.reportStepDtlsWithScreenshot("Employee details in directory Search ", "Employee details",
							"Pass");

				} else if (!isPresent) {
					String message = driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]")).getText();
					oPSelFW.reportStepDetails("Employee details in directory Search ", message + " for" + empName,
							"Pass");
					oPSelFW.reportStepDtlsWithScreenshot("Employee details in directory Search", "Employee details",
							"Pass");

				}
			}

		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if any error is displayed for directory search",
					"Exception is displayed while directory search", "Fail");
			String expectedresult = "No error message should be displayed";
			Assert.assertEquals("Error message is displayed", expectedresult);
		}
	}

	public void addEmployee(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> excelData)
			throws IOException {
		try {
			Actions actions = new Actions(driver);
			WebElement pim = driver.findElement(By.xpath(PIMtab));
			actions.moveToElement(pim);
			WebElement addEmp = driver.findElement(By.xpath(addEmployee));
			actions.moveToElement(addEmp);
			actions.click().build().perform();
			String fName = excelData.get(enumToString(ExcelDataFields.First_Name));
			String lName = excelData.get(enumToString(ExcelDataFields.Last_Name));
			String empId = excelData.get(enumToString(ExcelDataFields.Employee_Id));
			String photoPath = excelData.get(enumToString(ExcelDataFields.Photograph));
			gen.setText(driver, firstName, fName, "First Name #", oPSelFW);
			gen.setText(driver, lastName, lName, "Last Name #", oPSelFW);
			driver.findElement(By.xpath(employeeId)).clear();
			gen.setText(driver, employeeId, empId, "Employee Id #", oPSelFW);
			driver.findElement(By.xpath(uploadPhoto)).sendKeys(photoPath);
			gen.clickElement(driver, saveBtn, "Save button", oPSelFW);
			if (driver.findElement(By.xpath("//*[text()=\"Personal Details\"]")).getText()
					.equalsIgnoreCase("Personal Details")) {
				oPSelFW.reportStepDetails(
						"Verify the Personal details with Employee name displayed after adding an employee ",
						"Employee name with personal details are displayed for " + fName + " " + lName, "Pass");
				oPSelFW.reportStepDtlsWithScreenshot("Employee's personal details after adding an employee ",
						"Employee Personal details", "Pass");
			}
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if any error is displayed after adding an employee",
					"Exception is displayed while adding an employee", "Fail");
			String expectedresult = "No error message should be displayed";
			Assert.assertEquals("Error message is displayed", expectedresult);
		}

	}

	public void addEmployee_EmployeeList(WebDriver driver, ProlificsSeleniumAPI oPSelFW,
			HashMap<String, String> excelData) throws IOException {
		try {
			navigateToEmployeeList(driver);
			gen.clickElement(driver, btnAddEmployee, "Add button", oPSelFW);
			String fName = excelData.get(enumToString(ExcelDataFields.First_Name));
			String lName = excelData.get(enumToString(ExcelDataFields.Last_Name));
			String empId = excelData.get(enumToString(ExcelDataFields.Employee_Id));
			String photoPath = excelData.get(enumToString(ExcelDataFields.Photograph));
			gen.setText(driver, firstName, fName, "First Name #", oPSelFW);
			gen.setText(driver, lastName, lName, "Last Name #", oPSelFW);
			driver.findElement(By.xpath(empId)).clear();
			gen.setText(driver, employeeId, empId, "Employee Id #", oPSelFW);
			driver.findElement(By.xpath(uploadPhoto)).sendKeys(photoPath);
			gen.clickElement(driver, saveBtn, "Save button", oPSelFW);
			if (driver.findElement(By.xpath("//*[text()=\"Personal Details\"]")).getText()
					.equalsIgnoreCase("Personal Details")) {
				oPSelFW.reportStepDetails(
						"Verify the Personal details with Employee name displayed after adding an employee ",
						"Employee name with personal details are displayed for " + fName + " " + lName, "Pass");
				oPSelFW.reportStepDtlsWithScreenshot("Employee's personal details after adding an employee ",
						"Employee Personal details", "Pass");
			}
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if any error is displayed after adding an employee",
					"Exception is displayed while adding an employee", "Fail");
			String expectedresult = "No error message should be displayed";
			Assert.assertEquals("Error message is displayed", expectedresult);
		}

	}

	public void deleteEmployee(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> excelData)
			throws IOException {
		try {
			navigateToEmployeeList(driver);
			boolean isPresent = searchEmp(driver, oPSelFW, excelData);
			if (isPresent == false) {
				oPSelFW.reportStepDetails("Verify the employee data to delete is displayed or not.",
						"Employee data to delete is not displayed and shown message as- No Records Found", "Pass");
				// oPSelFW.reportStepDtlsWithScreenshot("Employee data to delete is not found ",
				// "Employee details not found", "Pass");
			}

			else {
				// gen.clickElement(driver, btnDelete, "Delete", oPSelFW);
				if (driver.findElement(By.xpath(dltDisabled)).getAttribute("disabled").equalsIgnoreCase("disabled")) {
					System.out.println("Delete button disabled");
					List<WebElement> checkboxAll = driver.findElements(By.xpath("(//input[@type='checkbox'])"));
					for (WebElement checkboxSelect : checkboxAll) {
						String checkBox = checkboxSelect.toString();
						gen.clickElement(driver, checkBox, "CheckBox", oPSelFW);
						System.out.println("Delete button enabled");
						break;
					}
					gen.clickElement(driver, btnDelete, "Delete", oPSelFW);
					if (driver.findElement(By.xpath(deleteAlertText)).getText().contains("Confirm")) {
						System.out.println("User is able to see Confirmation Pop up for Delete functionality");
						oPSelFW.reportStepDetails(
								"Verify the user is able to see the Confirmation pup-up for Delete functionality ",
								"User is able to see confirmation pop-up for delete the emplyee from employee list ",
								"Pass");
						gen.clickElement(driver, btnDeleteConfirm, " Confirm Delete button", oPSelFW);
						System.out.println("User clicked on Delete button successfully");
						oPSelFW.reportStepDetails("Verify the user is clicked on Delete button successfully ",
								"User is clicked on Delete button successfully ", "Pass");
					} else {
						System.out.println("User is not able to see Confirmation Pop up for Delete functionality");
						oPSelFW.reportStepDetails(
								"Verify the user is able to see the Confirmation pup-up for Delete functionality ",
								"User is not able to see confirmation pop-up for delete the emplyee from employee list ",
								"Fail");
						Assert.assertEquals("Error message is displayed", "Exception");
						// gen.clickElement(driver, btnDeleteConfirm, " Confirm Delete button",
						// oPSelFW);
					}
					searchEmp(driver, oPSelFW, excelData);
					if ("No records".equalsIgnoreCase(
							driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]")).getText())) {
						oPSelFW.reportStepDetails("Verify the employee data is deleted",
								"Employee data is successfully deleted from employee list ", "Pass");
						oPSelFW.reportStepDtlsWithScreenshot("Employee data deleted deleted  ",
								"Employee details deleted", "Pass");
					} else {
						oPSelFW.reportStepDetails("Verify the employee data is deleted",
								"Employee data is not deleted from employee list ", "Fail");
						oPSelFW.reportStepDtlsWithScreenshot("Employee data not deleted deleted  ",
								"Employee details not deleted", "Fail");
					}
				}
			}
		} catch (

		Exception e) {
			oPSelFW.reportStepDetails("Verify if any error is displayed while deleting employee",
					"Exception is displayed while deleting an employee", "Fail");
			String expectedresult = "No error message should be displayed";
			Assert.assertEquals("Error message is displayed", expectedresult);
		}

	}

	public void searchEmployee(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> excelData)
			throws Exception {
		navigateToEmployeeList(driver);
		String fName = excelData.get(enumToString(ExcelDataFields.First_Name));
		String lName = excelData.get(enumToString(ExcelDataFields.Last_Name));
		String empId = excelData.get(enumToString(ExcelDataFields.Employee_Id));
		gen.setText(driver, empName, fName+" "+lName, "Employee Name #", oPSelFW);
		gen.setText(driver, id, empId, "Employee Id #", oPSelFW);
		gen.clickElement(driver, btnSearch, "Search button", oPSelFW);
		Thread.sleep(20000);
		boolean isPresent = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td")).size() > 1;
		if (isPresent) {
			String eId = driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[2]")).getText();
			String eName = driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[3]")).getText() + " "
					+ driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[4]")).getText();
			oPSelFW.reportStepDetails(
					"Verify the Employee Search with Employee name: " + fName + " " + lName + "and IdName: " + empId
							+ " display in Employee List search ",
					"Employee name are displayed as " + eName + "and Employee id are displayed as " + eId, "Pass");
			oPSelFW.reportStepDtlsWithScreenshot("Employee details in Employee list Search ", "Employee details",
					"Pass");

		} else if (!isPresent) {
			String message = driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]")).getText();
			oPSelFW.reportStepDetails("Employee details in employee list Search ",
					message + " for" + fName + " " + lName, "Pass");
			// oPSelFW.reportStepDtlsWithScreenshot("Employee details in Employee list
			// Search", "Employee details","Pass");

		}
	}

	public boolean searchEmp(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> excelData)
			throws Exception {
		String fName = excelData.get(enumToString(ExcelDataFields.First_Name));
		String lName = excelData.get(enumToString(ExcelDataFields.Last_Name));
		String empId = excelData.get(enumToString(ExcelDataFields.Employee_Id));
		gen.setText(driver, empName, fName + " " + lName, "Employee Name #", oPSelFW);
		gen.setText(driver, id, empId, "Employee Id #", oPSelFW);
		Thread.sleep(20000);
		gen.clickElement(driver, btnSearch, "Search button", oPSelFW);
		Thread.sleep(20000);
		boolean isPresent = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td")).size() > 1;
		if (isPresent) {
			String eId = driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[2]")).getText();
			String eName = driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[3]")).getText()
					+ driver.findElement(By.xpath("(//table[@id='resultTable']/tbody/tr/td)[4]")).getText();
			oPSelFW.reportStepDetails(
					"Verify the Employee Search with Employee name: " + fName + " " + lName + "and IdName: " + empId
							+ " display in Employee List search ",
					"Employee name are displayed for " + eName + "and Employee id are displayed for " + eId, "Pass");
			oPSelFW.reportStepDtlsWithScreenshot("Employee details in Employee list Search ", "Employee details",
					"Pass");
			return isPresent;
		} else if (!isPresent) {
			String message = driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]")).getText();
			oPSelFW.reportStepDetails("Employee details in employee list Search ", message + " for" + fName + lName,
					"Pass");

			// oPSelFW.reportStepDtlsWithScreenshot("Employee details in Employee list
			// Search", "Employee details","Pass");
			return isPresent;
		}
		return isPresent;
	}

	public void navigateToEmployeeList(WebDriver driver) {
		Actions actions = new Actions(driver);
		WebElement pim = driver.findElement(By.xpath(PIMtab));
		actions.moveToElement(pim);
		WebElement empList = driver.findElement(By.xpath(employeeList));
		actions.moveToElement(empList);
		actions.click().build().perform();

	}

	public String enumToString(ExcelDataFields enumField) {

		return String.valueOf(enumField);
	}

	enum ExcelDataFields {

		Name, First_Name, Last_Name, Employee_Id, Photograph;

	}

	public void resetEmployee(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> excelData)
			throws InterruptedException, IOException {
		navigateToEmployeeList(driver);
		String fName = excelData.get(enumToString(ExcelDataFields.First_Name));
		String lName = excelData.get(enumToString(ExcelDataFields.Last_Name));
		String empId = excelData.get(enumToString(ExcelDataFields.Employee_Id));
		gen.setText(driver, empName, fName + lName, "Employee Name #", oPSelFW);
		gen.setText(driver, id, empId, "Employee Id #", oPSelFW);
		gen.clickElement(driver, btnReset, "Reset button", oPSelFW);

	}

}
