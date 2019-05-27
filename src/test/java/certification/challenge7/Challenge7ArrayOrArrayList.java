package certification.challenge7;

import certification.SingleBrowserTest;
import com.copart.page.home.TrendingSubTab;
import com.copart.page.search_results.SearchResults;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

/*
 * Challenge 7 - Array or ArrayList
 * For this challenge, take a look at https://www.copart.com main page.  Go to the Makes/Models section of the page.
 * Create a 2 dimensional array or arraylist that stores all the values displayed on the page along w/ the URL for that
 * link.  Once you have this array, you can verify all the elements in the array navigates to the correct page.
 *
 * Optional: using a map:
 */
public class Challenge7ArrayOrArrayList extends SingleBrowserTest {

    @Test
    public void testMakesModelsLinksWork() {
        //Using a browser is a slower way to validate that link URLs are correct than straight HTTP calls, but this is a
        //Selenium certification so using the browser
        WebDriver driver = WebDriverFactory.getWebDriver();
        String currentURL = driver.getCurrentUrl();
        List<WebElement> makesModelsList = TrendingSubTab.build().display().getMakesModelsLinks();

        //Extract link texts and urls from WebElement list to avoid stale element exceptions after navigation away from
        //currentURL
        HashMap<String, String> linkNamesUrlsMap = new HashMap<>();
        for(WebElement linkElement : makesModelsList) {
            linkNamesUrlsMap.put(linkElement.getText(), linkElement.getAttribute("href"));
        }

        //Verify each page linked contains the model name that was the link text
        linkNamesUrlsMap.forEach((makeModel, url) -> {
            driver.get(url);
            Assert.assertTrue(SearchResults.build().doesTableContain(makeModel),
                    "Did not find ["+makeModel+"] on ["+url+"]");
        });

        //Return to starting page
        driver.get(currentURL);
    }
}
