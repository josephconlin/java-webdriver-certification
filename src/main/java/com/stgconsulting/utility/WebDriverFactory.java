package com.stgconsulting.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        //Return previously setup WebDriver if available
        return webDriverThreadLocal.get();
    }

    public static WebDriver setUp(Browser browser) {
        //Configure browser settings
        //If webdriver.hub_address system property is not set, use browser specific drivers instead of RemoteWebDriver
        String selenium_hub_addr = System.getProperty("webdriver.hub_address");

        try {
            switch (browser) {
                //Assume that driver executable files are either available via PATH environment variable or the user set
                //system properties for each driver
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //Configure remote or local depending on settings
                    if(selenium_hub_addr != null) {
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(selenium_hub_addr), chromeOptions));
                    }
                    else {
                        webDriverThreadLocal.set(new ChromeDriver(chromeOptions));
                    }
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    //Configure remote or local depending on settings
                    if(selenium_hub_addr != null) {
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(selenium_hub_addr), edgeOptions));
                    }
                    else {
                        webDriverThreadLocal.set(new EdgeDriver(edgeOptions));
                    }
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    //Configure remote or local depending on settings
                    if(selenium_hub_addr != null) {
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(selenium_hub_addr), firefoxOptions));
                    }
                    else {
                        webDriverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                    }
                    break;
                case IE:
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    //Configure remote or local depending on settings
                    if(selenium_hub_addr != null) {
                        webDriverThreadLocal.set(new RemoteWebDriver(new URL(selenium_hub_addr), ieOptions));
                    }
                    else {
                        webDriverThreadLocal.set(new InternetExplorerDriver(ieOptions));
                    }
                    break;
                default:
                    System.err.println("Unknown browser: "+browser);
                    break;
            }
        }
        //If value given for selenium_hub_addr causes an exception, print the exception and throw to end execution
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //Configure more browser properties here
        webDriverThreadLocal.get().manage().timeouts().pageLoadTimeout(
                Config.PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        webDriverThreadLocal.get().manage().window().maximize();

        //Return newly created WebDriver
        return webDriverThreadLocal.get();
    }

    public static void tearDown() {
        //Cleanup browser
        WebDriver driver = webDriverThreadLocal.get();
        if(driver != null) {
            webDriverThreadLocal.remove();
            driver.quit();
        }
    }
}