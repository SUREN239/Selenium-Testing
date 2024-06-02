package Test_Firstcry;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.EventHandler;

public class Test_firstcry {
	private static final long PAGE_LOAD_TIME = 10;
	public static WebDriver driver;

	@BeforeMethod
	public WebDriver beforeMethod() throws MalformedURLException {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions);
		driver.get("https://www.firstcry.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
		WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return driver;

	}

	@Test(priority = 1)
	public void test1() throws Exception {
		WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/input"));
		search.click();
		Thread.sleep(3000);


		search.sendKeys("Toys" + Keys.RETURN);
		Thread.sleep(3000);
		if (driver.getCurrentUrl().contains("kids-toys")) {
			System.out.println("Toy search successfull");
		} else {
			System.out.println("Enter proper search item");
		}
	
	}

	@Test(priority = 2)
	public void test2() throws Exception {
		WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/input"));
		search.click();
		Thread.sleep(3000);

		search.sendKeys("Clothes" + Keys.RETURN);
		Thread.sleep(3000);

		WebElement ethnic = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]"));
		ethnic.click();
		Thread.sleep(10000);

		if (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText()
				.contains("Ethnic Wear")) {
			System.out.println("Filter applied successfull");
		} else {
			System.out.println("Enter proper search item");
		}
		
	}

	@Test(priority = 3)
	public void test3() throws Exception {
		WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/input"));
		search.click();
		Thread.sleep(3000);


		search.sendKeys("Balloons" + Keys.RETURN);
		Thread.sleep(3000);

		String title = driver.findElement(By.linkText("Balloon Table Holder")).getText();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,550)", "");
		Thread.sleep(4000);

		WebElement balloon = driver.findElement(By.linkText("Balloon Table Holder"));
		balloon.click();
		Thread.sleep(10000);

		String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

		if (driver.findElement(By.id("prod_name")).getText().equals(title)) {
			System.out.println("Product display successfull");
		} else {
			System.out.println("Enter proper search item");
		}
		
	}

	@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
		}
}
}
