package crystalcrusiestestngdemo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class CrystalCrusiesWrapperMethods {

	WebDriver driver;
	public static String date1 = null;
	public static WritableWorkbook workbookCopy = null;
	WritableWorkbook workbookCopy1 = null;
	public static WritableSheet sheetToEdit = null;
	WritableSheet sheetToEdit1 = null;
	public static String date2;
	public static String date3;
	public static int k;
	public static int j=1;
	
	public String URL;
	public static String ReportPath;
	public static String TestDataPath;
	public String ScreenshotPath;
	public String IEDriverPath;
	public String ChromeDriverPath;
	public String SauceLabFlag;
	public String GridFlag;
	public String Browser;
	public static String testName;
	
	
	public CrystalCrusiesWrapperMethods(WebDriver driver) throws BiffException, IOException, RowsExceededException, WriteException{	
		this.driver = driver;		
		Workbook wb = Workbook.getWorkbook(new File("F:\\TestLeaf - ProjectWork\\TestData.xls"));
		Sheet sh = wb.getSheet("Configuration");
		int conRow = sh.getRows();
		for(int m=1;m<conRow;m++){
		Cell cell = sh.getCell(0, m);
		String name = cell.getContents();
		if(name.equalsIgnoreCase("URL")){
			URL = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("ReportPath")){
			ReportPath = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("TestDataPath")){
			TestDataPath = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("ScreenshotPath")){
			ScreenshotPath = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("IEDriverPath")){
			IEDriverPath = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("ChromeDriverPath")){
			ChromeDriverPath = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("SauceLabFlag")){
			SauceLabFlag = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("GridFlag")){
			GridFlag = sh.getCell(1, m).getContents();
		}
		/*else if(name.equalsIgnoreCase("Browser")){
			Browser = sh.getCell(1, m).getContents();
		}*/
	}
}
	
	public void takeSnap() throws IOException{
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		int b = CrystalCrusiesWrapperMethods.k;
		int c = b+1;
		FileUtils.copyFile(src, new File(ScreenshotPath + "CC_"+"TC"+c+ "_" + Browser +"_"+ date2 +".jpg"));
	}
	
	public void setValueById(String idtxt, String val) throws IOException, RowsExceededException, WriteException, BiffException{
		try {
			driver.findElement(By.id(idtxt)).clear();
			driver.findElement(By.id(idtxt)).sendKeys(val);
			getExistingWorkbook();
			writeValues("Set the value" + " " + "'" + val + "'" + " ", "value " + "'" + val + "'" + " " + "is setted", "Pass");
			
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Set the value" + " " + "'" + val + "'" + " ", "value " + "'" + val + "'" + "is not setted. Element is not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Set the value" + " " + "'" + val + "'" + " ", "value " + "'" + val + "'" + "is not setted. Webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Set the value" + " " + "'" + val + "'" + " ", "value " + "'" + val + "'" + "is not setted. Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}
	
	public void setValueByName(String name,String val1) throws IOException{
		try {
			driver.findElement(By.name(name)).clear();
			driver.findElement(By.name(name)).sendKeys(val1);
			
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
	}
	
	public void closeBrowser(){
	try{
		driver.close();
	} catch(WebDriverException e1){
		System.out.println("The webdriver is having issues");
	}catch(Exception e2){
		System.out.println("Unknown error happened");
	}
	}

	public void invokeApplication(String Browser) throws InterruptedException, RowsExceededException, WriteException, IOException, BiffException
	{
		try {
				this.Browser = Browser;
				if(SauceLabFlag.equalsIgnoreCase("Yes") || GridFlag.equalsIgnoreCase("Yes")){
					gridORSaucelabExecution();
				}
				else if(SauceLabFlag.equalsIgnoreCase("No")){
				
					if(Browser.equalsIgnoreCase("FireFox")){
						driver = new FirefoxDriver();
					}
					else if(Browser.equalsIgnoreCase("IE")){
						System.setProperty("webdriver.ie.driver", IEDriverPath);
						driver = new InternetExplorerDriver();
					}
					else if(Browser.equalsIgnoreCase("Chrome")){
						System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
						driver = new ChromeDriver();
					}
				}		
			driver.get(URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			getExistingWorkbook();
			writeValues("Launch the URL" +" "+ "'" + URL + "'" + " " ,  "URL " + " " + "'" + URL + "'" + " " + "is launched", "Pass");
			Thread.sleep(5000);
	
		} catch (WebDriverException e) {
			System.out.println("Webdriver is having issues" +e);
			getExistingWorkbook();
			writeValues("Launch the URL" +" "+ "'" + URL + "'" + " ", "The URL" + "'" + URL + "'" + "is not launched. Webdriver Exception occur", "Fail");
		}finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}

	public void clickByLinkText(String linkText) throws IOException, RowsExceededException, WriteException, BiffException
	
	{
		try {
			driver.findElement(By.linkText(linkText)).click();
			getExistingWorkbook();
			writeValues("Click the link" +" " + "'" + linkText + "'" +" ", "Link " + "'" + linkText + "'" + " " + "is clicked", "Pass");
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Click the link" +" " + "'" + linkText + "'" +" ", "The link" + "'" + linkText + "'" + "is not clicked.Element is not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Click the link" +" " + "'" + linkText + "'" +" ", "The link" + "'" + linkText + "'" + "is not clicked.Webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Click the link" +" " + "'" + linkText + "'" +" ", "The link" + "'" + linkText + "'" + "is not clicked.Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}
	
	public void clickObjectById(String objectId) throws IOException
	{
		try {
			driver.findElement(By.id(objectId)).click();			
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
	}
	
	public void clickObjectByClassName(String objectClassName) throws IOException{
		try {
			List<WebElement> list = driver.findElements(By.className(objectClassName));	
			
			String[] dob = {"July","15","2012","What was your first phone number"};
			
			for(int i=0;i<list.size();i++){
				list.get(i).click();
				driver.findElement(By.linkText(dob[i])).click();
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
		
	}
	
	public void clickObjectByName(String objectName) throws IOException{
		try {
			driver.findElement(By.name(objectName)).click();			
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
		
	}

	public void verifyTitle(String expres) throws IOException, RowsExceededException, WriteException, BiffException{
		
		try {
			if(expres.equals(driver.getTitle()))
			{
				getExistingWorkbook();
				writeValues("Verify title" +" " + "'" + expres + "'" +" ", "Title " + "'" + driver.getTitle() + "'" + " " + "is verified", "Pass");
			}
			else{
				getExistingWorkbook();
				writeValues("Verify title" +" " + "'" + expres + "'" +" ", "Title " + "'" + driver.getTitle() + "'" + " " + "is not verified", "Fail");
			}
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Verify title" +" " + "'" + expres + "'" +" ", "Title " + "'" + driver.getTitle() + "'" + " " + "is not verified. Element not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Verify title" +" " + "'" + expres + "'" +" ", "Title " + "'" + driver.getTitle() + "'" + " " + "is not verified. Webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Verify title" +" " + "'" + expres + "'" +" ", "Title " + "'" + driver.getTitle() + "'" + " " + "is not verified. Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
		
	}
	
	public void selectListByID(String list, String val) throws IOException, RowsExceededException, WriteException, BiffException
	{
		try {

			WebElement countryDropdown = driver.findElement(By.id(list));
			Select dropdown = new Select(countryDropdown);
			dropdown.selectByVisibleText(val);
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is selected", "Pass");

		}  catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Element not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}
	
	public void selectListByIndex(String list, String val) throws IOException, RowsExceededException, WriteException, BiffException
	{
		try {
			WebElement countryDropdown = driver.findElement(By.id(list));
			Select dropdown = new Select(countryDropdown);
			dropdown.selectByIndex(Integer.parseInt(val));
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is selected", "Pass");
			
		}  catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Element not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}
	
	public void selectListByName(String list1, String val) throws IOException, RowsExceededException, WriteException, BiffException
	{
		try {
			
			WebElement countryDropdown = driver.findElement(By.name(list1));
			Select dropdown = new Select(countryDropdown);
			dropdown.selectByVisibleText(val);
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is selected", "Pass");
			
		}  catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Element not found exception occur", "Fail");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. webdriver exception occur", "Fail");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
			getExistingWorkbook();
			writeValues("Select the value" +" " + "'" + val + "'" +" ", "Value " + "'" + val + "'" + " " + "is not selected. Unknown exception occur", "Fail");
		}
		finally{
			takeSnap();
			closeExistingWorkbook();
		}
	}
	
	public void verifyCurrentUrl() throws IOException{
		try {
			System.out.println(driver.getCurrentUrl());
		 } catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
	}
	
	public void verifyText(String tag,String vtext) throws IOException{
		try {
			if(vtext.equalsIgnoreCase(driver.findElement(By.tagName(tag)).getText())){
				System.out.println("Login succesffully and home is displayed");
			}
			else{
				System.out.println("Login was not successful");
			}
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}
	}
	
	public void selectRadioGroup(String radioID) throws IOException{
		try {
			if(driver.findElement(By.id(radioID)).isSelected() == true){
				driver.findElement(By.id(radioID)).click();
			}
			else{
				driver.findElement(By.id(radioID)).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}	
	}
	
	public void selectCheckBox(String checkID) throws IOException{
		try {
			if(driver.findElement(By.id(checkID)).isSelected() == false){
				driver.findElement(By.id(checkID)).click();
			}
			else{
				System.out.println("The check is not selected");
			}
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
		} catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}	
	}
	
	public void killAllBrowsers(){
		try {
			driver.quit();
		} catch(Exception e2){
			System.out.println("Unknown error happened");
		}
	}

	public void switchToFrame(String classname) throws IOException{
		try {
			driver.switchTo().frame(driver.findElement(By.className(classname)));
		}catch(NoSuchFrameException e){
			System.out.println("The frame is not found"); 
		}
		catch(WebDriverException e1){
			System.out.println("The webdriver is having issues");
		}catch(Exception e2){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}	
	}

	public void getAlertText() throws IOException{
		try {
			Alert alert = driver.switchTo().alert();
			Thread.sleep(10000);
			System.out.println("Alert text is:" + alert.getText());
			alert.accept();
		} catch(NoAlertPresentException e){
			System.out.println("The alert is not found"); 
		}catch(UnhandledAlertException e1){
			System.out.println("Alert is not handled");
		}catch(WebDriverException e2){
			System.out.println("The webdriver is having issues");
		}catch(Exception e3){
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}	
	}

	public void switchToWindow(String title) throws IOException{
	
		try {
			Set<String> window = driver.getWindowHandles();
			
			for(String win:window){
				driver.switchTo().window(win);
				if(title.equalsIgnoreCase(driver.getTitle())){
					break;
				}		
			}
		} catch (Exception e) {
			System.out.println("Unknown error happened");
		}	
		finally{
			takeSnap();
		}	
	}
	
	public void enterTab() throws IOException{
		try {
			Actions builder = new Actions(driver);
			builder.sendKeys(Keys.TAB).build().perform();
		} catch (Exception e) {
			System.out.println("Unknown error happened");
		}
		finally{
			takeSnap();
		}	
	}
	
	public static void createWorkbook(String testName) throws IOException{
		try {
			date3 = CrystalCrusiesWrapperMethods.datetime();
			workbookCopy = Workbook.createWorkbook(new File(ReportPath + "_" + testName + "_" + date3 + ".xls"));
			CrystalCrusiesWrapperMethods.createWorksheet();
		} catch (Exception e) {
			System.out.println("Unknown error happened");
		}
		
	}
	
	public static void createWorksheet() throws IOException, RowsExceededException, WriteException{
		
		try {			
			
			for(int s=1;s<CrystalCrusiesWrapperMethods.rowCount();s++){
				
			sheetToEdit = workbookCopy.createSheet("TC"+s,s);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.getWrap();
			
			sheetToEdit.setColumnView(0, 10);
			Label lab = new Label(0,0,"Step No",cellFormat);
			sheetToEdit.addCell(lab);
			
			sheetToEdit.setColumnView(1, 50);
			Label lab1 = new Label(1,0, "Expected Result",cellFormat);
			sheetToEdit.addCell(lab1);
			
			sheetToEdit.setColumnView(2, 50);
			Label lab2 = new Label(2,0, "Actual Result",cellFormat);
			sheetToEdit.addCell(lab2);
			
			sheetToEdit.setColumnView(3, 15);
			Label lab3 = new Label(3,0, "Status",cellFormat);
			sheetToEdit.addCell(lab3);
			
			}
		 	CrystalCrusiesWrapperMethods.closeWorkbook();
			
		} catch (Exception e) {
			System.out.println("unknown error happened");	
		}
	}
	
	public static void getIteratorValue(int m, String className){
		k=m;
		j=1;
		testName = className;
	}
	
	public void getExistingWorkbook() throws BiffException, IOException
	{
		try {
			Workbook existingWorkbook = Workbook.getWorkbook(new File(ReportPath + "_" + CrystalCrusiesWrapperMethods.testName + "_" + date3 + ".xls"));
			workbookCopy1 = Workbook.createWorkbook(new File(ReportPath + "_" + CrystalCrusiesWrapperMethods.testName + "_" + date3 + ".xls"), existingWorkbook);
			sheetToEdit1 = workbookCopy1.getSheet(CrystalCrusiesWrapperMethods.k);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Sheet index exceed the actual index value");
		}
	}

	public void writeValues(String testdes, String teststep, String status) throws RowsExceededException, WriteException, BiffException, IOException{
				
		try {
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.getWrap();
			
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setBackground(Colour.GREEN);
			
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setBackground(Colour.RED);
					
			Label lab4 = new Label(0, j, Integer.toString(j),cellFormat);
			sheetToEdit1.setColumnView(0, 10);
			sheetToEdit1.addCell(lab4);
			
			Label lab5 = new Label(1, j, testdes,cellFormat);
			sheetToEdit1.setColumnView(1, 50);
			sheetToEdit1.addCell(lab5);
			
			Label lab6 = new Label(2, j, teststep,cellFormat);
			sheetToEdit1.setColumnView(2, 50);
			sheetToEdit1.addCell(lab6);
			
			if(status.equalsIgnoreCase("pass")){
			Label lab7 = new Label(3, j, status,cellFormat1);
			sheetToEdit1.setColumnView(3, 15);
			sheetToEdit1.addCell(lab7);
			}
			else if(status.equalsIgnoreCase("fail")){
			Label lab7 = new Label(3, j, status,cellFormat2);
			sheetToEdit1.setColumnView(3, 15);
			sheetToEdit1.addCell(lab7);
			}
			date2 = CrystalCrusiesWrapperMethods.datetime();
			sheetToEdit1.setColumnView(4, 15);
			sheetToEdit1.addCell(new Formula(4, j,
					"HYPERLINK(\"F:\\SeleniumScreenShots\\CC_"+date2+".jpg\"," + "\"View Snap\")"));
			j++;
		} catch (Exception e) {
			System.out.println("unknown error happened");
		}
		
	}
	
	public void closeExistingWorkbook() throws IOException, WriteException{
		try {
			workbookCopy1.write();	
			workbookCopy1.close();
		} catch (Exception e) {
			System.out.println("unknown error happened");
		}
	}

	public static void closeWorkbook() throws IOException, WriteException{
		try {
			workbookCopy.write();	
			workbookCopy.close();
		} catch (Exception e) {
			System.out.println("unknown error happened");
		}
	}
	
	public static String readData(int col, int row) throws BiffException, IOException {

		String cont = null;
		try {
			
			Workbook wb = Workbook.getWorkbook(new File(TestDataPath));
			Sheet sh = wb.getSheet("CrystalCruises");
			Cell cell = sh.getCell(col, row);
			cont = cell.getContents();
			
		} catch (Exception e) {
			System.out.println("unknown error happened");
		}
		
		return cont;

	}
	
	public static int rowCount() throws BiffException, IOException{
		
		int rowCount = 0;
		
		try {
			Workbook wb = Workbook.getWorkbook(new File(TestDataPath));
			Sheet sh = wb.getSheet("CrystalCruises");
			rowCount = sh.getRows();
			
		} catch (Exception e) {
			System.out.println("unknown error happened");
		}
		
		return rowCount;
	}	

	public static String datetime(){
	
	String date1 = null;
	try {
		DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
		Calendar calobj = Calendar.getInstance();
		date1 = df.format(calobj.getTime());
	} catch (Exception e) {
		System.out.println("unknown error happened");
	}
    
    return date1;
}
	
	public void gridORSaucelabExecution() throws MalformedURLException{
		
		try {
			if(Browser.equalsIgnoreCase("FireFox")){
				DesiredCapabilities dC = new DesiredCapabilities();
				dC.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
				dC.setPlatform(Platform.WINDOWS);
					if(GridFlag.equalsIgnoreCase("Yes")){
						System.out.println("Selenium grid execution start");
						driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), dC);
					}
					else
					{
						System.out.println("Selenium saucelab execution start");
						URL url = new URL("http://baskar10576:6fc351c8-65e5-4f59-8cdd-405129653695@ondemand.saucelabs.com:80/wd/hub");
						driver = new RemoteWebDriver(url,dC);
						
					}
			}
			else if(Browser.equalsIgnoreCase("IE")){
				DesiredCapabilities dC = new DesiredCapabilities();
				dC.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				dC.setPlatform(Platform.WINDOWS);
					if(GridFlag.equalsIgnoreCase("Yes")){
						System.out.println("Selenium grid execution start");
						driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), dC);
					}
					else
					{
						System.out.println("Selenium saucelab execution start");
						URL url = new URL("http://baskar10576:6fc351c8-65e5-4f59-8cdd-405129653695@ondemand.saucelabs.com:80/wd/hub");
						driver = new RemoteWebDriver(url,dC);

					}
			}
			else if(Browser.equalsIgnoreCase("Chrome")){
				DesiredCapabilities dC = new DesiredCapabilities();
				dC.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
				dC.setPlatform(Platform.WINDOWS);
					if(GridFlag.equalsIgnoreCase("Yes")){
						System.out.println("Selenium grid execution start");
						driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), dC);
					}
					else
					{
						System.out.println("Selenium saucelab execution start");
						URL url = new URL("http://baskar10576:6fc351c8-65e5-4f59-8cdd-405129653695@ondemand.saucelabs.com:80/wd/hub");
						driver = new RemoteWebDriver(url,dC);
					}
			}
		} catch (Exception e) {
			System.out.println("Unkonwn error happened");
		}
		
	}	
}
