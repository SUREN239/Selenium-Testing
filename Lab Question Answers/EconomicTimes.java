
package economic;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import jdk.internal.org.jline.utils.Log;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.EventHandler;


public class TestEconomic {
	public static WebDriver driver;
    private WebDriverWait wait;
	public final int IMPLICIT_WAIT_TIME=10;
	public final int PAGE_LOAD_TIME=10;
    
	 @BeforeMethod
	    public void beforeMethod() throws MalformedURLException  {
	        ChromeOptions chromeOptions = new ChromeOptions();
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        driver.get("https://economictimes.indiatimes.com/et-now/results");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebDriverListener listener = new EventHandler();
	        driver = new EventFiringDecorator<>(listener).decorate(driver);
			System.out.println("Browser");
	    }

		@Test
		public void economic() throws Exception
		{
		driver.findElement(By.xpath("//*[@id=\"topnav\"]/div[10]/a")).click();
        Thread.sleep(10000);
        JavascriptExecutor JS = (JavascriptExecutor) driver;
        JS.executeScript("window.scrollBy(0,1000)", "");
        driver.findElement(By.id("amcSelection")).click();
        Thread.sleep(5000);
        Select S = new Select(driver.findElement(By.id("amcSelection")));
        S.selectByVisibleText("Canara Robeco");
        Thread.sleep(10000);
        driver.findElement(By.id("schemenm")).click();
        Thread.sleep(5000);
        Select Se = new Select(driver.findElement(By.id("schemenm")));
        Se.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
        Thread.sleep(5000);
        driver.findElement(By.id("anchor3")).click();
        Thread.sleep(15000);

        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        WebElement dropdownContainer = driver
                .findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[2]/ul/li/span"));
        dropdownContainer.click();

        WebElement Options = dropdownContainer
                .findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[2]/ul/li/ul/li[1]"));
        Options.click();
        Thread.sleep(2000);
        WebElement Container = driver
                .findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[3]/ul/li/span"));
        Container.click();

        WebElement Option = dropdownContainer.findElement(
                By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[3]/ul/li/ul/li[3]/span"));
        Option.click();

        Thread.sleep(2000);

        WebElement Contain = driver
                .findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[4]/ul/li/span"));
        Contain.click();

        WebElement Opt = dropdownContainer.findElement(
                By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[4]/ul/li/ul/li[4]/span"));
        Opt.click();

        WebElement returns = driver.findElement(By.xpath("/html/body/main/div[10]/section[3]/div/ul/li[2]"));
        returns.click();
        Thread.sleep(5000);

        WebElement printthis = driver.findElement(By.xpath(
                "/html/body/main/div[10]/section[5]/div/div[1]/section[1]/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));
        System.out.println(printthis.getText());

		}
		
		@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}