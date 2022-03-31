package plinth;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserYard extends PlinthInitializer {
    private static final Logger logger = LogManager.getLogger(BrowserYard.class);
    WebDriver browser = null;

    public void getBrowser(String browserName) {
        switch (browserName) {
            case "Chrome":
            case "Headless Chrome":
            case "Chromium":
            case "Headless Chromium":
                ChromeOptions ccOptions = new ChromeOptions();
                if (browserName.contains("Chrome")) {
                    WebDriverManager.chromedriver().setup();
                } else {
                    WebDriverManager.chromiumdriver().setup();
                }
                if (browserName.contains("Headless")) {
                    ccOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
                }
                browser = new ChromeDriver(ccOptions);
                break;
            case "Firefox":
            case "Headless Firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 1);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.manager.focusWhenStarting", false);
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                profile.setPreference("browser.download.manager.closeWhenDone", true);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                // You will need to find the content-type of your app and set it here.
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("browser", "Firefox");
                capabilities.setCapability("browser_version", "latest");
                capabilities.setCapability("os", "Windows");
                capabilities.setCapability("os_version", "10");
                capabilities.setCapability("build", "Selenium Java Firefox Profile");
                capabilities.setProfile(profile);
                if (browserName.contains("Headless")) {
                    capabilities.setHeadless(true);
                }
                WebDriverManager.firefoxdriver().setup();
                browser = new FirefoxDriver(capabilities);
                break;
            case "Safari":
                WebDriverManager.safaridriver().setup();
                browser = new SafariDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                browser = new EdgeDriver();
                break;
            default:
                logger.error(browserName + " is not supported");
                break;
        }
    }

    public WebDriver createBrowser(String browserName) {
        if (!browserName.equals("")) {
            getBrowser(browserName);
            logger.info(browserName + " started successfully and " + browser);
        } else {
            logger.error("No Browser name is given");
        }
        return browser;
    }

    public void quitBrowser() {
        browser.quit();
        browser = null;
        logger.info("Browser closed");
    }
}
