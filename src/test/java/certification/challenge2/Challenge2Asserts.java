package certification.challenge2;

import certification.SingleBrowserTest;
import com.copart.page.header.MainSearchHeader;
import com.copart.page.search_results.SearchResults;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Challenge 2 - Asserts:
 * For this challenge, look through the different ways to do assertions.  Then write a script that will go to
 * copart.com, search for exotics and verify porsche is in the list of cars.  Use the hard assertion for this challenge.
 */
public class Challenge2Asserts extends SingleBrowserTest {

    @Test
    public void testSearchForExoticsReturnsPorsche() {
        MainSearchHeader.build().setSearchBoxTo("exotics").clickSearchButton();
        String resultsText = SearchResults.build().getTableText();
        Assert.assertTrue(resultsText.contains("PORSCHE"), "PORSCHE not found in\n"+resultsText);
    }
}
