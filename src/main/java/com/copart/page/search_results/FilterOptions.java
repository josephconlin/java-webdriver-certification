package com.copart.page.search_results;

import com.stgconsulting.utility.Config;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterOptions {

    private WebDriver driver;
    private WebDriverWait wait;

    public FilterOptions() {
        driver = WebDriverFactory.getWebDriver();
        wait = new WebDriverWait(driver, Config.DEFAULT_ELEMENT_WAIT_SECONDS);
    }

    public static FilterOptions build() {
        return new FilterOptions();
    }

    private static final String FILTER_LINK_TEMPLATE = "a[data-uname=%sFilter]";
    private static final By EXPANDED_DIV_BY = By.xpath("./ancestor::li//div");

    public FilterOptions expandFilter(String filterName) {
        WebElement filterLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(FILTER_LINK_TEMPLATE, filterName)))
        );
        //Only click the filterLink if it's currently collapsed
        if(filterLink.findElement(By.tagName("i")).getAttribute("class").contains("fa-plus-square")) {
            filterLink.click();
        }
        //Wait for the expansion to complete
        wait.until(ExpectedConditions.attributeContains(
                filterLink.findElement(EXPANDED_DIV_BY), "class", "collapse in"));
        return this;
    }

    public FilterOptions setFilterTo(String filterName, String value) {
        expandFilter(filterName);
        WebElement filterInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(FILTER_LINK_TEMPLATE, filterName)))
        )
                .findElement(EXPANDED_DIV_BY).findElement(By.xpath(".//input[@type='text']"));
        filterInput.clear();
        filterInput.sendKeys(value);
        return this;
    }

    public FilterOptions checkFilterCheckbox(String filterName, String checkboxName) {
        wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(FILTER_LINK_TEMPLATE, filterName)))
        )
                .findElement(EXPANDED_DIV_BY).findElement(By.xpath(".//label[text()='"+checkboxName+"']"))
        .click();
        return this;
    }
}
