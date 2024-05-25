package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	public static final int Implicit_wait =30;
	public static final int PageLoad_Time =10;
	public static String genrateTimestamp() {
		Date date = new Date();
		return "shingadee.shwetha"+date.toString().replace(" ", "_").replace(":", "_")+"@gmail.com"; 
		
	}
//	public static void main(String[] args) {
//		
//		Arrays.asList(readExcel("sheet1")).stream().forEach(data->{
//			for(int c=0;c<data.length;c++)
//			System.out.println(data[c]);
//			
//		System.out.println();
//		});	
//	}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		 File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         String destinationPath = System.getProperty("user.dir") + "\\src\\main\\java\\qa\\config\\" + testName + ".png";
         try {
			FileHandler.copy(screenshot, new File(destinationPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	return destinationPath;
	}
public static Object[][] readExcel(String sheetName){
	 XSSFWorkbook workbook = null;
	File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\Book1.xlsx");
	try {
		FileInputStream fis = new FileInputStream(f);
		 workbook = new XSSFWorkbook(fis);
				}catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	XSSFSheet sheet = workbook.getSheet(sheetName);
	 int rows = sheet.getLastRowNum();
	 System.out.println(rows);
	 int cols = sheet.getRow(0).getLastCellNum();
	 Object[][] data = new Object[rows][cols];
	 System.out.println(rows + cols);
	 for(int r=0;r<rows;r++) {
		 XSSFRow rowvalue = sheet.getRow(r+1);
		 for(int c=0;c<cols;c++) {
			 XSSFCell cellvalue = rowvalue.getCell(c);
			switch (cellvalue.getCellType()) {
			case NUMERIC:
				data[r][c]= Integer.toString((int)cellvalue.getNumericCellValue());
				break;
			case BOOLEAN:
				data[r][c]= String.valueOf(cellvalue.getBooleanCellValue());
				break;
			case STRING:
				data[r][c]= String.valueOf(cellvalue.getStringCellValue());	
				break;
	
			 }
			 
		 }
	 }
	return data;
	
}
}
