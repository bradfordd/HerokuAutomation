package Sprint1;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InfiniteScroll {
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
			driver.findElement(By.xpath("//a[contains(text(),'Infinite Scroll')]")).click();
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			for (int i = 0; i < 10; i++) {
				js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
				Thread.sleep(1000);
			}
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//h3[contains(text(),'Infinite Scroll')]"));
			driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
			WebElement firstParagraph = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
			//System.out.println(firstParagraph.getText());
			Thread.sleep(1000);
			driver.close();
		}
		catch(Exception e) {
			driver.close();
			Assert.fail();
		}
		//driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
	}
}
