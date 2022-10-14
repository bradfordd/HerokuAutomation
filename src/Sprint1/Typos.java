package Sprint1;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Typos {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void checkForTypos(String driverPath) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Typos')]")).click();
		Thread.sleep(1000);
		WebElement titleElement = driver.findElement(By.xpath("//h3"));
		softAssert.assertEquals("Typos", titleElement.getText());
		System.out.println(driver.findElement(By.xpath("//p[1]")).getText());
		softAssert.assertEquals(driver.findElement(By.xpath("//p[1]")).getText(), "This example demonstrates a typo being introduced. It does it randomly on each page load.");
		softAssert.assertEquals(driver.findElement(By.xpath("//p[2]")).getText(), "Sometimes you'll see a typo, other times you won't.");
		driver.close();
		softAssert.assertAll();
	} 
} 