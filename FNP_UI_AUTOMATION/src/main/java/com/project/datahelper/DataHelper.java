package com.project.datahelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.project.utils.CommonTestConstants;

public class DataHelper {

	 
	 public static XSSFSheet xssfSheet;
	 
	 private static XSSFWorkbook ExcelWBook;
	 private static Cell cell;
	
	public static Object[][] getExcelData(String filePath, String sheetName){
		
		String[][] array =null;
		try {
		FileInputStream excelFile=new FileInputStream(filePath);
			ExcelWBook =new XSSFWorkbook(excelFile);
			xssfSheet= ExcelWBook.getSheet(sheetName);
			int startRow=1;
			int startCol=1;
			
			int ci, cj;
			int totalRows=xssfSheet.getLastRowNum();
			
			int totalColumn=2;
			array=new String[totalRows][totalColumn];
			
			ci=0;
			
			for(int i=startRow;i<=totalRows;i++, ci++) {
			    cj=0;
				for(int j=startCol;j<=totalColumn;j++, cj++) {
					array[ci][cj]=getCellData(i,j);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
		
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			cell = xssfSheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);

		}
	}
	
	 @DataProvider(name = "UserDetails")
	  public static Object[][] credentials() {
		  Object[][] testObjArray = getExcelData(System.getProperty("user.dir")+CommonTestConstants.excelPath,"Sheet1");
		  return (testObjArray);
	 
	  }

}
