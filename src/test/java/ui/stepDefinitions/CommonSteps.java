package ui.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.pages.AddNewUserPage;
import ui.pages.CommonPage;
import ui.pages.LandingPage;
import ui.pages.UpdateUserPage;
import config.ConfigReader;
import ui.utils.Pages;
import ui.utils.WebDriverSingleton;
import ui.utils.Waiters;


public class CommonSteps {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private final UpdateUserPage updateUserPage = new UpdateUserPage(driver);
    private final Waiters waiters = new Waiters(driver);
    static String environment = System.getProperty("env");
    static ConfigReader config = new ConfigReader(environment);

    @Given("^the user is on the main page$")
    public void theUserIsOnTheMainPage() {
        driver.get(config.getProperty("frontend.url"));
        waiters.waitForPageIsOpened(landingPage.getUrl());
    }

    @Then("^the title is displayed on the \"(add new user|main|update user)\" page$")
    public void theTitleIsDisplayedOnTheAddNewUserMainUpdateUserPage(String pageName) {
        Assert.assertTrue(commonPage.getH1Header().isDisplayed());
        Assert.assertTrue(commonPage.getTxtEnjoyYourTestAutomationJourney().isDisplayed());
    }

    @Then("^the \"Test Application\" link is displayed on the \"(add new user|main|update user)\" page$")
    public void theTestApplicationLinkIsDisplayedOnTheMainAddNewUserUpdateUserPage(String pageName) {
        Assert.assertTrue(commonPage.getLnkTestApp().isDisplayed());
    }

    @Then("^the copyright statement is displayed on the \"(add new user|main|update user)\" page$")
    public void theCopyrightStatementIsDisplayedOnTheAddNewUserMainUpdateUserPage(String pageName) {
        Assert.assertTrue(commonPage.getTxtCopyright().isDisplayed());
    }

    @Then("^the \"([^\"]*)\" page is opened$")
    public void thePageIsOpened(String pageName) {
        String pageElem = Pages.getUrl(pageName);
        if (pageElem == null) {
            throw new IllegalArgumentException("The " + pageName + " page is not defined");
        }
        waiters.waitForPageIsOpened(pageElem);
    }
}
