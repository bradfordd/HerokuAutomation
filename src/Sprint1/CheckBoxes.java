package Sprint1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckBoxes {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyBrokenImages(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Checkboxes')]")).click();
		WebElement title = driver.findElement(By.xpath(" //h3[contains(text(),'Checkboxes')]"));
		Assert.assertEquals(title.getText(), "Checkboxes");
		WebElement firstCheckbox = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[1]"));
		WebElement secondCheckbox = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[2]"));
		Assert.assertEquals(firstCheckbox.isSelected(), false);
		Assert.assertEquals(secondCheckbox.isSelected(), true);
		firstCheckbox.click();
		secondCheckbox.click();
		Assert.assertEquals(firstCheckbox.isSelected(), true);
		Assert.assertEquals(secondCheckbox.isSelected(), false);
		secondCheckbox.click();
		Assert.assertEquals(firstCheckbox.isSelected(), true);
		Assert.assertEquals(secondCheckbox.isSelected(), true);
		firstCheckbox.click();
		secondCheckbox.click();
		Assert.assertEquals(firstCheckbox.isSelected(), false);
		Assert.assertEquals(secondCheckbox.isSelected(), false);
		driver.close();
	}
}
