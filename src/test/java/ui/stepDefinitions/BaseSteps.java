package ui.stepDefinitions;

import io.cucumber.java.AfterAll;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebElement;
import ui.pages.LandingPage;
import ui.utils.WebDriverSingleton;
import ui.utils.helpers.Waiters;
import java.util.List;

public class BaseSteps {

    private static WebDriver driver;

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

        driver.get(landingPage.getUrl());
        List<WebElement> rows = landingPage.getRowsUsersTable();
        if(!rows.isEmpty()) {
            for (int row = rows.size(); row >= 1; row--) {
                waiters.waitAndClick(landingPage.getBtnRemoveUser(row));
            }
        }
        WebDriverSingleton.quitDriver();
    }

}