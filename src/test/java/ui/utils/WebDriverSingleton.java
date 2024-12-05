package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {
    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private WebDriverSingleton() {}

    // Method to get the single instance of WebDriver
    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (WebDriverSingleton.class) {
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-search-engine-choice-screen");
                    driver = new ChromeDriver(options);
                }
            }
        }
        return driver;
    }

    // Method to quit the WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}