package ui.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.pages.AddNewUserPage;
import ui.pages.CommonPage;
import ui.pages.LandingPage;
import ui.pages.UpdateUserPage;
import ui.utils.WebDriverSingleton;
import ui.utils.models.UserData;
import ui.utils.context.UpdateUserContext;
import ui.utils.Waiters;

import java.util.List;

public class UpdateUser {
    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final UserData userData = new UserData();
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private final UpdateUserPage updateUserPage = new UpdateUserPage(driver);
    private final Waiters waiters = new Waiters(driver);

    @Given("^new user is added to the \"Users\" table$")
    public void newUserIsAddedToTheUsersTable() {
        waiters.waitAndClick(landingPage.getBtnAddNewUser());
        waiters.waitForPageIsOpened(addNewUserPage.getUrl());

        final String userName = userData.getName();
        final String userSurname = userData.getSurname();
        final String userEmail = userData.getEmail();
        final String userPosition = userData.getPosition();

        updateUserPage.enterUserData(userName,userSurname,userEmail,userPosition);
        updateUserPage.setUserDataContext(userName,userSurname,userEmail,userPosition);

        waiters.waitAndClick(addNewUserPage.getBtnCreateUser());
        waiters.waitForPageIsOpened(landingPage.getUrl());
    }

    @When("^the user clicks the \"update user\" button against the new user created$")
    public void theUserClicksTheUpdateUserButtonAgainstTheNewUserCreated() {
        waiters.waitForPageIsOpened(landingPage.getUrl());
        boolean userFound = false;
        Number rowNum = -1;

        List<WebElement> rows = landingPage.getRowsUsersTable();

        String expectedColumnNameSurname = UpdateUserContext.getUserName() + " " + UpdateUserContext.getUserSurname();
        String expectedColumnEmail = UpdateUserContext.getUserEmail();
        String expectedColumnPosition = UpdateUserContext.getUserPosition();

        for (int row = 1; row <= rows.size(); row++) {
            List<WebElement> columns = landingPage.getCellsUsersTable(row);

            String actualColumnNameSurname = columns.get(0).getText();
            String actualColumnEmail = columns.get(1).getText();
            String actualColumnPosition = columns.get(2).getText();

            if (actualColumnNameSurname.contentEquals(expectedColumnNameSurname) &&
                    actualColumnEmail.contentEquals(expectedColumnEmail) &&
                    actualColumnPosition.contentEquals(expectedColumnPosition)) {
                userFound = true;
                rowNum=row;
                break;
            }
        }
        if (!userFound) {
            throw new NoSuchElementException("There is no corresponding line for new user in the \"Users\" table");
        } else {
            waiters.waitAndClick(landingPage.getBtnUpdateUser(rowNum));
            waiters.waitForPageIsOpened(updateUserPage.getUrl());
        }
    }

    @When("^the user changes \"(Name|Surname|Email|Position)\"$")
    public void theUserChangesNameSurnameEmailPosition(String fieldUserData) {
        WebElement elem;
        String newUserData;
        switch (fieldUserData) {
            case "Name":
                elem = updateUserPage.getFieldName();
                newUserData = userData.getName();
                UpdateUserContext.setUserName(newUserData);
                elem.clear();
                break;
            case "Surname":
                elem = updateUserPage.getFieldSurname();
                newUserData = userData.getSurname();
                UpdateUserContext.setUserSurname(newUserData);
                elem.clear();
                break;
            case "Email":
                elem = updateUserPage.getFieldEmail();
                newUserData = userData.getEmail();
                UpdateUserContext.setUserEmail(newUserData);
                elem.clear();
                break;
            case "Position":
                elem = updateUserPage.getFieldPosition();
                newUserData = userData.getPosition();
                UpdateUserContext.setUserPosition(newUserData);
                break;
            default:
                throw new IllegalArgumentException("The " + fieldUserData + " field is not defined");
        }
        elem.sendKeys(newUserData);
    }

