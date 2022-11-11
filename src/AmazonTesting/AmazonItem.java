package AmazonTesting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonItem {
	public String baseUrl = "https://www.amazon.com/ref=nav_logo";
	public WebDriver driver;
	@Parameters({ "driverPath"})
	public void verifyHover(String driverPath) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
		WebElement accountMenu = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
		Actions action = new Actions(driver);
		action.moveToElement(accountMenu).perform();
	}
	@Parameters({ "driverPath"})
	public void verifyScrolling(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 50)", "");
        for (int i = 0; i < 10; i++) {
        	js.executeScript("window.scrollBy(0, 50)", "");
        }
        Thread.sleep(99999);
	}
	@Parameters({ "driverPath"})
	@Test
	public void averifyUserReviewsFunctionality(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
		Assert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute nav-active", "Test Failed: Unexpected CSS class found for searchbarForm after click.\nExpected Class: nav-input nav-searchbar-attribute nav-active\nFound Class: " + searchBarFormClass);
		searchBar.sendKeys("1:24 Diecast");
		String searchBarText = searchBar.getAttribute("value");
		softAssert.assertTrue(searchBarText.equals("1:24 Diecast"));
		if (!searchBarText.equals("1:24 Diecast")) {
			System.out.print("TEST FAILED: \n Unexpected text in search bar. \n Expected: 1:24 Diecast \n Actual: " + searchBarText);
		}
		searchBar.sendKeys(Keys.ENTER);
		WebElement ratingBar = driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/span[1]/span[1]/a[1]/i[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ratingBar);
        js.executeScript("window.scrollBy(0, 4)", "");
		Actions action = new Actions(driver);
		//action.moveToElement(ratingBar).build().perform();
		ratingBar.click();
		//5-star ratings xpath
		//body[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/a[1]
		WebElement ratingDropDownMenu = driver.findElement(By.xpath("//div[@id='a-popover-2']"));
		List<WebElement> anchorTags = ratingDropDownMenu.findElements(By.tagName("a"));
		anchorTags.get(0).click();
		//fiveStarRating.click();
		Thread.sleep(99999999);
		////a[contains(text(),'5 star')
		//action.moveToElement(ratingBar).perform();
		
	}
	@Parameters({ "driverPath"})
	public void verifyItemSearchAndTitle(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
		//Search Bar Testing
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
		Assert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute nav-active", "Test Failed: Unexpected CSS class found for searchbarForm after click.\nExpected Class: nav-input nav-searchbar-attribute nav-active\nFound Class: " + searchBarFormClass);
		searchBar.sendKeys("1:24 Diecast");
		String searchBarText = searchBar.getAttribute("value");
		softAssert.assertTrue(searchBarText.equals("1:24 Diecast"));
		if (!searchBarText.equals("1:24 Diecast")) {
			System.out.print("TEST FAILED: \n Unexpected text in search bar. \n Expected: 1:24 Diecast \n Actual: " + searchBarText);
		}
		searchBar.sendKeys(Keys.ENTER);
		String strUrl = driver.getCurrentUrl();
		try {
			Assert.assertNotEquals(strUrl, "https://www.amazon.com/ref=nav_logo");
		}
		catch (AssertionError e) {
			System.out.print("Test Failed: Could not navigate to new page after sending enter key to search bar.\n");
			return;
		}
		//SoftAssert.assertEquals(searchBarText, "1:24 Diecast", "Search Bar text matches text entered.");
		WebElement sponsoredEntry = driver.findElement(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
		String productName = sponsoredEntry.getText();
		sponsoredEntry.click();
		WebElement itemPageTitle = driver.findElement(By.xpath("//span[@id='productTitle']"));
		String itemPageTitleText = itemPageTitle.getText();
		softAssert.assertEquals(itemPageTitleText, productName, "\nTEST FAILED: PDP Item name doesn't match name of item clicked");
	}
	
	@AfterTest
	public void testCleanUp() {
		driver.close();
	}
}
