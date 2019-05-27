package com.stgconsulting.utility;

public class Config {

    /*
       Hard code values that are unlikely to need to be changed often. Set values that could be changed via pom.xml,
       command line, environment variables, or whatever other methods are needed in the static block.
     */

    //Browser
    public static final Browser BROWSER;
    private static final Browser DEFAULT_BROWSER = Browser.CHROME;

    //Environment settings
    public static final String BASE_URL;

    //Timeouts
    public static final long DEFAULT_ELEMENT_WAIT_SECONDS = 10;
    public static final long PAGE_LOAD_TIMEOUT_SECONDS = 30;

    static {
        //Browser
        String browser = System.getProperty("webdriver.browser");
        //if webdriver.browser is found use it, else use DEFAULT_BROWSER
        BROWSER = null != browser ? Browser.fromString(browser) : DEFAULT_BROWSER;

        //Environment settings
        BASE_URL = System.getProperty("app.base.url");
    }
}
