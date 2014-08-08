package crystalcrusies;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CreateWorkbook {



	/**
	 * @param args
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException {
		// TODO Auto-generated method stub
		
		String file ="F://xyz.xls" ;
		
		try {
			
	        WritableWorkbook workbookCopy = Workbook.createWorkbook(new File(file));
	        
	        for(int i=0;i<10;i++){
	        
			WritableSheet sheetToEdit = workbookCopy.createSheet("TC"+i, i);
			
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.getWrap();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setBackground(Colour.GREEN);
			
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setBackground(Colour.RED);
			
			Label lab = new Label(0,0,"Sno",cellFormat);
			Label lab1 = new Label(1,0, "Test Description",cellFormat);
			Label lab2 = new Label(2,0, "Test Step",cellFormat);
			Label lab3 = new Label(3,0, "Status",cellFormat);

			sheetToEdit.addCell(lab);
			sheetToEdit.addCell(lab1);
			sheetToEdit.addCell(lab2);
			sheetToEdit.addCell(lab3);
		
	        }
	   	 workbookCopy.write(); // You missed this line.
	     workbookCopy.close();	
	     
	     OpenAndWriteExcel owe = new OpenAndWriteExcel();
	     owe.writeExcel();
	       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
