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
import ui.utils.context.AddNewUserContext;
import ui.utils.WebDriverSingleton;
import ui.utils.models.UserData;
import ui.utils.Waiters;

import java.util.List;

public class AddNewUser {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final UserData userData = new UserData();
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private final Waiters waiters = new Waiters(driver);

    @Given("^the user navigates to the \"add new user\" page$")
    public void theUserNavigatesToTheAddNewUserPage() {
        waiters.waitAndClick(landingPage.getBtnAddNewUser());
        waiters.waitForPageIsOpened(addNewUserPage.getUrl());
    }

    @When("^the user fills in the user data missing (Name|Email)$")
    public void theUserFillsInTheUserDataMissingNameOrEmail(String fieldName) {
        String valueNameField = userData.getName();
        String valueSurnameField = userData.getSurname();
        String valueEmailField = userData.getEmail();
        String valuePositionField = userData.getPosition();

        addNewUserPage.setUserDataContext(valueNameField,valueSurnameField,valueEmailField,valuePositionField);

        switch (fieldName) {
            case "Name":
                addNewUserPage.enterUserDataMissingName(valueSurnameField, valueEmailField, valuePositionField);
                break;
            case "Email":
                addNewUserPage.enterUserDataMissingEmail(valueNameField,valueSurnameField,valuePositionField);
                break;
            default:
                throw new IllegalArgumentException("The " + fieldName + " field is not defined");
        }
    }

    @When("^the user fills in the user data$")
    public void theUserFillsInTheUserData() {
        String valueNameField = userData.getName();
        String valueSurnameField = userData.getSurname();
        String valueEmailField = userData.getEmail();
        String valuePositionField = userData.getPosition();

        addNewUserPage.enterUserData(valueNameField,valueSurnameField,valueEmailField,valuePositionField);
        addNewUserPage.setUserDataContext(valueNameField,valueSurnameField,valueEmailField,valuePositionField);
    }

    @When("^the user clicks the \"Create user\" button on the \"add new user\" page$")
    public void theUserClicksTheCreateUserButtonOnTheAddNewUserPage() {
        waiters.waitAndClick(addNewUserPage.getBtnCreateUser());
    }

    @When("^the user clicks the \"Test Application\" link$")
    public void theUserClicksTheTestApplicationLink() {
        waiters.waitAndClick(commonPage.getLnkTestApp());
    }

    @Then("^the \"Create user\" button is present and active on the \"add new user\" page$")
    public void theCreateUserButtonIsPresentAndActiveOnTheAddNewUserPage() {
        Assert.assertTrue(addNewUserPage.getBtnCreateUser().isDisplayed());
        Assert.assertTrue(addNewUserPage.getBtnCreateUser().isEnabled());
    }

    @Then("^the \"Fill in all the details\" error appears on the \"add new user\" page$")
    public void theFillInAllTheDetailsErrorAppearsOnTheAddNewUserPage() {
        Assert.assertTrue(addNewUserPage.getErrorTxtFillInDetails().isDisplayed());
    }

    @Then("^the \"Add new user\" text is displayed on the \"add new user\" page$")
    public void theAddNewUserTextIsDisplayedOnTheAddNewUserPage() {
        Assert.assertTrue(commonPage.getTxtAddNewUser().isDisplayed());
    }

    @Then("^the input fields are displayed on the \"add new user\" page$")
    public void theInputFieldsAreDisplayedOnTheAddNewUserPage() {
        Assert.assertTrue(addNewUserPage.getFieldName().isDisplayed());
        Assert.assertTrue(addNewUserPage.getFieldSurname().isDisplayed());
        Assert.assertTrue(addNewUserPage.getFieldEmail().isDisplayed());
        Assert.assertTrue(addNewUserPage.getFieldPosition().isDisplayed());
    }

    @Then("^the \"(Name|Surname|Email|Position)\" (text field|dropdown) is displayed on the \"add new user\" page$")
    public void theNameSurnameEmailPositionTextFieldDropdownIsDisplayedOnTheAddNewUserPage(String fieldName, String fieldType) {
        WebElement elemLabel;
        WebElement elem;
        switch (fieldName) {
            case "Name":
                elemLabel = addNewUserPage.getTitleFieldName();
                elem = addNewUserPage.getFieldName();
                break;
            case "Surname":
                elemLabel = addNewUserPage.getTitleFieldSurname();
                elem = addNewUserPage.getFieldSurname();
                break;
            case "Email":
                elemLabel = addNewUserPage.getTitleFieldEmail();
                elem = addNewUserPage.getFieldEmail();
                break;
            case "Position":
                elemLabel = addNewUserPage.getTitleFieldPosition();
                elem = addNewUserPage.getFieldPosition();
                break;
            default:
                throw new IllegalArgumentException("The " + fieldName + " field is not defined");
        }
        Assert.assertTrue(elemLabel.isDisplayed());
        Assert.assertTrue(elem.isDisplayed());
    }

    @Then("^the user for the \"Add new user\" scenario is in the \"Users\" table$")
    public void theUserForTheAddNewUserScenarioIsInTheUsersTable() {

        String expectedColumnFullName = AddNewUserContext.getUserName() + " " + AddNewUserContext.getUserSurname();
        String expectedColumnEmail = AddNewUserContext.getUserEmail();
        String expectedColumnPosition = AddNewUserContext.getUserPosition();

        boolean userFound = false;

        List<WebElement> rows = landingPage.getRowsUsersTable();

        for (int row = 1; row <= rows.size(); row++) {

            List<WebElement> columns = landingPage.getCellsUsersTable(row);

            String actualColumnNameSurname = columns.get(0).getText();
            String actualColumnEmail = columns.get(1).getText();
            String actualColumnPosition = columns.get(2).getText();
            if (actualColumnNameSurname.contentEquals(expectedColumnFullName) &&
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
