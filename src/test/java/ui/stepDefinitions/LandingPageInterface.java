package ui.stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.pages.LandingPage;
import ui.utils.Waiters;
import ui.utils.WebDriverSingleton;

public class LandingPageInterface {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final Waiters waiters = new Waiters(driver);


    @When("^the user clicks the \"Add new user\" button on the \"main\" page$")
    public void theUserClicksTheAddNewUserButtonOnTheMainPage() {
        waiters.waitAndClick(landingPage.getBtnAddNewUser());
    }

    @Then("^the \"Add new users\" button is present and active on the \"main\" page$")
    public void theAddNewUsersButtonIsPresentAndActiveOnTheMainPage() {
        Assert.assertTrue(landingPage.getBtnAddNewUser().isDisplayed());
        Assert.assertTrue(landingPage.getBtnAddNewUser().isEnabled());
    }

    @Then("^the \"List of test users\" text is displayed on the \"main\" page$")
    public void theListOfTestUsersTextIsDisplayedOnTheMainPage() {
        Assert.assertTrue(landingPage.getTxtListOfUsers().isDisplayed());
    }

    @Then("^the header of the \"Users\" table is displayed on the \"main\" page$")
    public void theHeaderOfTheUsersTableIsDisplayedOnTheMainPage() {
        Assert.assertTrue(landingPage.getHeaderUsersTable().isDisplayed());
    }
}
