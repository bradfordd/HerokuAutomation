package Sprint1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BasicAuth {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyBasicAuthCorrectCredentials(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]")).click();
		String URL = "https://" + "admin" +":" + "admin" +"@"+ "the-internet.herokuapp.com/basic_auth";
		driver.get(URL);
		driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
		driver.close();
	}
	@Test
	@Parameters({ "driverPath"})
	public void verifyBasicAuthIncorrectCredentials(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]")).click();
		String URL = "https://" + "NotTheUsername" +":" + "NotThePassword" +"@"+ "the-internet.herokuapp.com/basic_auth";
		driver.get(URL);
		List<WebElement> elementsOnPage = driver.findElements(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
		Assert.assertEquals(0, elementsOnPage.size());
		driver.close();
	}
}
