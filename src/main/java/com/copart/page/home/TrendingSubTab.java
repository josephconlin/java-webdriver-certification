package com.copart.page.home;

import com.stgconsulting.utility.Config;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TrendingSubTab {

    private WebDriver driver;
    private WebDriverWait wait;

    public TrendingSubTab() {
        driver = WebDriverFactory.getWebDriver();
        wait = new WebDriverWait(driver, Config.DEFAULT_ELEMENT_WAIT_SECONDS);
    }

    public static TrendingSubTab build() {
        return new TrendingSubTab();
    }

    /* Trending link */
    public TrendingSubTab display() {
        By trendingLinkBy = By.cssSelector("a[href='#tabTrending']");
        By trendingLinkParentBy = By.xpath("./parent::li");
        String attrName = "class";
        String attrValueExpected = "active";

        WebElement trendingLink = wait.until(ExpectedConditions.elementToBeClickable(trendingLinkBy));
        WebElement trendingLinkParentLi = trendingLink.findElement(trendingLinkParentBy);
        //If trending tab is not already visible, click it
        if(!trendingLinkParentLi.getAttribute(attrName).equals(attrValueExpected)) {
            trendingLink.click();
            wait.until(ExpectedConditions.attributeContains(trendingLinkParentLi, attrName, attrValueExpected));
        }
        return this;
    }

    /* Makes/Models div */
    public List<WebElement> getMakesModelsLinks() {
        By makesModelsDivBy = By.cssSelector("div[ng-if=popularSearches]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(makesModelsDivBy));
        return driver.findElement(makesModelsDivBy).findElements(By.tagName("a"));
    }

    public TrendingSubTab printMakesModelsLinkNamesAndLocations() {
        List<WebElement> makesModelsList = getMakesModelsLinks();
        for(WebElement link : makesModelsList) {
            System.out.println(link.getText()+" - "+link.getAttribute("href"));
        }
        return this;
    }

}
