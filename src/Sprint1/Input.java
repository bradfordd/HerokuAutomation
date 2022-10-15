package Sprint1;

import java.awt.AWTException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Input {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyInputBox(String driverPath) throws InterruptedException, AWTException {
		try {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			SoftAssert softAssert = new SoftAssert();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get(baseUrl);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[contains(text(),'Inputs')]")).click();
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Inputs')]")).getText(), "Inputs");
			WebElement inputBox = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]"));
			softAssert.assertEquals(inputBox.getAttribute("value"), "");
			inputBox.sendKeys(Keys.UP);
			softAssert.assertEquals(inputBox.getAttribute("value"), "1");
			inputBox.sendKeys(Keys.DOWN);
			inputBox.sendKeys(Keys.DOWN);
			inputBox.sendKeys(Keys.DOWN);
			softAssert.assertEquals(inputBox.getAttribute("value"), "-2");
			inputBox.sendKeys("5");
			softAssert.assertEquals(inputBox.getAttribute("value"), "-25");
			Thread.sleep(10000);
			driver.close();
			softAssert.assertAll();
		}
		catch(Exception e) {
			driver.close();
		}
	}
}
