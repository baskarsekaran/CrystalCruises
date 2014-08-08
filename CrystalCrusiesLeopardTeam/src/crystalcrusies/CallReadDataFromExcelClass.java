package crystalcrusies;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.read.biff.BiffException;

public class CallReadDataFromExcelClass {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver = null;
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Registration
		
		for(int i=1;i<=ReadDataFromExcel.rowCount();i++){
			
		driver.get("http://crystalcruises.com");	
		
		driver.findElement(By.linkText("REGISTERED USER SIGN IN")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.id("ctl00_MainContent_m_EmailAddress")).sendKeys(ReadDataFromExcel.readData(0, i));
		
		driver.findElement(By.id("ctl00_MainContent_m_Password")).sendKeys(ReadDataFromExcel.readData(1, i));
		
		driver.findElement(By.linkText("Login")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("SIGN OUT")).click();
		}

	}

}
