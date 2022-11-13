package helperClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementHelper {
	public WebElement verifyWebElementsPresenceWithXpath(String passedXpath, WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath(passedXpath));
		if (elements.size() > 0) {
			return elements.get(0);
		} else {
			return null;
		}
	}
}
