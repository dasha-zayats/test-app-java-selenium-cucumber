package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import pages.LandingPage;
import utils.WebDriverSingleton;

public class LandingPageInterface {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);


    @When("^the user clicks the \"Add new user\" button on the \"main\" page$")
    public void the_user_clicks_the_add_new_user_button_on_the_main_page() {
        landingPage.getBtnAddNewUser().click();
    }

    @Then("^the \"Add new users\" button is present and active on the \"main\" page$")
    public void the_add_new_users_button_is_present_and_active_on_the_main_page() {
        Assert.assertTrue(landingPage.getBtnAddNewUser().isDisplayed());
        Assert.assertTrue(landingPage.getBtnAddNewUser().isEnabled());
    }

    @Then("^the \"List of test users\" text is displayed on the \"main\" page$")
    public void the_list_of_test_users_text_is_displayed_on_the_main_page() {
        Assert.assertTrue(landingPage.getTxtListOfUsers().isDisplayed());
    }

    @Then("^the header of the \"Users\" table is displayed on the \"main\" page$")
    public void the_header_of_the_users_table_is_displayed_on_the_main_page() {
        Assert.assertTrue(landingPage.getHeaderUsersTable().isDisplayed());
    }
}
