package AmazonBestSellers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import helperClasses.WebElementHelper;

public class bestSellerCarousel {
	public String baseUrl = "https://www.amazon.com/ref=nav_logo";
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	WebElementHelper w = new WebElementHelper();
	
	@BeforeTest
	@Parameters({ "driverPath"})
	public void testSetup(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		//driver.manage().deleteAllCookies();
	}
	
	@Test
	@Parameters({ "driverPath"})
	public void verifyBestSellerCarousel(String driverPath) throws InterruptedException {
		WebElementHelper w = new WebElementHelper();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		List<WebElement> bestSellerElements = driver.findElements(By.xpath("//*[contains(text(),'Best Sellers')]"));
		WebElement bestSellerNavElement = null;
		int i;
		for (i = 0; i < bestSellerElements.size(); i++) {
			if (bestSellerElements.get(i).getAttribute("class").contains("nav-a")) {
				bestSellerNavElement = bestSellerElements.get(i);
				break;
			}
		}
		if (i == bestSellerElements.size()) {
			System.out.print("\nverifyBestSellerCarousel Test FAILED: Could not find Best Seller WebElement in nav-bar\n");
			Assert.fail();
		}
		bestSellerNavElement.click();
		List<WebElement> carouselElements = driver.findElements(By.xpath("//*[@role='list']"));
		if (carouselElements.size() == 0) {
			System.out.print("failure");
		}
//		WebElement BestSeller = w.verifyWebElementsPresenceWithXpath("//*[contains(text(),'Best Sellers')]", driver);
//		if (BestSellerNavTab == null) {
//			System.out.print("\nverifyBestSellerCarousel Test FAILED: Could not find Best Seller WebElement\n");
//			Assert.fail();
//		}
//		if (BestSellerNavTab.getAttribute("class") != "nav-a ") {
//			System.out.print("\nverifyBestSellerCarousel Test FAILED: Could not find Best Seller WebElement in nav-bar\n");
//			System.out.print("\nFound WebElement Class: " + BestSellerNavTab.getAttribute("class"));
//			Assert.fail();
//		}
		Thread.sleep(99999999);
		//*[contains(text(),'Best Sellers')]
	}
	
	@AfterMethod
	public void testCleanUp() {
		driver.close();
	}
}
