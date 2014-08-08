package crystalcrusiestestngdemo;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Scenario3Chrome {
  @Test
  public void ScenarioThree() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException {
	  
	  WebDriver driver = null;
		
	  CrystalCrusiesWrapperMethods ccwm = new CrystalCrusiesWrapperMethods(driver);
	  
	  Thread.sleep(5000);
	  
	  String className = this.getClass().getSimpleName();
	  
	  CrystalCrusiesWrapperMethods.createWorkbook(className);
	  
	  for(int j=1;j<CrystalCrusiesWrapperMethods.rowCount();j++){
		 
	 		CrystalCrusiesWrapperMethods.getIteratorValue(j-1, className);
	 		
	 		ccwm.invokeApplication("Chrome");
	  		
	  		CrystalCrusiesRegistrationTestngClass ccrtc = new CrystalCrusiesRegistrationTestngClass();
	  		
	  		ccrtc.Registration(j,ccwm);
	  		
	  		CrystalCrusiesLoginTestngClass ccltc = new CrystalCrusiesLoginTestngClass();
	  		
	  		ccltc.Login(j,ccwm);
	  	}
  }
  @BeforeMethod
  public void beforeMethod() { 
	  System.out.println("Chrome execution start");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Chrome execution completed");
  }

}
