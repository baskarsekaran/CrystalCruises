package crystalcrusiestestng;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import crystalcrusies.ReadDataFromExcel;

public class ReadExcel {
  @Test
  public void f() throws BiffException, IOException {
	  
	  System.out.println(ReadDataFromExcel.rowCount());
	  
	  for(int i=1;i<ReadDataFromExcel.rowCount();){
		  System.out.println(ReadDataFromExcel.readData(0, i));
		  System.out.println(ReadDataFromExcel.readData(1, i));
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