    @When("^the user clicks the \"(Update user|Cancel)\" button on the \"update user\" page$")
    public void theUserClicksTheUpdateUserButtonOnTheAddNewUserPage(String btnName) {
        WebElement elem = switch (btnName) {
            case "Update user" -> updateUserPage.getBtnUpdateUser();
            case "Cancel" -> updateUserPage.getBtnCancelUpdate();
            default -> throw new IllegalArgumentException("The " + btnName + " button is not defined");
        };
        waiters.waitAndClick(elem);
    }

    @Then("^the \"(Update user|Cancel)\" button is present and active on the \"update user\" page$")
    public void theUpdateUserCancelButtonIsPresentAndActiveOnTheUpdateUserPage(String btnName) {
        WebElement elem = switch (btnName) {
            case "Update user" -> updateUserPage.getBtnUpdateUser();
            case "Cancel" -> updateUserPage.getBtnCancelUpdate();
            default -> throw new IllegalArgumentException("The " + btnName + " button is not defined");
        };
        Assert.assertTrue(elem.isDisplayed());
        Assert.assertTrue(elem.isEnabled());
    }

    @Then("^the \"Add new user\" text is displayed on the \"update user\" page$")
    public void theAddNewUserTextIsDisplayedOnTheUpdateUserPage() {
        Assert.assertTrue(commonPage.getTxtAddNewUser().isDisplayed());
    }

    @Then("^the input fields are displayed on the \"update user\" page$")
    public void theInputFieldsAreDisplayedOnTheUpdateUserPage() {
        Assert.assertTrue(updateUserPage.getFieldName().isDisplayed());
        Assert.assertTrue(updateUserPage.getFieldSurname().isDisplayed());
        Assert.assertTrue(updateUserPage.getFieldEmail().isDisplayed());
        Assert.assertTrue(updateUserPage.getFieldPosition().isDisplayed());
    }

    @Then("^the \"(Name|Surname|Email|Position)\" (text field|dropdown) is displayed on the \"update user\" page$")
    public void theNameSurnameEmailPositionTextFieldDropdownIsDisplayedOnTheUpdateUserPage(String fieldName, String fieldType) {
        WebElement elemLabel;
        WebElement elem;
        switch (fieldName) {
            case "Name":
                elemLabel = updateUserPage.getTitleFieldName();
                elem = updateUserPage.getFieldName();
                break;
            case "Surname":
                elemLabel = updateUserPage.getTitleFieldSurname();
                elem = updateUserPage.getFieldSurname();
                break;
            case "Email":
                elemLabel = updateUserPage.getTitleFieldEmail();
                elem = updateUserPage.getFieldEmail();
                break;
            case "Position":
                elemLabel = updateUserPage.getTitleFieldPosition();
                elem = updateUserPage.getFieldPosition();
                break;
            default:
                throw new IllegalArgumentException("The " + fieldName + " field is not defined");
        }
        Assert.assertTrue(elemLabel.isDisplayed());
        Assert.assertTrue(elem.isDisplayed());
    }

    @Then("^the user for the \"Update user\" scenario is in the \"Users\" table$")
    public void theUserForTheUpdateUserScenarioIsInTheUsersTable() {
        boolean userFound = false;

        List<WebElement> rows = landingPage.getRowsUsersTable();

        String expectedColumnNameSurname = UpdateUserContext.getUserName() + " " + UpdateUserContext.getUserSurname();
        String expectedColumnEmail = UpdateUserContext.getUserEmail();
        String expectedColumnPosition = UpdateUserContext.getUserPosition();

        for (int row = 1; row <= rows.size(); row++) {

            List<WebElement> columns = landingPage.getCellsUsersTable(row);

            String actualColumnNameSurname = columns.get(0).getText();
            String actualColumnEmail = columns.get(1).getText();
            String actualColumnPosition = columns.get(2).getText();
            if (actualColumnNameSurname.contentEquals(expectedColumnNameSurname) &&
                    actualColumnEmail.contentEquals(expectedColumnEmail) &&
                    actualColumnPosition.contentEquals(expectedColumnPosition)) {
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            throw new NoSuchElementException("There is no corresponding line for new user in the \"Users\" table");
        }
    }
}

