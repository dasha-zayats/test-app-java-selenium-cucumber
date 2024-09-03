package ui.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.pages.LandingPage;
import config.ConfigReader;
import ui.utils.WebDriverSingleton;
import ui.utils.Waiters;
import java.util.List;

public class BaseSteps {

    private static WebDriver driver;
    static String environment = System.getProperty("env");
    static ConfigReader config = new ConfigReader(environment);

    @Before
    public void setUp() {
        WebDriverSingleton.getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return WebDriverSingleton.getDriver();

    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @AfterAll
    public static void clearDataAndTearDown(){
        WebDriver driver = WebDriverSingleton.getDriver();
        LandingPage landingPage = new LandingPage(driver);
        Waiters waiters = new Waiters(driver);

        driver.get(config.getProperty("frontend.url"));
        List<WebElement> rows = landingPage.getRowsUsersTable();
        if(!rows.isEmpty()) {
            for (int row = rows.size(); row >= 1; row--) {
                waiters.waitAndClick(landingPage.getBtnRemoveUser(row));
            }
        }
        WebDriverSingleton.quitDriver();
    }

}