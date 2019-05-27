package certification;

import com.stgconsulting.utility.Config;
import com.stgconsulting.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SingleBrowserTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = WebDriverFactory.setUp(Config.BROWSER);
        driver.get(Config.BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.tearDown();
    }
}
