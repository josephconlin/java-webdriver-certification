package certification.challenge5;

import certification.SingleBrowserTest;
import com.copart.page.header.MainSearchHeader;
import com.copart.page.search_results.SearchResults;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Challenge 5 - If/Else/Switch
 * For this challenge, go to https://www.copart.com and do a search for “porsche” and change the  drop down for “Show
 * Entries” to 100 from 20.  Count how many different models of porsche is in the results on the first page and return
 * in the terminal how many of each type exists.
 *
 * Possible values can be “CAYENNE S”, “BOXSTER S”, etc.
 *
 * For the 2nd part of this challenge, create a switch statement to count the types of damages.
 * Here’s the types:
 * REAR END
 * FRONT END
 * MINOR DENT/SCRATCHES
 * UNDERCARRIAGE
 * And any other types can be grouped into one of MISC.
 */
public class Challenge5IfElseSwitch extends SingleBrowserTest {

    @Test
    public void testCountDifferentModelsOfMakeAndDamageTypes() {
        String searchMake = "porsche";
        String resultsToShow = "100";

        MainSearchHeader.build().setSearchBoxTo(searchMake).clickSearchButton();
        System.out.println("Counting first "+resultsToShow+" models of "+searchMake+".\nAlso summing damage types.");
        SearchResults.build()
                .setShowEntriesTo(resultsToShow)
                .printModelsAndModelCount()
                .printDamageCountByTypes();

    }
}
