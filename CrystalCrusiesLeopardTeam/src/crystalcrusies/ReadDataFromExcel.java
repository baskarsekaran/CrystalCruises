package crystalcrusies;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadDataFromExcel {

	public static String readData(int col, int row) throws BiffException, IOException {

		Workbook wB = Workbook.getWorkbook(new File(
				"F:\\TestLeaf - ProjectWork\\TestData.xls"));

		// Go to sheet from wB
		Sheet sh = wB.getSheet("CrystalCrusies");

		// Go to cell from sh
		Cell cell = sh.getCell(col, row);

		// read the contents from cell
		String cont = cell.getContents();
		
		return cont;

	}
	
	public static int rowCount( ) throws BiffException, IOException{
		
		Workbook wB = Workbook.getWorkbook(new File(
				"F:\\TestLeaf - ProjectWork\\TestData.xls"));

		// Go to sheet from wB
		Sheet sh = wB.getSheet("CrystalCrusies");
		
		// Get the number of rows
		int rowCount =  sh.getRows();
		
		return rowCount;
		
	}

}
