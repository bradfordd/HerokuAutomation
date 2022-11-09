package AmazonTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonItem {
	public String baseUrl = "https://www.amazon.com/ref=nav_logo";
	public WebDriver driver;
	@Test
	@Parameters({ "driverPath"})
	public void verifyAddRemoveElements(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		WebElement searchBar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		WebElement searchBarForm = driver.findElement(By.xpath("//form[@id='nav-search-bar-form']"));
		String searchBarFormClass = searchBarForm.getAttribute("class");
		try {
			Assert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute");
		}
		catch (AssertionError e) {
			System.out.print("Test Failed: Unexpected CSS class found for searchbarForm. \n Expected Class: nav-input nav-searchbar-attribute \n Found Class: " + searchBarFormClass);
		}
	
		searchBar.click();
		searchBarFormClass = searchBarForm.getAttribute("class");
		try {
			Assert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute nav-active");
		}
		catch (AssertionError e) {
			System.out.print("Test Failed: Unexpected CSS class found for searchbarForm after click.\nExpected Class: nav-input nav-searchbar-attribute nav-active\nFound Class: " + searchBarFormClass);
		}
		
		searchBar.sendKeys("1:24 Diecast");
		String searchBarText = searchBar.getAttribute("value");
		softAssert.assertTrue("Hello".equals("hello"), "Second soft assert failed");
		//SoftAssert.assertEquals(searchBarText, "1:24 Diecast", "Search Bar text matches text entered.");
		
//		String searchBarClass = searchBar.getAttribute("class");
//		try {
//			Assert.assertEquals(searchBarClass, "nav-input nav-progressive-attribute");
//		}
//		catch (AssertionError e) {
//			System.out.print("Unexpected CSS class found for searchbar. \n Expected Class: nav-input nav-progressive-attribute \n Found Class: " + searchBarClass);
//		}
//		searchBar.click();
		Thread.sleep(9999999);
		driver.close();
	}
}
