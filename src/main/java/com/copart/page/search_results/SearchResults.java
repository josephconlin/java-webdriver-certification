package com.copart.page.search_results;

import com.stgconsulting.utility.Config;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResults {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResults() {
        driver = WebDriverFactory.getWebDriver();
        wait = new WebDriverWait(driver, Config.DEFAULT_ELEMENT_WAIT_SECONDS);
    }

    public static SearchResults build() {
        return new SearchResults();
    }

    /* Loading spinner div */
    private void waitForLoadingSpinnerToFinish() {
        By spinnerDiv = By.id("serverSideDataTable_processing");

        //Wait a shorter time for the spinner to appear before assuming it won't appear
        WebDriverWait spinnerAppearWait = new WebDriverWait(driver, 2);
        try {
            spinnerAppearWait.until(ExpectedConditions.visibilityOfElementLocated(spinnerDiv));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerDiv));
        }
        catch(TimeoutException e) {
            //Do nothing, sufficient waiting has happened
        }
    }

    /* Show <count> entries select (above table) */
    public SearchResults setShowEntriesTo(String value) {
        Select showEntries = new Select(wait.until(
                ExpectedConditions.elementToBeClickable(By.name("serverSideDataTable_length")))
        );
        showEntries.selectByValue(value);
        waitForLoadingSpinnerToFinish();
        return this;
    }

    /* Results table */
    private By results_table_by = By.id("serverSideDataTable");

    public String getTableText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(results_table_by)).getText();
    }

    public boolean doesTableContain(String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(results_table_by, expectedText));
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }

    public SearchResults printModelsAndModelCount() {
        /* Two implementations of counting frequency are implemented below along with basic performance calculations.
         * Java 8 Streams / Collections
         * Sorted array of string using indexes to count
         *
         * Other possible options that could be explored include
         * Using a hashmap to store model as a key and incrementing the value as each item is explored
         * Using a MultiSet from Guava
         * Using a Bag or CollectionUtils from Apache Commons
         */
        System.out.println("Model : Displayed Count");
        List<WebElement> models = wait.until(ExpectedConditions.visibilityOfElementLocated(results_table_by))
                .findElements(By.cssSelector("[data-uname=lotsearchLotmodel]"));

        /*
         * Output using Java 8 streams (100 models)
         * 23 May 2019 performance 1 (run alone) **Stream time in ms: 1687
         * 23 May 2019 performance 2 (run alone) **Stream time in ms: 1627
         * 23 May 2019 performance 3 (run alone) **Stream time in ms: 1666
         * 23 May 2019 performance 4 (run both) **Stream time in ms: 1562
         * 23 May 2019 performance 5 (run both) **Stream time in ms: 1534
         * 23 May 2019 performance 6 (run both) **Stream time in ms: 1549
         */
        long streamStart = System.currentTimeMillis();
        models.stream()
                .collect(Collectors.groupingBy(WebElement::getText))
                .forEach((k, v) -> System.out.println(k+" : "+v.size()));
        long streamEnd = System.currentTimeMillis();
        System.out.println("**Stream time in ms: "+(streamEnd-streamStart)+"\n\n");

        System.out.println("Model : Displayed Count");
        /*
         * Output using sorted array (100 models)
         * 23 May 2019 performance 1 (run alone) **Sorted array time in ms: 1556
         * 23 May 2019 performance 2 (run alone) **Sorted array time in ms: 1503
         * 23 May 2019 performance 3 (run alone) **Sorted array time in ms: 1522
         * 23 May 2019 performance 4 (run both) **Sorted array time in ms: 903
         * 23 May 2019 performance 5 (run both) **Sorted array time in ms: 933
         * 23 May 2019 performance 6 (run both) **Sorted array time in ms: 887
         */
        long arraySortStart = System.currentTimeMillis();
        //Get models WebElement.getText into an array
        String[] modelsArray = new String[models.size()];
        int i=0;
        for(WebElement element : models) {
            modelsArray[i]=element.getText();
            i++;
        }

        //Sort the array
        Arrays.sort(modelsArray);

        //Count the duplicates by finding first and last index and calculating last index - first index
        int first = 0;
        for(i=0; i<modelsArray.length; i++) {
            if(!(modelsArray[first].equals(modelsArray[i]))) {
                //i-first is the count so print value and count, then move to next one by updating first
                System.out.println(modelsArray[first]+" : "+(i-first));
                first=i;
            }
        }

        long arraySortEnd = System.currentTimeMillis();
        System.out.println("**Sorted array time in ms: "+(arraySortEnd-arraySortStart)+"\n\n");
        return this;
    }

    private static final String DAMAGE_REAR_END = "REAR END";
    private static final String DAMAGE_FRONT_END = "FRONT END";
    private static final String DAMAGE_MINOR_DENT_SCRATCHES = "MINOR DENT/SCRATCHES";
    private static final String DAMAGE_UNDERCARRIAGE = "UNDERCARRIAGE";
    private static final String DAMAGE_MISC = "MISC";

    public SearchResults printDamageCountByTypes() {
        List<WebElement> damages = wait.until(ExpectedConditions.visibilityOfElementLocated(results_table_by))
                .findElements(By.cssSelector("[data-uname=lotsearchLotdamagedescription]"));
        int rearEndCount = 0, frontEndCount = 0, minorDentScratchesCount = 0, undercarriageCount = 0, miscCount = 0;

        System.out.println("Damage Type : Displayed Count");
        for(WebElement damage : damages) {
            switch (damage.getText()) {
                case DAMAGE_REAR_END:
                    rearEndCount++;
                    break;
                case DAMAGE_FRONT_END:
                    frontEndCount++;
                    break;
                case DAMAGE_MINOR_DENT_SCRATCHES:
                    minorDentScratchesCount++;
                    break;
                case DAMAGE_UNDERCARRIAGE:
                    undercarriageCount++;
                    break;
                default:
                    miscCount++;
                    break;
            }
        }

        System.out.println(DAMAGE_REAR_END+" : "+rearEndCount);
        System.out.println(DAMAGE_FRONT_END+" : "+frontEndCount);
        System.out.println(DAMAGE_MINOR_DENT_SCRATCHES+" : "+minorDentScratchesCount);
        System.out.println(DAMAGE_UNDERCARRIAGE+" : "+undercarriageCount);
        System.out.println(DAMAGE_MISC+" : "+miscCount+"\n\n");
        return this;
    }
}
