/*
 * package reusableFunctions;
 * 
 * import java.io.BufferedReader; import java.io.File; import
 * java.io.FileNotFoundException; import java.io.FileReader; import
 * java.io.IOException; import java.io.StringReader; import
 * java.math.BigDecimal; import java.sql.Connection; import
 * java.sql.DriverManager; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.sql.Statement; import
 * java.time.LocalDateTime; import java.time.ZoneId; import
 * java.time.ZonedDateTime; import java.time.format.DateTimeFormatter; import
 * java.util.ArrayList; import java.util.HashMap; import java.util.Iterator;
 * import java.util.List;
 * 
 * import javax.xml.namespace.NamespaceContext; import
 * javax.xml.parsers.DocumentBuilder; import
 * javax.xml.parsers.DocumentBuilderFactory; import
 * javax.xml.parsers.ParserConfigurationException; import
 * javax.xml.transform.Transformer; import
 * javax.xml.transform.TransformerConfigurationException; import
 * javax.xml.transform.TransformerException; import
 * javax.xml.transform.TransformerFactory; import
 * javax.xml.transform.dom.DOMSource; import
 * javax.xml.transform.stream.StreamResult; import javax.xml.xpath.XPath; import
 * javax.xml.xpath.XPathConstants; import javax.xml.xpath.XPathExpression;
 * import javax.xml.xpath.XPathExpressionException; import
 * javax.xml.xpath.XPathFactory;
 * 
 * import org.apache.xmlbeans.XmlException; import
 * org.apache.xmlbeans.XmlObject; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import org.w3c.dom.Attr; import
 * org.w3c.dom.DOMException; import org.w3c.dom.Document; import
 * org.w3c.dom.Element; import org.w3c.dom.NamedNodeMap; import
 * org.w3c.dom.Node; import org.w3c.dom.NodeList; import
 * org.wsi.module.AppModule; //import org.wsi.module.DBModule; import
 * org.wsi.module.Modules; import org.wsi.module.objects.OrderLine; import
 * org.xml.sax.InputSource; import org.xml.sax.SAXException; import
 * com.prolifics.ProlificsSeleniumAPI;
 * 
 * import utility.BaseReport; import utility.BaseTest;
 * 
 * 
 * 
 * public class Validation { public static Connection stST2Con; public
 * Connection ecomCon; // protected String orderNumber; protected String
 * receiptHeaderKey; protected String receiptNo; protected List<OrderLine>
 * lines; protected static String apiOut; public static Connection OraConn;
 * protected String orderNumber;
 * 
 * public String getOrderNumber() { return orderNumber; }
 * 
 * public static final Logger logger =
 * LoggerFactory.getLogger(Validation.class.getName());
 * 
 * public void debug(String message) { logger.debug(message); }
 * 
 * public String readXMl(String filePath) { BufferedReader br = null; try {
 * 
 * br = new BufferedReader(new FileReader(new File(filePath))); } catch
 * (FileNotFoundException e) { System.out.println("File Not Found");
 * e.printStackTrace(); } String line; StringBuilder sb = new StringBuilder();
 * 
 * try { while ((line = br.readLine()) != null) { sb.append(line); } } catch
 * (IOException e) { e.printStackTrace(); }
 * 
 * XmlObject xmlObject = null; try { xmlObject =
 * XmlObject.Factory.parse(sb.toString()); } catch (XmlException e) {
 * e.printStackTrace(); }
 * 
 * try { br.close(); } catch (IOException e) { e.printStackTrace(); } return
 * xmlObject.toString(); }
 * 
 * public String readXMLValue(String xpathExpr, Document doc) { XPath xpath =
 * XPathFactory.newInstance().newXPath(); if (xpathExpr.contains(":")) {
 * xpath.setNamespaceContext(new NamespaceContext() {
 * 
 * @Override public Iterator getPrefixes(String arg0) { return null; }
 * 
 * @Override public String getPrefix(String arg0) { return null; }
 * 
 * @Override public String getNamespaceURI(String arg0) { if
 * ("req".equals(arg0)) { return
 * "http://www.sterlingcommerce.com/documentation/YFS/CCRefund/Request"; }
 * return null; } }); }
 * 
 * String value = "";
 * 
 * try { XPathExpression expr = xpath.compile(xpathExpr); Object result =
 * expr.evaluate(doc, XPathConstants.NODESET); NodeList nodes = (NodeList)
 * result; value = nodes.item(0).getNodeValue();
 * 
 * } catch (XPathExpressionException e) {
 * System.out.println("XML Xpath is not valid."); e.printStackTrace(); } return
 * value; }
 * 
 * public void CreateReturnReceipt(ResultSet rs, HashMap<String, String>
 * XLTestData, String orderNo) throws Exception {
 * 
 * // String data = this.readXMl(System.getProperty("user.dir") + //
 * "\\Data\\Test.xml"); // Document document = this.docFeeder(data);
 * 
 * System.out.println("Creating Return Receipt"); DocumentBuilderFactory df =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument();
 * 
 * Element root = doc.createElement("Receipt");
 * root.setAttribute("DocumentType", "0003"); root.setAttribute("OrderType",
 * XLTestData.get("OrderType").toString()); root.setAttribute("ReceivingNode",
 * XLTestData.get("ReceivingNode").toString()); root.setAttribute("ReceiptDate",
 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now
 * ())); doc.appendChild(root);
 * 
 * Element shipment = doc.createElement("Shipment");
 * shipment.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString().trim()); shipment.setAttribute("OrderNo",
 * orderNo); shipment.setAttribute("ShipmentKey", "");
 * root.appendChild(shipment);
 * 
 * Element extn = doc.createElement("Extn"); extn.setAttribute("ExtnUserID",
 * XLTestData.get("ExtnUserID").toString());
 * extn.setAttribute("ExtnClosedAtStore", "N");
 * extn.setAttribute("ExtnOperatorID",
 * XLTestData.get("ExtnOperatorID").toString());
 * extn.setAttribute("ExtnWarehouseSiteCode",
 * XLTestData.get("ExtnWarehouseSiteCode").toString());
 * extn.setAttribute("ExtnPrinterQueue",
 * XLTestData.get("ExtnPrinterQueue").toString()); root.appendChild(extn);
 * Element receipt_lines = doc.createElement("ReceiptLines");
 * root.appendChild(receipt_lines);
 * 
 * 
 * basetest.test.log(Status.INFO,
 * "<table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b><span style='font-weight:bold;color:blue'>ItemDetails</span></b></td><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b><span style='font-weight:bold;color:blue'>SterlingValues</span></b></td></tr>"
 * + "</table>");
 * 
 * 
 * while (rs.next()) {
 * 
 * Element receipt_line = doc.createElement("ReceiptLine");
 * 
 * receipt_line.setAttribute("DispositionCode", "DD");
 * receipt_line.setAttribute("OrderNo", orderNo); String PRIMELINENO =
 * rs.getString("PRIME_LINE_NO");
 * 
 * System.out.println(rs.getString("PRIME_LINE_NO"));
 * 
 * receipt_line.setAttribute("PrimeLineNo", PRIMELINENO); String orderQntity =
 * rs.getString("ordered_qty");
 * 
 * System.out.println(rs.getString("ordered_qty"));
 * receipt_line.setAttribute("Quantity", orderQntity); String SubLineNo =
 * rs.getString("SUB_LINE_NO");
 * 
 * System.out.println(rs.getString("SUB_LINE_NO"));
 * receipt_line.setAttribute("SubLineNo", SubLineNo); String itemid =
 * rs.getString("item_id");
 * 
 * basetest.test.log(Status.INFO,
 * "<table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>PrimeLine Number: </b></td>"
 * +
 * "<td width ='1'><td word-wrap: break-word width ='39' style='text-align:left'><b>"
 * + PRIMELINENO + "</b></td></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Quantity: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * orderQntity + "</td></b></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Subline Number: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * SubLineNo + "</td></b></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>ItemId: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * itemid + "</td></b></tr>" + "</table>");
 * 
 * System.out.println(rs.getString("item_id"));
 * receipt_line.setAttribute("ItemID", itemid.trim());
 * receipt_lines.appendChild(receipt_line);
 * 
 * Element receipt_extn = doc.createElement("Extn");
 * receipt_extn.setAttribute("ExtnConditionCode", "DM");
 * receipt_line.appendChild(receipt_extn);
 * 
 * } saveFile(doc);
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Created  ReturnOrderReceipt Xml   to Receive: " +
 * "<span style='font-weight:bold;color:blue'>ReturnOrderReceipt XML</span>");
 * 
 * String out = AppModule.callApi("ReturnOrderReceipt",
 * Modules.buildStringFromDocument(doc), true); receiptHeaderKey =
 * Modules.getAttributeValue(out, "Receipt", "ReceiptHeaderKey"); receiptNo =
 * Modules.getAttributeValue(out, "Receipt", "ReceiptNo");
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Executed ReturnOrderReceipt Api   to Receive: " +
 * "<span style='font-weight:bold;color:blue'>ReturnOrderReceipt</span>");
 * 
 * }
 * 
 * 
 * public void CreateReturnReceipt(ProlificsSeleniumAPI oPSelFW,ResultSet rs,
 * HashMap<String, String> XLTestData, String orderNo) throws Exception {
 * 
 * // String data = this.readXMl(System.getProperty("user.dir") + //
 * "\\Data\\Test.xml"); // Document document = this.docFeeder(data);
 * 
 * System.out.println("Creating Return Receipt"); DocumentBuilderFactory df =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument();
 * 
 * Element root = doc.createElement("Receipt");
 * root.setAttribute("DocumentType", "0003"); root.setAttribute("OrderType",
 * XLTestData.get("OrderType").toString()); root.setAttribute("ReceivingNode",
 * XLTestData.get("ReceivingNode").toString()); root.setAttribute("ReceiptDate",
 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now
 * ())); doc.appendChild(root);
 * 
 * Element shipment = doc.createElement("Shipment");
 * shipment.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString().trim()); shipment.setAttribute("OrderNo",
 * orderNo); shipment.setAttribute("ShipmentKey", "");
 * root.appendChild(shipment);
 * 
 * Element extn = doc.createElement("Extn"); extn.setAttribute("ExtnUserID",
 * XLTestData.get("ExtnUserID").toString());
 * extn.setAttribute("ExtnClosedAtStore", "N");
 * extn.setAttribute("ExtnOperatorID",
 * XLTestData.get("ExtnOperatorID").toString());
 * extn.setAttribute("ExtnWarehouseSiteCode",
 * XLTestData.get("ExtnWarehouseSiteCode").toString());
 * extn.setAttribute("ExtnPrinterQueue",
 * XLTestData.get("ExtnPrinterQueue").toString()); root.appendChild(extn);
 * Element receipt_lines = doc.createElement("ReceiptLines");
 * root.appendChild(receipt_lines);
 * 
 * 
 * basetest.test.log(Status.INFO,
 * "<table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b><span style='font-weight:bold;color:blue'>ItemDetails</span></b></td><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b><span style='font-weight:bold;color:blue'>SterlingValues</span></b></td></tr>"
 * + "</table>");
 * 
 * 
 * while (rs.next()) {
 * 
 * Element receipt_line = doc.createElement("ReceiptLine");
 * 
 * receipt_line.setAttribute("DispositionCode", "DD");
 * receipt_line.setAttribute("OrderNo", orderNo); String PRIMELINENO =
 * rs.getString("PRIME_LINE_NO");
 * 
 * System.out.println(rs.getString("PRIME_LINE_NO"));
 * 
 * receipt_line.setAttribute("PrimeLineNo", PRIMELINENO); String orderQntity =
 * rs.getString("ordered_qty");
 * 
 * System.out.println(rs.getString("ordered_qty"));
 * receipt_line.setAttribute("Quantity", orderQntity); String SubLineNo =
 * rs.getString("SUB_LINE_NO");
 * 
 * System.out.println(rs.getString("SUB_LINE_NO"));
 * receipt_line.setAttribute("SubLineNo", SubLineNo); String itemid =
 * rs.getString("item_id");
 * 
 * basetest.test.log(Status.INFO,
 * "<table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>PrimeLine Number: </b></td>"
 * +
 * "<td width ='1'><td word-wrap: break-word width ='39' style='text-align:left'><b>"
 * + PRIMELINENO + "</b></td></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Quantity: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * orderQntity + "</td></b></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Subline Number: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * SubLineNo + "</td></b></tr>" +
 * "<tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>ItemId: </b></td><td>"
 * + "<td word-wrap: break-word width ='39' style='text-align:left'><b>" +
 * itemid + "</td></b></tr>" + "</table>");
 * 
 * System.out.println(rs.getString("item_id"));
 * receipt_line.setAttribute("ItemID", itemid.trim());
 * receipt_lines.appendChild(receipt_line);
 * 
 * Element receipt_extn = doc.createElement("Extn");
 * receipt_extn.setAttribute("ExtnConditionCode", "DM");
 * receipt_line.appendChild(receipt_extn);
 * 
 * } saveFile(doc);
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Created  ReturnOrderReceipt Xml   to Receive: " +
 * "<span style='font-weight:bold;color:blue'>ReturnOrderReceipt XML</span>");
 * 
 * String out = AppModule.callApi("ReturnOrderReceipt",
 * Modules.buildStringFromDocument(doc), true); receiptHeaderKey =
 * Modules.getAttributeValue(out, "Receipt", "ReceiptHeaderKey"); receiptNo =
 * Modules.getAttributeValue(out, "Receipt", "ReceiptNo");
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Executed ReturnOrderReceipt Api   to Receive: " +
 * "<span style='font-weight:bold;color:blue'>ReturnOrderReceipt</span>");
 * 
 * }
 * 
 * 
 * 
 * public void closeReturnReceipt(ProlificsSeleniumAPI oPSelFW,HashMap<String,
 * String> XLTestData, String orderNo) throws Exception { DocumentBuilderFactory
 * df = DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument(); Element root =
 * doc.createElement("Receipt");
 * 
 * root.setAttribute("DocumentType", "0003"); root.setAttribute("ReceivingNode",
 * XLTestData.get("ReceivingNode").toString());
 * root.setAttribute("ReceiptHeaderKey", receiptHeaderKey);
 * root.setAttribute("ReceiptNo", receiptNo); doc.appendChild(root);
 * 
 * Element shipment = doc.createElement("Shipment");
 * shipment.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString().trim()); shipment.setAttribute("OrderNo",
 * orderNo); root.appendChild(shipment);
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Created  closeReceipt Xml   to CloseReceipt: " +
 * "<span style='font-weight:bold;color:blue'>closeReceipt XML</span>");
 * 
 * saveFile(doc); AppModule.callApi("closeReceipt",
 * Modules.buildStringFromDocument(doc), false);
 * 
 * basetest.test.log(Status.PASS,
 * "Successfully Executed closeReceipt Api   to CloseReceipt: " +
 * "<span style='font-weight:bold;color:blue'>closeReceipt</span>");
 * 
 * 
 * }
 * 
 * public String createOrderInvoice_modified(ProlificsSeleniumAPI
 * oPSelFW,ResultSet rs, HashMap<String, String> xLTestData, String
 * returnRerefenceNo) throws Exception {
 * 
 * Thread.sleep(10000); String result = ""; try {
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("Order"); root.setAttribute("OrderNo",
 * returnRerefenceNo); root.setAttribute("DocumentType", "0003");
 * root.setAttribute("EnterpriseCode", xLTestData.get("Brand"));
 * root.setAttribute("TransactionId", "CREATE_RETURN_INVOICE.0003.ex");
 * 
 * Element orderlines = doc.createElement("OrderLines");
 * 
 * while (rs.next()) { Element orderline = doc.createElement("OrderLine");
 * String PRIMELINENO = rs.getString("PRIME_LINE_NO");
 * orderline.setAttribute("PrimeLineNo", PRIMELINENO); String SubLineNo =
 * rs.getString("SUB_LINE_NO"); orderline.setAttribute("SubLineNo", SubLineNo);
 * String orderQntity = rs.getString("ordered_qty");
 * orderline.setAttribute("Quantity", orderQntity);
 * 
 * orderlines.appendChild(orderline); } root.appendChild(orderlines);
 * doc.appendChild(root); saveFile(doc); result =
 * AppModule.createOrderInvoice(Modules.buildStringFromDocument(doc)); } catch
 * (Exception e) { throw e; } return result; }
 * 
 * public void processDCCancelSingleItem1(ProlificsSeleniumAPI oPSelFW,String
 * orderNumber, HashMap<String, String> XLTestData) throws Exception {
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst");
 * 
 * Statement stat = stST2Con.createStatement();
 * 
 * String Stat = ""; String StatusValue = "";
 * 
 * String sqlQuery =
 * "select * from yfs_order_line where Order_Header_Key in (select Order_Header_Key from  YFS_ORDER_HEADER WHERE ORDER_NO = '"
 * + orderNumber + "') and prime_line_no != '950' and SHIPNODE_KEY = "+null+"";
 * 
 * ResultSet rs = stat.executeQuery(sqlQuery);
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("OrderRelease");
 * 
 * root.setAttribute("Action", "MODIFY"); root.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand")); root.setAttribute("ModificationReasonCode",
 * "CANCEL_CS"); root.setAttribute("ModificationReasonText",
 * "DC Cancellations"); root.setAttribute("OrderNo", orderNumber);
 * root.setAttribute("Override", "Y");
 * 
 * Element orderLines = doc.createElement("OrderLines"); while (rs.next()) {
 * 
 * Element orderLine = doc.createElement("OrderLine");
 * orderLine.setAttribute("Action", "CANCEL"); String cancelitemquan =
 * rs.getString("ORDERED_QTY");
 * 
 * String minus = "-";
 * 
 * orderLine.setAttribute("ChangeInQuantity", minus+cancelitemquan);
 * 
 * String PrimeLineNo = rs.getString("PRIME_LINE_NO");
 * orderLine.setAttribute("PrimeLineNo", PrimeLineNo);
 * 
 * String subLineNo = rs.getString("SUB_LINE_NO");
 * orderLine.setAttribute("SubLineNo", subLineNo);
 * 
 * String cancelItemId = rs.getString("item_id");
 * 
 * Element item = doc.createElement("Item"); item.setAttribute("ItemID",
 * cancelItemId);
 * 
 * orderLines.appendChild(item); orderLines.appendChild(orderLine);
 * 
 * 
 * 
 * } root.appendChild(orderLines); doc.appendChild(root);
 * 
 * System.out.println("output xml is: " + Modules.buildStringFromDocument(doc) +
 * "xml extraction completed");
 * 
 * AppModule.callApi("ProcessDCCancelMsg", Modules.buildStringFromDocument(doc),
 * true); }
 * 
 * public void releaseOrderLineHold(ProlificsSeleniumAPI oPSelFW,ResultSet rs,
 * String holdType, HashMap<String, String> XLTestData) throws Exception {
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("Order"); root.setAttribute("OrderNo",
 * "992071654600"); root.setAttribute("EnterpriseCode", "WE");
 * root.setAttribute("DocumentType", XLTestData.get("DocumentType"));
 * root.setAttribute("Action", "Modify"); root.setAttribute("Override", "Y");
 * 
 * Element orderLines = doc.createElement("OrderLines");
 * 
 * while (rs.next()) { Element orderLine = doc.createElement("OrderLine");
 * String PRIMELINENO = rs.getString("PRIME_LINE_NO");
 * orderLine.setAttribute("PrimeLineNo", PRIMELINENO); String SubLineNo =
 * rs.getString("SUB_LINE_NO"); orderLine.setAttribute("SubLineNo", SubLineNo);
 * orderLine.setAttribute("Action", "Modify"); Element orderHoldTypes =
 * doc.createElement("OrderHoldTypes"); Element orderHoldType =
 * doc.createElement("OrderHoldType"); orderHoldType.setAttribute("HoldType",
 * holdType); orderHoldType.setAttribute("Status", "1100");
 * orderHoldTypes.appendChild(orderHoldType);
 * orderLine.appendChild(orderHoldTypes); orderLines.appendChild(orderLine); }
 * 
 * root.appendChild(orderLines); doc.appendChild(root);
 * 
 * apiOut = AppModule.callApi("changeOrder",
 * Modules.buildStringFromDocument(doc), false);
 * 
 * }
 * 
 * 
 * public void requestCollection() throws Exception { DocumentBuilderFactory df
 * = DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder();
 * 
 * Document doc = db.newDocument(); Element root = doc.createElement("Order");
 * root.setAttribute("DocumentType", documentType);
 * root.setAttribute("EnterpriseCode", organizationCode);
 * root.setAttribute("IgnoreTransactionDependencies", "Y");
 * root.setAttribute("OrderHeaderKey", getOrderHeaderKey());
 * root.setAttribute("OrderNo", orderNumber);
 * 
 * doc.appendChild(root);
 * 
 * AppModule.callApi("requestCollection", Modules.buildStringFromDocument(doc),
 * false); }
 * 
 * public void executeCollection() throws Exception { DocumentBuilderFactory df
 * = DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder();
 * 
 * Document doc = db.newDocument(); Element root =
 * doc.createElement("ExecuteCollection"); root.setAttribute("DocumentType",
 * documentType); root.setAttribute("EnterpriseCode", organizationCode);
 * root.setAttribute("IgnoreTransactionDependencies", "Y");
 * root.setAttribute("OrderHeaderKey", getOrderHeaderKey());
 * root.setAttribute("OrderNo", orderNumber);
 * root.setAttribute("ChargeTransactionKey", "");
 * root.setAttribute("IgnoreCollectionDate", "");
 * 
 * doc.appendChild(root);
 * 
 * AppModule.callApi("executeCollection", Modules.buildStringFromDocument(doc),
 * false); }
 * 
 * public void ScheduleOrderApi(ProlificsSeleniumAPI oPSelFW,HashMap<String,
 * String> XLTestData,String StatusValue, String OrderNo) throws Exception,
 * Exception {
 * 
 * if (StatusValue.contains("Created")) { DocumentBuilderFactory df =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument();
 * 
 * Element root = doc.createElement("ScheduleOrder");
 * root.setAttribute("DocumentType", "0001");
 * root.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString().trim()); root.setAttribute("OrderNo",
 * OrderNo); doc.appendChild(root); saveFile(doc);
 * 
 * AppModule.callApi("scheduleOrder", Modules.buildStringFromDocument(doc),
 * false); }
 * 
 * }
 * 
 * public void orderStatus(String OrderNumber) throws SQLException { stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement(); try { String sqlQuery =
 * "select Max(Status) from yfs_order_release_Status where Order_Header_Key in "
 * + "(select Order_Header_Key from  YFS_ORDER_HEADER WHERE ORDER_NO = '" +
 * OrderNumber + "')"; ResultSet rs = stat.executeQuery(sqlQuery); while
 * (rs.next()) { String OrderStatus = rs.getString("Max(Status)");
 * System.out.println(OrderStatus); } } catch (Exception e) { // TODO
 * Auto-generated catch block e.printStackTrace();
 * 
 * } }
 * 
 * public String getOrderStatus(String orderNumber,HashMap<String, String>
 * envTestData, ProlificsSeleniumAPI oPSelFW) throws Exception { //
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@"+envTestData.get("HostName")+
 * ":1521:"+envTestData.get("SID"), envTestData.get("DBUserName") ,
 * envTestData.get("DBPassword"));
 * 
 * Statement stat = stST2Con.createStatement();
 * 
 * String Stat = ""; String StatusValue = "";
 * 
 * String sqlQuery = "select Max(Status) from "+envTestData.get("Schema")
 * +".yfs_order_release_Status where Order_Header_Key in " +
 * "(select Order_Header_Key from  "+envTestData.get("Schema")
 * +".YFS_ORDER_HEADER WHERE ORDER_NO = '" + orderNumber + "')";
 * 
 * ResultSet rs = stat.executeQuery(sqlQuery);
 * 
 * while (rs.next()) { Stat = rs.getString("Max(Status)"); }
 * 
 * if (Stat.contains("1100")) StatusValue = "Created"; else if
 * (Stat.contains("1300")) StatusValue = "BackOrder"; else if
 * (Stat.contains("1000")) StatusValue = "Draft Created"; else if
 * (Stat.contains("1500.101")) StatusValue = "DTCSchedule"; else if
 * (Stat.contains("1500")) StatusValue = "Scheduled"; else if
 * (Stat.contains("1600")) StatusValue = "Awaiting DS PO Creation"; else if
 * (Stat.contains("2100")) StatusValue = "DS PO Created"; else if
 * (Stat.contains("3700.01.540")) StatusValue = "Reprocessed"; else if
 * (Stat.contains("3200.050")) StatusValue = "Dropped For Fulfillment"; else if
 * (Stat.contains("3200.200")) StatusValue = "Invoice Preship"; else if
 * (Stat.contains("3200.100")) StatusValue = "Acknowledge"; else if
 * (Stat.contains("3950.01")) StatusValue = "Return Invoiced"; else if
 * (Stat.contains("3700") && Stat.contains("00.03")) StatusValue = "Delivered";
 * else if (Stat.contains("3700.01.545")) StatusValue = "DCcancel"; else if
 * (Stat.contains("9000")) StatusValue = "cancel"; else if
 * (Stat.contains("3700") == true && Stat.contains("7777") == false) StatusValue
 * = "Shipped"; else if (Stat.contains("3700.7777")) StatusValue = "Delivered";
 * else if (Stat.contains("3200.520")) StatusValue = "PreReprocessed"; else if
 * (Stat.contains("3950")) StatusValue = "Receipt Closed"; else if
 * (Stat.contains("3950.01")) StatusValue = "Return Invoiced"; else if
 * (Stat.contains("3900")) StatusValue = "Received"; else { StatusValue =
 * "Invalid status"; } System.out.println(StatusValue); return (StatusValue); }
 * 
 * public void ReleaseOrderApi(ProlificsSeleniumAPI oPSelFW,HashMap<String,
 * String> XLTestData,String StatusValue, String OrderNo) throws
 * TransformerException, Exception { Thread.sleep(3000);
 * 
 * if (StatusValue.contains("DTCSchedule") || StatusValue.contains("Scheduled"))
 * {
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("ReleaseOrder");
 * root.setAttribute("DocumentType", XLTestData.get("DocumentType").toString());
 * root.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString().trim());
 * root.setAttribute("IgnoreReleaseDate", "Y"); root.setAttribute("OrderNo",
 * OrderNo); doc.appendChild(root); saveFile(doc);
 * AppModule.callApi("releaseOrder", Modules.buildStringFromDocument(doc),
 * false); } }
 * 
 * public void processOrderDropAck(ProlificsSeleniumAPI oPSelFW,HashMap<String,
 * String> XLTestData, String actualShipmentDate, String StatusValue, String
 * OrderNo) throws TransformerException, Exception { Thread.sleep(3000); if
 * (StatusValue.contains("Dropped For Fulfillment")) { stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * basetest.test.log(Status.PASS, "Quntatity: " +
 * "<span style='font-weight:bold;color:blue'>Connected to Sterling Database </span>"
 * );
 * 
 * // String orderNumber = XLTestData.get("orderNumber").toString();
 * 
 * String Query =
 * "select * from yantra_stst_owner.yfs_order_release where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_release where sales_order_no ='"
 * + OrderNo + "')";
 * 
 * ResultSet rs = stat.executeQuery(Query);
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument(); while (rs.next()) {
 * 
 * // OrderLine firstOrderLine = lines.get(0); Element root =
 * doc.createElement("OrderRelease");
 * 
 * root.setAttribute("ConsolidatorAddressCode", "");
 * root.setAttribute("DocumentType", "0001");
 * root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
 * root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate);
 * root.setAttribute("OrderNo", OrderNo); String ShipAdviceNo =
 * rs.getString("Ship_Advice_No"); System.out.println(ShipAdviceNo);
 * root.setAttribute("ShipAdviceNo", ShipAdviceNo); String ShipNode =
 * rs.getString("SHIPNODE_KEY"); root.setAttribute("ShipNode", ShipNode.trim());
 * // String TransactionType = rs.getString("TRANSACTION_TYPE"); //
 * root.setAttribute("TransactionType", OrderNo); doc.appendChild(root);
 * saveFile(doc); AppModule.callApi("ProcessOrderDropAck",
 * Modules.buildStringFromDocument(doc), false); } } }
 * 
 * public static void releaseOrderLineHold(ProlificsSeleniumAPI oPSelFW,String
 * holdType, String OrderNumber, HashMap<String, String> XLTestData) throws
 * Exception {
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * basetest.test.log(Status.PASS, "Quntatity: " +
 * "<span style='font-weight:bold;color:blue'>Connected to Sterling Database </span>"
 * );
 * 
 * 
 * // String orderNo = "990939477007";
 * 
 * String Query =
 * "select * from yantra_stst_owner.yfs_order_line where order_header_key in (select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
 * + OrderNumber + "')";
 * 
 * ResultSet rs = stat.executeQuery(Query);
 * 
 * System.out.println(rs);
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("Order"); root.setAttribute("OrderNo",
 * OrderNumber); root.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString()); root.setAttribute("DocumentType",
 * "0001"); root.setAttribute("Action", "Modify"); root.setAttribute("Override",
 * "Y");
 * 
 * Element orderLines = doc.createElement("OrderLines");
 * 
 * while (rs.next()) { Element orderLine = doc.createElement("OrderLine");
 * String PRIMELINENO = rs.getString("PRIME_LINE_NO");
 * orderLine.setAttribute("PrimeLineNo", PRIMELINENO); String SubLineNo =
 * rs.getString("SUB_LINE_NO"); orderLine.setAttribute("SubLineNo", SubLineNo);
 * orderLine.setAttribute("Action", "Modify");
 * 
 * Element orderHoldTypes = doc.createElement("OrderHoldTypes");
 * 
 * Element orderHoldType = doc.createElement("OrderHoldType");
 * orderHoldType.setAttribute("HoldType", holdType);
 * orderHoldType.setAttribute("Status", "1300");
 * 
 * orderHoldTypes.appendChild(orderHoldType);
 * orderLine.appendChild(orderHoldTypes); orderLines.appendChild(orderLine); }
 * 
 * root.appendChild(orderLines); doc.appendChild(root);
 * 
 * apiOut = AppModule.callApi("changeOrder",
 * Modules.buildStringFromDocument(doc), false);
 * 
 * }
 * 
 * public void releaseholdsnew(ProlificsSeleniumAPI oPSelFW,String
 * OrderNumber,HashMap<String, String> XLTestData) throws Exception {
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement(); // Verify if Order Status is not equal to
 * DTCSchedule // if (GetOrderStatusFromDB(OrderNumber).contains("1500.101") ==
 * false) {
 * 
 * String HoldExistenceQuery =
 * "SELECT * FROM yantra_stst_owner.YFS_ORDER_HEADER where order_no = '" +
 * OrderNumber + "'"; ResultSet rs = stat.executeQuery(HoldExistenceQuery);
 * String HOLD_FLAGExist = ""; while (rs.next()) { HOLD_FLAGExist =
 * rs.getString("HOLD_FLAG"); } if (HOLD_FLAGExist.contains("Y")) { String
 * HoldType =
 * "SELECT * FROM yantra_stst_owner.YFS_ORDER_HOLD_TYPE where ORDER_HEADER_KEY in (SELECT ORDER_HEADER_KEY FROM yantra_stst_owner.YFS_ORDER_HEADER where order_no = '"
 * + OrderNumber + "')"; rs = stat.executeQuery(HoldType); while (rs.next()) {
 * 
 * String HOLD_TYPE = rs.getString("HOLD_TYPE");
 * 
 * Validation.releaseOrderLineHold(oPSelFW,HOLD_TYPE, OrderNumber, XLTestData);
 * 
 * // } }
 * 
 * } }
 * 
 * public String getOrderHoldList(ProlificsSeleniumAPI oPSelFW,String
 * OrderNumber,HashMap<String, String> XLTestData) throws Exception { //
 * <OrderHoldType HoldType="" LastHoldTypeDate="" // Modifyuserid=""
 * OrderAuditKey="" OrderHeaderKey="" OrderLineKey="" // ReasonText=""
 * ResolverUserId="" Status="" StatusDescription="" // TransactionId=""
 * TransactionName="" /> String result = null;
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * String Query =
 * "select * from yantra_stst_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
 * + OrderNumber + "') and kit_code=' '";
 * 
 * ResultSet rs = stat.executeQuery(Query);
 * 
 * System.out.println(rs);
 * 
 * while (rs.next()) { DocumentBuilderFactory df =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument();
 * 
 * Element root = doc.createElement("OrderHoldType");
 * root.setAttribute("HoldType", ""); root.setAttribute("LastHoldTypeDate", "");
 * root.setAttribute("Modifyuserid", ""); root.setAttribute("OrderAuditKey",
 * ""); root.setAttribute("OrderHeaderKey", rs.getString("ORDER_HEADER_KEY"));
 * root.setAttribute("OrderLineKey", ""); root.setAttribute("ReasonText", "");
 * root.setAttribute("ResolverUserId", ""); root.setAttribute("Status", "");
 * root.setAttribute("StatusDescription", "");
 * root.setAttribute("TransactionId", ""); root.setAttribute("TransactionName",
 * ""); doc.appendChild(root);
 * 
 * result = AppModule.callApi("getOrderHoldTypeList",
 * Modules.buildStringFromDocument(doc), false);
 * 
 * saveFile(doc);
 * 
 * } return result;
 * 
 * }
 * 
 * private ArrayList<String> getUresolvedHolds(ProlificsSeleniumAPI
 * oPSelFW,String OrderNumber, HashMap<String, String> XLTestData) throws
 * Exception { Node nod; NodeList nodes; // XMLDocument xdoc = new
 * XMLDocument(); apiOut = getOrderHoldList(oPSelFW,OrderNumber, XLTestData);
 * 
 * ArrayList<String> unresolved_holds = new ArrayList<String>(0);
 * 
 * if ((nod =
 * Modules.buildDocumentFromString(apiOut).getElementsByTagName("OrderHoldTypes"
 * ).item(0)) != null) { nodes = nod.getChildNodes();
 * 
 * // Loop through OrderHoldTypes for (int i = 0; i < nodes.getLength(); i++) {
 * String hold_type = Modules.getAttributeValue(nodes.item(i), "HoldType");
 * String hold_status = Modules.getAttributeValue(nodes.item(i), "Status"); //
 * Only want the unresolved ones // Status 1300 indicates Resolved if
 * (!"1300".equals(hold_status)) { unresolved_holds.add(hold_type); //
 * unresolved_holds.add(hold_status); } } } return unresolved_holds; }
 * 
 * public static void releaseHold(ProlificsSeleniumAPI oPSelFW,String
 * OrderNumber, String holdType, HashMap<String, String> XLTestData) throws
 * Exception {
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("Order"); root.setAttribute("OrderNo",
 * OrderNumber); root.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString()); root.setAttribute("DocumentType",
 * "0001"); root.setAttribute("SelectMethod", "WAIT");
 * 
 * Element orderHoldTypes = doc.createElement("OrderHoldTypes");
 * 
 * Element orderHoldType = doc.createElement("OrderHoldType");
 * orderHoldType.setAttribute("Status", "1300");
 * orderHoldType.setAttribute("HoldType", holdType.trim());
 * 
 * orderHoldTypes.appendChild(orderHoldType); root.appendChild(orderHoldTypes);
 * doc.appendChild(root);
 * 
 * apiOut = AppModule.callApi("changeOrder",
 * Modules.buildStringFromDocument(doc), false); }
 * 
 * public String GetOrderStatusFromDB(String OrderNumber) throws SQLException {
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * String sqlQuery =
 * "select Max(Status) from yfs_order_release_Status where Order_Header_Key in "
 * + "(select Order_Header_Key from  YFS_ORDER_HEADER WHERE ORDER_NO = '" +
 * OrderNumber + "')"; ResultSet rs = stat.executeQuery(sqlQuery);
 * 
 * String Status = ""; while (rs.next()) { Status = rs.getString("Max(Status)");
 * System.out.println(Status); } return Status;
 * 
 * }
 * 
 * public void releaseHolds(ProlificsSeleniumAPI oPSelFW,ResultSet rs,
 * HashMap<String, String> XLTestData, String OrderNumber) throws Exception {
 * 
 * Thread.sleep(10000);
 * 
 * try { List<String> unresolved_holds =
 * getUresolvedHolds(oPSelFW,OrderNumber,XLTestData); while
 * (unresolved_holds.size() > 0) { for (String holdType : unresolved_holds) { //
 * TODO: identify what indicates order line level hold if
 * ("VALIDATION_HOLD".equals(holdType)) { HashMap<String, String> basetest;
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * } else if ("CONTINUITY_HOLD".equals(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("EVENT_HOLD_WINDOWED".equals(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("IDS_VALIDATION_HOLD".equals(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("UPHOLSTERY_HOLD".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber, XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("Concierge_Hold".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("EVENT_HOLD_Automatic".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("REPRO_HOLD".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber, XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("DELAYED_ORDER_DROP".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber, XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("WINDOWED_HOLD".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber,XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * } else if ("FUTURE_REL_HOLD".equalsIgnoreCase(holdType)) {
 * releaseOrderLineHold(oPSelFW,holdType, OrderNumber, XLTestData); //
 * releaseHold(OrderNumber, holdType, basetest, XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * 
 * }
 * 
 * else {
 * 
 * releaseHold(oPSelFW,OrderNumber, holdType,XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
 * holdType + "</span>" + " Hold Resolved Succesfully ");
 * 
 * } } unresolved_holds = getUresolvedHolds(oPSelFW,OrderNumber,XLTestData);
 * System.out.println("Hold Resolved");
 * 
 * } } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); //basetest.test.log(Status.PASS,
 * "Hold not Resolved Succesfully "); }
 * 
 * }
 * 
 * public void invoicePreShip(ProlificsSeleniumAPI oPSelFW,HashMap<String,
 * String> XLTestData, String orderNumber, String StatusValue) throws
 * TransformerException, Exception { Thread.sleep(3000); if
 * (StatusValue.contains("Acknowledge")) { stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * basetest.test.log(Status.PASS, "Quntatity: " +
 * "<span style='font-weight:bold;color:blue'>Connected to Sterling Database </span>"
 * );
 * 
 * // String orderNumber = XLTestData.get("orderNumber").toString();
 * 
 * String sql = String.format(
 * "select task_q_key, transaction_key, data_key from yfs_task_q where transaction_key in (select transaction_key from yfs_transaction where tranid='WSI_ORD_ACK_INVOICE.0001.ex')"
 * +
 * "and data_key in (select order_header_key from yfs_order_header where order_no='%s')"
 * , orderNumber);
 * 
 * ResultSet rs = stat.executeQuery(sql);
 * 
 * basetest.test.log(Status.PASS, "ResultSet: " +
 * "<span style='font-weight:bold;color:blue'>Query Executed for InvoicePreship</span>"
 * );
 * 
 * while (rs.next()) { DocumentBuilderFactory df =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
 * df.newDocumentBuilder(); Document doc = db.newDocument();
 * 
 * Element root = doc.createElement("Task"); root.setAttribute("DataKey",
 * rs.getString("data_key")); root.setAttribute("TaskQKey",
 * rs.getString("task_q_key")); root.setAttribute("TransactionKey",
 * rs.getString("transaction_key")); doc.appendChild(root); saveFile(doc);
 * AppModule.callApi("CreateOrderInvoiceService",
 * Modules.buildStringFromDocument(doc), true); } }
 * 
 * }
 * 
 * 
 * public String confirmShipmentMultiline(String actualShipmentDate, String
 * expectedDeliveryDate, List<OrderLine> orderLines) throws Exception {
 * 
 * String apiOut = confirmShipment(null, actualShipmentDate,
 * expectedDeliveryDate, orderLines); return apiOut;
 * 
 * }
 * 
 * 
 * 
 * public String confirmShipment(HashMap<String, String> XLTestData,String
 * actualShipmentDate, String expectedDeliveryDate, List<OrderLine> orderLines)
 * throws Exception { return confirmShipment(XLTestData, actualShipmentDate,
 * expectedDeliveryDate, null, orderLines); }
 * 
 * 
 * 
 * public String confirmShipment(String actualShipmentDate, String
 * expectedDeliveryDate, String numberOfShippedItems) throws Exception {
 * List<OrderLine> orderLines = lines.stream() .filter(ln ->
 * ln.getItem().getInventory().get(ln.getShipNode()) != null &&
 * ln.getItem().getInventory().get(ln.getShipNode()).getQuantity() > 0 &&
 * ("REGULAR".equals(ln.getOrderLineType()) ||
 * "COMPONENT".equals(ln.getOrderLineType())) &&
 * "N".equals(ln.getItem().getExtnDirectShipInd()) &&
 * "".equals(ln.getItem().getIsPickupAllowed()) && !ln.getShipmentConfirmed())
 * .collect(Collectors.toList()); String apiOut =
 * confirmShipment(actualShipmentDate, expectedDeliveryDate,
 * numberOfShippedItems, orderLines); return apiOut; }
 * 
 * 
 * 
 * public void ConfirmShipmentOfLinesMultiShipNode(Connection OraConn) throws
 * Exception, InterruptedException { DateTimeFormatter format_long =
 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); ZonedDateTime today =
 * ZonedDateTime.now(ZoneId.of("America/Los_Angeles")); for (OrderLine orderLine
 * : this.lines) { String KitCode = orderLine.getKitCode(); String
 * PrimeLineNumber = orderLine.getPrimeLineNo(); String SubLineNumber =
 * orderLine.getSubLineNo(); if (KitCode.contains("BUNDLE") == false)
 * ConfirmShip(format_long.format(today), orderLine, OraConn); } }
 * 
 * 
 * 
 * public int ConfirmShipmentOfLinesMultiShipNode(ResultSet rs,HashMap<String,
 * String> XLTestData,String actualShipmentDate) throws Exception { Validation
 * validate = new Validation();
 * 
 * 
 * 
 * 
 * for (int i = 1; i <= num; i += 2) { num += i; validate.ConfirmShip(rs,
 * XLTestData, actualShipmentDate); }
 * 
 * 
 * return num;
 * 
 * }
 * 
 * 
 * public String ConfirmShip(ResultSet rs, HashMap<String, String> XLTestData,
 * String actualShipmentDate, String StatusValue, String OrderNo) throws
 * Exception { Thread.sleep(3000); String TrackingNo = ""; if
 * (StatusValue.contains("Invoice Preship")) {
 * 
 * int num = 1101;
 * 
 * for (int i = 1101; i <= num; i += 1) {
 * 
 * while (rs.next()) {
 * 
 * num += 1; DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument(); // OrderLine firstOrderLine = lines.get(0); Element root =
 * doc.createElement("Shipment");
 * 
 * root.setAttribute("Action", "Create"); root.setAttribute("DocumentType",
 * "0001"); // root.setAttribute("CarrierServiceCode", "FURNITURE_REGULAR");
 * root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
 * root.setAttribute("SCAC", "WS_CARRIER");
 * root.setAttribute("SellerOrganizationCode",
 * XLTestData.get("Brand").toString() + "DTC"); String ShipNode =
 * rs.getString("SHIPNODE_KEY"); root.setAttribute("ShipNode", ShipNode.trim());
 * root.setAttribute("TrackingNo", OrderNo + num);
 * root.setAttribute("ActualShipmentDate", actualShipmentDate);
 * root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate); //
 * root.setAttribute("ConsolidatorAddressCode", consolidatorAddressCode);
 * doc.appendChild(root);
 * 
 * Element ShipmentLines = doc.createElement("ShipmentLines");
 * root.appendChild(ShipmentLines);
 * 
 * Element ShipmentLine = doc.createElement("ShipmentLine");
 * ShipmentLine.setAttribute("Action", "Create");
 * ShipmentLine.setAttribute("DocumentType", "0001"); String ItemId =
 * rs.getString("ITEM_ID"); ShipmentLine.setAttribute("ItemID", ItemId.trim());
 * ShipmentLine.setAttribute("OrderNo", OrderNo);
 * 
 * String PRIMELINENO = rs.getString("PRIME_LINE_NO");
 * 
 * ShipmentLine.setAttribute("PrimeLineNo", PRIMELINENO);
 * ShipmentLine.setAttribute("ProductClass", "");
 * 
 * String OrderQuantity = rs.getString("ORDERED_QTY");
 * ShipmentLine.setAttribute("Quantity", OrderQuantity);
 * ShipmentLine.setAttribute("UnitOfMeasure", "EACH"); String SubLineNo =
 * rs.getString("SUB_LINE_NO"); ShipmentLine.setAttribute("SubLineNo",
 * SubLineNo); ShipmentLine.setAttribute("ReleaseNo", "1"); //
 * ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
 * ShipmentLines.appendChild(ShipmentLine);
 * 
 * Element Containers = doc.createElement("Containers");
 * root.appendChild(Containers);
 * 
 * Element Container = doc.createElement("Container");
 * Container.setAttribute("Action", "Create");
 * Container.setAttribute("ContainerNo", OrderNo + num);
 * Container.setAttribute("TrackingNo", OrderNo + num); //
 * Container.setAttribute("Ucc128code", ""); Containers.appendChild(Container);
 * 
 * Element ContainerDetails = doc.createElement("ContainerDetails");
 * Container.appendChild(ContainerDetails);
 * 
 * Element ContainerDetail = doc.createElement("ContainerDetail");
 * ContainerDetail.setAttribute("Action", "Create");
 * ContainerDetail.setAttribute("Quantity", OrderQuantity);
 * ContainerDetails.appendChild(ContainerDetail);
 * 
 * Element ShipmentLineNode = doc.createElement("ShipmentLine");
 * ShipmentLineNode.setAttribute("OrderNo", OrderNo); String PRIMELINENO1 =
 * rs.getString("PRIME_LINE_NO"); ShipmentLineNode.setAttribute("PrimeLineNo",
 * PRIMELINENO1); String SubLineNo1 = rs.getString("SUB_LINE_NO");
 * ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
 * ShipmentLineNode.setAttribute("ReleaseNo", "1"); //
 * ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
 * ContainerDetail.appendChild(ShipmentLineNode);
 * 
 * Element ShipmentTagSerials = doc.createElement("ShipmentTagSerials");
 * ContainerDetail.appendChild(ShipmentTagSerials);
 * 
 * saveFile(doc);
 * 
 * AppModule.callApi("ConfirmShipment", Modules.buildStringFromDocument(doc),
 * true); }
 * 
 * }
 * 
 * } return TrackingNo; }
 * 
 * // isCreated = true;
 * 
 * public void DeliverShipmentMultiline(ProlificsSeleniumAPI
 * oPSelFW,HashMap<String, String> XLTestData, String actualShipmentDate, String
 * StatusValue, String orderNo) throws Exception { Thread.sleep(3000); if
 * (StatusValue.contains("Shipped")) {
 * 
 * stST2Con =
 * DriverManager.getConnection("jdbc:oracle:thin:@SCOMDBTSTRK1P:1521:yanstst6",
 * "yantra_stst_owner", "yntrstst"); Statement stat =
 * stST2Con.createStatement();
 * 
 * basetest.test.log(Status.PASS, "Quntatity: " +
 * "<span style='font-weight:bold;color:blue'>Connected to Sterling Database </span>"
 * );
 * 
 * 
 * // String orderNo = "990939477007";
 * 
 * String Query =
 * "select ysl.order_no, ysl.prime_line_no,ysl.sub_line_no, ysl.item_id,ysl.QUANTITY, ys.tracking_no from yantra_stst_owner.yfs_shipment ys join yantra_stst_owner.yfs_shipment_line ysl on  ys.shipment_key=ysl.shipment_key where ysl.order_no='"
 * + orderNo + "' and prime_line_no != '950'";
 * 
 * ResultSet rs = stat.executeQuery(Query);
 * 
 * 
 * basetest.test.log(Status.PASS, "ResultSet: " +
 * "<span style='font-weight:bold;color:blue'>Query Executed</span>");
 * 
 * 
 * System.out.println(rs);
 * 
 * while (rs.next()) {
 * 
 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
 * db.newDocument();
 * 
 * Element root = doc.createElement("MultiApi"); doc.appendChild(root);
 * 
 * Element APINode = doc.createElement("API"); APINode.setAttribute("FlowName",
 * "ExecutePOD"); root.appendChild(APINode);
 * 
 * Element Input = doc.createElement("Input"); APINode.appendChild(Input);
 * 
 * Element ShipmentNode = doc.createElement("Shipment");
 * ShipmentNode.setAttribute("OrderNo", orderNo);
 * ShipmentNode.setAttribute("DocumentType", "0001");
 * ShipmentNode.setAttribute("EnterpriseCode",
 * XLTestData.get("Brand").toString()); ShipmentNode.setAttribute("StatusDate",
 * actualShipmentDate);
 * 
 * Input.appendChild(ShipmentNode);
 * 
 * Element ContainersNode = doc.createElement("Containers");
 * ShipmentNode.appendChild(ContainersNode);
 * 
 * Element ContainerNode;
 * 
 * ContainerNode = doc.createElement("Container"); String trackingNo =
 * rs.getString("TRACKING_NO"); ContainerNode.setAttribute("TrackingNo",
 * trackingNo); ContainerNode.setAttribute("ContainerNo", trackingNo);
 * ContainerNode.setAttribute("ISDropShipContainer", "N");
 * ContainersNode.appendChild(ContainerNode);
 * 
 * Element ContainerDetailsNode = doc.createElement("ContainerDetails");
 * ContainerNode.appendChild(ContainerDetailsNode);
 * 
 * Element ContainerDetailNode = doc.createElement("ContainerDetail"); String
 * ItemId = rs.getString("ITEM_ID"); ContainerDetailNode.setAttribute("ItemID",
 * ItemId.trim()); String OrderQuantity = rs.getString("QUANTITY");
 * ContainerDetailNode.setAttribute("Quantity", OrderQuantity);
 * ContainerNode.setAttribute("ISDropShipContainer", "N");
 * ContainerDetailsNode.appendChild(ContainerDetailNode);
 * 
 * Element ShipmentLineNode = doc.createElement("ShipmentLine");
 * ShipmentLineNode.setAttribute("OrderNo", orderNo); String PRIMELINENO1 =
 * rs.getString("PRIME_LINE_NO"); ShipmentLineNode.setAttribute("PrimeLineNo",
 * PRIMELINENO1); String SubLineNo1 = rs.getString("SUB_LINE_NO");
 * ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
 * ContainerDetailsNode.appendChild(ShipmentLineNode);
 * 
 * // API Second Node APINode = doc.createElement("API");
 * APINode.setAttribute("FlowName", "ExecuteContainerActivity");
 * root.appendChild(APINode);
 * 
 * Input = doc.createElement("Input"); APINode.appendChild(Input);
 * 
 * ContainerNode = doc.createElement("Container");
 * ContainerNode.setAttribute("IsDropShipContainer", "N");
 * ContainerNode.setAttribute("ContainerNo", trackingNo);
 * Input.appendChild(ContainerNode);
 * 
 * Element ContainerActivitiesNode = doc.createElement("ContainerActivities");
 * ContainerNode.appendChild(ContainerActivitiesNode);
 * 
 * Element ContainerActivityNode = doc.createElement("ContainerActivity");
 * ContainerActivityNode.setAttribute("ActivityCode", "DELIVERED TO CUSTOMER");
 * ContainerActivityNode.setAttribute("ActivityTimeStamp", actualShipmentDate);
 * ContainerActivitiesNode.appendChild(ContainerActivityNode);
 * 
 * Element ExtnNode = doc.createElement("Extn");
 * ExtnNode.setAttribute("ExtnActivityTime", actualShipmentDate);
 * ExtnNode.setAttribute("ExtnPkmsStatusDesc", "X1");
 * ContainerActivityNode.appendChild(ExtnNode);
 * 
 * Element ActivityLocationNode = doc.createElement("ActivityLocation");
 * ContainerActivityNode.appendChild(ActivityLocationNode);
 * ContainerActivityNode.appendChild(ActivityLocationNode);
 * 
 * Element OrderNode = doc.createElement("Order");
 * OrderNode.setAttribute("DocumentType", "0001");
 * OrderNode.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
 * OrderNode.setAttribute("OrderNo", orderNo);
 * ContainerNode.appendChild(OrderNode);
 * 
 * Element OrderLinesNode = doc.createElement("OrderLines");
 * OrderNode.appendChild(OrderLinesNode);
 * 
 * Element OrderLineNode = doc.createElement("OrderLine");
 * OrderLineNode.setAttribute("OrderedQty", OrderQuantity);
 * OrderLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
 * OrderLineNode.setAttribute("SubLineNo", SubLineNo1);
 * OrderLinesNode.appendChild(OrderLineNode);
 * 
 * Element ItemNode = doc.createElement("Item");
 * ItemNode.setAttribute("PackItemID", ""); ItemNode.setAttribute("ItemID",
 * ItemId.trim()); OrderLineNode.appendChild(ItemNode); saveFile(doc); apiOut =
 * AppModule.callApi("multiApi", Modules.buildStringFromDocument(doc), false); }
 * }
 * 
 * }
 * 
 * public void saveFile(Document document) { DOMSource source = new
 * DOMSource(document); TransformerFactory transformerFactory =
 * TransformerFactory.newInstance(); Transformer transformer = null; try {
 * transformer = transformerFactory.newTransformer(); } catch
 * (TransformerConfigurationException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } StreamResult result = new
 * StreamResult(System.getProperty("user.dir") + "\\Data\\Test.xml"); try {
 * transformer.transform(source, result); } catch (TransformerException e) { //
 * TODO Auto-generated catch block e.printStackTrace(); } }
 * 
 * public Document docFeeder(String xmlData) { DocumentBuilderFactory factory =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder = null; try {
 * builder = factory.newDocumentBuilder(); } catch (ParserConfigurationException
 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); } Document doc
 * = null; try { doc = builder.parse(new InputSource(new
 * StringReader(xmlData))); } catch (SAXException | IOException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * 
 * Element root = doc.getDocumentElement(); Node node =
 * root.getElementsByTagName("ReceiptLines").item(0);
 * 
 * while (node.hasChildNodes()) { node.removeChild(node.getFirstChild()); }
 * 
 * return doc; }
 * 
 * }
 */