package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.AddNewUserPage;
import pages.CommonPage;
import pages.LandingPage;
import pages.UpdateUserPage;
import utils.WebDriverSingleton;
import utils.helpers.UserData;
import utils.context.UpdateUserContext;
import utils.helpers.Waiters;
import java.util.List;

public class UpdateUser {
    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final UserData userData = new UserData();
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private final UpdateUserPage updateUserPage = new UpdateUserPage(driver);
    private  final Waiters waiters = new Waiters(driver);

    @Given("^new user is added to the \"Users\" table$")
    public void new_user_is_added_to_the_users_table() {
        landingPage.getBtnAddNewUser().click();
        waiters.waitForPageIsOpened(addNewUserPage.getUrl());

        final String userName = userData.getName();
        final String userSurname = userData.getSurname();
        final String userEmail = userData.getEmail();
        final String userPosition = userData.getPosition();

        UpdateUserContext.setUserName(userName);
        UpdateUserContext.setUserSurname(userSurname);
        UpdateUserContext.setUserEmail(userEmail);
        UpdateUserContext.setUserPosition(userPosition);

        commonPage.getFieldName().sendKeys(userName);
        commonPage.getFieldSurname().sendKeys(userSurname);
        commonPage.getFieldEmail().sendKeys(userEmail);
        commonPage.getFieldPosition().sendKeys(userPosition);

        waiters.waitAndClick(addNewUserPage.getBtnCreateUser());
        waiters.waitForPageIsOpened(landingPage.getUrl());
    }

    @When("^the user clicks the \"update user\" button against the new user created$")
    public void the_user_clicks_the_update_user_button_against_the_new_user_created() {
        waiters.waitForPageIsOpened(landingPage.getUrl());
        boolean userFound = false;
        int userFoundInRow = -1;

        List<WebElement> rows = landingPage.getRowsUsersTable();

        String expectedColumnNameSurname = UpdateUserContext.getUserName() + " " + UpdateUserContext.getUserSurname();
        String expectedColumnEmail = UpdateUserContext.getUserEmail();
        String expectedColumnPosition = UpdateUserContext.getUserPosition();

        for (int row = 1; row <= rows.size(); row++) {
            List<WebElement> columns = landingPage.getCellsUsersTable(row);

            String actualColumnNameSurname = columns.get(0).getText();
            String actualColumnEmail = columns.get(1).getText();
            String actualColumnPosition = columns.get(2).getText();

            if (actualColumnNameSurname.contentEquals(expectedColumnNameSurname) && actualColumnEmail.contentEquals(expectedColumnEmail) && actualColumnPosition.contentEquals(expectedColumnPosition)) {
                userFound = true;
                waiters.waitAndClick(landingPage.getBtnUpdateUser(row));
                waiters.waitForPageIsOpened(updateUserPage.getUrl());
                break;
            }
        }
        if (!userFound) {
            throw new NoSuchElementException("There is no corresponding line for new user in the \"Users\" table");
        }
    }

    @When("^the user changes \"(Name|Surname|Email|Position)\"$")
    public void the_user_changes_name_surname_email_position(String fieldUserData) {
        WebElement elem;
        String newUserData;
        switch (fieldUserData) {
            case "Name":
                elem = commonPage.getFieldName();
                newUserData = userData.getName();
                UpdateUserContext.setUserName(newUserData);
                elem.clear();
                break;
            case "Surname":
                elem = commonPage.getFieldSurname();
                newUserData = userData.getSurname();
                UpdateUserContext.setUserSurname(newUserData);
                elem.clear();
                break;
            case "Email":
                elem = commonPage.getFieldEmail();
                newUserData = userData.getEmail();
                UpdateUserContext.setUserEmail(newUserData);
                elem.clear();
                break;
            case "Position":
                elem = commonPage.getFieldPosition();
                newUserData = userData.getPosition();
                UpdateUserContext.setUserPosition(newUserData);
                break;
            default:
                throw new IllegalArgumentException("The " + fieldUserData + " field is not defined");
        }
        elem.sendKeys(newUserData);
    }

    @When("^the user clicks the \"(Update user|Cancel)\" button on the \"update user\" page$")
    public void the_user_clicks_the_update_user_button_on_the_add_new_user_page(String btnName) {
        WebElement elem = switch (btnName) {
            case "Update user" -> updateUserPage.getBtnUpdateUser();
            case "Cancel" -> updateUserPage.getBtnCancelUpdate();
            default -> throw new IllegalArgumentException("The " + btnName + " button is not defined");
        };
        waiters.waitAndClick(elem);
    }

    @Then("^the \"(Update user|Cancel)\" button is present and active on the \"update user\" page$")
    public void the_update_user_cancel_button_is_present_and_active_on_the_update_user_page(String btnName) {
        WebElement elem = switch (btnName) {
            case "Update user" -> updateUserPage.getBtnUpdateUser();
            case "Cancel" -> updateUserPage.getBtnCancelUpdate();
            default -> throw new IllegalArgumentException("The " + btnName + " button is not defined");
        };
        Assert.assertTrue(elem.isDisplayed());
        Assert.assertTrue(elem.isEnabled());
    }
}

