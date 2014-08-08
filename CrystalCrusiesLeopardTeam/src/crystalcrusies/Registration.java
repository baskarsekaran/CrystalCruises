package crystalcrusies;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class Registration {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = null;
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://crystalcruises.com");
		
		//Registration
		
		driver.findElement(By.linkText("REGISTERED USER SIGN IN")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("Register now")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.id("ctl00_MainContent_m_EmailAddress")).sendKeys("baskar10576@gmail.com");
		
		driver.findElement(By.id("ctl00_MainContent_m_ConfirmationEmailAddress")).sendKeys("baskar10576@gmail.com");
		
		driver.findElement(By.id("ctl00_MainContent_m_Password")).sendKeys("baskar10576");
		
		driver.findElement(By.id("ctl00_MainContent_m_ConfirmationPassword")).sendKeys("baskar10576");
		
		WebElement salutation = driver.findElement(By.id("ctl00_MainContent_m_Salutation"));
		
		Select sal = new Select(salutation);
		
		sal.selectByVisibleText("Mr.");
		
		driver.findElement(By.id("ctl00_MainContent_m_FirstName")).sendKeys("baskar");
		
		driver.findElement(By.id("ctl00_MainContent_m_MiddleName")).sendKeys("bass");
		
		driver.findElement(By.id("ctl00_MainContent_m_LastName")).sendKeys("Sekaran");
		
		driver.findElement(By.id("ctl00_MainContent_m_Address1")).sendKeys("No.13, Kasthuribai 2nd Street");
		
		driver.findElement(By.id("ctl00_MainContent_m_Address2")).sendKeys("Srinivasa Nagar, New Perungalathur");
		
		driver.findElement(By.id("ctl00_MainContent_m_City")).sendKeys("Chennai");
		
		driver.findElement(By.id("ctl00_MainContent_m_State")).sendKeys("TamilNadu");
		
		driver.findElement(By.id("ctl00_MainContent_m_PostalCode")).sendKeys("600063");
				
		WebElement country = driver.findElement(By.id("ctl00_MainContent_m_Country"));
		
		Select con = new Select(country);
		
		con.selectByVisibleText("India");
		
		driver.findElement(By.id("ctl00_MainContent_m_PhoneNumber")).sendKeys("9080083848");
		
		driver.findElement(By.id("ctl00_MainContent_m_FAXNumber")).sendKeys("04423305501");
		
		WebElement travalAgent = driver.findElement(By.id("ctl00_MainContent_m_TravelAgentQuestion"));
		
		Select tag = new Select(travalAgent);
		
		tag.selectByIndex(2);
		
		driver.findElement(By.id("ctl00_MainContent_m_Subscribe_eValues")).click();
		
		driver.findElement(By.id("ctl00_MainContent_m_Subscribe_Promotions")).click();
		
		driver.findElement(By.linkText("Submit")).click();
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(5000);
		
		driver.findElement(By.linkText("Submit")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("GO")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.id("ctl00_MainContent_m_Key")).sendKeys("1771402-D51284");
		
		driver.findElement(By.linkText("Submit")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("Continue")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("SIGN OUT")).click();
		
		//Login
		
		driver.findElement(By.id("ctl00_MainContent_m_EmailAddress")).sendKeys("baskar10576@gmail.com");
		
		driver.findElement(By.id("ctl00_MainContent_m_Password")).sendKeys("baskar10576");
		
		driver.findElement(By.linkText("Login")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.linkText("SIGN OUT")).click();
		
		
		
	}

}
