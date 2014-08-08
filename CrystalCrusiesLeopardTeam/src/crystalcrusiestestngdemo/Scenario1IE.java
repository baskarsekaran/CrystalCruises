package crystalcrusiestestngdemo;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Scenario1IE {
  @Test
  public void ScenarioOne() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException {
	  
	  WebDriver driver = null;
		
	  CrystalCrusiesWrapperMethods ccwm = new CrystalCrusiesWrapperMethods(driver);
	  
	  Thread.sleep(5000);
	  
	  String className = this.getClass().getSimpleName();
	  
	  CrystalCrusiesWrapperMethods.createWorkbook(className);
	  
	  for(int j=1;j<CrystalCrusiesWrapperMethods.rowCount()-1;j++){
		  
	 		CrystalCrusiesWrapperMethods.getIteratorValue(j-1,className);
	 		
	 		ccwm.invokeApplication("IE");
	  		
	  		CrystalCrusiesRegistrationTestngClass ccrtc = new CrystalCrusiesRegistrationTestngClass();
	  		
	  		ccrtc.Registration(j,ccwm);
	  		
	  		CrystalCrusiesLoginTestngClass ccltc = new CrystalCrusiesLoginTestngClass();
	  		
	  		ccltc.Login(j,ccwm);
	  	}
  }
   
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("IE browser execution start");
  }

  @AfterMethod
  public void afterMethod() throws RowsExceededException, BiffException, WriteException, IOException {
	//Send Email
	  MailWrapperMethods mwm = new MailWrapperMethods(); 
	  mwm.MailParameter();
	  mwm.SendMail();
	  System.out.println("IE browser execution completed, Status mail sent successfully");
  }
}
