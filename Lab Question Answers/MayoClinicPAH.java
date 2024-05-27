package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utils.Base;
import utils.Screenshot;

public class AppTest extends Base
{
    Screenshot ss;
    @Test
    public void testcase() throws Exception
    {
       driver = openBrowser();
       Thread.sleep(3000);

       WebElement drop = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div/header/div[2]/div/nav/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/ul/li[1]/div[1]/div/button/span/span[1]"));
       drop.click();
       Thread.sleep(2000);
       
       WebElement finddoc = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div/header/div[2]/div/nav/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/ul/li[1]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[4]/div/a/span/span/span[1]/span"));
       finddoc.click();
       Thread.sleep(2000);
       
       WebElement searchbar = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div[1]/input"));
       searchbar.sendKeys("Heart");
       
       WebElement location = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div[2]/select"));
       location.click();
       Thread.sleep(2000);

       WebElement locationselect = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div[2]/select/option[2]"));
       locationselect.click();
       Thread.sleep(2000);

       WebElement search = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div[3]/input"));
       search.click();
       Thread.sleep(2000);
       
       ss.getScreenShot(driver,"verifyscreen.png");
       
       WebElement doc = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div/div/div[2]/div/ol/li[1]/div[2]/h4/a"));
       doc.click();
       Thread.sleep(2000);
       
       WebElement clicksecure = driver.findElement(By.xpath("/html/body/form/div[6]/article/div[1]/div/div[2]/div[5]/div/ol/li[1]/a"));
       clicksecure.click();
       Thread.sleep(2000);

       WebElement verify = driver.findElement(By.xpath("/html/body/main/article/section[1]/div/div/p/a"));

       if(verify.getText().equals("PATIENT ONLINE SERVICES"))
       {
        ss.getScreenShot(driver,"verifytheverify.png");
       }
    }
}
