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

public class AddRemoveElements {
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
		driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]")).click();
		driver.findElement(By.xpath("//h3[contains(text(),'Add/Remove Elements')]"));
		WebElement addElementButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
		List<WebElement> elementsOnPage = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
		Assert.assertEquals(0, elementsOnPage.size());
		addElementButton.click();
		addElementButton.click();
		elementsOnPage = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
		Assert.assertEquals(2, elementsOnPage.size());
		elementsOnPage.get(1).click();
		elementsOnPage = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
		Assert.assertEquals(1, elementsOnPage.size());
		elementsOnPage.get(0).click();
		elementsOnPage = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
		Assert.assertEquals(0, elementsOnPage.size());
		driver.close();
	}
}
