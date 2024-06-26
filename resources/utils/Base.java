package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Base 
{
	public static WebDriver driver;
	public final int IMPLICIT_WAIT_TIME=10;
	public final int PAGE_LOAD_TIME=5;
	public Properties prop;
	private static final Logger log = LogManager.getLogger(Base.class);

	

	public Base() 
	{
		String filepath=System.getProperty("user.dir")+"/config/browser.properties";
		System.setProperty("log4j2.configurationFile", "/src/main/resource/log4j2.xml");
		try
		{
			FileInputStream file=new FileInputStream(filepath);
			prop=new Properties();
			prop.load(file);
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	public WebDriver openBrowser() throws MalformedURLException
	{
		String browsername=prop.getProperty("browsername");
		if(browsername.equalsIgnoreCase("chrome"))
		{
		DesiredCapabilities dc=new DesiredCapabilities();
			dc.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
			   
		}
		else if(browsername.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("ie")){
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else if(browsername.equalsIgnoreCase("safari")){
			WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
		}
		driver.manage().window().maximize();
		log.info("Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		log.info("Browser Loaded the URL");

		WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return driver;

	}

	public void navigateToURL(WebDriver driver, Properties prop) {
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			e.printStackTrace(); 
			
		}
	}



	
	
	
    
}
