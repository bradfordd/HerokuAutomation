package Sprint1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.reactivex.functions.Action;

public class DragDrop {
	public String baseUrl = "https://the-internet.herokuapp.com/";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyAddRemoveElements(String driverPath) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]")).click();
		driver.findElement(By.xpath("//h3[contains(text(),'Drag and Drop')]"));
		Robot robot = new Robot();
		WebElement leftBlock = driver.findElement(By.xpath("//div[@id='column-a']"));
		Point leftBlockLocation = leftBlock.getLocation();
		WebElement rightBlock = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
		Point rightBlockLocation = rightBlock.getLocation();
		robot.mouseMove(leftBlockLocation.getX(), leftBlockLocation.getY() + 140);
		Thread.sleep(1000);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		Thread.sleep(1000);
		robot.mouseMove(rightBlockLocation.getX() + 20, rightBlockLocation.getY() + 140);
		Thread.sleep(1000);
		robot.mouseMove(rightBlockLocation.getX() + 50, rightBlockLocation.getY() + 140);
		Thread.sleep(1000);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(2000);
		String leftBlockText = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/header[1]")).getText();
		Assert.assertEquals("B", leftBlockText);
		String rightBlockText = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/header[1]")).getText();
		Assert.assertEquals("A", rightBlockText);
		driver.close();
		
	}
}
