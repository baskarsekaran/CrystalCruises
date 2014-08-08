package crystalcrusiestestng;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import crystalcrusies.ReadDataFromExcel;

public class ReadExcel1 {
  @Test
  public void f() throws BiffException, IOException {
	  
	  System.out.println(ReadDataFromExcel.rowCount());
	  
	  for(int j=1;j<ReadDataFromExcel.rowCount();){
		  System.out.println(ReadDataFromExcel.readData(2, j));
		  System.out.println(ReadDataFromExcel.readData(3, j));
		  break;
	  }
  }	  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

}
