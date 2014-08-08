package crystalcrusiestestngdemo;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class CrystalCrusiesRegistrationTestngClass {
  @Test
  public void Registration(int i, CrystalCrusiesWrapperMethods ccwm) throws IOException, BiffException, WriteException, InterruptedException {
	  
  		ccwm.clickByLinkText("REGISTERED USER SIGN IN");

  		ccwm.verifyTitle("Crystal Cruises ");

  		ccwm.clickByLinkText("Register now");

  		ccwm.verifyTitle("Crystal Cruises");

  		ccwm.setValueById("ctl00_MainContent_m_EmailAddress", CrystalCrusiesWrapperMethods.readData(1, i));

  		ccwm.setValueById("ctl00_MainContent_m_ConfirmationEmailAddress", CrystalCrusiesWrapperMethods.readData(2, i));

  		ccwm.setValueById("ctl00_MainContent_m_Password", CrystalCrusiesWrapperMethods.readData(3, i));

  		ccwm.setValueById("ctl00_MainContent_m_ConfirmationPassword", CrystalCrusiesWrapperMethods.readData(4, i));

  		ccwm.selectListByID("ctl00_MainContent_m_Salutation", CrystalCrusiesWrapperMethods.readData(5, i));

  		ccwm.setValueById("ctl00_MainContent_m_FirstName", CrystalCrusiesWrapperMethods.readData(6, i));

  		ccwm.setValueById("ctl00_MainContent_m_MiddleName", CrystalCrusiesWrapperMethods.readData(7, i));

  		ccwm.setValueById("ctl00_MainContent_m_LastName", CrystalCrusiesWrapperMethods.readData(8, i));

  		ccwm.setValueById("ctl00_MainContent_m_Address1", CrystalCrusiesWrapperMethods.readData(9, i));

  		ccwm.setValueById("ctl00_MainContent_m_Address2", CrystalCrusiesWrapperMethods.readData(10, i));

  		ccwm.setValueById("ctl00_MainContent_m_City", CrystalCrusiesWrapperMethods.readData(11, i));

  		ccwm.setValueById("ctl00_MainContent_m_State", CrystalCrusiesWrapperMethods.readData(12, i));

  		ccwm.setValueById("ctl00_MainContent_m_PostalCode", CrystalCrusiesWrapperMethods.readData(13, i));

  		ccwm.selectListByID("ctl00_MainContent_m_Country", CrystalCrusiesWrapperMethods.readData(14, i));

  		ccwm.setValueById("ctl00_MainContent_m_PhoneNumber", CrystalCrusiesWrapperMethods.readData(15, i));

  		ccwm.setValueById("ctl00_MainContent_m_FAXNumber", CrystalCrusiesWrapperMethods.readData(16, i));

  		ccwm.selectListByIndex("ctl00_MainContent_m_TravelAgentQuestion", CrystalCrusiesWrapperMethods.readData(17, i));

  		ccwm.selectCheckBox("ctl00_MainContent_m_Subscribe_eValues");

  		ccwm.selectCheckBox("ctl00_MainContent_m_Subscribe_Promotions");

  		ccwm.clickByLinkText("Submit");
	}	  	
		
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

}
