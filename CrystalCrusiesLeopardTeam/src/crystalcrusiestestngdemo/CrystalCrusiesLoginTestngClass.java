package crystalcrusiestestngdemo;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class CrystalCrusiesLoginTestngClass {
  @Test
  public void Login(int i, CrystalCrusiesWrapperMethods ccwm) throws IOException, BiffException, RowsExceededException, WriteException, InterruptedException {
	   			
	  	ccwm.clickByLinkText("REGISTERED USER SIGN IN");
	  	
	  	ccwm.setValueById("ctl00_MainContent_m_EmailAddress", CrystalCrusiesWrapperMethods.readData(18, i));
		
		ccwm.setValueById("ctl00_MainContent_m_Password",CrystalCrusiesWrapperMethods.readData(19, i));
		
		ccwm.clickByLinkText("Login");
		
		ccwm.verifyTitle("Crystal Cruises");
		
		ccwm.clickByLinkText("SIGN OUT");
		
		ccwm.killAllBrowsers();
		
	}	
  @BeforeMethod
  public void beforeMethod() throws BiffException, IOException {
	  System.out.println("Execution start" + " " + "Total no.of iteration :" + CrystalCrusiesWrapperMethods.rowCount() );
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Testng execution completed");
	  
  }

}
