package Sprint1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ContextMenu {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyAddRemoveElements(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Context Menu')]")).click();
		WebElement title = driver.findElement(By.xpath("//h3[contains(text(),'Context Menu')]"));
		WebElement ContextMenu = driver.findElement(By.xpath("//div[@id='hot-spot']"));
		ContextMenu.click();
		Assert.assertEquals(isAlertPresent(), false);
		Actions action = new Actions(driver);
		action.moveToElement(ContextMenu).perform();
		action.contextClick().perform();
		Assert.assertEquals(isAlertPresent(), true);
		String alertText = driver.switchTo().alert().getText();
		Assert.assertEquals(isAlertPresent(), isAlertPresent());
		driver.switchTo().alert().accept();
		Assert.assertEquals("You selected a context menu", alertText);
		driver.close();
	}
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   
	} 
}
