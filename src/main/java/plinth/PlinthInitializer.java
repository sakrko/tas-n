package plinth;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ConfigHelper;
import utils.JsonDataHelper;

import java.util.concurrent.ConcurrentMap;

public class PlinthInitializer {
    private static final ThreadLocal<WebDriver> threadLocalBrowser = new ThreadLocal<>();

    public static synchronized void setBrowser(WebDriver browser) {
        threadLocalBrowser.set(browser);
    }

    public static synchronized WebDriver getBrowser() {
        return threadLocalBrowser.get();
    }

    public static ConfigHelper configHelper = ConfigHelper.INSTANCE;

    public static JsonDataHelper jsonDataHelper = JsonDataHelper.INSTANCE;

    private static final ThreadLocal<ConcurrentMap<String, String>> threadLocalConMap = new ThreadLocal<>();

    public static synchronized void setConMap(ConcurrentMap<String, String> cm) {
        threadLocalConMap.set(cm);
    }

    public static synchronized ConcurrentMap<String, String> getConMap() {
        return threadLocalConMap.get();
    }

    private static final ThreadLocal<Scenario> threadLocalScenario = new ThreadLocal<>();

    public static synchronized void setScenario(Scenario cm) {
        threadLocalScenario.set(cm);
    }

    public static synchronized Scenario getScenario() {
        return threadLocalScenario.get();
    }
}
