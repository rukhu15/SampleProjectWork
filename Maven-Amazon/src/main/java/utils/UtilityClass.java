package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class UtilityClass {
	
	public static void captureScreenshot(WebDriver driver, int TestID) throws IOException {
		File capturescreen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date dateRef = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("DDD-MM-YYYY hh mm ss");
		sdf.format(dateRef);
		File dst=new File("test-output/testScreenshots\\Test-"+TestID+sdf.format(dateRef)+".png");
		FileHandler.copy(capturescreen, dst);
	}
	
	public static String getDataFromExelSheet(String sheet, int row, int column) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("src//test/resources//testData//AutomationTestData.xlsx");
		Cell expectedCell=WorkbookFactory.create(file).getSheet(sheet).getRow(row).getCell(column);
		
		String cellValue="";
		
		try {
			cellValue=expectedCell.getStringCellValue();
		}catch(IllegalStateException ise) {
			double numCellValue=expectedCell.getNumericCellValue();
			//cellValue=NumCellValue+"";
			cellValue=Double.toString(numCellValue);
			
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			npe.getMessage();
		}
		return cellValue;
	}
}
