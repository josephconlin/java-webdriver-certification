package com.stgconsulting.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshot {

    private WebDriver driver;

    public Screenshot() {
        driver = WebDriverFactory.getWebDriver();
    }

    public static Screenshot build() {
        return new Screenshot();
    }

    public void takeScreenshot() {
        Path output = Paths.get("./target/screenshot.png").toAbsolutePath();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.deleteIfExists(output);
            Files.copy(Paths.get(screenshot.getAbsolutePath()), output);
            System.out.println("Screenshot saved to "+output.toString());
        }
        catch(IOException e) {
            System.err.println("Unable to save screenshot to "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
