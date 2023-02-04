package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	public FileOutputStream fileOut;
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public static String cFileNameWithPath;
	public static String cSheetName;
	public static String cTcID;
	public static String cTcValue;
	public XSSFSheet sheet;
	public XSSFRow row;

	public XSSFCell cell;

	public Excel_Reader(String path) {

		this.path = path;

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int GetRowCount(String sheetName) {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum() + 1;
		return (totalRow);
	}

	public String[][] getDataFromSheet(String sheetName) {

		String dataSet[][] = null;

		try {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			int totalRow = sheet.getLastRowNum() + 1;

			int totalColumn = sheet.getRow(0).getLastCellNum();

			dataSet = new String[totalRow - 1][totalColumn];

			for (int i = 1; i < totalRow; i++) {

				XSSFRow row = sheet.getRow(i);

				for (int j = 0; j < totalColumn; j++) {

					XSSFCell cell = row.getCell(j);

					if (cell.getCellType() == cell.CELL_TYPE_STRING) {

						dataSet[i - 1][j] = cell.getStringCellValue();

					} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {

						String cellText = String.valueOf(cell.getNumericCellValue());

						dataSet[i - 1][j] = cellText;

					}

					else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {

						String cellText = String.valueOf(cell.getBooleanCellValue());

						dataSet[i - 1][j] = cellText;

					}

				}

			}

			return dataSet;

		} catch (Exception e) {

			System.out.println("Exception in reading excel file" + e.getMessage());

			e.printStackTrace();

		}

		return dataSet;

	}

	public String getStringCellData(String sheetName, String colName, int rowNum) {

		try {

			int col_Num = 0;

			int index = workbook.getSheetIndex(sheetName);

			sheet = workbook.getSheetAt(index);

			XSSFRow row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {

				if (row.getCell(i).getStringCellValue().equals(colName)) {

					col_Num = i;

					break;

				}

			}

			row = sheet.getRow(rowNum - 1);

			XSSFCell cell = row.getCell(col_Num);

			if (cell.getCellType() == cell.CELL_TYPE_STRING) {

				return cell.getStringCellValue();

			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {

				return "";

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	/* InitialArguments */
	public void xlsx_Initial_Arguments(String cFileNameWithPath, String cSheetName, String cTCColumnName,
			String cTcValue) {
		System.out.println("Hello");
		this.cFileNameWithPath = cFileNameWithPath;
		this.cSheetName = cSheetName;
		this.cTcID = cTCColumnName;
		this.cTcValue = cTcValue;
	}

	/**
	 * Description of class body
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	/* getRowColumnNumbers */
	public static int[] getRowColumnNumbers(String file, String sheetName, String sTCidColumnLabel,
			String TcidsColumnValue, String sUpdateColumanName) throws FileNotFoundException, IOException {
		FileInputStream ExcelFile = new FileInputStream(file);
		XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
		// XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
		int columnCount = myExcelSheet.getRow(0).getPhysicalNumberOfCells();
		int rowCount = 0;
		Iterator rowIter = myExcelSheet.rowIterator();
		while (rowIter.hasNext()) {
			Row r = (Row) rowIter.next();
			rowCount = r.getRowNum();
		}

		boolean sColumnValueNumberBoolean = false;
		int sColumnValueNumber = 0;
		int sCoulumnHeadingNumber = 0;
		int sRowNumber = 0;
		boolean sRowValueBoolean = false;
		boolean sColumnHeadinBoolean = false;
		// System.out.println("rowCount"+rowCount+"columnCount"+columnCount);
		String sCellValueName = null;
		Rcount: for (int r = 0; r < rowCount + 1; r++) {
			for (int c = 0; c < columnCount; c++) {
				try {
					XSSFRow row = myExcelSheet.getRow(r);
					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_STRING) {
						sCellValueName = row.getCell(c).getStringCellValue();
						// System.out.println("NAME : " + sCellValueName);
					}

					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						double birthdate = row.getCell(c).getNumericCellValue();
						// System.out.println("DOB :" + birthdate);
						sCellValueName = Double.toString(birthdate);
					}
					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
						boolean birthdate = row.getCell(c).getBooleanCellValue();
						// System.out.println("DOB :" + birthdate);
						sCellValueName = String.valueOf(birthdate);
					}
					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_ERROR) {
						sCellValueName = "NoValueCellTypeERROR";
					}
					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						sCellValueName = "NoValueCellTypeBLANK";
					}
					if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
						sCellValueName = row.getCell(c).getCellFormula();
						// System.out.println("DOB :" + sCellValueName);
					}
				} catch (Exception e) {

				}

				if (sCellValueName.equals(sTCidColumnLabel)) {
					// System.out.println("enterf1");
					sCoulumnHeadingNumber = c;
					sColumnHeadinBoolean = true;
				}

				String ssCoulumnHeadingNumber = Integer.toString(sCoulumnHeadingNumber);
				if (sColumnHeadinBoolean) {
					if (sCellValueName.equals(TcidsColumnValue)) {
						String ssColumnNumber = Integer.toString(c);
						if (ssCoulumnHeadingNumber.equals(ssColumnNumber)) {
							sRowNumber = r;
							sRowValueBoolean = true;
						}
					}
					if (sUpdateColumanName != null && sUpdateColumanName.trim().length() != 0) {
						if (sUpdateColumanName.equals(sCellValueName)) {
							// System.out.println("enterf34");
							sColumnValueNumber = c;
							sColumnValueNumberBoolean = true;
						}
					}
					if (sColumnHeadinBoolean && sRowValueBoolean && sColumnValueNumberBoolean) {
						break Rcount;
					}
				}
			}
		}
		int rowColumnArray[] = { sRowNumber, sColumnValueNumber };
		return rowColumnArray;

	}

	
	@SuppressWarnings("deprecation")
	public static HashMap<String, String> readExcel(String sTcValue) throws FileNotFoundException, IOException {
		//
		System.out.println(cFileNameWithPath + ":" + cSheetName + ":" + cTcID + ":" + sTcValue);
		int crValues[] = getRowColumnNumbers(cFileNameWithPath, cSheetName, cTcID, sTcValue, "");
		XSSFWorkbook myExcelBook = new XSSFWorkbook(cFileNameWithPath);
		XSSFSheet myExcelSheet = myExcelBook.getSheet(cSheetName);
		HashMap<String, String> hmap = new HashMap<String, String>();
		int columnCount = myExcelSheet.getRow(0).getPhysicalNumberOfCells();
		XSSFRow rowZero = null;
		String sCellValueName = null;
		for (int c = 0; c < columnCount; c++) {
			try {

				XSSFRow row = myExcelSheet.getRow(crValues[0]);
				rowZero = myExcelSheet.getRow(0);
				System.out.println("ROW:" + crValues[0] + "Column:" + c);
				System.out.println(row.getCell(c).getCellType());
				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					sCellValueName = row.getCell(c).getStringCellValue();
					System.out.println("CELL_TYPE_STRING : " + sCellValueName);
				}

				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					double birthdate = row.getCell(c).getNumericCellValue();
					System.out.println("CELL_TYPE_NUMERIC" + birthdate);
					sCellValueName = Double.toString(birthdate);
				}
				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					boolean birthdate = row.getCell(c).getBooleanCellValue();
					System.out.println("CELL_TYPE_BOOLEAN" + birthdate);
					sCellValueName = String.valueOf(birthdate);
				}
				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_ERROR) {
					System.out.println("CELL_TYPE_ERROR");
					sCellValueName = "NoValueCellTypeERROR";
				}
				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					System.out.println("CELL_TYPE_BLANK");
					sCellValueName = "NoValueCellTypeBLANK";
				}
				if (row.getCell(c).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {

					sCellValueName = row.getCell(c).getRawValue();
					// row.getCell(c).getCellFormula();
					System.out.println("DOB :" + sCellValueName);
				}
			} catch (Exception e) {

			}
			System.out.println(rowZero.getCell(c).getStringCellValue() + " " + sCellValueName);
			hmap.put(rowZero.getCell(c).getStringCellValue(), sCellValueName);
			sCellValueName = "";
		}
		return hmap;

	}

	public static void writeExcel(int RowNumber, String sUpdateColumnName, String sUpdateColumanValue,
			String FileNameWithPath, HashMap<String, String> XLTestData) throws FileNotFoundException, IOException {
		int crValues[] = getRowColumnNumbers(cFileNameWithPath, cSheetName, cTcID, cTcValue, sUpdateColumnName);
		FileInputStream fis = new FileInputStream(new File(cFileNameWithPath));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(cSheetName);
		// Cell cell = null;

		XSSFRow row1 = sheet.getRow(RowNumber); // createRow(RowNumber);
		// System.out.println(XLTestData.size());
		int LastColumn = XLTestData.size();
		XSSFCell r1c1 = row1.createCell(LastColumn + 1);
		r1c1.setCellValue(sUpdateColumanValue);
		fis.close();
		double x = Math.random();
		FileOutputStream fos = new FileOutputStream(new File(FileNameWithPath));
		workbook.write(fos);
		fos.close();

	}
	
	public static HashMap<String, String> readTestData(String filePath, String sheetName, String ID, String tcValue)
			throws FileNotFoundException, IOException {
		
		cFileNameWithPath = filePath;
		cSheetName = sheetName;
		cTcID = ID;
		return Excel_Reader.readExcel(tcValue);
	}
	
}
