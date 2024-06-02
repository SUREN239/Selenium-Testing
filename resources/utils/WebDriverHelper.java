package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;


public class WebDriverHelper  {
    public final static int IMPLICIT_WAIT_TIME = 10;
    public final static int PAGE_LOAD_TIME = 10;

     private WebDriver driver;

    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url)throws Exception  {
        try {
             driver.get(url);
        } catch (Exception e){
              e.printStackTrace();
            throw new Exception("Error in " + e.getMessage());
        }
       
    }
    public  void ClickOnElement(By element) {
        String elementName = Base.driver.findElement(element).getText();
        Base.driver.findElement(element).click();
       
    }

    public  void sendKeys(By element, String data) {
        Base.driver.findElement(element).sendKeys(data);
    }

    public  String getText(By element) {
        return Base.driver.findElement(element).getText();
    }

    public  void jsClick(By locator) {
        WebElement element = Base.driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(Base.driver, Duration.ofSeconds(10));
        WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) Base.driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');", element);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        js.executeScript("arguments[0].click();", element);
        String elementName = Base.driver.findElement(locator).getText();
        
    }

    public  void javascriptScroll(By locator) {
        WebElement element = Base.driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(Base.driver, Duration.ofSeconds(10));
        WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) Base.driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');", element);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public  void switchToNewWindow() throws Throwable {
        try {
            Set<String> windowNames = Base.driver.getWindowHandles();
            for (String windowName : windowNames) {
                if (windowName != null) {
                    Base.driver.switchTo().window(windowName);
                } else {
                    throw new Exception("New window could not be retrieved");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void enter_action(By element) {
        Base.driver.findElement(element).sendKeys(Keys.ENTER);
    }
    
    public  void hoverOverElement(By element) {
        WebElement webElement = Base.driver.findElement(element);
        Actions actions = new Actions(Base.driver);
        actions.moveToElement(webElement).perform();
       
    }
}
