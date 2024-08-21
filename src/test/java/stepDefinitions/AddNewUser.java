package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AddNewUserPage;
import pages.CommonPage;
import pages.LandingPage;
import utils.context.AddNewUserContext;
import utils.WebDriverSingleton;
import utils.helpers.UserData;
import utils.helpers.Waiters;

public class AddNewUser {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final UserData userData = new UserData();
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private  final Waiters waiters = new Waiters(driver);

    @Given("^the user navigates to the \"add new user\" page$")
    public void the_user_navigates_to_the_add_new_user_page() {
        landingPage.getBtnAddNewUser().click();
        waiters.waitForPageIsOpened(addNewUserPage.getUrl());
    }

    @When("^the user fills in the user data missing (Name|Email)$")
    public void the_user_fills_in_the_user_data_missing_name_surname_email(String fieldName) {
        String valueNameField = userData.getName();
        String valueSurnameField = userData.getSurname();
        String valueEmailField = userData.getEmail();
        String valuePositionField = userData.getPosition();

        AddNewUserContext.setUserName(valueNameField);
        AddNewUserContext.setUserSurname(valueSurnameField);
        AddNewUserContext.setUserEmail(valueEmailField);
        AddNewUserContext.setUserPosition(valuePositionField);

        switch (fieldName) {
            case "Name":
                commonPage.getFieldEmail().sendKeys(valueEmailField);
                break;
            case "Email":
                commonPage.getFieldName().sendKeys(valueNameField);
                break;
            default:
                throw new IllegalArgumentException("The " + fieldName + " field is not defined");
        }
        commonPage.getFieldSurname().sendKeys(valueSurnameField);
        commonPage.getFieldPosition().sendKeys(valuePositionField);
    }

    @When("^the user fills in the user data$")
    public void the_user_fills_in_the_user_data() {

        String valueNameField = userData.getName();
        String valueSurnameField = userData.getSurname();
        String valueEmailField = userData.getEmail();
        String valuePositionField = userData.getPosition();

        AddNewUserContext.setUserName(valueNameField);
        AddNewUserContext.setUserSurname(valueSurnameField);
        AddNewUserContext.setUserEmail(valueEmailField);
        AddNewUserContext.setUserPosition(valuePositionField);

        commonPage.getFieldName().sendKeys(valueNameField);
        commonPage.getFieldSurname().sendKeys(valueSurnameField);
        commonPage.getFieldEmail().sendKeys(valueEmailField);
        commonPage.getFieldPosition().sendKeys(valuePositionField);
    }

    @When("^the user clicks the \"Create user\" button on the \"add new user\" page$")
    public void the_user_clicks_the_create_user_button_on_the_add_new_user_page() {
        waiters.waitAndClick(addNewUserPage.getBtnCreateUser());
    }

    @When("^the user clicks the \"Test Application\" link$")
    public void the_user_clicks_the_test_application_link() {
        waiters.waitAndClick(commonPage.getLnkTestApp());
    }


    @Then("^the \"Create user\" button is present and active on the \"add new user\" page$")
    public void the_create_user_button_is_present_and_active_on_the_add_new_user_page() {
        Assert.assertTrue(addNewUserPage.getBtnCreateUser().isDisplayed());
        Assert.assertTrue(addNewUserPage.getBtnCreateUser().isEnabled());
    }

    @Then("^the \"Fill in all the details\" error appears on the \"add new user\" page$")
    public void the_fill_in_all_the_details_error_appears_on_the_add_new_user_page() {
        Assert.assertTrue(addNewUserPage.getErrorTxtFillInDetails().isDisplayed());
    }
}
