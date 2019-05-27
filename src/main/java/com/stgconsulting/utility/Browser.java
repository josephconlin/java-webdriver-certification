package com.stgconsulting.utility;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Browser {
    CHROME("chrome", BrowserType.CHROME),
    EDGE("edge", BrowserType.EDGE),
    FIREFOX("firefox", BrowserType.FIREFOX),
    IE("ie", BrowserType.IE);

    private static final Map<String,Browser> ALIAS_MAP = new HashMap<>();
    static {
        for (Browser browser : Browser.values()) {
            //Ignore case by normalizing to uppercase
            ALIAS_MAP.put(browser.name().toUpperCase(), browser);
            for (String alias : browser.aliases)
                ALIAS_MAP.put(alias.toUpperCase(), browser);
        }
    }
    private List<String> aliases;

    Browser(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public static Browser fromString(String value) {
        if (value == null) throw new NullPointerException("Received null input parameter");
        Browser browser = ALIAS_MAP.get(value.toUpperCase());
        if (browser == null)
            throw new IllegalArgumentException("Not a supported browser: "+value);
        return browser;
    }

    public static Browser fromWebDriver(WebDriver driver) {
        if (driver == null) throw new NullPointerException("Received null input parameter");
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        return(fromString(cap.getBrowserName()));
    }

}