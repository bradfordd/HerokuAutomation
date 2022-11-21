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
		driver.manage().deleteAllCookies();
	}
	
	@Test
	@Parameters({ "driverPath"})
	public void verifyBestSellerCarousel(String driverPath) throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		List<WebElement> bestSellerElement = driver.findElements(By.xpath("//div[@id=\"nav-xshop\"]//*[text()=\'Best Sellers\']"));
		if (bestSellerElement.size() == 0) {
			System.out.print("\nERROR: Could not find top best seller element of on Amazon homepage.\n");
			Assert.fail();
		}
		bestSellerElement.get(0).click();
		Thread.sleep(2000);
		String currUrl = driver.getCurrentUrl();
		if (!currUrl.equals("https://www.amazon.com/gp/bestsellers/?ref_=nav_cs_bestsellers")) {
			System.out.print("\nERROR: Incorrect url navigated to upon clicking best sellers nav-bar element\n");
			Assert.fail();
		}
		List<WebElement> carouselElements = driver.findElements(By.xpath("(//*[@class=\"a-begin a-carousel-container a-carousel-display-swap a-carousel-transition-swap p13n-sc-shoveler p13n-carousel-initialized a-carousel-initialized\"])[1]"));
		if (carouselElements.size() == 0) {
			System.out.print("\nERROR: Could not find carousel element.\n");
			Assert.fail();
		}
		
		//System.out.print(carouselElements.get(0).getAttribute("data-a-carousel-options"));
		
		List<WebElement> carouselButtons = driver.findElements(By.xpath("(//*[@class=\"a-begin a-carousel-container a-carousel-display-swap a-carousel-transition-swap p13n-sc-shoveler p13n-carousel-initialized a-carousel-initialized\"])[1]//span[@class=\"a-button-inner\"]"));
		if (carouselButtons.size() != 2) {
			System.out.print("\nERROR: Could not find proper number of carousel transition elements (2). Found Elements: " + carouselButtons.size() + "\n");
			Assert.fail();
		}
		carouselButtons.get(1).click();
		Thread.sleep(2000);
		String firstCarouselXpath = "(//*[@class=\"a-begin a-carousel-container a-carousel-display-swap a-carousel-transition-swap p13n-sc-shoveler p13n-carousel-initialized a-carousel-initialized\"])[1]";
		String firstCarouselRankingXpath = firstCarouselXpath + "//*[@class=\"zg-bdg-text\"]";
		List<WebElement> firstCarouselRankings = driver.findElements(By.xpath(firstCarouselRankingXpath));
		if (firstCarouselRankings.size() == 0) {
			System.out.print("\nERROR: Could not find carousel ranking");
			Assert.fail();
		}
		String rankingText = "";
		String expectedRankingText = "";
		for (int i = 0; i < firstCarouselRankings.size(); i++) {
			rankingText = firstCarouselRankings.get(i).getText();
			expectedRankingText = "#" + (i + 1 + firstCarouselRankings.size());
			if (!rankingText.equals(expectedRankingText)) {
				System.out.print("\nERROR: Improper ranking Text \n Expected Ranking Text: " + expectedRankingText 
						+ " Found Raning Text: " + rankingText);
				softAssert.fail();
			}
		}
		String pageTitleXpath = "//span[@id=\"zg_banner_text\"]";
		List<WebElement> pageTitle = driver.findElements(By.xpath(pageTitleXpath));
		if (pageTitle.size() != 1) {
			System.out.print("\n ERROR: Did not find proper number of page title elements (1). Found Elements: " + pageTitle.size());
			softAssert.fail();
		} else {
			String expectedPageTitleText = "Amazon Best Sellers";
			String pageTitleText = pageTitle.get(0).getText();
			if (!pageTitleText.equals(expectedPageTitleText)) {
				System.out.print("\nERROR: Did not find proper page title. \nExpected Page Title: " + expectedPageTitleText);
				System.out.print("\nFound Page Title Text: " + pageTitleText + "\n");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void testCleanUp() {
		driver.close();
	}
}
