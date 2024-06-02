import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AdBlocker {
    public static void main(String[] args) throws MalformedURLException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        String remoteWebDriverUrl = "http://localhost:4444/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);

        proxy.blacklistRequests("https?://.*\\.doubleclick\\.net/.*", 200);
        proxy.blacklistRequests("https?://.*\\.googleadservices\\.com/.*", 200);
        proxy.blacklistRequests("https?://.*\\.googlesyndication\\.com/.*", 200);
        proxy.blacklistRequests("https?://.*\\.adsafeprotected\\.com/.*", 200);
        proxy.blacklistRequests("https?://.*\\.adnxs\\.com/.*", 200);

        driver.get("http://example.com");

        driver.quit();
        proxy.stop();
    }
}
