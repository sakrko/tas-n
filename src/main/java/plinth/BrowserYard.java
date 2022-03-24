package plinth;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserYard {
    WebDriver browser = null;
    private static final Logger logger = LogManager.getLogger(BrowserYard.class);


    public void getBrowser(String browserName) {
        switch (browserName) {
            case "Chrome":
            case "Headless Chrome":
            case "Chromium":
            case "Headless Chromium":
                ChromeOptions options = new ChromeOptions();
                if (browserName.contains("Chrome")) {
                    WebDriverManager.chromedriver().setup();
                } else {
                    WebDriverManager.chromiumdriver().setup();
                }
                if (browserName.contains("Headless")) {
                    options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
                }
                browser = new ChromeDriver(options);
                break;
            case "Firefox":
                break;
            case "Safari":
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
