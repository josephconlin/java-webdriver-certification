package com.copart.page.header;

import com.stgconsulting.utility.Config;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainSearchHeader {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainSearchHeader() {
        driver = WebDriverFactory.getWebDriver();
        wait = new WebDriverWait(driver, Config.DEFAULT_ELEMENT_WAIT_SECONDS);
    }

    public static MainSearchHeader build() {
        return new MainSearchHeader();
    }

    public MainSearchHeader setSearchBoxTo(String value) {
        By searchBoxBy = By.id("input-search");
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxBy)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxBy)).sendKeys(value);
        return this;
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.cssSelector("div button[data-uname=homepageHeadersearchsubmit]"))
        )).click();
    }
}
