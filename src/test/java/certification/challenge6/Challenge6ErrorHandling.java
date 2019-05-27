package certification.challenge6;

import certification.SingleBrowserTest;
import com.copart.page.header.MainSearchHeader;
import com.copart.page.search_results.FilterOptions;
import com.copart.page.search_results.SearchResults;
import com.stgconsulting.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Challenge 6 - Error Handling
 * For this challenge, go to copart site, search for nissan, and then for the model in the left side filter option,
 * search for “skyline”.  Now look for a check box for a Skyline.  This is a rare car that might or might not be in the
 * list for models.  When the link does not exist to click on, your script will throw an exception.  Catch the exception
 * and take a screenshot of the page of what it looks like.
 */
public class Challenge6ErrorHandling extends SingleBrowserTest {

    @Test
    public void testNissanSkylineCheckboxPresence() {
        String searchMake = "nissan";
        String resultsToShow = "20";

        MainSearchHeader.build().setSearchBoxTo(searchMake).clickSearchButton();
        SearchResults.build()
                .setShowEntriesTo(resultsToShow);
        FilterOptions filter = FilterOptions.build().setFilterTo("Model", "skyline");
        //I would normally use a ReportListener or put screenshot logging in an AfterMethod in the base class, but those
        //don't show try / catch so doing it here instead.
        try {
            filter.checkFilterCheckbox("Model", "Skyline");
        }
        catch(Exception e) {
            //Mark the test failed
            Assert.fail("Checkbox [Skyline] not found.");
        }
        finally {
            //Take a screenshot whether pass or fail so that screenshot example is available regardless
            Screenshot.build().takeScreenshot();
        }
    }
}
