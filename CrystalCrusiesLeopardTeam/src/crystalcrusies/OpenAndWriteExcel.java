package crystalcrusies;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OpenAndWriteExcel {

	/**
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public void writeExcel() throws BiffException, IOException,
			RowsExceededException, WriteException {
		
		Workbook existingWorkbook = Workbook.getWorkbook(new File(
				"F://xyz.xls"));
		WritableWorkbook workbookCopy = Workbook.createWorkbook(new File(
				"F://xyz.xls"), existingWorkbook);
		
		WritableSheet[] s = workbookCopy.getSheets();
		
		System.out.println(s.length);
		
		for(int i=0;i<s.length;i++){

		WritableSheet sheetToEdit = workbookCopy.getSheet(i);
		//Add colors
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setBackground(Colour.GREEN);

		int j = sheetToEdit.getRows();
		Label l1 = new Label(0, j, "2");
		Label l2 = new Label(1, j, "Logout is successful");
		Label l3 = new Label(2, j, "Passed", cellFormat);

		sheetToEdit.addCell(l1);
		sheetToEdit.addCell(l2);
		sheetToEdit.addCell(l3);

		sheetToEdit.addCell(new Formula(3, j,
				"HYPERLINK(\"D:\\MySnapShot1.png\"," + "\"View Snap\")"));
	
		}	
		workbookCopy.write();
		workbookCopy.close();
	}

}
