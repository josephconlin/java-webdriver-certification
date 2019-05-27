package certification.challenge3;

import certification.SingleBrowserTest;
import com.copart.page.home.TrendingSubTab;
import org.testng.annotations.Test;

/*
 * Challenge 3 - Loops:
 * For this challenge, go to copart and print a list of all the “Popular Items” of vehicle Make/Models on the home page
 * and the URL/href for each type.  This list can dynamically change depending on what is authored by the content
 * creator but using a loop will make sure that everything will be displayed regardless of the list size.
 *
 * Your output in the console would look like:
 * IMPREZA - https://www.copart.com/popular/model/impreza
 * CAMRY - https://www.copart.com/popular/model/camry
 * ELANTRA - https://www.copart.com/popular/model/elantra
 * ...
 */
public class Challenge3Loops extends SingleBrowserTest {

    @Test
    public void testPrintMakesModelsInformation() {
        TrendingSubTab.build().display().printMakesModelsLinkNamesAndLocations();
    }
}
