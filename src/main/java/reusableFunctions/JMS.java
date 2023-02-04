/*
 * package reusableFunctions;
 * 
 * import java.io.BufferedReader; import java.io.File; import
 * java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.io.FileReader; import java.io.FileWriter; import java.io.IOException;
 * import java.util.Properties;
 * 
 * import javax.jms.DeliveryMode; import javax.jms.JMSException; import
 * javax.jms.QueueConnection; import javax.jms.QueueConnectionFactory; import
 * javax.jms.QueueReceiver; import javax.jms.QueueSender; import
 * javax.jms.QueueSession; import javax.jms.TextMessage; import
 * javax.xml.parsers.DocumentBuilder; import
 * javax.xml.parsers.DocumentBuilderFactory; import
 * javax.xml.parsers.ParserConfigurationException; import
 * javax.xml.transform.Transformer; import
 * javax.xml.transform.TransformerException; import
 * javax.xml.transform.TransformerFactory; import
 * javax.xml.transform.dom.DOMSource; import
 * javax.xml.transform.stream.StreamResult;
 * 
 * import org.w3c.dom.DOMException; import org.w3c.dom.Document; import
 * org.w3c.dom.Element; import org.w3c.dom.NamedNodeMap; import
 * org.w3c.dom.Node; import org.w3c.dom.NodeList; import
 * org.xml.sax.SAXException;
 * 
 * import com.aventstack.extentreports.Status;
 * 
 * import utility.BaseTest; import utility.Util;
 * 
 * public class JMS {
 * 
 * private static final String db = null;
 * 
 * public QueueSession connectToTIBCO(String TemplateName, BaseTest basetest)
 * throws JMSException { File file = new
 * File("D:\\ReturnsAndReplacements\\param.properties");
 * 
 * FileInputStream fileInput = null; try { fileInput = new
 * FileInputStream(file); } catch (FileNotFoundException e) {
 * e.printStackTrace(); } Properties prop = new Properties();
 * 
 * // load properties file try { prop.load(fileInput); } catch (IOException e) {
 * e.printStackTrace(); }
 * 
 * QueueSession session = null;
 * 
 * javax.jms.Queue queue;
 * 
 * String TIBCOItemPub = "Q.WSI.BW.RETURNORDER.RECEIVE"; try { Util util = new
 * Util(); String serverUrl = prop.getProperty("EMSServerURL");
 * 
 * String userName = prop.getProperty("EMSUserName");
 * 
 * String password = prop.getProperty("EMSPassword");
 * 
 * QueueConnection connection; QueueConnectionFactory factory; factory = new
 * com.tibco.tibjms.TibjmsQueueConnectionFactory(serverUrl); connection =
 * factory.createQueueConnection(userName, password); session =
 * connection.createQueueSession(false, javax.jms.Session.DUPS_OK_ACKNOWLEDGE);
 * connection.start();
 * 
 * String filepath = System.getProperty("user.dir") +
 * "\\Data\\" + TemplateName + ".xml"; queue =
 * session.createQueue(TIBCOItemPub); QueueSender sender =
 * session.createSender(queue); TextMessage tmessage =
 * session.createTextMessage();
 * tmessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT); String InputToTIBCO
 * = util .ConvertXMLFileToString(System.getProperty("user.dir") +
 * "\\Data\\" + TemplateName + ".xml"); tmessage.setText(InputToTIBCO);
 * sender.send(tmessage); } catch (Exception e) { basetest.test.log(Status.FAIL,
 * "Exception " + e + " is displayed"); } return (session);
 * 
 * }
 * 
 * public void (String filePath, String ReturnReference, String[] items,
 * String[] primeline, String[] subLineNos, String[] quantity) { try { String
 * filepath =
 * "P:\\WorkingFolder\\ReturnsAndReplacements\\Data\\ReturnOrderReceipt.xml";
 * DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
 * DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); Document doc =
 * docBuilder.parse(filepath); // Get the root element Node company =
 * doc.getFirstChild(); for(int i=0; i<=items.length-1;i++) { Node receiptLines
 * = doc.getElementsByTagName("ReceiptLine").item(i); // update Item ID
 * attribute NamedNodeMap attr = receiptLines.getAttributes(); Node itemAttr =
 * attr.getNamedItem("ItemID"); itemAttr.setTextContent(items[i]); Node
 * primeLineAttr = attr.getNamedItem("PrimeLineNo");
 * primeLineAttr.setTextContent(primeline[i]); Node subLineAttr =
 * attr.getNamedItem("SubLineNo"); subLineAttr.setTextContent(subLineNos[i]);
 * Node quantityAttr = attr.getNamedItem("Quantity");
 * quantityAttr.setTextContent(quantity[i]); Node orderNoAttr =
 * attr.getNamedItem("OrderNo"); orderNoAttr.setTextContent(ReturnReference); }
 * // write the content into xml file TransformerFactory transformerFactory =
 * TransformerFactory.newInstance(); Transformer transformer =
 * transformerFactory.newTransformer(); DOMSource source = new DOMSource(doc);
 * StreamResult result = new StreamResult(new File(filepath));
 * transformer.transform(source, result); } catch (ParserConfigurationException
 * pce) { pce.printStackTrace(); } catch (TransformerException tfe) {
 * tfe.printStackTrace(); } catch (IOException ioe) { ioe.printStackTrace(); }
 * catch (SAXException sae) { sae.printStackTrace(); } }
 * 
 * 
 * public void modifyTIBCOFile(String filePath, String ItemNumber, Long
 * ADBSequenceNum, String FieldScenario) { File fileToBeModified = new
 * File(filePath); String oldContent = ""; BufferedReader reader = null;
 * FileWriter writer = null; try { reader = new BufferedReader(new
 * FileReader(fileToBeModified)); String line = reader.readLine(); boolean
 * DSIndicatorY = false;
 * 
 * while (line != null) { if (line.indexOf("<ADB__SEQUENCE>") > 1) line =
 * line.substring(0, line.indexOf("<ADB__SEQUENCE>") + 15) +
 * String.valueOf(ADBSequenceNum) +
 * line.substring(line.indexOf("</ADB__SEQUENCE>"), line.length()); if
 * (line.indexOf("itemId") > 1) line = line.substring(0, line.indexOf("itemId")
 * + 8) + ItemNumber + "\" " + line.substring(line.indexOf("conceptCode"),
 * line.length()); if (line.indexOf("ItemID") > 1) line = line.substring(0,
 * line.indexOf("ItemID") + 8) + ItemNumber + "\" " +
 * line.substring(line.indexOf("ConceptID"), line.length()); if
 * (line.indexOf("<ITEM>") > 1) line = line.substring(0, line.indexOf("<ITEM>")
 * + 6) + ItemNumber + line.substring(line.indexOf("</ITEM>"), line.length());
 * if (FieldScenario.contains("UDA ID 8 and UDA Value 4") &&
 * line.indexOf("UDAID") >= 0) line = line.substring(0, line.indexOf("UDAID") +
 * 7) + "8\" UDAValue=\"4\" " + line.substring(line.indexOf("UDAIDDesc"),
 * line.length()); if (FieldScenario.contains("UDA ID 1009 and UDA Value 1") &&
 * line.indexOf("UDAID") >= 0) line = line.substring(0, line.indexOf("UDAID") +
 * 7) + "1009\" UDAValue=\"1\" " + line.substring(line.indexOf("UDAIDDesc"),
 * line.length()); if (FieldScenario.contains("Ship Mode 35") &&
 * line.indexOf("ItemShipMode") >= 0) line = line.substring(0,
 * line.indexOf("ItemShipMode") + 14) + "35\" " +
 * line.substring(line.indexOf("JEActive"), line.length()); if
 * (FieldScenario.contains("NLA is Y") && line.indexOf("NoLongerAvail") >= 0)
 * line = line.substring(0, line.indexOf("NoLongerAvail") + 15) + "Y\" " +
 * line.substring(line.indexOf("OutOfMarket"), line.length()); if
 * ((FieldScenario.contains("NLA is N")) && (line.indexOf("NoLongerAvail") >=
 * 0)) line = line.substring(0, line.indexOf("NoLongerAvail") + 15) + "N\" " +
 * line.substring(line.indexOf("OutOfMarket"), line.length()); if
 * ((FieldScenario.contains("DS Indicator Y")) && (line.indexOf("DirectShipInd")
 * >= 0) && (line.indexOf("NoLongerAvail") >= 1) && (DSIndicatorY == false))
 * line = line.substring(0, line.indexOf("DirectShipInd") + 15) + "Y\" " +
 * line.substring(line.indexOf("NoLongerAvail"), line.length()); if
 * ((FieldScenario.contains("DS Indicator N")) && (line.indexOf("DirectShipInd")
 * >= 0) && (line.indexOf("NoLongerAvail") >= 1) && (DSIndicatorY == false))
 * line = line.substring(0, line.indexOf("DirectShipInd") + 15) + "N\" " +
 * line.substring(line.indexOf("NoLongerAvail"), line.length()); if
 * ((FieldScenario.contains("DS Indicator Y")) && (line.indexOf("DirectShipInd")
 * >= 0)) line = line.substring(0, line.indexOf("DirectShipInd") + 15) + "Y\" "
 * + line.substring(line.indexOf("SupName"), line.length()); if
 * ((FieldScenario.contains("DS Indicator N")) && (line.indexOf("DirectShipInd")
 * >= 0)) line = line.substring(0, line.indexOf("DirectShipInd") + 15) + "N\" "
 * + line.substring(line.indexOf("SupName"), line.length()); oldContent =
 * oldContent + line + System.lineSeparator(); line = reader.readLine(); //
 * System.out.println(line); } reader.close(); System.out.println(oldContent);
 * FileWriter fileWriter = new FileWriter(fileToBeModified);
 * fileWriter.write(oldContent); fileWriter.close(); } catch (IOException e) {
 * e.printStackTrace(); } }
 * 
 * public boolean verifyIfItemExistsInQueue(QueueReceiver receiver, String
 * GenItemNumber, String QueueName) throws JMSException {
 * 
 * String XMLReturned = readMessage(receiver, 0); if
 * (XMLReturned.contains(GenItemNumber)) return (true); else { XMLReturned =
 * readMessage(receiver, 1); if (XMLReturned.contains(GenItemNumber)) return
 * (true); else { XMLReturned = readMessage(receiver, 2); if
 * (XMLReturned.contains(GenItemNumber)) return (true); else { XMLReturned =
 * readMessage(receiver, 3); if (XMLReturned.contains(GenItemNumber)) return
 * (true); else { XMLReturned = readMessage(receiver, 4); if
 * (XMLReturned.contains(GenItemNumber)) return (true); else { XMLReturned =
 * readMessage(receiver, 5); if (XMLReturned.contains(GenItemNumber)) return
 * (true); else { XMLReturned = readMessage(receiver, 6); if
 * (XMLReturned.contains(GenItemNumber)) return (true); else { XMLReturned =
 * readMessage(receiver, 7); if (XMLReturned.contains(GenItemNumber)) return
 * (true); else { XMLReturned = readMessage(receiver, 8); if
 * (XMLReturned.contains(GenItemNumber)) return (true); else { XMLReturned =
 * readMessage(receiver, 9); if (XMLReturned.contains(GenItemNumber)) return
 * (true);
 * 
 * } } } } } } } } } return (false); }
 * 
 * public boolean checkIfItemExistsInMessage(String Message, String
 * GenItemNumber) throws JMSException { System.out.println(Message);
 * System.out.println(""); boolean ItemFound = false; try { if
 * (Message.contains(GenItemNumber)) { ItemFound = true; return (ItemFound); } }
 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
 * } return (ItemFound); }
 * 
 * public boolean verifyExistenceOfAddInternalApproval() throws
 * ParserConfigurationException, SAXException, IOException { boolean
 * addEventFound = true; try { File folder = new
 * File(System.getProperty("user.dir") + "\\target\\MDMXMLs\\PayLoads\\");
 * File[] listOfFiles = folder.listFiles(); DocumentBuilderFactory docfactory =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
 * docfactory.newDocumentBuilder(); for (int FileNum = 0; FileNum <
 * listOfFiles.length; FileNum++) { if (listOfFiles[FileNum].isFile()) {
 * Document document = builder.parse(new File(listOfFiles[FileNum].getPath()));
 * NodeList eElementList = document.getElementsByTagName("Document"); Element
 * element = (Element) eElementList.item(0); NodeList Action =
 * document.getElementsByTagName("Event"); for (int NodNum = 0; NodNum <
 * Action.getLength(); NodNum++) { NodeList childList =
 * Action.item(NodNum).getChildNodes(); for (int j = 0; j <
 * childList.getLength(); j++) { Node childNode = childList.item(j); if
 * ("Addendum".equals(childNode.getNodeName())) { if
 * (childList.item(j).getTextContent().trim().contains(
 * "ProductAddInternalApproval")) addEventFound = true; } } } } } } catch
 * (DOMException e) { // TODO Auto-generated catch block e.printStackTrace();
 * System.out.println(e); } return (addEventFound); }
 * 
 * public boolean verifyFieldValueInPayLoadXML(String FieldName, String
 * FieldValue, BaseTest basetest) throws ParserConfigurationException,
 * SAXException, IOException { boolean ValuesPresent = false; try { File folder
 * = new File(System.getProperty("user.dir") + "\\target\\MDMXMLs\\PayLoads\\");
 * File[] listOfFiles = folder.listFiles(); DocumentBuilderFactory docfactory =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
 * docfactory.newDocumentBuilder();
 * 
 * for (int FileNum = 0; FileNum < listOfFiles.length; FileNum++) { if
 * (listOfFiles[FileNum].isFile()) {
 * System.out.println(listOfFiles[FileNum].getPath());
 * 
 * Document document = builder.parse(new File(listOfFiles[FileNum].getPath()));
 * NodeList eElementList = document.getElementsByTagName("Document"); Element
 * element = (Element) eElementList.item(0); NodeList Action =
 * document.getElementsByTagName("Action"); for (int NodNum = 0; NodNum <
 * Action.getLength(); NodNum++) { NodeList childList =
 * Action.item(NodNum).getChildNodes(); for (int j = 0; j <
 * childList.getLength(); j++) { Node childNode = childList.item(j); if
 * ("Attribute".equals(childNode.getNodeName())) {
 * System.out.println(childList.item(j).getAttributes().item(1).getNodeValue());
 * System.out.println(childList.item(j).getAttributes().item(1).getNodeName());
 * System.out.println(childList.item(j).getAttributes().item(0).getNodeName());
 * System.out.println(childList.item(j).getAttributes().item(0).getNodeName());
 * String XMLFieldName =
 * childList.item(j).getAttributes().item(1).getNodeValue(); String IsChanged =
 * childList.item(j).getAttributes().item(0).getNodeValue(); String NodeName =
 * childList.item(j).getAttributes().item(0).getNodeName(); NodeList
 * subchildList = childList.item(j).getChildNodes(); String XMLFieldValue = "";
 * for (int SubChildCount = 0; SubChildCount < childList.getLength();
 * SubChildCount++) { if (SubChildCount == 0) {
 * System.out.println(subchildList.item(SubChildCount).getTextContent());
 * XMLFieldValue = subchildList.item(SubChildCount).getTextContent(); } } if
 * (XMLFieldName.contains(FieldName) && XMLFieldValue.contains(FieldValue)) {
 * basetest.test.log(Status.PASS, "Field name: " + FieldName + " Field value: "
 * + FieldValue + " are updated successfully in output XML"); ValuesPresent =
 * true; break; } if (XMLFieldName.contains(FieldName) &&
 * IsChanged.contains("true") && NodeName.contains("changed")) {
 * basetest.test.log(Status.PASS, "Field name: " + FieldName + " Field value: "
 * + FieldValue + " are updated successfully in output XML"); ValuesPresent =
 * true; break; } }
 * 
 * } }
 * 
 * } if (ValuesPresent == true) break; } if (ValuesPresent == false)
 * basetest.test.log(Status.INFO, "Field name: " + FieldName + " Field value: "
 * + FieldValue + " are not updated in PayLoad");
 * 
 * } catch (SAXException e) { basetest.test.log(Status.FAIL, "SAXException " + e
 * + " is displayed"); } catch (ParserConfigurationException e) {
 * basetest.test.log(Status.FAIL, "ParserConfiguration Exception " + e +
 * " is displayed"); } catch (IOException e) { basetest.test.log(Status.FAIL,
 * "There is an error in parsing the TIBCO XML file");
 * basetest.test.log(Status.FAIL, "IOException : " + e + " is displayed"); }
 * return ValuesPresent;
 * 
 * }
 * 
 * public String readMessage(QueueReceiver receiver, int Number) throws
 * JMSException { String MsgRead = ""; try { javax.jms.Message message =
 * receiver.receive(100); if (message != null) MsgRead = message.toString(); }
 * catch (Exception e) { e.printStackTrace(); System.out.println(e); } return
 * (MsgRead); }
 * 
 * public void verifyMDMEventUpdate(HashMap<String, String> XLTestData, String
 * TemplateName, String GenItemNumber, BaseTest basetest, boolean
 * isMasterEventUpdated, boolean isFulFillmentEventUpdated, boolean
 * isSellingEventUpdated) throws JMSException { try { JMS jms = new JMS();
 * String serverUrl = XLTestData.get("EMSServerURL").toString(); String userName
 * = XLTestData.get("EMSUserName").toString(); String password =
 * XLTestData.get("EMSPassword").toString(); String FulFillmentEvent =
 * XLTestData.get("FulFillmentEventQueue").toString(); String MasterEvent =
 * XLTestData.get("MasterEventQueue").toString(); String SellingEvent =
 * XLTestData.get("SellingEventQueue").toString(); QueueSession session =
 * connectToTIBCO(XLTestData, TemplateName, basetest);
 * 
 * QueueConnectionFactory factory = new
 * com.tibco.tibjms.TibjmsQueueConnectionFactory(serverUrl); QueueConnection
 * connection = factory.createQueueConnection(userName,password); session =
 * connection.createQueueSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
 * connection.start(); boolean itemExists; if(isMasterEventUpdated) {
 * javax.jms.Queue queue = session.createQueue(MasterEvent); QueueReceiver
 * receiver = session.createReceiver(queue); itemExists =
 * jms.verifyIfItemExistsInQueue(receiver, GenItemNumber, MasterEvent);
 * if(itemExists) basetest.test.log(Status.PASS,
 * "MDM Processed the inbound message successfully and published <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> to the Master Event Queue"); else
 * basetest.test.log(Status.INFO,
 * "MDM Processed the inbound message and <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> is not publised to the Master Event Queue"); }
 * if(isFulFillmentEventUpdated) { javax.jms.Queue queue =
 * session.createQueue(FulFillmentEvent); QueueReceiver receiver =
 * session.createReceiver(queue); boolean ItemExistsInItemFullFillment =
 * jms.verifyIfItemExistsInQueue(receiver, GenItemNumber, FulFillmentEvent);
 * if(ItemExistsInItemFullFillment) basetest.test.log(Status.PASS,
 * "MDM Processed the event successfully and published <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> to the Fullfillment Event Queue"); else
 * basetest.test.log(Status.INFO,
 * "MDM Processed the event message and <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> is not publised to the Fullfillment Event Queue"); }
 * if(isSellingEventUpdated) { javax.jms.Queue queue =
 * session.createQueue(SellingEvent); QueueReceiver receiver =
 * session.createReceiver(queue); itemExists =
 * jms.verifyIfItemExistsInQueue(receiver, GenItemNumber, SellingEvent);
 * if(itemExists) basetest.test.log(Status.PASS,
 * "MDM Processed the event message successfully and published <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> to the Selling Event Queue"); else
 * basetest.test.log(Status.INFO,
 * "MDM Processed the event message and <span style='font-weight:bold;color:blue'>  "
 * +GenItemNumber+" </span> is not publised to the Selling Event Queue"); } //
 * session.commit(); session.close(); } catch (Exception e) {
 * 
 * e.printStackTrace(); basetest.test.log(Status.FAIL, "Exception " + e +
 * " is displayed"); } } public String uploadITEMToTIBCO(HashMap<String, String>
 * XLTestData,String TemplateName, String NameOfFile, BaseTest basetest) throws
 * JMSException, IOException, InterruptedException, SQLException,
 * ParserConfigurationException, SAXException { String GenItemNumber ="";
 * try{QueueSession session = connectToTIBCO(XLTestData, TemplateName,
 * basetest); GenItemNumber = publishAnItemInTIBCO(session, NameOfFile,
 * XLTestData, basetest); verifyMDMEventUpdate(XLTestData, GenItemNumber,
 * TemplateName, basetest, true, true, true);
 * database.savePayLoadXMLs(GenItemNumber, XLTestData, basetest);
 * verifyExistenceOfAddInternalApproval(); } catch(Exception e) {
 * basetest.test.log(Status.FAIL, "Exception " + e + " is displayed"); }
 * return(GenItemNumber); }
 * 
 * public String uploadITEMToTIBCOTempName(HashMap<String, String> XLTestData,
 * String TemplateName, String FieldScenario, BaseTest basetest) throws
 * JMSException, IOException, InterruptedException, SQLException,
 * ParserConfigurationException, SAXException { String GenItemNumber =""; try {
 * QueueSession session = connectToTIBCO(XLTestData, TemplateName, basetest);
 * GenItemNumber = publishAnItemInTIBCO(session, TemplateName, FieldScenario,
 * XLTestData, basetest); publishAnItemInTIBCO(session, TemplateName,
 * XLTestData, basetest); verifyMDMEventUpdate(XLTestData, GenItemNumber,
 * TemplateName, basetest, true, true, true);
 * database.savePayLoadXMLs(GenItemNumber, XLTestData, basetest);
 * verifyExistenceOfAddInternalApproval(); } catch (Exception e) {
 * e.printStackTrace(); basetest.test.log(Status.FAIL, "Exception " + e +
 * " is displayed"); } return(GenItemNumber); }
 * 
 * }
 */