/*
 * package reusableFunctions; import static org.testng.Assert.assertTrue;
 * 
 * import java.io.IOException; import java.util.ArrayList; import
 * java.util.HashMap; import java.util.LinkedHashMap; import java.util.List;
 * 
 * import org.apache.poi.xssf.XLSBUnsupportedException; import
 * org.openqa.selenium.By; import com.prolifics.ProlificsSeleniumAPI; import
 * org.openqa.selenium.JavascriptExecutor; import org.openqa.selenium.Keys;
 * import org.openqa.selenium.WebDriver; import org.openqa.selenium.WebElement;
 * import org.testng.Assert;
 * 
 * 
 * 
 * import io.github.sukgu.Shadow; //import utility.BaseTest; import
 * utility.Generic; import utility.ScreenShot;
 * 
 * 
 * public class CCUI { Generic gen = new Generic(); ScreenShot screen = new
 * ScreenShot(); ProlificsSeleniumAPI oPSelFW = null; // Login to CCUI String
 * txtUserName = "//input[@id='ws-user']"; String txtPassword =
 * "//input[@id='ws-pw']"; String btnLogin = "//button[@id='ws-sign-in']"; //
 * static // Order search in CCUI String txtOrderSearch =
 * "//input[@name='orderNo']"; // static String txtOrderSearch =
 * "div[calss='InputGroup // ']:nth-child(1)>input[name='orderNo']"; // static
 * String btnSearch ="button[type='submit']"; String tabOrders =
 * "//li[@id='tab-order']"; String tabReturn = "tab-header[name='return']";
 * String tabReturns = "//tab-header[@name='returns']"; String chkItem =
 * "div.CheckboxGroup >label[for^='line-']"; String selReturnReplace =
 * "select[name='lines[0].returnAction']"; String selReturnReplace1 =
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(\"+i+\") td:nth-child(3) select[name='lines[0].returnAction']"
 * ; String chkReturnComponent = "label[for^='component-']"; String
 * selReturnComponent = "select[name='lines[0].components[0].returnAction']";
 * String btnCreateReturn = "button#return-selection-submit"; // static String
 * btnCreateReturn1 = "div[style='grid-column: 1 / span //
 * 3;']>button#return-selection-submit"; String btnCreateReturn1 =
 * "fieldset[name='lines']+button[id='formConfirm']"; String btnContinueShipping
 * = "fieldset[name='shipToAddress']+button[id='formConfirm']"; // fill return
 * #1 line info select[name='returnReason.reason']>optgroup[label='― //
 * Company-Driven Reasons ―']>option[text='Shipping issue'] // static String
 * selReturnReason = //
 * "select[name='returnReason.reason']>optgroup:nth-child(3)"; String
 * selReturnReason = "select[name='returnReason.reason']"; String selSubReason =
 * "select[name='returnReason.subReason']"; String selReceiptPolicy =
 * "select[name='merchReceiptPolicy']"; String txtReturnLineNote =
 * "input[name='notes[0].text']"; String btnContinue = "button#formConfirm";
 * String selRefundPolicy = "select[name='returnRefundInfo.policy']"; // Refund
 * / Replace Now String selReturnShipping = "select[name='returnMethod']"; //
 * Customer will drop off at UPS String valReturnShipping =
 * "fieldset[name='shipToAddress'] fieldset > div[class='InputGroup SelectGroup ']"
 * ; String chkReturnGiftRec = "input[name='returnByGiftRecipient']+label";
 * String selReturnType = "select[name='returnType']"; String txtNoOfLables =
 * "input[name='returnLabelInfo.numberOfLabels']"; // 1 String selLabelInfo =
 * "select[name='returnLabelInfo.method']"; String btnReviewReturn =
 * "fieldset[name='billToAddress']+button[id='formConfirm']"; // static String
 * // btnConfirmReturn=
 * "fieldset[name='confirmation-fieldset']:nth-child(2)+button[id='formConfirm']";
 * // static String // btnConfirmReturn=
 * "fieldset.confirmation-fieldset:nth-child(2)+button#formConfirm.primary"; //
 * static String btnConfirmReturn="button:contains('Confirm')"; String
 * btnConfirmReturn = "form>fieldset:nth-child(2)+button#formConfirm.primary";
 * String valReplacementNo =
 * "(//span[@class='headingClr']//copyable-text[@class='ng-binding' and text()])[1]"
 * ; String valOrderType =
 * "//span[text()='Order Type:']/following-sibling::span//strong[@class='ng-binding']"
 * ; //String valOrderTotal =
 * "//span[text()='Order Total: ']/following-sibling::span//strong//currency-display[text()]"
 * ; String valOrderTotal = "//currency-display[@amount]";
 * 
 * // Refund Order Details Validation String headerReturnOrder =
 * "//h4[text()='Return Order Details']"; String valReturnORderNo =
 * "(//copyable-text[@class='ng-binding'])[2]"; String valReturnORderAmnt =
 * "(//span[contains(text(),'Return Amount:')]//following::strong[@class='ng-binding'])[1]"
 * ; String valReturnORderStatus =
 * "(//p[contains(text(),'Status:')]//following::strong[@class='ng-binding'])[1]";
 * // Payment Method Validation in CCUI String lnkNewPayment =
 * "dialog-anchor[target='createTender']"; String btnAddPayment =
 * "div[class='InputGroup SelectGroup ']+button[type='submit']"; String
 * selPaymentMethodType =
 * "div[class='InputGroup SelectGroup ']>select[name='type']"; // Payment
 * validations in CCUI String tabPayment =
 * "dialog-anchor[target='createTender']"; // Add address From String selNewAdd
 * = "toggler-button > i[class='fa']"; String txtNewFirstName =
 * "input[name='shipToAddress.name.firstName']"; String txtNewLastName =
 * "input[name='shipToAddress.name.lastName']"; String txtAdd =
 * "input[name='shipToAddress.address.addressLine1']"; String txtCity =
 * "input[name='shipToAddress.address.city']"; String txtState =
 * "input[name='shipToAddress.address.stateProvince']"; String txtZipCode =
 * "input[name='shipToAddress.address.postalCode']"; String txtContactNo =
 * "input[title='Enter a valid phone number.']"; String txtEmail =
 * "input[name='shipToAddress.contact.emailId']"; String valNewAdd =
 * "(//h4[@class='capitalized']//copyable-text[@class='ng-binding'])[1]"; //
 * Cancel return Order String lnkEditReturnOrder =
 * "(//a[@class='quick_launch_links' and text()='Edit'])[1]"; String
 * btnCanacelReturn = "button#line-cancel-button"; String chkCancelOrder =
 * "label[for^='cancel-orderline']"; String btnSelLineCancel =
 * "button[class='return-cancel-button']"; String btnCancelTotalOrder =
 * "button[class='WSIModelFormSubmit warning']"; String msgCancelOrder =
 * "//snack-bar[@open]"; String lnkReturnOrder =
 * "a[href^='#/detail/return/default']"; // Add address To String selNewAddTo =
 * "fieldset[name='billToAddress'] select:nth-child(1)"; String
 * txtNewFirstNameBill = "input[name='billToAddress.name.firstName']"; String
 * txtNewLastNameBill = "input[name='billToAddress.name.lastName']"; String
 * txtAddBill = "input[name='billToAddress.address.addressLine1']"; String
 * txtCityBill = "input[name='billToAddress.address.city']"; String txtStateBill
 * = "input[name='billToAddress.address.stateProvince']"; String txtZipCodeBill
 * = "input[name='billToAddress.address.postalCode']"; String txtContactNoBill =
 * "input[title='Enter a valid phone number.']"; String txtEmailBill =
 * "input[name='billToAddress.contact.emailId']"; String valNewBillAddLine =
 * "(//span[@class='ng-binding'])[1]"; String valNewBillAdd =
 * "(//span[@class='ng-binding'])[2]"; //Cancel Order PArtially String imgSearch
 * = "//wsimodel-form[@id='qos-orderNo']//button[@type='submit']"; String
 * valReturnableItems = "fieldset[name='lines'] return-line"; String
 * btnLineLevelCancel = "label[for^='cancel-orderline']"; String
 * btnConfirmLineLevelCan = "button[id='line-cancel-button']"; String
 * txtTotalRefundAmnt =
 * "main > table[class='clean'] tr[id='total-row'] td:nth-child(2) currency-display"
 * ;
 * 
 * // Replacement OrderHeader Level String valBillCustNam
 * ="//h3[@class='capitalized']//human-readable"; String valBillCustAdd
 * ="//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[1]/copyable-text/span[1]";
 * String valBillCityZip =
 * "//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[1]/copyable-text/span[2]";
 * String valBillContact
 * ="//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[2]/copyable-text/format-text";
 * String valBillEmail = "//copyable-text[@class='header-email ng-binding']";
 * String valReplacemntNo =
 * "(//p[contains(text(),'#:')]//copyable-text[@class='ng-binding'])[1]"; //same
 * locater will work for return number String valBrand =
 * "//span[@class='brand-literal ng-binding']"; String valSalesOrder =
 * "//p[contains(text(),'Sales Order')]//span//strong"; String valDatCreatedRef
 * = "//li[@class='span3']//p[@class='fontSmall']//strong[@class='ng-binding']";
 * // Date created for refund String valDatCreatedReplacement =
 * "//span[contains(text(),'Created')]/parent::p//span[2]//strong[1]"; // Date
 * created for replacement String valOrderStatus =
 * "//p[contains(text(),'Status:')]//strong[@class='ng-binding']"; String
 * valPaymentType = "//p[contains(text(),'Payment Type')]//span//strong";
 * //String lnkReturnOrder = "//a[contains(@href,'#/detail/return/default')]";
 * //Add new Payment With credit card String txtCardNo =
 * "input[title='Enter a valid credit card number.]"; String txtExpireDate =
 * "input[name='creditCardExpDate']"; String txtCardHolderName =
 * "input[name='creditCardName']"; String btnAddNewPayment
 * ="button[type='submit']"; Rewards Locators
 * 
 * String tabRewards = "//a[contains(text(),'Rewards')]"; String
 * txtCertificateNo =
 * "div[class='InputGroup '] > input[id = 'certificate-number-input']"; String
 * btnCertSearch = "button[class='WSIModelFormSubmit primary']"; String btnReset
 * = "button[type = 'reset']"; String txtPhoneNo =
 * "div[class='InputGroup PhoneInput '] > input[id = 'certificate-phone-input']"
 * ; String txtPLCC = "#certificate-plcc-input"; String tblCertificate =
 * "div[id='cert-table'] > table[class= 'clean striped'] > tbody > tr > td:nth-child(1)"
 * ; String tblviewHistory =
 * "div[id='cert-table'] > table[class= 'clean striped'] > tbody > tr > td:nth-child(8) > dialog-anchor"
 * ; String tabCertificateSearch =
 * "//span[contains(text(),'Certificate Search')]"; String txtCertificateNum =
 * "(//input[@class='MuiInputBase-input MuiOutlinedInput-input'])[1]"; //in
 * certificate history search String phnnumber =
 * "(//input[@class='MuiInputBase-input MuiOutlinedInput-input'])[3]"; //in
 * certificate history search
 * 
 * String btnSubmit ="//div[contains(text(),'SEARCH')]"; //in certificate
 * history search button submit String clearbtn
 * ="//span[contains(text(),'Clear')]";
 * 
 * String msgCertNo = "//table[@class='undefined']//tbody//tr[1]//td[1]";
 * //certificate search and phone number search Rewards Locators for loyality
 * account search tab
 * 
 * String AccntSearch = "//span[contains(text(),'Account Search')]"; //
 * KeyRewards String KeyRewards="//span[contains(text(),'Key Rewards')]";
 * //Phone number_search String txtPhNoLoyality
 * ="//span[contains(text(),'Phone #')]"; //first name_Search String
 * txtFirstName = "//span[contains(text(),'First Name')]";
 * 
 * String txtLastName = "//span[contains(text(),'Last Name')]"; //Email_Search
 * String txtEmailAdd = "//span[contains(text(),'Email')]";
 * 
 * //First name String btnSearchLoyality = "//div[contains(text(),'SEARCH')]";
 * String msgNameValidation =
 * "//table[@class='undefined']//tbody//tr[1]//td[3]"; String msgEmailValidation
 * = "//table[@class='undefined']//tbody//tr[1]//td[2]"; String msgLoylPhno=
 * "//*[@id='rewardSearchResults']/div/div[2]/p-datatable/div/div[1]/table/tbody/tr/td[1]";
 * String msgFName=
 * "//*[@id='rewardSearchResults']/div/div[2]/p-datatable/div/div[1]/table/tbody/tr/td[3]";
 * String msgEmail=
 * "//*[@id='rewardSearchResults']/div/div[2]/p-datatable/div/div[1]/table/tbody/tr/td[2]";
 * 
 * 
 * public void loyalityAccSearchFLnamePh(WebDriver driver,String fName,String
 * lName,String phno,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElement(driver, AccntSearch, "Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtFirstName1)).sendKeys(fName);
 * oPSelFW.reportStepDetails("Verify the First Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the First name after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtLastName1)).sendKeys(lName);
 * oPSelFW.reportStepDetails("Verify the last Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the last name after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * 
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[2]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(phnnumm)).sendKeys(phno);
 * oPSelFW.reportStepDetails("Verify the phonenum after search"
 * ,"The phone number is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the phonenum  after search"
 * ,"The phone number is not displaying .", "Fail"); }
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with first last name and Phone num"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } String txtFirstName1=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input";
 * String txtLastName1=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[2]/div/div/input";
 * String btnSearchLoyality1=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[1]/div[2]/button[2]/span[1]/div";
 * String phnnumm=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[1]/div[1]/div[3]/div/div/input";
 * String Emailid=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[4]/div/div/input";
 * String Search_Result=
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]";
 * 
 * public void loyalityAccSearchFnamePh(WebDriver driver,String fName,String
 * phno,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, AccntSearch, "Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW); //
 * Thread.sleep(2000); if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtFirstName1)).sendKeys(fName);
 * oPSelFW.reportStepDetails("Verify the Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the name after search"
 * ,"The name is not displaying .", "Fail"); } if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[1]/div[1]/div[3]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(phnnumm)).sendKeys(phno);
 * oPSelFW.reportStepDetails("Verify the phonenum after search"
 * ,"The phone number is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the phonenum  after search"
 * ,"The phone number is not displaying .", "Fail"); }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * // gen.setToText(driver, txtFirstName,fName, "First name", oPSelFW);
 * //gen.setToText(driver, txtPhNoLoyality, phno, "Phone Number", oPSelFW); //
 * gen.clickToElement(driver, btnSearchLoyality, "search", oPSelFW);
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no and first name"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } public void loyalityAccSearchFstname(WebDriver driver,String
 * fName,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, AccntSearch, " Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtFirstName1)).sendKeys(fName);
 * oPSelFW.reportStepDetails("Verify the Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the name after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no and first name"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } public void loyalityAccSearchEmail(WebDriver driver,String
 * eMail,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, AccntSearch, "Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[4]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(Emailid)).sendKeys(eMail);
 * oPSelFW.reportStepDetails("Verify the email  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the email after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no and first name"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } public void loyalityAccSearchFLname(WebDriver driver,String fName,String
 * lName,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver,AccntSearch, "Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtFirstName1)).sendKeys(fName);
 * oPSelFW.reportStepDetails("Verify the First Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the First name after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[2]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtLastName1)).sendKeys(lName);
 * oPSelFW.reportStepDetails("Verify the last Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the last name after search"
 * ,"The name is not displaying .", "Fail"); }
 * 
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no and first name"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } public void loyalityAccSearchFname(WebDriver driver,String
 * fName,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElement(driver, tabRewards); gen.clickElement(driver,AccntSearch,
 * "Clicked on Account search", oPSelFW); gen.clickElements(driver, KeyRewards,
 * "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div/div[1]/div[1]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(txtFirstName1)).sendKeys(fName);
 * oPSelFW.reportStepDetails("Verify the Name  after search"
 * ,"The name is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the name after search"
 * ,"The name is not displaying .", "Fail"); }
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no and first name"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * } public void loyalityAccSearchPh(WebDriver driver,String
 * phno,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, AccntSearch, "Account search", oPSelFW);
 * gen.clickElements(driver, KeyRewards, "Key Rewards", oPSelFW);
 * 
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[1]/div[1]/div[3]/div/div/input"
 * )).size()!=0) { driver.findElement(By.xpath(phnnumm)).sendKeys(phno);
 * oPSelFW.reportStepDetails("Verify the phonenum after search"
 * ,"The phone number is displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the phonenum  after search"
 * ,"The phone number is not displaying .", "Fail"); }
 * 
 * 
 * 
 * Thread.sleep(4000);
 * 
 * 
 * 
 * 
 * driver.findElement(By.xpath(btnSearchLoyality1)).click(); Thread.sleep(4000);
 * // String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(driver.findElements(By.xpath(
 * "//*[@id='single-spa-application:@ccui/rewards']/div/div[3]/div/div[3]/div/div[2]/table/thead/tr/th[2]"
 * )).size()!=0) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are displaying successfully.", "Pass");
 * 
 * }
 * 
 * else { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results are not displaying .", "Fail"); }
 * 
 * 
 * 
 * 
 * gen.setToText(driver, txtPhNoLoyality, phno, "Phone Number", oPSelFW);
 * gen.clickToElement(driver, btnSearchLoyality, "search", oPSelFW);
 * Thread.sleep(3000); String
 * errorMsg=driver.findElement(By.xpath("//*[@id='tabMessage']")).getText();
 * if(errorMsg.contains("Could Not Retrieve Authentication.")) {
 * oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results after search is Could not retrieve authentication.","Pass");
 * 
 * } else if(errorMsg.
 * contains("LOYALTY ACCOUNT LOOKUP FAILED. PLEASE CONTACT WCC SUPPORT AND RAISE A TICKET"
 * )) { oPSelFW.reportStepDetails("Verify the Results after search"
 * ,"The Results after search is LOYALTY ACCOUNT LOOKUP FAILED. PLEASE CONTACT WCC SUPPORT AND RAISE A TICKET"
 * ,"Pass");
 * 
 * } else { String text = gen.getTextOfElement(driver, msgLoylPhno); //getting
 * certificate number from CCUI screen aftercertificate search
 * if(text.contains(phno)) {
 * oPSelFW.reportStepDetails("Verify the Results after Search"
 * ,"The Results after Search "+phno+"","Pass"); //oPSelFW.
 * reportStepDtlsWithScreenshot("Loyality Account Search with Phone num "
 * ," Search Details", "Pass");
 * 
 * }else { oPSelFW.reportStepDetails("Verify the Results after Search"
 * ,"The Results after Search"+phno,"Fail");
 * oPSelFW.reportStepDtlsWithScreenshot("Loyality Account Search with Phone num"
 * ,"Search Details", "Fail");
 * 
 * Assert.assertEquals("Loyality Account Search with Phone num","Search Details"
 * );
 * 
 * } }
 * 
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for Loyality Account Search with phone no"
 * , "Exception is displayed while phone number search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * }
 * 
 * 
 * 
 * public void certificateHistorySearch(WebDriver driver,String
 * certNo,ProlificsSeleniumAPI oPSelFW) throws IOException { try {
 * gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, tabCertificateSearch, "certificate history search",
 * oPSelFW); gen.setToText(driver, txtCertificateNum, certNo, "Certificate #",
 * oPSelFW); gen.clickToElement(driver, btnSubmit, "submit", oPSelFW);
 * Thread.sleep(3000);
 * 
 * String
 * err_msg="//p[contains(text(),'EPS internal error. Contact EPS support.')]";
 * if (driver.findElements(By.
 * xpath("(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-justify-content-xs-space-between'])[1]"
 * )).size() != 0) { oPSelFW.
 * reportStepDetails("Verify the Certificate details display in ceritificate history search "
 * ,"Certificate details are displayed for "+certNo, "Pass"); oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Pass");
 * 
 * } else if (driver.findElements(By.
 * xpath("//p[contains(text(),'EPS internal error. Contact EPS support.')]")).
 * size() != 0) { oPSelFW.
 * reportStepDetails("Certificate details display in ceritificate history search "
 * ,"Certificate details are not displayed for "+certNo, "Fail"); oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Fail");
 * Assert.assertEquals("Certificate deatails in certificate history Search "
 * ,"Certificate details"); }
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify certificate details displayed for certificate search in certificate history search"
 * , "Exception is displayed while certificate search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); }
 * 
 * }
 * 
 * 
 * public void Resetphndetails(WebDriver driver,String
 * phoneNo,ProlificsSeleniumAPI oPSelFW) throws IOException {
 * 
 * try { gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, tabCertificateSearch, "certificate history search",
 * oPSelFW); gen.setToText(driver, phnnumber, phoneNo, "Certificate #",
 * oPSelFW); gen.clickToElement(driver, btnSubmit, "submit", oPSelFW);
 * Thread.sleep(3000);
 * 
 * if (driver.findElements(By.
 * xpath("(//table//tr[1])[1]//th[contains(text(),'Certificate Number' ) ]")).
 * size() != 0) {
 * oPSelFW.reportStepDetails("Verify the  details display in phone search "
 * ,"Details displayed after search with phone number "+phoneNo, "Pass");
 * oPSelFW.
 * reportStepDtlsWithScreenshot("Verify the  details display in phone search "
 * ," details Displayed", "Pass");
 * 
 * } else if (driver.findElements(By.
 * xpath("//p[contains(@class,'MuiFormHelperText-root MuiFormHelperText-contained Mui-error MuiFormHelperText-filled' ) ]"
 * )).size() != 0) {
 * oPSelFW.reportStepDetails("Certificate details display in  history search "
 * ,"Certificate details are not displayed for "+phoneNo, "Fail"); oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Fail");
 * Assert.assertEquals("Certificate deatails in certificate history Search "
 * ,"Certificate details"); }
 * 
 * Thread.sleep(3000);
 * 
 * gen.clickToElement(driver, clearbtn, "clear", oPSelFW);
 * 
 * if (driver.findElements(By.
 * xpath("(//table//tr[1])[1]//th[contains(text(),'Certificate Number' ) ]")).
 * size() != 0) {
 * oPSelFW.reportStepDetails("Verify the  details display in phone search "
 * ,"Details displayed after search with phone number "+phoneNo, "Fail");
 * oPSelFW.
 * reportStepDtlsWithScreenshot("Verify the  details display in phone search "
 * ," details Displayed", "Fail");
 * 
 * }
 * 
 * else {
 * oPSelFW.reportStepDetails("Verify the  details display in phone search "
 * ,"Details displayed after search with phone number "+phoneNo, "Pass");
 * oPSelFW.
 * reportStepDtlsWithScreenshot("Verify the  details display in phone search "
 * ," details Displayed", "Pass");
 * 
 * } }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for Loyality Account search"
 * , "Exception is displayed while Loyality Account search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } }
 * 
 * 
 * 
 * public void certificateSearch(WebDriver driver,String
 * certificateNo,ProlificsSeleniumAPI oPSelFW) throws IOException {
 * 
 * try { gen.clickElement(driver, tabRewards); gen.clickElements(driver,
 * tabCertificateSearch, "certificate history search", oPSelFW);
 * gen.setToText(driver, txtCertificateNum, certificateNo, "Certificate #",
 * oPSelFW); gen.clickToElement(driver, btnSubmit, "submit", oPSelFW);
 * Thread.sleep(3000);
 * 
 * String
 * err_msg="//p[contains(text(),'EPS internal error. Contact EPS support.')]";
 * if (driver.findElements(By.
 * xpath("(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-justify-content-xs-space-between'])[1]"
 * )).size() != 0) { oPSelFW.
 * reportStepDetails("Verify the Certificate details display in ceritificate history search "
 * ,"Certificate details are displayed for "+certificateNo, "Pass"); oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Pass");
 * 
 * } else if (driver.findElements(By.
 * xpath("//p[contains(text(),'EPS internal error. Contact EPS support.')]")).
 * size() != 0) { oPSelFW.
 * reportStepDetails("Certificate details display in ceritificate history search "
 * ,"Certificate details are not displayed for "+certificateNo, "Fail");
 * oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Fail");
 * Assert.assertEquals("Certificate deatails in certificate history Search "
 * ,"Certificate details"); }
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for certificate search",
 * "Exception is displayed while certificate search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } } public
 * void certificateSearchwithoutParameter(WebDriver driver,ProlificsSeleniumAPI
 * oPSelFW) throws IOException {
 * 
 * try { gen.clickElement(driver, tabRewards); gen.clickElements(driver,
 * tabCertificateSearch, "certificate history search", oPSelFW);
 * gen.clickToElement(driver, btnSubmit, "submit", oPSelFW);
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for certificate search",
 * "Exception is displayed while certificate search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } } public
 * void phnSearch(WebDriver driver,String phoneNo,ProlificsSeleniumAPI oPSelFW)
 * throws IOException {
 * 
 * try { gen.clickElements(driver, tabRewards,"Rewards",oPSelFW);
 * gen.clickElements(driver, tabCertificateSearch, "certificate history search",
 * oPSelFW); gen.setToText(driver, phnnumber, phoneNo, "Certificate #",
 * oPSelFW); gen.clickToElement(driver, btnSubmit, "submit", oPSelFW);
 * Thread.sleep(3000);
 * 
 * if (driver.findElements(By.
 * xpath("(//table//tr[1])[1]//th[contains(text(),'Certificate Number' ) ]")).
 * size() != 0) {
 * oPSelFW.reportStepDetails("Verify the  details display in phone search "
 * ,"Details displayed after search with phone number "+phoneNo, "Pass");
 * oPSelFW.
 * reportStepDtlsWithScreenshot("Verify the  details display in phone search "
 * ," details Displayed", "Pass");
 * 
 * } else if (driver.findElements(By.
 * xpath("//p[contains(@class,'MuiFormHelperText-root MuiFormHelperText-contained Mui-error MuiFormHelperText-filled' ) ]"
 * )).size() != 0) {
 * oPSelFW.reportStepDetails("Certificate details display in  history search "
 * ,"Certificate details are not displayed for "+phoneNo, "Fail"); oPSelFW.
 * reportStepDtlsWithScreenshot("Certificate deatails in certificate history Search "
 * ,"Certificate details", "Fail");
 * Assert.assertEquals("Certificate deatails in certificate history Search "
 * ,"Certificate details"); }
 * 
 * }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for Loyality Account search"
 * , "Exception is displayed while Loyality Account search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } }
 * 
 * 
 * public void loyalityACCPLCCSearch(WebDriver driver,String
 * plcc,ProlificsSeleniumAPI oPSelFW) throws IOException {
 * 
 * try { gen.clickElements(driver, tabRewards,"Rewards",oPSelFW); Shadow shadow
 * = new Shadow(driver); WebElement root1 = driver.findElement(By.
 * cssSelector("#NG2_UPGRADE_2_0 > div > content-tabs > tab-body:nth-child(2) > certificate-search-page > shadow-node"
 * )); WebElement shadowRoot1 =expandShadowRootElement(driver, root1);
 * if(shadowRoot1.findElements(By.cssSelector(txtPLCC)).size()>0) {
 * 
 * gen.setTextShadowElementWithRoot(driver, shadowRoot1, txtPLCC, plcc,"PLCC #",
 * oPSelFW); Thread.sleep(8000); WebElement root11 =
 * driver.findElement(By.cssSelector("shadow-node")); WebElement shadowRoot11
 * =expandShadowRootElement(driver, root11);
 * gen.clickShadowElementWithRoot(driver, shadowRoot11, btnCertSearch,"Search",
 * oPSelFW); Thread.sleep(9000);
 * if(shadowRoot1.findElements(By.cssSelector(tblCertificate)).size()>0) {
 * 
 * String text =
 * shadowRoot1.findElement(By.cssSelector(tblCertificate)).getText();
 * oPSelFW.reportStepDetails("Get Certificate Number","Certificate Number is "
 * +text, "Pass"); gen.clickShadowElementWithRoot(driver, shadowRoot1,
 * tblviewHistory,"View History", oPSelFW); Thread.sleep(15000);
 * 
 * String certificateNo =
 * shadowRoot1.findElement(By.cssSelector("span:nth-child(1)")).getText();
 * if(certificateNo.contains(text)) {
 * oPSelFW.reportStepDetails("Certificate search in Rewards "
 * ,"Certificate details displayed for "+certificateNo, "Pass");
 * oPSelFW.reportStepDtlsWithScreenshot("Certificate Search in Rewards"
 * ,"Certificate Search", "Pass");
 * 
 * }else { oPSelFW.reportStepDetails("Certificate search in Rewards "
 * ,"Certificate details not displayed for "+certificateNo, "Fail");
 * oPSelFW.reportStepDtlsWithScreenshot("Certificate Search in Rewards"
 * ,"Certificate Search", "Fail");
 * Assert.assertEquals("Certificate Search in Rewards","Certificate Search"); }
 * } else { oPSelFW.reportStepDetails("Certificate search in Rewards with PLCC "
 * ,"Certificate search in Rewards is:no certicate asscoiate with this PLCC",
 * "Pass");
 * 
 * } } }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for Loyality Account search"
 * , "Exception is displayed while Loyality Account search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } }
 * 
 * public void loyalityACCPLCCSearchReset(WebDriver driver,String
 * plcc,ProlificsSeleniumAPI oPSelFW) throws IOException {
 * 
 * try { gen.clickElements(driver, tabRewards,"Rewards",oPSelFW); Shadow shadow
 * = new Shadow(driver); WebElement root1 = driver.findElement(By.
 * cssSelector("#NG2_UPGRADE_2_0 > div > content-tabs > tab-body:nth-child(2) > certificate-search-page > shadow-node"
 * )); WebElement shadowRoot1 =expandShadowRootElement(driver, root1);
 * if(shadowRoot1.findElements(By.cssSelector(txtPLCC)).size()>0) {
 * gen.setTextShadowElementWithRoot(driver, shadowRoot1, txtPLCC, plcc,"PLCC #",
 * oPSelFW); Thread.sleep(5000); WebElement root11 =
 * driver.findElement(By.cssSelector("shadow-node")); WebElement shadowRoot11
 * =expandShadowRootElement(driver, root11);
 * gen.clickShadowElementWithRoot(driver, shadowRoot11, btnCertSearch,"Search",
 * oPSelFW); Thread.sleep(5000); if(shadowRoot1.findElements(By.
 * cssSelector("#cert-table > table > tbody > tr:nth-child(1) > td:nth-child(1)"
 * )).size()>0) { String certificateNo = shadowRoot1.findElement(By.
 * cssSelector("#cert-table > table > tbody > tr:nth-child(1) > td:nth-child(1)"
 * )).getText(); if(shadowRoot1.findElements(By.
 * cssSelector("#cert-table > table > tbody > tr:nth-child(1) > td:nth-child(1)"
 * )).size()> 0) {
 * oPSelFW.reportStepDetails("Reset for Certificate search in Rewards "
 * ,"Reset has been for Loyality Account Number"+plcc, "Pass");
 * oPSelFW.reportStepDtlsWithScreenshot("Reset for certificate Seacrh "
 * ,"Reset is not done", "Pass"); }else {
 * oPSelFW.reportStepDetails("Reset for Certificate search in Rewards "
 * ,"Reset has not been for Loyality Account Number"+plcc, "Fail");
 * oPSelFW.reportStepDtlsWithScreenshot("Reset for certificate Seacrh "
 * ,"Reset is not done", "Fail");
 * Assert.assertEquals("Reset for certificate Seacrh ","Reset is not done"); }
 * gen.clickShadowElementWithRoot(driver, shadowRoot1,
 * "main > wsimodel-form > event-listener > button:nth-child(6)","Reset",
 * oPSelFW); } else {
 * oPSelFW.reportStepDetails("Certificate search in Rewards with PLCC "
 * ,"Certificate search in Rewards is:no certicate asscoiate with this PLCC",
 * "Pass");
 * 
 * } } }catch(Exception e) { oPSelFW.
 * reportStepDetails("Verify if any error is displayed for Loyality Account search"
 * , "Exception is displayed while Loyality Account search", "Fail"); String
 * expectedresult = "No error message should be displayed";
 * Assert.assertEquals("Error message is displayed", expectedresult); } }
 * 
 * 
 * 
 * 
 * ############################################################## # Method :
 * ccuiLogin # Description :Log in to CCUI Application # Parameters
 * :UserName,Password,driver,basetest # Author : Prasanna # Date : 05/29/2019 #
 * Modifications : # Modified Date :
 * ##############################################################
 * 
 * public void ccuiLogin(WebDriver driver,HashMap<String, String>
 * envTestData,HashMap<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
 * throws Exception {
 * 
 * try {
 * 
 * 
 * String url = envTestData.get("CCUIURL").toString(); String userName =
 * envTestData.get("CCUIUserName").toString(); String pwd =
 * envTestData.get("CCUIPasword").toString(); driver.get(url);
 * gen.setText(driver, txtUserName, userName, oPSelFW); gen.setText(driver,
 * txtPassword, pwd, oPSelFW); gen.clickElement(driver, btnLogin,oPSelFW);
 * Thread.sleep(14000); if
 * (driver.findElements(By.xpath("//li[@id='tab-order']")).size() != 0) {
 * System.out.println("user logged sucessfully");
 * oPSelFW.reportStepDetails("user is successfully Logged in CCUI",userName,
 * "Pass"); } else { oPSelFW.reportStepDetails("User Login"
 * ,"user is not successfully Logged in CCUI","Fail");
 * Assert.assertEquals("user is not successfully Logged in CCUI","Login failed"
 * ); } } catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail");
 * Assert.assertEquals("Exception","Throwing Exception"); } } public void
 * ccuiNonReutnableItems(WebDriver driver,String orderNo,ProlificsSeleniumAPI
 * oPSelFW) throws Exception {
 * 
 * try { gen.setText(driver, txtOrderSearch,
 * orderNo,"Sales Order "+orderNo+" is entered in to order search box",
 * oPSelFW); driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * Thread.sleep(4000); Shadow shadow = new Shadow(driver);
 * gen.clickElement(driver, tabReturns,oPSelFW); Thread.sleep(40000); if
 * (shadow.findElements(btnCreateReturn).size() == 0) {
 * System.out.println("Item is not eligible for Return");
 * oPSelFW.reportStepDetails("Items are not eligible for returns","Pass",
 * orderNo); } else {
 * oPSelFW.reportStepDetails("Items are not eligible for returns","Fail",
 * orderNo); Assert.assertTrue(false); } } catch (Exception e) {
 * oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail");
 * Assert.assertEquals("Exception","Throwing Exception");
 * 
 * } }
 * 
 * public orderheaderDetails_Payments
 * ccuiReplacementOrderHeadervalidation(WebDriver driver, String orderNo,int
 * subOrderCnt, ProlificsSeleniumAPI oPSelFW) throws InterruptedException,
 * IOException { orderheaderDetails_Payments objItemDet = new
 * orderheaderDetails_Payments();
 * objItemDet.BilltocustomerName_CCUI_text.clear();
 * objItemDet.Billtocustomeraddress_CCUI.clear();
 * objItemDet.Billtocustomercityandzipcode_CCUI.clear();
 * objItemDet.Billtocustomerprimarycontact_CCUI.clear();
 * objItemDet.BilltocustomerEmailid_CCUI.clear();
 * objItemDet.ReturnNumber_CCUI.clear(); objItemDet.Brand_CCUI.clear();
 * objItemDet.ReturnMethod_CCUI.clear(); objItemDet.SalesOrder_CCUI.clear();
 * objItemDet.Datecreated_CCUI.clear(); objItemDet.ReturnAmount_CCUI.clear();
 * objItemDet.Status_CCUI.clear(); objItemDet.OrderType.clear();
 * objItemDet.RefundAmount_CCUI.clear(); objItemDet.Ordertotal_CCUI.clear();
 * objItemDet.paymentType.clear(); JavascriptExecutor js = (JavascriptExecutor)
 * driver; try {
 * objItemDet.BilltocustomerName_CCUI_text.add(driver.findElement(By.xpath(
 * valBillCustNam)).getAttribute("innerText"));
 * objItemDet.Billtocustomeraddress_CCUI.add(gen.getTextOfElement(driver,
 * valBillCustAdd));
 * objItemDet.Billtocustomercityandzipcode_CCUI.add(gen.getTextOfElement(driver,
 * valBillCityZip));
 * objItemDet.Billtocustomerprimarycontact_CCUI.add(gen.getTextOfElement(driver,
 * valBillContact).replaceAll("[^0-9\\.]+", ""));
 * objItemDet.BilltocustomerEmailid_CCUI.add(gen.getTextOfElement(driver,
 * valBillEmail)); objItemDet.ReturnNumber_CCUI.add(gen.getTextOfElement(driver,
 * valReplacemntNo)); objItemDet.Brand_CCUI.add(gen.getTextOfElement(driver,
 * valBrand).replaceAll("[^A-Z\\.]+", ""));
 * objItemDet.ReturnMethod_CCUI.add(gen.getTextOfElement(driver, valOrderType));
 * objItemDet.SalesOrder_CCUI.add(gen.getTextOfElement(driver, valSalesOrder));
 * objItemDet.Datecreated_CCUI.add(gen.getTextOfElement(driver,
 * valDatCreatedReplacement));
 * objItemDet.Status_CCUI.add(gen.getTextOfElement(driver, valOrderStatus));
 * objItemDet.OrderType.add(gen.getTextOfElement(driver, valOrderType)); String
 * amount = driver.findElement(By.xpath(valOrderTotal)).getAttribute("amount");
 * 
 * if(amount.contains(",")) { amount = amount.replace(",", ""); } amount =
 * amount.replace("$", ""); System.out.println("amount :"+amount);
 * objItemDet.Ordertotal_CCUI.add(amount); String paymentType =
 * gen.getTextOfElement(driver, valPaymentType); paymentType =
 * paymentType.replace(" ", "_");
 * System.out.println("paymentType :"+paymentType); paymentType =
 * paymentType.toUpperCase(); System.out.println("paymentType ::"+paymentType);
 * objItemDet.paymentType.add(paymentType); for (int i = 1; i <= subOrderCnt;
 * i++) { Thread.sleep(5000); String Item_Id =
 * driver.findElement(By.xpath("(//a[@class='themeColor ng-scope ng-binding'])["
 * +i+"]")) .getAttribute("tooltip").replaceAll("[^0-9]", ""); String
 * Shipping_Customername_CCUI=null; //Sub-Order #01
 * oPSelFW.reportStepDetails("Ship to Address for the Item ", "Pass", Item_Id);
 * Shipping_Customername_CCUI=driver.findElement(By.xpath(
 * "(//div[@ng-if='subOrder.shipTo'])["+i+"]//h4[1]")).getAttribute("innerText")
 * ;
 * 
 * System.out.println("Shipping_Customername_CCUI" +
 * Shipping_Customername_CCUI);
 * objItemDet.Shipping_Customername_CCUI.add(Shipping_Customername_CCUI);
 * oPSelFW.reportStepDetails("Shipto customer name displayed</span> " +
 * " Successfully<br>","Pass", Shipping_Customername_CCUI); Thread.sleep(2000);
 * 
 * String Shipping_Customeraddress_CCUI=null;
 * Shipping_Customeraddress_CCUI=driver.findElement(By.xpath(
 * "(//div[@ng-if='subOrder.shipTo'])["+i+"]//h4[2]")).getAttribute("innerText")
 * ;
 * 
 * System.out.println("Shipping_Customeraddress_CCUI" +
 * Shipping_Customeraddress_CCUI);
 * objItemDet.Shipping_Customeraddress_CCUI.add(Shipping_Customeraddress_CCUI);
 * oPSelFW.reportStepDetails("Shipto customer address displayed</span> " +
 * " Successfully<br>","Pass", Shipping_Customeraddress_CCUI); }
 * oPSelFW.reportStepDetails("Shipping Address:"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", paymentType); } catch (Exception e) {
 * oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * return objItemDet; } public orderheaderDetails_Payments
 * ccuiSalOrderdetailsvalidation(WebDriver driver, String orderNo,int
 * subOrderCnt, ProlificsSeleniumAPI oPSelFW) throws InterruptedException,
 * IOException { orderheaderDetails_Payments objItemDet = new
 * orderheaderDetails_Payments();
 * objItemDet.BilltocustomerName_CCUI_text.clear();
 * objItemDet.Billtocustomeraddress_CCUI.clear();
 * objItemDet.Billtocustomercityandzipcode_CCUI.clear();
 * objItemDet.Billtocustomerprimarycontact_CCUI.clear();
 * objItemDet.BilltocustomerEmailid_CCUI.clear();
 * objItemDet.ReturnNumber_CCUI.clear(); objItemDet.Brand_CCUI.clear();
 * objItemDet.ReturnMethod_CCUI.clear(); objItemDet.SalesOrder_CCUI.clear();
 * objItemDet.Datecreated_CCUI.clear(); objItemDet.ReturnAmount_CCUI.clear();
 * objItemDet.Status_CCUI.clear(); objItemDet.RefundAmount_CCUI.clear();
 * objItemDet.Ordertotal_CCUI.clear(); JavascriptExecutor js =
 * (JavascriptExecutor) driver;
 * 
 * try {
 * 
 * WebElement BilltocustomerName_CCUI =
 * driver.findElement(By.xpath("//h3[@class='capitalized']//human-readable"));
 * String
 * BilltocustomerName_CCUI_text=BilltocustomerName_CCUI.getAttribute("innerText"
 * ); System.out.println("BilltocustomerName_CCUI_text" +
 * BilltocustomerName_CCUI_text);
 * objItemDet.BilltocustomerName_CCUI_text.add(BilltocustomerName_CCUI_text);
 * String Billtocustomeraddress_CCUI = driver.findElement(By.xpath(
 * "//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[1]/copyable-text/span[1]"
 * )).getText();
 * objItemDet.Billtocustomeraddress_CCUI.add(Billtocustomeraddress_CCUI); String
 * Billtocustomercityandzipcode_CCUI = driver.findElement(By.xpath(
 * "//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[1]/copyable-text/span[2]"
 * )).getText(); System.out.println("Billtocustomercityandzipcode_CCUI" +
 * Billtocustomercityandzipcode_CCUI);
 * objItemDet.Billtocustomercityandzipcode_CCUI.add(
 * Billtocustomercityandzipcode_CCUI); String Billtocustomerprimarycontact_CCUI
 * = driver.findElement(By.xpath(
 * "//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[2]/copyable-text/format-text"
 * )).getText().replaceAll("[^0-9\\.]+", "");
 * objItemDet.Billtocustomerprimarycontact_CCUI.add(
 * Billtocustomerprimarycontact_CCUI);
 * System.out.println("Billtocustomerprimarycontact_CCUI :"
 * +Billtocustomerprimarycontact_CCUI); String BilltocustomerEmailid_CCUI =
 * driver.findElement(By.
 * xpath("//copyable-text[@class='header-email ng-binding']")).getText();
 * objItemDet.BilltocustomerEmailid_CCUI.add(BilltocustomerEmailid_CCUI);
 * System.out.println("BilltocustomerEmailid_CCUI :"+BilltocustomerEmailid_CCUI)
 * ; String ReturnNumber_CCUI = driver.findElement(By.xpath(
 * "(//p[contains(text(),'#:')]//copyable-text[@class='ng-binding'])[1]")).
 * getText(); objItemDet.ReturnNumber_CCUI.add(ReturnNumber_CCUI);
 * System.out.println("ReturnNumber_CCUI :"+ReturnNumber_CCUI); String
 * Brand_CCUI =
 * driver.findElement(By.xpath("//span[@class='brand-literal ng-binding']")).
 * getText().replaceAll("[^A-Z\\.]+", "");
 * objItemDet.Brand_CCUI.add(Brand_CCUI);
 * System.out.println("Brand_CCUI :"+Brand_CCUI); String ReturnMethod_CCUI =
 * driver.findElement(By.
 * xpath("//span[contains(text(),'Return Method: ')]/following::span[1]//span[contains(@class,'ng-binding')]"
 * )).getText(); objItemDet.ReturnMethod_CCUI.add(ReturnMethod_CCUI);
 * System.out.println("ReturnMethod_CCUI :"+ReturnMethod_CCUI); String
 * SalesOrder_CCUI = driver.findElement(By.
 * xpath("//span[contains(text(),'Sales Order ')]/following::span[1]//strong[contains(@class,'ng-binding')]"
 * )).getText(); objItemDet.SalesOrder_CCUI.add(SalesOrder_CCUI);
 * System.out.println("SalesOrder_CCUI :"+SalesOrder_CCUI); //String
 * Datecreated_CCUI = driver.findElement(By.xpath(
 * "//li[@class='span3']//p[@class='fontSmall']//strong[@class='ng-binding']")).
 * getText(); //refund locater String Datecreated_CCUI =
 * driver.findElement(By.xpath(
 * "//span[contains(text(),'Created')]/parent::p//span[2]//strong[1]")).getText(
 * ); //replacement locater objItemDet.Datecreated_CCUI.add(Datecreated_CCUI);
 * System.out.println("Datecreated_CCUI :"+Datecreated_CCUI); String
 * ReturnAmount_CCUI = driver.findElement(By.
 * xpath("//span[contains(text(),'Return Amount: ')]/following::span[1]//strong[contains(@class,'ng-binding')]"
 * )).getText(); objItemDet.ReturnAmount_CCUI.add(ReturnAmount_CCUI);
 * System.out.println("ReturnAmount_CCUI :"+ReturnAmount_CCUI); String
 * Status_CCUI = driver.findElement(By.xpath(
 * "//p[contains(text(),'Status:')]//strong[@class='ng-binding']")).getText();
 * objItemDet.Status_CCUI.add(Status_CCUI);
 * System.out.println("Status_CCUI :"+Status_CCUI); String RefundAmount_CCUI =
 * driver.findElement(By.
 * xpath("//p[contains(text(),'Payment Type:')]//strong[@class='ng-binding']")).
 * getText(); objItemDet.RefundAmount_CCUI.add(RefundAmount_CCUI);
 * System.out.println("RefundAmount_CCUI :"+RefundAmount_CCUI); //String
 * RefundAmount = driver.findElement(By.
 * xpath("//span[contains(text(),'Refund Amount:')]/following-sibling::span//currency-display[text()]"
 * )).getAttribute("innerText"); String RefundAmount =
 * driver.findElement(By.xpath(
 * "//span[contains(text(),'Amount')]/following-sibling::span//strong[text()]"))
 * .getAttribute("innerText"); String Ordertotal1 = RefundAmount.replace(",",
 * ""); String RefundAmount_CCUI =Ordertotal1.replace("$", "");
 * objItemDet.RefundAmount_CCUI.add(RefundAmount_CCUI);
 * System.out.println("Ordertotal :"+RefundAmount_CCUI); for (int i = 1; i <=
 * subOrderCnt; i++) {
 * 
 * //Sub order # // Name, address, city, zip code, state //phone number, mail id
 * Thread.sleep(5000); //(//h4[@class='ng-binding'])[1]
 * //if(driver.findElement(By.xpath(OrderQuantity_PT_PB)).isDisplayed()){ String
 * Item_Id =
 * driver.findElement(By.xpath("(//a[@class='themeColor ng-scope ng-binding'])["
 * +i+"]")) .getAttribute("tooltip").replaceAll("[^0-9]", ""); String
 * Shipping_Customername_CCUI=null; //Sub-Order #01 oPSelFW.
 * reportStepDetails("<span style='font-weight:bold;color:blue'>Ship to Address for the Item "
 * +Item_Id+"</span","Pass", Shipping_Customername_CCUI);
 * Shipping_Customername_CCUI=driver.findElement(By.xpath(
 * "(//div[@ws-ng-show='!isShipAddessEmpty'])["+i+"]//h4[1]")).getAttribute(
 * "innerText");
 * 
 * System.out.println("Shipping_Customername_CCUI" +
 * Shipping_Customername_CCUI);
 * objItemDet.Shipping_Customername_CCUI.add(Shipping_Customername_CCUI);
 * oPSelFW.reportStepDetails("<b>"+Shipping_Customername_CCUI+"</b>" + "    " +
 * " "+ "Shipto customer name displayed</span> " + " Successfully<br>","Pass",
 * Shipping_Customername_CCUI); Thread.sleep(2000);
 * 
 * String Shipping_Customeraddress_CCUI=null;
 * Shipping_Customeraddress_CCUI=driver.findElement(By.xpath(
 * "(//div[@ws-ng-show='!isShipAddessEmpty'])["+i+"]//h4[2]")).getAttribute(
 * "innerText");
 * 
 * System.out.println("Shipping_Customeraddress_CCUI" +
 * Shipping_Customeraddress_CCUI);
 * objItemDet.Shipping_Customeraddress_CCUI.add(Shipping_Customeraddress_CCUI);
 * oPSelFW.reportStepDetails("<b>"+Shipping_Customeraddress_CCUI+"</b>" + "    "
 * + " "+ "Shipto customer address displayed</span> " +
 * " Successfully<br>","Pass", Shipping_Customeraddress_CCUI); }
 * oPSelFW.reportStepDetails("Shipping Address:"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", RefundAmount_CCUI); } catch (Exception e) {
 * oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * return objItemDet; }
 * 
 * ############################################################## # Method
 * :ccuiReturnWithRefund # Description :Sales Order Return in CCUI # Parameters
 * : driver, orderNo, basetest # Author : Prasanna # Date : 06/03/2019 #
 * Modifications : # Modified Date :
 * ##############################################################
 * 
 * public int ccuiReturnWithRefundBasedItem(WebDriver driver,HashMap<String,
 * String> XLTestData,HashMap<String, String> envTestdata, ProlificsSeleniumAPI
 * oPSelFW) throws Exception { System.out.println("ccuiReturnWithRefund " +
 * driver); String[] items = null; String[] quantities = null; String[]
 * returnActn = null; int returnItems =0; Thread.sleep(9000);
 * 
 * String itemId=XLTestData.get("ItemID").toString(); String
 * orderNo=XLTestData.get("SalesOrderNo").toString();
 * 
 * String quantity=XLTestData.get("Quantity").toString(); String
 * returnAction=XLTestData.get("ReturnAction").toString(); try {
 * gen.setText(driver, txtOrderSearch,
 * orderNo,"Sales Order "+orderNo+" is entered in to order search box",
 * oPSelFW); driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * Thread.sleep(4000); Shadow shadow = new Shadow(driver);
 * gen.clickElement(driver, tabReturns,oPSelFW); Thread.sleep(10000); if
 * (shadow.findElements(btnCreateReturn).size() == 0) {
 * System.out.println("Item is not eligible for Return"); oPSelFW.
 * reportStepDetails("Item is not eligible for Return <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", returnAction); Assert.assertTrue(false); } else
 * { Thread.sleep(4000); int size =
 * shadow.findElements("table.clean>tbody>returnable-counter > dropdown-tr").
 * size(); //basetest.test.log(Status.
 * PASS,"<span style='font-weight:bold;color:blue'><table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Item Id</b></td><td word-wrap: break-word width ='30' style='text-align:left'>Quantity</td><td word-wrap: break-word width ='30' style='text-align:left'>Return Action</td></tr></table></span>"
 * ); if(itemId.contains(",")) {
 * 
 * 
 * items= itemId.split(","); returnItems =items.length; quantities =
 * quantity.split(","); returnActn = returnAction.split(","); for(int j=0;
 * j<=items.length-1;j++ ) { for (int i = 1; i <= size; i++) {
 * System.out.println("In For Loop " + shadow.findElements(
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i +
 * ") td:nth-child(4) a") .size()); String text = shadow.findElement(
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i +
 * ") td:nth-child(4) a") .getAttribute("tooltip"); if (text.contains(items[j]))
 * { shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i + ") td:nth-child(1) div.CheckboxGroup label[for^='line-']").click();
 * Thread.sleep(2000); int k=i-1; WebElement selQunatity = shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i + ") td:nth-child(2)  fieldset  input[name='lines["+k+
 * "].quantityToBeReturned']"); selQunatity.click(); selQunatity.clear();
 * selQunatity.sendKeys(quantities[j]); Thread.sleep(2000);
 * selQunatity.sendKeys(Keys.TAB); String selReturnAction =
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i+
 * ") td:nth-child(3) select[name='lines["+k+"].returnAction']";
 * gen.selectShadowElementByVal(driver,selReturnAction
 * ,returnActn[j],returnActn[j]+" has been selected as action for item "+items[j
 * ]+" with quantity "+quantities[j],oPSelFW); //gen.tblReportRightSide("PASS",
 * items[j], quantities[j], returnActn[j], basetest); break; } } } }else {
 * returnItems = 1; for (int i = 1; i <= size; i++) {
 * System.out.println("In For Loop " + shadow.findElements(
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i +
 * ") td:nth-child(4) a") .size()); String text = shadow.findElement(
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i +
 * ") td:nth-child(4) a") .getAttribute("tooltip"); if (text.contains(itemId)) {
 * shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i + ") td:nth-child(1) div.CheckboxGroup label[for^='line-']").click();
 * Thread.sleep(2000); int k=i-1; WebElement selQunatity = shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i + ") td:nth-child(2)  fieldset  input[name='lines["+k+
 * "].quantityToBeReturned']"); selQunatity.click(); selQunatity.clear();
 * selQunatity.sendKeys(quantity); Thread.sleep(2000);
 * selQunatity.sendKeys(Keys.TAB); String selReturnAction =
 * "table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" + i+
 * ") td:nth-child(3) select[name='lines["+k+"].returnAction']";
 * gen.selectShadowElementByVal(driver,selReturnAction
 * ,returnAction,returnAction+" has been selected as action for item "
 * +itemId+" with quantity "+quantity,oPSelFW); //gen.tblReportRightSide("PASS",
 * itemId, quantity, returnAction, basetest); } } }
 * gen.clickShadowElement(driver, btnCreateReturn,oPSelFW); Thread.sleep(10000);
 * if (shadow.findElements(selReturnReason).size() != 0)
 * oPSelFW.reportStepDetails("Return has been created with "
 * +returnAction+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", returnAction); else {
 * oPSelFW.reportStepDetails("Return has not been created with "
 * +returnAction+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", returnAction); Assert.assertTrue(false); } }
 * 
 * }catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } return returnItems; }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnWithRefund # Description :Sales Order Return in CCUI # Parameters
 * : driver, orderNo, basetest # Author : Prasanna # Date : 06/03/2019 #
 * Modifications : # Modified Date :
 * ##############################################################
 * 
 * public int ccuiMultipleItemsReturn(WebDriver driver, String orderNo, String
 * returnType, ProlificsSeleniumAPI oPSelFW) throws Exception {
 * Thread.sleep(9000); int size = 0; try { System.out.println("orderNo" +
 * orderNo); System.out.println("returnType" + returnType); gen.setText(driver,
 * txtOrderSearch, orderNo, oPSelFW);
 * driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * Thread.sleep(4000); Shadow shadow = new Shadow(driver);
 * gen.clickElement(driver, tabReturns, oPSelFW); Thread.sleep(4000); if
 * (shadow.findElements(btnCreateReturn).size() == 0) {
 * System.out.println("Item is not eligible for Return"); oPSelFW.
 * reportStepDetails("Item is not eligible for Return <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", returnType); Assert.assertTrue(false); } else {
 * System.out.println("Item is eligible for Return"); Thread.sleep(4000); size =
 * shadow.findElements("td:nth-child(1) div.CheckboxGroup >label[for^='line-']")
 * .size(); System.out.println("No Of Check Boxes " + size); for (int i = 1; i
 * <= size; i++) { shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i +
 * ") td:nth-child(1) div[class='CheckboxGroup '] label[for^='line-']").click();
 * String item = shadow.
 * findElement("table.clean>tbody>returnable-counter > dropdown-tr:nth-child(" +
 * i + ") td:nth-child(4) a").getAttribute("tooltip").replaceAll("[^0-9]", "");
 * System.out.println("item +++++++++++++++++++++++++++++++++++++"+item); int j
 * = i - 1; gen.selectShadowElementByVal(driver, "select[name='lines[" + j +
 * "].returnAction']",
 * returnType,"Action has been selected as "+returnType+"for the item "+item,
 * oPSelFW); } gen.clickShadowElement(driver, btnCreateReturn,oPSelFW);
 * Thread.sleep(10000); if (shadow.findElements(selReturnReason).size() != 0)
 * oPSelFW.reportStepDetails("Return has been created with Refund for order "
 * +orderNo+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", returnType); else { oPSelFW.
 * reportStepDetails("Return has not been created with Refund for order <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", returnType); Assert.assertTrue(false); } } }
 * catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } return size; }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnWithReplacement # Description :Sales Order Return in CCUI #
 * Parameters : # Author: Prasanna # Date : 06/03/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnWithReplacement(WebDriver driver, String orderNo,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { try { gen.setText(driver,
 * txtOrderSearch, orderNo, oPSelFW);
 * driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * gen.clickElement(driver, tabReturns, oPSelFW); Thread.sleep(4000); Shadow
 * shadow = new Shadow(driver); if (shadow.findElements(btnCreateReturn).size()
 * == 0) { oPSelFW.
 * reportStepDetails("Item is not eligible for Return <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", orderNo); Assert.assertTrue(false); } else {
 * gen.clickShadowElement(driver, chkItem, oPSelFW);
 * gen.selectShadowElementByText(driver, selReturnReplace, "REPLACEMENT",
 * oPSelFW); gen.clickShadowElement(driver, btnCreateReturn, oPSelFW); if
 * (driver.findElements(By.cssSelector("select[name='returnReason.reason']")).
 * size() != 0)
 * oPSelFW.reportStepDetails("Return has been created with Replacement ","Pass",
 * orderNo); else { oPSelFW.
 * reportStepDetails("Return has not been created with Replacement <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail", orderNo); Assert.assertTrue(false); } } } catch
 * (Exception e) { oPSelFW.reportStepDetails("Exception" , e + " is displayed",
 * "Fail"); } }
 * 
 * 
 * ############################################################## # Method
 * :ccuiAddNewPayment # Description : Add new payment method # Parameters : #
 * Author: Prasanna # Date : 06/13/2019 # Modifications : # Modified Date :
 * ##############################################################
 * 
 * public void ccuiAddNewPayment(WebDriver driver, String paymentType,String
 * cardNo,String expiryDate,String name, ProlificsSeleniumAPI oPSelFW) throws
 * Exception { try { Shadow shadow = new Shadow(driver);
 * System.out.println("ccuiAddNewPayment"); System.out.println("paymentType" +
 * paymentType); // Add new payment gen.clickShadowElement(driver,
 * lnkNewPayment, oPSelFW); gen.clickShadowElement(driver, selPaymentMethodType,
 * oPSelFW); gen.selectShadowElementByVal(driver, selPaymentMethodType,
 * paymentType, oPSelFW); Thread.sleep(5000);
 * if(paymentType.contains("CREDIT_CARD")) { gen.setTextShadowElement(driver,
 * txtCardNo, cardNo, "Entering credit card number for new payment", oPSelFW);
 * Thread.sleep(3000); gen.setTextShadowElement(driver, txtExpireDate,
 * expiryDate, oPSelFW); gen.setTextShadowElement(driver, txtCardHolderName,
 * name, oPSelFW); //gen.clickShadowElement(driver, btnAddNewPayment,
 * "Clicked on Add new payment button", basetest); Thread.sleep(3000); }
 * gen.clickShadowElement(driver, btnAddPayment, oPSelFW); Thread.sleep(5000);
 * String attribute = shadow.findElement("span[class='tender-type']").getText();
 * System.out.println("attribute" + attribute); if
 * (paymentType.contains("GIFT_CARD") && attribute.contains("Gift Card")) {
 * oPSelFW.reportStepDetails("New Payment method override " + paymentType
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", paymentType); } else
 * if((paymentType.contains("MERCH_CARD") && attribute.contains("Merch Card"))){
 * oPSelFW.reportStepDetails("New Payment method override " +
 * paymentType+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", paymentType); }else
 * if((paymentType.contains("REFUND_CHECK") &&
 * attribute.contains("Refund Check"))){
 * oPSelFW.reportStepDetails("New Payment method override " +
 * paymentType+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", paymentType); }else
 * if((paymentType.contains("CREDIT_CARD") &&
 * attribute.contains("Credit Card"))){
 * oPSelFW.reportStepDetails("New Payment method override " + paymentType
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", paymentType); }else { {
 * oPSelFW.reportStepDetails("New Payment method "
 * +paymentType+" is not to override "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", paymentType ); Assert.assertTrue(false); } }
 * 
 * Thread.sleep(5000); } catch (Exception e) {
 * oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); } }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnWithNoAction # Description :Sales Order Return in CCUI #
 * Parameters : # Author: Prasanna # Date : 06/03/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnWithNoAction(WebDriver driver, String orderNo,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { try { gen.setText(driver,
 * txtOrderSearch, orderNo, oPSelFW);
 * driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * gen.clickElement(driver, tabReturns, oPSelFW); Thread.sleep(4000); Shadow
 * shadow = new Shadow(driver); if (shadow.findElements(btnCreateReturn).size()
 * == 0) { oPSelFW.reportStepDetails("Element is not eligible for Return"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", orderNo); } else { gen.clickShadowElement(driver,
 * chkItem, oPSelFW); gen.selectShadowElementByVal(driver, selReturnReplace,
 * "NO_ACTION", oPSelFW); gen.clickShadowElement(driver, btnCreateReturn,
 * oPSelFW); if
 * (driver.findElements(By.cssSelector("select[name='returnReason.reason']")).
 * size() != 0)
 * oPSelFW.reportStepDetails("Return has been created with NoAction "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", orderNo); else {
 * oPSelFW.reportStepDetails("Return has not been created with NoAction "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", orderNo); Assert.assertTrue(false); } } } catch
 * (Exception e) { oPSelFW.reportStepDetails("Exception" , e + " is displayed",
 * "Fail"); } }
 * 
 * 
 * ############################################################## # Method
 * :ccuiComponentReplace # Description :Sales Order Return in CCUI # Parameters
 * : # Author: Prasanna # Date : 06/03/2019 # Modifications : # Modified Date :
 * ##############################################################
 * 
 * public void ccuiComponentReplace(WebDriver driver, String orderNo,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { try { gen.setText(driver,
 * txtOrderSearch, orderNo, oPSelFW);
 * driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * gen.clickElement(driver, tabReturns, oPSelFW); Thread.sleep(4000); Shadow
 * shadow = new Shadow(driver); if
 * (shadow.findElements("i[class='fa fa-exclamation-circle']").size() != 0) {
 * oPSelFW.reportStepDetails("Element is not eligible for Return"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", orderNo); Assert.assertTrue(false); } else {
 * gen.clickShadowElement(driver, chkReturnComponent, oPSelFW);
 * gen.selectShadowElementByText(driver, selReturnComponent, "Replace",
 * oPSelFW); gen.clickShadowElement(driver, btnCreateReturn, oPSelFW); if
 * (driver.findElements(By.cssSelector("select[name='returnReason.reason']")).
 * size() != 0) oPSelFW.
 * reportStepDetails("Return has been created with Component Replacement "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", orderNo); else { oPSelFW.
 * reportStepDetails("Return has not been created with Component Replacement "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", orderNo); Assert.assertTrue(false); } } } catch
 * (Exception e) { oPSelFW.reportStepDetails("Exception" , e + " is displayed",
 * "Fail"); } }
 * 
 * ############################################################## # Method
 * :ccuiPaymentRefund # Description : filling the info for return details #
 * Parameters : # Author: Prasanna # Date : 06/05/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public itemDetails ccuiPaymentRefund(WebDriver driver, int itemsReturn,
 * String returnReason, String subReason, String gift, String recieptPolicy,
 * String returnShipping, String returnType, String email, String refundPolicy,
 * String labels,String returnLineNote,String phoneNo, ProlificsSeleniumAPI
 * oPSelFW) throws Exception { Shadow shadow = new Shadow(driver); itemDetails
 * objItemDet = new itemDetails();
 * 
 * try { if(returnReason.contains(",")) { String[] retReason =
 * returnReason.split(","); String[] retSubReason = subReason.split(",");
 * String[] policy = recieptPolicy.split(",");
 * 
 * for (int i = 0; i <= retReason.length-1; i++) { Thread.sleep(30000);
 * System.out.println("i value " + i); if (i == 0) { String
 * text="#lines > content-carousel > carousel-slide:nth-child("+i+1+") > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); Thread.sleep(1000);
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,retReason[i].trim(),
 * "    Return Reason has been selcted as "+retReason[i],oPSelFW);
 * //gen.selectShadowElementByText(driver, selReturnReason, returnReason); if
 * (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * retSubReason[i], "    Return sub reason has been selcted as " +
 * "<span style='font-weight:bold;color:blue'>" + retSubReason[i] + "</span>",
 * oPSelFW); } Thread.sleep(3000); gen.selectShadowElementByText(driver,
 * selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+returnLineNote+" has been entered to return line note"
 * ,oPSelFW); gen.clickShadowElement(driver, btnContinue,oPSelFW);
 * 
 * } else { int j= i+1; String
 * text="#lines > content-carousel > carousel-slide:nth-child("+j+") > h3";
 * System.out.println("text   :"+shadow.findElement(text).getText());
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); WebElement root1 =
 * driver.findElement(By.cssSelector("shadow-node[id='ReturnEditPage']"));
 * WebElement shadowRoot1 =expandShadowRootElement(driver, root1); WebElement
 * root2 =
 * shadowRoot1.findElement(By.cssSelector("return-edit-form[returnid^='20']"));
 * WebElement shadowRoot2 =expandShadowRootElement(driver, root2); WebElement
 * root3= shadowRoot2.findElement(By.
 * cssSelector("#lines>content-carousel > carousel-slide:nth-child("
 * +j+") > return-line-edit-form ")); WebElement shadowRoot3
 * =expandShadowRootElement(driver, root3);
 * gen.clickShadowElementWithRoot(driver, shadowRoot3, selReturnReason,oPSelFW);
 * Thread.sleep(1000); if(retReason[i].contains("Cancellation")||retReason[i].
 * contains("Customer ordered wrong") ||retReason[i].contains("Gift unwanted")
 * ||retReason[i].contains("Not as depicted")
 * ||retReason[i].contains("Not satisfied")||retReason[i].
 * contains("Refused delivery") ) { String option =
 * "optgroup[label*='Customer-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; }else { String option =
 * "optgroup[label*='Company-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; } gen.selectShadowElementByTextWithRoot(driver,shadowRoot3,
 * selReturnReason, retReason[i],"    Return Reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+returnReason+"</span>",oPSelFW)
 * ; // gen.clickShadowElementWithRoot(driver, shadowRoot3,option );
 * Thread.sleep(1000); if (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) {
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selSubReason,
 * retSubReason[i],"    Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+subReason+"</span>",oPSelFW); }
 * Thread.sleep(3000); gen.selectShadowElementByTextWithRoot(driver,shadowRoot3,
 * selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElementWithRoot(driver,shadowRoot3, txtReturnLineNote,
 * returnLineNote,"    "+returnLineNote+" has been entered to return line note"
 * ,oPSelFW); gen.clickShadowElementWithRoot(driver,shadowRoot3,
 * btnContinue,oPSelFW); } } }else { String
 * text="#lines > content-carousel > carousel-slide:nth-child(1) > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text);
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW); Thread.sleep(1000);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,
 * returnReason,"    Return Reason has been selcted as "+returnReason,oPSelFW);
 * //gen.selectShadowElementByText(driver, selReturnReason, returnReason); if
 * (!returnReason.contains("Blind return") &&
 * !returnReason.contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * subReason,"    Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+subReason+"</span>",oPSelFW); }
 * Thread.sleep(3000); gen.selectShadowElementByText(driver, selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+returnLineNote+" has been entered to return line note"
 * ,oPSelFW); gen.clickShadowElement(driver, btnContinue,oPSelFW); } int
 * retunableCheck = shadow.findElements(valReturnableItems).size();
 * System.out.println("retunableCheck "+retunableCheck); for(int
 * i=1;i<=retunableCheck; i++ ) {
 * objItemDet.itemIds.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * objItemDet.quantity.add(shadow.findElement("return-line:nth-child("+
 * i+") td[class='number'] span").getText());
 * objItemDet.price.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * objItemDet.reason.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(5)  reason-code-display").getAttribute("value"));
 * objItemDet.subReasonCode.add(shadow.findElement("return-line:nth-child("+
 * i+") td.cellOverflow  reason-code-display").getAttribute("value"));
 * objItemDet.returnAction.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(10)").getText());
 * objItemDet.returnRequired.add(shadow.findElement("return-line:nth-child("+i+
 * ")").getAttribute("merchreceiptpolicy")); } Thread.sleep(10000);
 * gen.selectShadowElementByText(driver, selRefundPolicy,
 * refundPolicy,"Transfer policy is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+refundPolicy+"</span>",oPSelFW)
 * ; Thread.sleep(4000); gen.clickShadowElement(driver,
 * btnCreateReturn1,oPSelFW); Thread.sleep(8000);
 * System.out.println("Refund Policy :"+shadow.findElement(selRefundPolicy).
 * getAttribute("value"));
 * objItemDet.transferPolicy.add(shadow.findElement(selRefundPolicy).
 * getAttribute("value")); if (returnShipping.equalsIgnoreCase("HUB_PICKUP")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); gen.clickShadowElement(driver,
 * selReturnType,oPSelFW); gen.selectShadowElementByText(driver, selReturnType,
 * returnType,"HUB Pick Up Method is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.returnType.add(returnType); }else
 * if(returnShipping.equalsIgnoreCase("UPS_LABEL")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping gen.setTextShadowElement(driver,
 * txtNoOfLables, labels,"Number labels entered as "
 * +"<span style='font-weight:bold;color:blue'>"+labels+"</span>",oPSelFW);
 * objItemDet.noOfLabels.add(labels);
 * //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); }else {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping Thread.sleep(3000);
 * gen.clickShadowElement(driver, selLabelInfo,oPSelFW);
 * gen.selectShadowElementByText(driver, selLabelInfo,
 * returnType,"Delivery Label is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.noOfLabels.add(returnType); }
 * 
 * if (gift.contains("YES")) { gen.clickShadowElement(driver,
 * chkReturnGiftRec,oPSelFW); gen.setTextShadowElement(driver, txtContactNo,
 * phoneNo, oPSelFW); gen.setTextShadowElement(driver, txtEmail, email,
 * oPSelFW);
 * 
 * } Thread.sleep(3000); gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000); } catch (Exception e) { //
 * TODO Auto-generated catch block e.printStackTrace(); }
 * 
 * return objItemDet;
 * 
 * } public LinkedHashMap<String, String> tendersequneceCCUI(WebDriver
 * driver,String gift,String email,String phoneNo,ProlificsSeleniumAPI oPSelFW)
 * throws Exception { Shadow shadow = new Shadow(driver); LinkedHashMap<String,
 * String> refundTender = new LinkedHashMap<String, String>(); try {
 * List<WebElement> paymentTenders = shadow.
 * findElements("fieldset[name='billToAddress'] > div[class='tenders'] return-tender"
 * ); System.out.println("paymentTenders   "+paymentTenders.size());
 * 
 * 
 * if(paymentTenders.size()>1) { for(int i=paymentTenders.size()+1; i>=2;i--) {
 * System.out.println("_____________________________"+i); WebElement element =
 * shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders']  return-tender:nth-child("
 * +i+") span.tender-type"); JavascriptExecutor js = (JavascriptExecutor)
 * driver; js.executeScript("arguments[0].scrollIntoView(true);", element);
 * System.out.println("++++++++++++++++++"+element.isDisplayed()); String
 * paymentType = element.getText().toUpperCase().replaceAll(" ", "");
 * System.out.println("paymentType  "+paymentType); WebElement element2 =
 * shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders']  return-tender:nth-child("
 * +i+") b.tender-plannedRefundAmount"); String amount =
 * element2.getText().substring(1); if(amount.contains(",")) { amount =
 * amount.replaceAll(",", ""); } System.out.println("amount "+amount);
 * oPSelFW.reportStepDetails("Amount  refunded to "+paymentType+" is "
 * +amount+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", paymentType);
 * if("COBRANDED|PLCC|MASTERCARD|VISA|AMEX|DISCOVER".contains(paymentType)) {
 * refundTender.put("CREDITCARD", amount); }else { refundTender.put(paymentType,
 * amount); } } }else { WebElement element = shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders'] span.tender-type"
 * ); String paymentType = element.getText().toUpperCase().replaceAll(" ", "");
 * WebElement element2 = shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders'] b.tender-plannedRefundAmount"
 * ); String amount = element2.getText().substring(1); if(amount.contains(","))
 * { amount = amount.replaceAll(",", ""); }
 * oPSelFW.reportStepDetails("Amount  refunded to "+paymentType+" is "
 * +amount+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", amount);
 * if("COBRANDED|PLCC|MASTERCARD|VISA|AMEX|DISCOVER".contains(paymentType)) {
 * refundTender.put("CREDITCARD", amount); }else { refundTender.put(paymentType,
 * amount); } } Thread.sleep(10000); if (gift.contains("YES")) {
 * gen.clickShadowElement(driver, chkReturnGiftRec,oPSelFW);
 * gen.clickShadowElement(driver, btnContinueShipping,oPSelFW);
 * Thread.sleep(15000); gen.setTextShadowElement(driver, txtContactNo, phoneNo,
 * oPSelFW); shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW);
 * 
 * }else if(gift.contains("gift")) { //gen.clickShadowElement(driver,
 * btnContinueShipping,basetest); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * Thread.sleep(5000); //shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW); }
 * //gen.clickShadowElement(driver, btnContinueShipping,basetest);
 * Thread.sleep(12000); if (shadow.findElements(btnReviewReturn).size() != 0) {
 * oPSelFW.reportStepDetails("Return Details are filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", phoneNo);
 * 
 * }else {
 * oPSelFW.reportStepDetails("Return Details are not filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", phoneNo); Assert.assertTrue(false); }
 * 
 * } catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); }
 * 
 * 
 * return refundTender; }
 * 
 * ############################################################## # Method
 * :ccuiReturnDetails # Description : filling the info for return details #
 * Parameters : # Author: Prasanna # Date : 06/05/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public itemDetails ccuiReturnDetails(WebDriver driver, int itemsReturn,
 * String returnReason, String subReason, String gift, String recieptPolicy,
 * String returnShipping, String returnType, String email, String refundPolicy,
 * String labels,String returnLineNote,String phoneNo, ProlificsSeleniumAPI
 * oPSelFW) { Shadow shadow = new Shadow(driver); itemDetails objItemDet = new
 * itemDetails(); System.out.println("phoneNo  :"+phoneNo);
 * System.out.println("returnShipping   :"+returnShipping); try {
 * if(returnReason.contains(",")) { String[] retReason =
 * returnReason.split(","); String[] retSubReason = subReason.split(",");
 * String[] policy = recieptPolicy.split(",");
 * 
 * for (int i = 0; i <= retReason.length-1; i++) { Thread.sleep(30000);
 * System.out.println("i value " + i); if (i == 0) { String
 * text="#lines > content-carousel > carousel-slide:nth-child("+i+1+") > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); Thread.sleep(1000);
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,retReason[i].trim(),
 * "    Return Reason has been selcted as "+
 * "<span style='font-weight:bold;color:blue'>" +retReason[i]+
 * "</span>",oPSelFW); //gen.selectShadowElementByText(driver, selReturnReason,
 * returnReason); if (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * retSubReason[i], "    Return sub reason has been selcted as " +
 * "<span style='font-weight:bold;color:blue'>" + retSubReason[i] + "</span>",
 * oPSelFW); } Thread.sleep(3000); gen.selectShadowElementByText(driver,
 * selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+"</span>"+" has been entered to return line note",oPSelFW);
 * gen.clickShadowElement(driver, btnContinue,oPSelFW);
 * 
 * } else { int j= i+1; String
 * text="#lines > content-carousel > carousel-slide:nth-child("+j+") > h3";
 * System.out.println("text   :"+shadow.findElement(text).getText());
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); WebElement root1 =
 * driver.findElement(By.cssSelector("shadow-node[id='ReturnEditPage']"));
 * WebElement shadowRoot1 =expandShadowRootElement(driver, root1); WebElement
 * root2 =
 * shadowRoot1.findElement(By.cssSelector("return-edit-form[returnid^='20']"));
 * WebElement shadowRoot2 =expandShadowRootElement(driver, root2); WebElement
 * root3= shadowRoot2.findElement(By.
 * cssSelector("#lines>content-carousel > carousel-slide:nth-child("
 * +j+") > return-line-edit-form ")); WebElement shadowRoot3
 * =expandShadowRootElement(driver, root3);
 * gen.clickShadowElementWithRoot(driver, shadowRoot3, selReturnReason,oPSelFW);
 * Thread.sleep(1000); if(retReason[i].contains("Cancellation")||retReason[i].
 * contains("Customer ordered wrong") ||retReason[i].contains("Gift unwanted")
 * ||retReason[i].contains("Not as depicted")
 * ||retReason[i].contains("Not satisfied")||retReason[i].
 * contains("Refused delivery") ) { String option =
 * "optgroup[label*='Customer-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; }else { String option =
 * "optgroup[label*='Company-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; } gen.selectShadowElementByTextWithRoot(driver,shadowRoot3,
 * selReturnReason, retReason[i],"    Return Reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+retReason[i]+"</span>",oPSelFW)
 * ; // gen.clickShadowElementWithRoot(driver, shadowRoot3,option );
 * Thread.sleep(1000); if (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) {
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selSubReason,
 * retSubReason[i],"    Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+retSubReason[i]+"</span>",
 * oPSelFW); } Thread.sleep(3000);
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElementWithRoot(driver,shadowRoot3, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+"</span>"+" has been entered to return line note",oPSelFW);
 * gen.clickShadowElementWithRoot(driver,shadowRoot3, btnContinue,oPSelFW); } }
 * }else { String
 * text="#lines > content-carousel > carousel-slide:nth-child(1) > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text);
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW); Thread.sleep(1000);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,
 * returnReason,"    Return Reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+returnReason+"</span>",oPSelFW)
 * ; //gen.selectShadowElementByText(driver, selReturnReason, returnReason); if
 * (!returnReason.contains("Blind return") &&
 * !returnReason.contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * subReason,"    Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+subReason+"</span>",oPSelFW); }
 * Thread.sleep(3000); gen.selectShadowElementByText(driver, selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+" has been entered to return line note"+"</span>",oPSelFW);
 * gen.clickShadowElement(driver, btnContinue,oPSelFW); } int retunableCheck =
 * shadow.findElements(valReturnableItems).size();
 * System.out.println("retunableCheck "+retunableCheck); for(int
 * i=1;i<=retunableCheck; i++ ) { System.out.println("For Loop" +i);
 * System.out.println("item Ids "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * System.out.println("Price is "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * System.out.println("Quantity is "+
 * shadow.findElement("return-line:nth-child("+i+") td[class='number'] span").
 * getText()); System.out.println("Return Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(5) reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Sub Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td.cellOverflow reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Action is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(10)").getText());
 * System.out.println("Return Required "+shadow.findElement(
 * "return-line:nth-child("+i+")").getAttribute("merchreceiptpolicy"));
 * 
 * objItemDet.itemIds.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * objItemDet.quantity.add(shadow.findElement("return-line:nth-child("+
 * i+") td[class='number'] span").getText());
 * objItemDet.price.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * objItemDet.reason.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(5)  reason-code-display").getAttribute("value"));
 * objItemDet.subReasonCode.add(shadow.findElement("return-line:nth-child("+
 * i+") td.cellOverflow  reason-code-display").getAttribute("value"));
 * objItemDet.returnAction.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(10)").getText());
 * objItemDet.returnRequired.add(shadow.findElement("return-line:nth-child("+i+
 * ")").getAttribute("merchreceiptpolicy")); } Thread.sleep(10000);
 * gen.selectShadowElementByText(driver, selRefundPolicy,
 * refundPolicy,"Transfer policy is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+refundPolicy+"</span>",oPSelFW)
 * ; Thread.sleep(4000); gen.clickShadowElement(driver,
 * btnCreateReturn1,oPSelFW); Thread.sleep(8000);
 * System.out.println("Refund Policy :"+shadow.findElement(selRefundPolicy).
 * getAttribute("value"));
 * objItemDet.transferPolicy.add(shadow.findElement(selRefundPolicy).
 * getAttribute("value")); if (returnShipping.equalsIgnoreCase("HUB_PICKUP")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); gen.clickShadowElement(driver,
 * selReturnType,oPSelFW); gen.selectShadowElementByText(driver, selReturnType,
 * returnType,"HUB Pick Up Method is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.returnType.add(returnType); }else
 * if(returnShipping.equalsIgnoreCase("UPS_LABEL")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping gen.setTextShadowElement(driver,
 * txtNoOfLables, labels,"Number labels entered as "
 * +"<span style='font-weight:bold;color:blue'>"+labels+"</span>",oPSelFW);
 * objItemDet.noOfLabels.add(labels);
 * //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); }else {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping Thread.sleep(3000);
 * gen.clickShadowElement(driver, selLabelInfo,oPSelFW);
 * gen.selectShadowElementByText(driver, selLabelInfo,
 * returnType,"Delivery Label is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.noOfLabels.add(labels); }
 * 
 * if (gift.contains("YES")) { gen.clickShadowElement(driver,
 * chkReturnGiftRec,oPSelFW); gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW);
 * 
 * }else if(gift.contains("gift")) { gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * Thread.sleep(5000); //shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW); }else {
 * gen.clickShadowElement(driver, btnContinueShipping,oPSelFW); }
 * Thread.sleep(3000); List<WebElement> paymentTenders = shadow.
 * findElements("fieldset[name='billToAddress'] > div[class='tenders'] return-tender"
 * ); System.out.println("paymentTenders   "+paymentTenders.size());
 * if(!(paymentTenders.size() == 0)) { if(paymentTenders.size()>1) { for(int
 * i=paymentTenders.size()+1; i>=2;i--) {
 * System.out.println("_____________________________"+i); WebElement element =
 * shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders']  return-tender:nth-child("
 * +i+") span.tender-type"); JavascriptExecutor js = (JavascriptExecutor)
 * driver; js.executeScript("arguments[0].scrollIntoView(true);", element);
 * System.out.println("++++++++++++++++++"+element.isDisplayed()); String
 * paymentType = element.getText();
 * System.out.println("paymentType  "+paymentType); WebElement element2 =
 * shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders']  return-tender:nth-child("
 * +i+") b.tender-plannedRefundAmount"); String amount =
 * element2.getText().substring(1); if(amount.contains(",")) { amount =
 * amount.replaceAll(",", ""); } System.out.println("amount "+amount);
 * oPSelFW.reportStepDetails("Amount  refunded to "+paymentType+" is "
 * +amount+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", amount); } }else { WebElement element = shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders'] span.tender-type"
 * ); String paymentType = element.getText(); WebElement element2 = shadow.
 * findElement("fieldset[name='billToAddress'] > div[class='tenders'] b.tender-plannedRefundAmount"
 * ); String amount = element2.getText().substring(1); if(amount.contains(","))
 * { amount = amount.replaceAll(",", ""); }
 * oPSelFW.reportStepDetails("Amount  refunded to "+paymentType+" is "
 * +amount+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass", amount); } } Thread.sleep(10000); if
 * (shadow.findElements(btnReviewReturn).size() != 0) {
 * oPSelFW.reportStepDetails("Return Details are filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", returnLineNote); }else {
 * oPSelFW.reportStepDetails("Return Details are not filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", returnLineNote); Assert.assertTrue(false); } } catch
 * (Exception e) { try { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } catch (Exception e1) { e1.printStackTrace(); } }
 * 
 * return objItemDet; }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnNewBillAdd # Description : filling the info for return details #
 * Parameters : # Author: Prasanna # Date : 06/05/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * 
 * public WebElement expandShadowRootElement(WebDriver driver, WebElement
 * element) { WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
 * .executeScript("return arguments[0].shadowRoot", element); return shadowRoot;
 * }
 * 
 * public void ccuiReturnNewBillAdd(WebDriver driver, int itemsReturn, String
 * returnReason, String subReason, String gift, String recieptPolicy, String
 * returnShipping, String returnType, String email, String refundPolicy, String
 * labels,String returnLineNote,String phoneNo,String fName,String lName, String
 * Address, String City, String state,String zipCode,ProlificsSeleniumAPI
 * oPSelFW) { Shadow shadow = new Shadow(driver); itemDetails objItemDet = new
 * itemDetails(); System.out.println("phoneNo  :"+phoneNo);
 * System.out.println("returnShipping   :"+returnShipping); try {
 * if(returnReason.contains(",")) { String[] retReason =
 * returnReason.split(","); String[] retSubReason = subReason.split(",");
 * String[] policy = recieptPolicy.split(",");
 * 
 * for (int i = 0; i <= retReason.length-1; i++) { Thread.sleep(30000);
 * System.out.println("i value " + i); if (i == 0) { String
 * text="#lines > content-carousel > carousel-slide:nth-child("+i+1+") > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); Thread.sleep(1000);
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,retReason[i].trim(),
 * "    Return Reason has been selcted as "+
 * "<span style='font-weight:bold;color:blue'>" +retReason[i]+
 * "</span>",oPSelFW); //gen.selectShadowElementByText(driver, selReturnReason,
 * returnReason); if (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * retSubReason[i], "    Return sub reason has been selcted as " +
 * "<span style='font-weight:bold;color:blue'>" + retSubReason[i] + "</span>",
 * oPSelFW); } Thread.sleep(3000); gen.selectShadowElementByText(driver,
 * selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+"</span>"+" has been entered to return line note",oPSelFW);
 * gen.clickShadowElement(driver, btnContinue,oPSelFW);
 * 
 * } else { int j= i+1; String
 * text="#lines > content-carousel > carousel-slide:nth-child("+j+") > h3";
 * System.out.println("text   :"+shadow.findElement(text).getText());
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", text); WebElement root1 =
 * driver.findElement(By.cssSelector("shadow-node[id='ReturnEditPage']"));
 * WebElement shadowRoot1 =expandShadowRootElement(driver, root1); WebElement
 * root2 =
 * shadowRoot1.findElement(By.cssSelector("return-edit-form[returnid^='20']"));
 * WebElement shadowRoot2 =expandShadowRootElement(driver, root2); WebElement
 * root3= shadowRoot2.findElement(By.
 * cssSelector("#lines>content-carousel > carousel-slide:nth-child("
 * +j+") > return-line-edit-form ")); WebElement shadowRoot3
 * =expandShadowRootElement(driver, root3);
 * gen.clickShadowElementWithRoot(driver, shadowRoot3, selReturnReason,oPSelFW);
 * Thread.sleep(1000); if(retReason[i].contains("Cancellation")||retReason[i].
 * contains("Customer ordered wrong") ||retReason[i].contains("Gift unwanted")
 * ||retReason[i].contains("Not as depicted")
 * ||retReason[i].contains("Not satisfied")||retReason[i].
 * contains("Refused delivery") ) { String option =
 * "optgroup[label*='Customer-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; }else { String option =
 * "optgroup[label*='Company-Driven Reasons'] > option[text='" + retReason[i] +
 * "']"; } gen.selectShadowElementByTextWithRoot(driver,shadowRoot3,
 * selReturnReason, retReason[i],"    Return Reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+retReason[i]+"</span>",oPSelFW)
 * ; // gen.clickShadowElementWithRoot(driver, shadowRoot3,option );
 * Thread.sleep(1000); if (!retReason[i].contains("Blind return") &&
 * !retReason[i].contains("No information")) {
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selSubReason,
 * retSubReason[i],"    Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+retSubReason[i]+"</span>",
 * oPSelFW); } Thread.sleep(3000);
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElementWithRoot(driver,shadowRoot3, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+"</span>"+" has been entered to return line note",oPSelFW);
 * gen.clickShadowElementWithRoot(driver,shadowRoot3, btnContinue,oPSelFW); } }
 * }else { String
 * text="#lines > content-carousel > carousel-slide:nth-child(1) > h3";
 * oPSelFW.reportStepDetails("<span style='font-weight:bold;color:blue'>"+shadow
 * .findElement(text).getText()+"</span>","Pass", "");
 * gen.clickShadowElement(driver, selReturnReason,oPSelFW); Thread.sleep(1000);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason,
 * returnReason,"    Return Reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+returnReason+"</span>",oPSelFW)
 * ; //gen.selectShadowElementByText(driver, selReturnReason, returnReason); if
 * (!returnReason.contains("Blind return") &&
 * !returnReason.contains("No information")) { //gen.clickShadowElement(driver,
 * selSubReason); gen.selectShadowElementByText(driver, selSubReason,
 * subReason,"Return sub reason has been selcted as "
 * +"<span style='font-weight:bold;color:blue'>"+subReason+"</span>",oPSelFW); }
 * Thread.sleep(3000); gen.selectShadowElementByText(driver, selReceiptPolicy,
 * recieptPolicy,"    Merchandise Reciept	Policy has been selected as "
 * +"<span style='font-weight:bold;color:blue'>"+recieptPolicy+"</span>",oPSelFW
 * ); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote,"    "+"<span style='font-weight:bold;color:blue'>"
 * +returnLineNote+" has been entered to return line note"+"</span>",oPSelFW);
 * gen.clickShadowElement(driver, btnContinue,oPSelFW); } int retunableCheck =
 * shadow.findElements(valReturnableItems).size();
 * System.out.println("retunableCheck "+retunableCheck); for(int
 * i=1;i<=retunableCheck; i++ ) { System.out.println("For Loop" +i);
 * System.out.println("item Ids "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * System.out.println("Price is "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * System.out.println("Quantity is "+
 * shadow.findElement("return-line:nth-child("+i+") td[class='number'] span").
 * getText()); System.out.println("Return Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(5) reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Sub Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td.cellOverflow reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Action is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(10)").getText());
 * System.out.println("Return Required "+shadow.findElement(
 * "return-line:nth-child("+i+")").getAttribute("merchreceiptpolicy"));
 * 
 * objItemDet.itemIds.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * objItemDet.quantity.add(shadow.findElement("return-line:nth-child("+
 * i+") td[class='number'] span").getText());
 * objItemDet.price.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * objItemDet.reason.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(5)  reason-code-display").getAttribute("value"));
 * objItemDet.subReasonCode.add(shadow.findElement("return-line:nth-child("+
 * i+") td.cellOverflow  reason-code-display").getAttribute("value"));
 * objItemDet.returnAction.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(10)").getText());
 * objItemDet.returnRequired.add(shadow.findElement("return-line:nth-child("+i+
 * ")").getAttribute("merchreceiptpolicy")); } Thread.sleep(10000);
 * gen.selectShadowElementByText(driver, selRefundPolicy,
 * refundPolicy,"Transfer policy is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+refundPolicy+"</span>",oPSelFW)
 * ; Thread.sleep(4000); gen.clickShadowElement(driver,
 * btnCreateReturn1,oPSelFW); Thread.sleep(8000);
 * System.out.println("Refund Policy :"+shadow.findElement(selRefundPolicy).
 * getAttribute("value"));
 * objItemDet.transferPolicy.add(shadow.findElement(selRefundPolicy).
 * getAttribute("value")); if (returnShipping.equalsIgnoreCase("HUB_PICKUP")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); gen.clickShadowElement(driver,
 * selReturnType,oPSelFW); gen.selectShadowElementByText(driver, selReturnType,
 * returnType,"HUB Pick Up Method is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.returnType.add(returnType); }else
 * if(returnShipping.equalsIgnoreCase("UPS_LABEL")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping gen.setTextShadowElement(driver,
 * txtNoOfLables, labels,"Number labels entered as "
 * +"<span style='font-weight:bold;color:blue'>"+labels+"</span>",oPSelFW);
 * objItemDet.noOfLabels.add(labels);
 * //System.out.println("Return shipping value :"+shadow.findElement(
 * valReturnShipping).getAttribute("value")); }else {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping Thread.sleep(3000);
 * gen.clickShadowElement(driver, selLabelInfo,oPSelFW);
 * gen.selectShadowElementByText(driver, selLabelInfo,
 * returnType,"Delivery Label is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.noOfLabels.add(labels); }
 * 
 * if (gift.contains("YES")) { gen.clickShadowElement(driver,
 * chkReturnGiftRec,oPSelFW); gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW);
 * 
 * }else if(gift.contains("gift")) { gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * Thread.sleep(5000); //shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW); }else {
 * gen.clickShadowElement(driver, btnContinueShipping,oPSelFW); }
 * //gen.clickShadowElement(driver, btnContinueShipping, basetest);
 * Thread.sleep(15000); gen.clickShadowElement(driver, selNewAddTo, oPSelFW);
 * gen.selectShadowElementByText(driver, selNewAddTo, "New Address", oPSelFW);
 * gen.setTextShadowElement(driver, txtNewFirstNameBill, fName, oPSelFW);
 * gen.setTextShadowElement(driver, txtNewLastNameBill, lName, oPSelFW);
 * gen.setTextShadowElement(driver, txtAddBill, Address, oPSelFW);
 * gen.setTextShadowElement(driver, txtCityBill, City, oPSelFW);
 * gen.setTextShadowElement(driver, txtStateBill, state, oPSelFW);
 * gen.setTextShadowElement(driver, txtZipCodeBill, zipCode, oPSelFW);
 * gen.setTextShadowElement(driver, txtContactNoBill, phoneNo, oPSelFW);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW);
 * Thread.sleep(3000); if(shadow.findElements(btnReviewReturn).size() != 0) {
 * oPSelFW.reportStepDetails("Return Details are filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", zipCode); }else {
 * oPSelFW.reportStepDetails("Return Details are not filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail", zipCode); Assert.assertTrue(false); }
 * 
 * } catch (Exception e) { try { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } catch (Exception e1) { // TODO Auto-generated
 * catch block e1.printStackTrace(); } } }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnDetails # Description : filling the info for return details #
 * Parameters : # Author: Prasanna # Date : 06/05/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public itemDetails ccuiReturnNewFromAdd(WebDriver driver,int itemsReturn,
 * String returnReason, String subReason, String gift, String recieptPolicy,
 * String returnShipping, String returnType, String returnLineNote, String
 * refundPolicy, String labels, String fName, String lName, String Address,
 * String City, String state, String zipCode, String phoneNo, String email,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { itemDetails objItemDet = new
 * itemDetails(); System.out.println("ccuiReturnDetails");
 * System.out.println("recieptPolicy" + recieptPolicy); try { Shadow shadow =
 * new Shadow(driver); int j =0; for (int i = 1; i <= itemsReturn; i++) {
 * Thread.sleep(30000); System.out.println("i value " + i); if (i == 1) { String
 * text="#lines > content-carousel > carousel-slide:nth-child("+i+") > h3";
 * System.out.println("text "+shadow.findElement(text).getText());
 * gen.clickShadowElement(driver, selReturnReason, oPSelFW); Thread.sleep(1000);
 * //gen.clickShadowElement(driver, "option[text='" + returnReason + "']");
 * gen.selectShadowElementByText(driver, selReturnReason, returnReason,
 * oPSelFW); if (!returnReason.contains("Blind return") &&
 * !returnReason.contains("No information")) {
 * gen.selectShadowElementByText(driver, selSubReason, subReason, oPSelFW); }
 * Thread.sleep(3000); gen.selectShadowElementByText(driver, selReceiptPolicy,
 * recieptPolicy, oPSelFW); gen.setTextShadowElement(driver, txtReturnLineNote,
 * returnLineNote, oPSelFW); gen.clickShadowElement(driver, btnContinue,
 * oPSelFW);
 * 
 * } else { String
 * text="#lines > content-carousel > carousel-slide:nth-child("+i+") > h3";
 * System.out.println("text "+shadow.findElement(text).getText()); WebElement
 * root1 =
 * driver.findElement(By.cssSelector("shadow-node[id='ReturnEditPage']"));
 * WebElement shadowRoot1 =expandShadowRootElement(driver, root1); WebElement
 * root2 =
 * shadowRoot1.findElement(By.cssSelector("return-edit-form[returnid^='20']"));
 * WebElement shadowRoot2 =expandShadowRootElement(driver, root2); WebElement
 * root3= shadowRoot2.findElement(By.
 * cssSelector("#lines>content-carousel > carousel-slide:nth-child("
 * +i+") > return-line-edit-form ")); WebElement shadowRoot3
 * =expandShadowRootElement(driver, root3);
 * //gen.clickShadowElementWithRoot(driver, shadowRoot3, selReturnReason,
 * basetest); Thread.sleep(1000);
 * if(returnReason.contains("Cancellation")||returnReason.
 * contains("Customer ordered wrong") ||returnReason.contains("Gift unwanted")
 * ||returnReason.contains("Not as depicted")
 * ||returnReason.contains("Not satisfied")||returnReason.
 * contains("Refused delivery") ) { String option =
 * "optgroup[label*='Customer-Driven Reasons'] > option[text='" + returnReason +
 * "']"; }else { String option =
 * "optgroup[label*='Company-Driven Reasons'] > option[text='" + returnReason +
 * "']"; } gen.selectShadowElementByTextWithRoot(driver,shadowRoot3,
 * selReturnReason, returnReason, oPSelFW); //
 * gen.clickShadowElementWithRoot(driver, shadowRoot3,option );
 * Thread.sleep(1000); if (!returnReason.contains("Blind return") &&
 * !returnReason.contains("No information")) {
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selSubReason,
 * subReason, oPSelFW); } Thread.sleep(3000);
 * gen.selectShadowElementByTextWithRoot(driver,shadowRoot3, selReceiptPolicy,
 * recieptPolicy, oPSelFW); gen.setTextShadowElementWithRoot(driver,shadowRoot3,
 * txtReturnLineNote, returnLineNote, oPSelFW);
 * gen.clickShadowElementWithRoot(driver,shadowRoot3, btnContinue, oPSelFW); } }
 * int retunableCheck = shadow.findElements(valReturnableItems).size();
 * System.out.println("retunableCheck "+retunableCheck); for(int
 * i=1;i<=retunableCheck; i++ ) { System.out.println("For Loop" +i);
 * System.out.println("item Ids "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * System.out.println("Price is "+shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * System.out.println("Quantity is "+
 * shadow.findElement("return-line:nth-child("+i+") td[class='number'] span").
 * getText()); System.out.println("Return Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(5) reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Sub Reason is "+shadow.findElement(
 * "return-line:nth-child("+i+") td.cellOverflow reason-code-display").
 * getAttribute("value"));
 * System.out.println("Return Action is "+shadow.findElement(
 * "return-line:nth-child("+i+") td:nth-child(10)").getText());
 * System.out.println("Return Required "+shadow.findElement(
 * "return-line:nth-child("+i+")").getAttribute("merchreceiptpolicy"));
 * 
 * objItemDet.itemIds.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(3)  a").getAttribute("tooltip").replaceAll("[^0-9]", ""));
 * objItemDet.quantity.add(shadow.findElement("return-line:nth-child("+
 * i+") td[class='number'] span").getText());
 * objItemDet.price.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(4)  currency-display").getAttribute("amount"));
 * objItemDet.reason.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(5)  reason-code-display").getAttribute("value"));
 * objItemDet.subReasonCode.add(shadow.findElement("return-line:nth-child("+
 * i+") td.cellOverflow  reason-code-display").getAttribute("value"));
 * objItemDet.returnAction.add(shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(10)").getText());
 * objItemDet.returnRequired.add(shadow.findElement("return-line:nth-child("+i+
 * ")").getAttribute("merchreceiptpolicy")); } Thread.sleep(5000);
 * gen.selectShadowElementByText(driver, selRefundPolicy, refundPolicy,
 * oPSelFW); Thread.sleep(4000);
 * objItemDet.transferPolicy.add(shadow.findElement(selRefundPolicy).
 * getAttribute("value")); gen.clickShadowElement(driver, btnCreateReturn1,
 * oPSelFW); Thread.sleep(8000); gen.clickShadowElement(driver, selNewAdd,
 * oPSelFW); // gen.selectShadowElementByText(driver, selNewAdd, "New Address");
 * gen.setTextShadowElement(driver, txtNewFirstName, fName, oPSelFW);
 * gen.setTextShadowElement(driver, txtNewLastName, lName, oPSelFW);
 * gen.setTextShadowElement(driver, txtAdd, Address, oPSelFW);
 * gen.setTextShadowElement(driver, txtCity, City, oPSelFW);
 * gen.setTextShadowElement(driver, txtState, state, oPSelFW);
 * gen.setTextShadowElement(driver, txtZipCode, zipCode, oPSelFW);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * gen.setTextShadowElement(driver, txtEmail, email, oPSelFW);
 * gen.clickShadowElement(driver, selReturnShipping, oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping, returnShipping,
 * oPSelFW); // select Return shipping if
 * (returnShipping.equalsIgnoreCase("HUB_PICKUP")) {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); gen.clickShadowElement(driver, selReturnType,oPSelFW);
 * gen.selectShadowElementByText(driver, selReturnType,
 * returnType,"HUB Pick Up Method is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); objItemDet.returnType.add(returnType); }else {
 * gen.clickShadowElement(driver, selReturnShipping,oPSelFW);
 * gen.selectShadowElementByVal(driver, selReturnShipping,
 * returnShipping,"Return Shipping is selected as "
 * +"<span style='font-weight:bold;color:blue'>"+returnShipping+"</span>",
 * oPSelFW); // select Return shipping gen.setTextShadowElement(driver,
 * txtNoOfLables, labels,"Number labels entered as "
 * +"<span style='font-weight:bold;color:blue'>"+labels+"</span>",oPSelFW);
 * objItemDet.noOfLabels.add(labels); } if (gift.contains("YES")) {
 * gen.clickShadowElement(driver, chkReturnGiftRec,oPSelFW);
 * gen.clickShadowElement(driver, btnContinueShipping,oPSelFW);
 * Thread.sleep(15000); gen.setTextShadowElement(driver, txtContactNo, phoneNo,
 * oPSelFW); shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW); }else
 * if(gift.contains("gift")) { gen.clickShadowElement(driver,
 * btnContinueShipping,oPSelFW); Thread.sleep(15000);
 * gen.setTextShadowElement(driver, txtContactNo, phoneNo, oPSelFW);
 * Thread.sleep(5000); //shadow.findElement(txtContactNo).sendKeys(Keys.TAB);
 * gen.setTextShadowElement(driver, txtEmailBill, email, oPSelFW); }else {
 * gen.clickShadowElement(driver, btnContinueShipping,oPSelFW); }
 * Thread.sleep(3000); if (shadow.findElements(btnReviewReturn).size() != 0)
 * oPSelFW.reportStepDetails("Return Details are filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass", ""); else {
 * oPSelFW.reportStepDetails("Return Details are not filled successfully"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail",""); Assert.assertTrue(false); } } catch (Exception e)
 * { oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * return objItemDet; }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnDetails # Description : filling the info for return details #
 * Parameters : # Author: Prasanna # Date : 06/05/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnNewFromAddValidation(WebDriver driver, String Address,
 * String city, String state, String zipCode, ProlificsSeleniumAPI oPSelFW)
 * throws Exception { System.out.println("ccuiReturnNewFromAddValidation");
 * 
 * try { String textOfElement = gen.getTextOfElement(driver, valNewAdd);
 * System.out.println("New Adddress Updated is "+textOfElement); if
 * (textOfElement.contains(Address) && textOfElement.contains(city) &&
 * textOfElement.contains(state) && textOfElement.contains(zipCode)) { oPSelFW.
 * reportStepDetails("New From Address updated for Return. Updated Address as "
 * +Address+" City as "+city+" and State as "
 * +state+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); } else { oPSelFW.
 * reportStepDetails("New From Address not Added SuccessFully for Return Updated Address is "
 * +textOfElement+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail",""); Assert.assertTrue(false); }
 * 
 * } catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } }
 * 
 * 
 * ############################################################## # Method
 * :ccuiReturnNewBillAddValidation # Description : Validating Billing Address #
 * Parameters : # Author: Prasanna # Date : 06/26/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnNewBillAddValidation(WebDriver driver, String Address,
 * String city, String state, String zipCode, ProlificsSeleniumAPI oPSelFW)
 * throws Exception { String textAddLine = null; String textAdd = null; try {
 * textAddLine = gen.getTextOfElement(driver, valNewBillAddLine); textAdd =
 * gen.getTextOfElement(driver, valNewBillAdd); if
 * (textAddLine.contains(Address) && textAdd.contains(city) &&
 * textAdd.contains(state) && textAdd.contains(zipCode)) {
 * System.out.println("PASS"); oPSelFW.
 * reportStepDetails("New  Address updated for Return. Updated Address as "
 * +Address+" City as "+city+" and State as "
 * +state+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); } else { System.out.println("FAIL");
 * oPSelFW.
 * reportStepDetails("New Address not Added SuccessFully for Return Updated Address is "
 * +textAddLine+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail",""); Assert.assertTrue(false); }
 * 
 * } catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } }
 * 
 * ############################################################## # Method
 * Method Name :ccuiReturnOrderCancelLineLevel # Description :Cancel Return
 * Order # Parameters : # Author: Prasanna # Date : 06/26/2019 # Modifications :
 * # Modified Date :
 * ##############################################################
 * 
 * public String ccuiReturnOrder(WebDriver driver,ProlificsSeleniumAPI oPSelFW)
 * throws Exception { String orderNo = null; try {
 * gen.clickShadowElement(driver, lnkReturnOrder, oPSelFW); orderNo =
 * gen.getTextOfElement(driver, valReturnORderNo);
 * 
 * 
 * } catch (Exception e) { oPSelFW.reportStepDetails("Exception" , e +
 * " is displayed", "Fail"); } return orderNo; }
 * 
 * 
 * 
 * ############################################################## # Method
 * Method Name :ccuiReturnOrderCancelLineLevel # Description :Cancel Return
 * Order # Parameters : # Author: Prasanna # Date : 06/26/2019 # Modifications :
 * # Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnPartialOrderCancelLineLevel(WebDriver driver, String
 * returnOrderNumber,String canItems,String canQuantity,String
 * returnAction,ProlificsSeleniumAPI oPSelFW) throws Exception {
 * 
 * try { Shadow shadow = new Shadow(driver); gen.clickElement(driver, tabOrders,
 * oPSelFW); Thread.sleep(1000); gen.clickShadowElement(driver, tabReturn,
 * oPSelFW); Thread.sleep(1000); gen.setText(driver, txtOrderSearch,
 * returnOrderNumber, oPSelFW); Thread.sleep(1000);
 * driver.findElement(By.xpath(imgSearch )).click(); Thread.sleep(3000);
 * if(returnAction.contains("REPLACEMENT")) { ccuiReturnOrder(driver,oPSelFW); }
 * gen.clickElement(driver, lnkEditReturnOrder, oPSelFW); Thread.sleep(3000);
 * gen.clickShadowElement(driver, btnSelLineCancel, oPSelFW);
 * Thread.sleep(3000); int retunableCheck =
 * shadow.findElements(valReturnableItems).size(); String[] cancelItems = null;
 * if(canItems.contains(",")) { cancelItems = canItems.split(","); String[]
 * cancelQuantity = canQuantity.split(","); for(int i=1; i<=retunableCheck; i++)
 * { String itemid =
 * shadow.findElement("return-line:nth-child("+i+") td:nth-child(3)  a").
 * getAttribute("tooltip").replaceAll("[^0-9]", ""); for(int
 * j=0;j<=cancelItems.length-1;j++) { if(itemid.contains(cancelItems[j])) {
 * shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(1) label[for^='cancel-orderline']").click(); } }
 * 
 * gen.clickShadowElement(driver, btnConfirmLineLevelCan, oPSelFW);
 * Thread.sleep(1000); for(int k=1; k<=retunableCheck; k++) { for(int
 * j=0;j<=cancelItems.length-1;j++) { String itemId =
 * shadow.findElement("return-line:nth-child("+k+") td:nth-child(3)  a").
 * getAttribute("tooltip").replaceAll("[^0-9]", "");
 * if(itemId.contains(cancelItems[j])) { String quantity =
 * shadow.findElement("return-line:nth-child("+k+") td[class='number'] span").
 * getText(); if(Integer.parseInt(quantity) != 0) { oPSelFW.
 * reportStepDetails("Return order is not cancelled line level for item :"
 * +itemid+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail",""); Assert.assertTrue(false); }else {
 * oPSelFW.reportStepDetails("Return order is cancelled line level for item :"
 * +itemid+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); } }
 * 
 * } } } }else { for(int i=1; i<=retunableCheck; i++) { String itemid =
 * shadow.findElement("return-line:nth-child("+i+") td:nth-child(3)  a").
 * getAttribute("tooltip").replaceAll("[^0-9]", "");
 * if(itemid.contains(canItems)) { shadow.findElement("return-line:nth-child("+
 * i+") td:nth-child(1) label[for^='cancel-orderline']").click();
 * gen.clickShadowElement(driver, btnConfirmLineLevelCan, oPSelFW);
 * Thread.sleep(6000); String quantity =
 * shadow.findElement("return-line:nth-child("+i+") td[class='number'] span").
 * getText(); if(Integer.parseInt(quantity) == 0) { oPSelFW.
 * reportStepDetails("Sucessfully cancelled return order line level for item :"
 * +itemid+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); }else { oPSelFW.
 * reportStepDetails("Return order is not cancelled line level for item :"
 * +itemid+" <a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Fail",""); Assert.assertTrue(false); } } } }
 * gen.clickShadowElement(driver, lnkReturnOrder, oPSelFW);
 * //Thread.sleep(1000); //gen.clickShadowElement(driver,
 * btnConfirmLineLevelCan, basetest); //Thread.sleep(1000);
 * //gen.clickShadowElement(driver, btnCanacelReturn, basetest); int
 * retunableCheck = shadow.findElements(valReturnableItems).size();
 * System.out.println("retunableCheck "+retunableCheck); for(int
 * i=1;i<=retunableCheck; i++ ) { String itemid =
 * shadow.findElement("return-line:nth-child("+i+") td:nth-child(3)  a").
 * getAttribute("tooltip").replaceAll("[^0-9]", "");
 * 
 * gen.clickShadowElement(driver, btnLineLevelCancel, basetest);
 * Thread.sleep(1000); gen.clickShadowElement(driver, btnCanacelReturn,
 * basetest); if(i!=1) { gen.clickShadowElement(driver, btnSelLineCancel,
 * basetest); Thread.sleep(3000); gen.clickShadowElement(driver,
 * btnLineLevelCancel, basetest); Thread.sleep(1000);
 * gen.clickShadowElement(driver, btnLineLevelCancel, basetest);
 * Thread.sleep(1000); gen.clickShadowElement(driver, btnCanacelReturn,
 * basetest); } i=i+1; } Thread.sleep(5000); int retunableCheckAfter = 0;
 * for(int j=1 ; j<=retunableCheck; j++) { String text = null;
 * System.out.println("j"+j); text =
 * shadow.findElement("return-line:nth-child("+j+") td[class='number'] span").
 * getText(); System.out.println("text "+text); if(text.contains("1")) {
 * System.out.println("Contains 1"); retunableCheckAfter = retunableCheckAfter
 * +1; } } System.out.println("retunableCheckAfter "+retunableCheckAfter);
 * System.out.println("retunableCheckAfter < retunableCheck "+retunableCheck);
 * if (retunableCheckAfter !=0 && retunableCheckAfter < retunableCheck) {
 * System.out.println("Line Level Pass"); basetest.test.log(Status.PASS,
 * "Cancelled returned ORder "+returnOrderNumber+"Line level"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>"); } else { basetest.test.log(Status.FAIL,
 * "Return order"+returnOrderNumber+" not cancelled in line level"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>"); } }catch (Exception e) {
 * oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * 
 * }
 * 
 * 
 * ############################################################## # Method
 * Method Name :ccuiReturnOrderCancel # Description :Cancel Return Order #
 * Parameters : # Author: Prasanna # Date : 06/26/2019 # Modifications : #
 * Modified Date :
 * ##############################################################
 * 
 * public void ccuiReturnOrderCancel(WebDriver driver, String returnOrderNumber,
 * ProlificsSeleniumAPI oPSelFW) throws Exception {
 * 
 * try { Shadow shadow = new Shadow(driver); Thread.sleep(1000);
 * gen.clickElement(driver, tabOrders, oPSelFW); Thread.sleep(1000);
 * gen.clickShadowElement(driver, tabReturn, oPSelFW); Thread.sleep(1000);
 * gen.setText(driver, txtOrderSearch, returnOrderNumber, oPSelFW);
 * driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * Thread.sleep(50000); gen.clickElement(driver, lnkEditReturnOrder, oPSelFW);
 * Thread.sleep(3000); gen.clickShadowElement(driver, btnCancelTotalOrder,
 * oPSelFW); Thread.sleep(5000); gen.clickElement(driver, tabOrders, oPSelFW);
 * Thread.sleep(1000); gen.clickShadowElement(driver, tabReturn, oPSelFW);
 * Thread.sleep(1000); gen.setText(driver, txtOrderSearch, returnOrderNumber,
 * oPSelFW); driver.findElement(By.xpath(txtOrderSearch)).sendKeys(Keys.ENTER);
 * Thread.sleep(3000); String orderStatus = gen.getTextOfElement(driver,
 * valReturnORderStatus); System.out.
 * println("orderStatus  _________________________________________________"
 * +orderStatus); if(orderStatus.trim().contains("Cancelled") ) {
 * oPSelFW.reportStepDetails("Return order "+returnOrderNumber+" cancelled"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Pass",""); }else {
 * oPSelFW.reportStepDetails("Return order is "
 * +returnOrderNumber+" not cancelled"
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail",""); Assert.assertTrue(false); }
 * 
 * gen.clickElement(driver, tabReturns, basetest); Thread.sleep(4000); if
 * (shadow.findElements(btnCreateReturn).size() == 0) {
 * System.out.println("Fail"); basetest.test.log(Status.FAIL,
 * "Return order not cancelled"); }else { System.out.println("Pass");
 * basetest.test.log(Status.PASS, "Cancelled returned ORder"); } } catch
 * (Exception e) { oPSelFW.reportStepDetails("Exception" , e + " is displayed",
 * "Fail"); } }
 * 
 * ############################################################## # Method
 * :ccuiFillLineInfo # Description :filling line info # Parameters : # Author:
 * Prasanna # Date : 06/05/2019 # Modifications : # Modified Date :
 * ##############################################################
 * 
 * public ArrayList<String> ccuiReplacementDetailsValidation(WebDriver driver,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { String returnNo = null;
 * String orderTotal = null; String orderType = null; ArrayList<String>
 * replacementDetails = new ArrayList<String>(); try {
 * System.out.println("ccuiReplacementDetailsValidation"); Thread.sleep(5000);
 * gen.clickShadowElement(driver, btnReviewReturn, oPSelFW); // click on review
 * return Thread.sleep(6000); gen.clickShadowElement(driver, btnConfirmReturn,
 * oPSelFW); Thread.sleep(90000); gen.clickShadowElement(driver, btnContinue,
 * oPSelFW); // Click on save replacement Thread.sleep(60000); returnNo =
 * gen.getTextOfElement(driver, valReplacementNo); orderTotal =
 * gen.getTextOfElement(driver, valOrderTotal); orderType =
 * gen.getTextOfElement(driver, valOrderType); if
 * (orderType.equalsIgnoreCase("Replacement")) oPSelFW.
 * reportStepDetails("Return with replacement has been done replacement order number is "
 * +returnNo+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); else {
 * oPSelFW.reportStepDetails("Return with replacement hasn't done "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail",""); Assert.assertTrue(false); } } catch (Exception e)
 * { oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * //returnNo = returnNo+","+orderTotal; replacementDetails.add(returnNo);
 * replacementDetails.add(orderTotal); return replacementDetails;
 * 
 * }
 * 
 * 
 * ############################################################## # Method
 * :refundDetailsValidation # Description :filling line info # Parameters : #
 * Author: Prasanna # Date : 06/12/2019 # Modifications : # Modified Date :
 * ##############################################################
 * 
 * public ArrayList<String> ccuiRefundDetailsValidation(WebDriver driver,
 * ProlificsSeleniumAPI oPSelFW) throws Exception { ArrayList<String>
 * refundDetails = new ArrayList<String>(); String orderNo = null; String
 * orderAmnt = null; String orderStatus = null; try {
 * gen.clickShadowElement(driver, btnReviewReturn, oPSelFW); // click on review
 * return Thread.sleep(5000); gen.clickShadowElement(driver, btnConfirmReturn,
 * oPSelFW); Thread.sleep(40000); orderNo = gen.getTextOfElement(driver,
 * valReturnORderNo); System.out.println("orderNo " + orderNo); orderAmnt =
 * gen.getTextOfElement(driver, valReturnORderAmnt); orderAmnt =
 * orderAmnt.substring(1); orderAmnt = orderAmnt.replace("$", ""); orderAmnt =
 * orderAmnt.replace(",", ""); System.out.println("orderAmnt " + orderAmnt);
 * orderStatus = gen.getTextOfElement(driver, valReturnORderStatus);
 * System.out.println("orderStatus " + orderStatus); if
 * (!orderAmnt.contains("$0.00"))
 * oPSelFW.reportStepDetails("Return with refund Order Number is "
 * +orderNo+" order amount "+orderAmnt+" Order Status "
 * +orderStatus+"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+"
 * .png'>Screenshot</a>","Pass",""); else {
 * oPSelFW.reportStepDetails("Return with refund hasn't done "
 * +"<a href='..\\target\\Screenshots\\" + screen.getScreenshot(driver)+".png'>
 * Screenshot</a>","Fail",""); Assert.assertTrue(false); } } catch (Exception e)
 * { oPSelFW.reportStepDetails("Exception" , e + " is displayed", "Fail"); }
 * refundDetails.add(orderNo); refundDetails.add(orderAmnt); return
 * refundDetails; } }
 */