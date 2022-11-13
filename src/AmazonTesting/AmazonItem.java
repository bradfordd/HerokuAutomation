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

import helperClasses.WebElementHelper;

public class AmazonItem {
	public String baseUrl = "https://www.amazon.com/ref=nav_logo";
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	@Test
	@Parameters({ "driverPath"})
	public void averifyUserReviewsFunctionality(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		WebElementHelper w = new WebElementHelper();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		WebElement searchBar = w.verifyWebElementsPresenceWithXpath("//input[@id='twotabsearchtextbox']", driver);
		if (searchBar == null) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: Could not find searchbar WebElement\n");
			Assert.fail();
		}
		WebElement searchBarForm =  w.verifyWebElementsPresenceWithXpath("//form[@id='nav-search-bar-form']", driver);
		if (searchBarForm == null) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: Could not find searchBarForm WebElement\n");
			Assert.fail();
		}
		String searchBarFormClass = searchBarForm.getAttribute("class");
		try {
			Assert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute");
		}
		catch (AssertionError e) {
			System.out.print("Test Failed: Unexpected CSS class found for searchbarForm. \n Expected Class: nav-input nav-searchbar-attribute \n Found Class: " + searchBarFormClass);
		}
	
		searchBar.click();
		searchBarFormClass = searchBarForm.getAttribute("class");
		softAssert.assertEquals(searchBarFormClass, "nav-searchbar nav-progressive-attribute nav-active", "Test Failed: Unexpected CSS class found for searchbarForm after click.\nExpected Class: nav-input nav-searchbar-attribute nav-active\nFound Class: " + searchBarFormClass);
		searchBar.sendKeys("1:24 Diecast");
		String searchBarText = searchBar.getAttribute("value");
		if (!searchBarText.equals("1:24 Diecast")) {
			System.out.print("\nTEST FAILED: \n Unexpected text in search bar. \n Expected: 1:24 Diecast \n Actual: " + searchBarText + "\n");
			Assert.fail();
		}
		searchBar.sendKeys(Keys.ENTER);
		WebElement ratingBar = driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/span[1]/span[1]/a[1]/i[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ratingBar);
        js.executeScript("window.scrollBy(0, 4)", "");
		Actions action = new Actions(driver);
		ratingBar.click();
		WebElement ratingDropDownMenu = w.verifyWebElementsPresenceWithXpath("//div[@id='a-popover-2']", driver);
		if (ratingDropDownMenu == null) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: Could not find ratings drop down menu\n");
			Assert.fail();
		}
		List<WebElement> anchorTags = ratingDropDownMenu.findElements(By.tagName("a"));
		if (anchorTags.size() == 0) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: Could not find anchor tags within ratings drop down menu\n");
			Assert.fail();
		}
		String starRating = anchorTags.get(0).getText();
		anchorTags.get(0).click();
		WebElement filter = w.verifyWebElementsPresenceWithXpath("//span[@id='reviews-filter-info-segment']", driver);
		if (filter == null) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: rating filter not found\n");
			Assert.fail();
		}
		if (!starRating.equals(filter.getText())) {
			System.out.print("\nverifyUserReviewsFunctionality Test FAILED: rating filter not found\n");
			softAssert.fail();
		}
		softAssert.assertAll();
	}
	@Test
	@Parameters({ "driverPath"})
	public void verifyItemSearchAndTitle(String driverPath) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		SoftAssert softAssert = new SoftAssert();
		driver = new ChromeDriver();
		WebElementHelper w = new WebElementHelper();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		WebElement searchBar = w.verifyWebElementsPresenceWithXpath("//input[@id='twotabsearchtextbox']", driver);
		if (searchBar == null) {
			System.out.print("\nverifyItemSearchAndTitle-- verifyUserReviewsFunctionality Test FAILED: Could not find searchbar WebElement\n");
			Assert.fail();
		}
		WebElement searchBarForm = w.verifyWebElementsPresenceWithXpath("//form[@id='nav-search-bar-form']", driver);
		if (searchBarForm == null) {
			System.out.print("\nverifyItemSearchAndTitle-- verifyUserReviewsFunctionality -- Test FAILED: Could not find searchbarForm WebElement\n");
			Assert.fail();
		}
		String searchBarFormClass = searchBarForm.getAttribute("class");
		if (searchBarFormClass == "nav-searchbar nav-progressive-attribute") {
			System.out.print("\nverifyItemSearchAndTitle-- Test Failed: Unexpected CSS class found for searchbarForm. \n Expected Class: nav-input nav-searchbar-attribute \n Found Class: " + searchBarFormClass + "\n");
			softAssert.fail();
		}
		searchBar.click();
		searchBarFormClass = searchBarForm.getAttribute("class");
		if (searchBarFormClass == "nav-searchbar nav-progressive-attribute nav-active") {
			System.out.print("\n verifyItemSearchAndTitle-- Test Failed: Unexpected CSS class found for searchbarForm after click.\\nExpected Class: nav-input nav-searchbar-attribute nav-active\\nFound Class: " + searchBarFormClass + "\n");
			softAssert.fail();
		}
		searchBar.sendKeys("1:24 Diecast");
		String searchBarText = searchBar.getAttribute("value");
		if (!searchBarText.equals("1:24 Diecast")) {
			System.out.print("\nverifyItemSearchAndTitle-- TEST FAILED: \n Unexpected text in search bar. \n Expected: 1:24 Diecast \n Actual: " + searchBarText + "\n");
			Assert.fail();
		}
		searchBar.sendKeys(Keys.ENTER);
		String strUrl = driver.getCurrentUrl();
		if (strUrl == "https://www.amazon.com/ref=nav_logo") {
			System.out.print("\n verifyItemSearchAndTitle-- Test Failed: Could not navigate to new page after sending enter key to search bar.\n");
			Assert.fail();
		}
		WebElement sponsoredEntry = w.verifyWebElementsPresenceWithXpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']", driver);
		if (sponsoredEntry == null) {
			System.out.print("\n verifyItemSearchAndTitle-- Test Failed: Could not find first entry in search.\n");
			Assert.fail();
		}
		String productName = sponsoredEntry.getText();
		sponsoredEntry.click();
		WebElement itemPageTitle = w.verifyWebElementsPresenceWithXpath("//span[@id='productTitle']", driver);
		if (itemPageTitle == null) {
			System.out.print("\n verifyItemSearchAndTitle-- Test Failed: Could not find productTitle Element.\n");
			Assert.fail();
		}
		String itemPageTitleText = itemPageTitle.getText();
		if (itemPageTitleText == productName) {
			System.out.print("\n verifyItemSearchAndTitle-- TEST FAILED: PDP Item name doesn't match name of item clicked.\n");
			softAssert.fail();
		}
		//softAssert.assertEquals(itemPageTitleText, productName, "\nTEST FAILED: PDP Item name doesn't match name of item clicked");
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void testCleanUp() {
		driver.close();
	}
}
