package certification.challenge1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/*
 * Step 17:
 * Click on the challenge1 class.  You’ll now see the basic java class structure.
 * Let’s add some structure to our test file by adding the following example code:
 *
 * Step 19:
 * Let’s declare a driver variable and instantiate a new driver like this.  We’ll make this variable global to this
 * the test class by adding it right after the line “public class challenge1 {“
 *
 * Step 20:
 * Before we can start our test, we’ll need to create a new webdriver object by adding these lines to the
 * @BeforeClass.  You can leave the @BeforeSuite, @BeforeMethod empty as they are not required until you want to add
 * additional steps that needs to happen at a class or suite level.
 *
 * Step 21:
 * Let’s add a line in the @AfterClass to close the chrome browser, otherwise we’ll need to manually close it.
 *
 * Step 22:
 * Let's write a test function that goes to google.com.
 *
 * Step 23:
 * Let’s write another test function that checks to make sure the page title is what we expected by using the testNG
 * Assert
 *
 * Step 24:
 * To launch your automation test, we’ll need to create a testng.xml file.  This file name can be anything you want
 * but it needs to be an xml file.
 */
public class Challenge1 {

    public WebDriver driver;

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception{
        //Use line below if chromedriver.exe is not already found in your PATH or webdriver.chrome.driver property is
        //not already set (via pom.xml, command line, etc). This project does not contain ./bin/chromedriver.exe
//        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
        driver =  new ChromeDriver();
    }

    @AfterClass
    public void stopClass(){
        driver.quit();
    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
    }

    @AfterMethod()
    public void afterMethod(){
    }

    @Test()
    public void goToGoogle() throws Exception {
        driver.get("https://www.google.com");
    }

    @Test()
    public void verifyGoogleTitle() throws Exception {
        Assert.assertEquals(driver.getTitle(), "Google");
    }

}
