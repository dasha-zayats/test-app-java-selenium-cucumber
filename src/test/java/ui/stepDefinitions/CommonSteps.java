package ui.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.pages.AddNewUserPage;
import ui.pages.CommonPage;
import ui.pages.LandingPage;
import ui.pages.UpdateUserPage;
import ui.utils.context.UpdateUserContext;
import ui.utils.context.AddNewUserContext;
import ui.utils.WebDriverSingleton;
import ui.utils.helpers.Waiters;
import java.util.List;


public class CommonSteps {

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final LandingPage landingPage = new LandingPage(driver);
    private final CommonPage commonPage = new CommonPage(driver);
    private final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private final UpdateUserPage updateUserPage = new UpdateUserPage(driver);
    private  final Waiters waiters = new Waiters(driver);

    @Given("^the user is on the main page$")
    public void the_user_is_on_the_main_page() {
        driver.get(landingPage.getUrl());
        waiters.waitForPageIsOpened(landingPage.getUrl());
    }

    @Then("^the title is displayed on the \"(add new user|main|update user)\" page$")
    public void the_title_is_displayed_on_the_add_new_user_main_update_user_page(String pageName) {
        Assert.assertTrue(commonPage.getH1Header().isDisplayed());
        Assert.assertTrue(commonPage.getTxtEnjoyYourTestAutomationJourney().isDisplayed());
    }

    @Then("^the \"Test Application\" link is displayed on the \"(add new user|main|update user)\" page$")
    public void the_test_application_link_is_displayed_on_the_main_add_new_user_update_user_page(String pageName) {
        Assert.assertTrue(commonPage.getLnkTestApp().isDisplayed());
    }

    @Then("^the copyright statement is displayed on the \"(add new user|main|update user)\" page$")
    public void the_copyright_statement_is_displayed_on_the_add_new_user_main_update_user_page(String pageName) {
        Assert.assertTrue(commonPage.getTxtCopyright().isDisplayed());
    }

    @Then("^the \"(add new user|main|update user)\" page is opened$")
    public void the_page_is_opened(String pageName) {
        String pageUrl;
        switch (pageName) {
            case "add new user":
                pageUrl = addNewUserPage.getUrl();
                break;
            case "main":
                pageUrl = landingPage.getUrl();
                break;
            case "update user":
                pageUrl = updateUserPage.getUrl();
                break;
            default:
                throw new IllegalArgumentException("The " + pageName + " page is not defined");
        }
        waiters.waitForPageIsOpened(pageUrl);
    }

    @Then("^the \"Add new user\" text is displayed on the \"(add new user|update user)\" page$")
    public void the_add_new_user_text_is_displayed_on_the_add_new_user_update_user_page(String pageName) {
        Assert.assertTrue(commonPage.getTxtAddNewUser().isDisplayed());
    }

    @Then("^the input fields are displayed on the \"(add new user|update user)\" page$")
    public void the_input_fields_are_displayed_on_the_add_new_user_update_user_page(String pageName) {
        Assert.assertTrue(commonPage.getFieldName().isDisplayed());
        Assert.assertTrue(commonPage.getFieldSurname().isDisplayed());
        Assert.assertTrue(commonPage.getFieldEmail().isDisplayed());
        Assert.assertTrue(commonPage.getFieldPosition().isDisplayed());
    }

    @Then("^the \"(Name|Surname|Email|Position)\" (text field|dropdown) is displayed on the \"(add new user|update user)\" page$")
    public void the_name_surname_email_position_text_field_dropdown_is_displayed_on_the_add_new_user_update_user_page(String fieldName, String fieldType,String pageName) {
        WebElement elemLabel;
        WebElement elem;
        switch (fieldName) {
            case "Name":
                elemLabel = commonPage.getTitleFieldName();
                elem = commonPage.getFieldName();
                break;
            case "Surname":
                elemLabel = commonPage.getTitleFieldSurname();
                elem = commonPage.getFieldSurname();
                break;
            case "Email":
                elemLabel = commonPage.getTitleFieldEmail();
                elem = commonPage.getFieldEmail();
                break;
            case "Position":
                elemLabel = commonPage.getTitleFieldPosition();
                elem = commonPage.getFieldPosition();
                break;
            default:
                throw new IllegalArgumentException("The " + fieldName + " field is not defined");
        }
        Assert.assertTrue(elemLabel.isDisplayed());
        Assert.assertTrue(elem.isDisplayed());
    }

    @Then("^the user for the \"(Add new user|Update user)\" scenario is in the \"Users\" table$")
    public void the_user_is_in_the_users_table(String scenarioName) {
        boolean userFound = false;

        List<WebElement> rows = landingPage.getRowsUsersTable();

        String expectedColumnNameSurname;
        String expectedColumnEmail;
        String expectedColumnPosition;

        if (scenarioName.contentEquals("Add new user")){
            expectedColumnNameSurname = AddNewUserContext.getUserName() + " " + AddNewUserContext.getUserSurname();
            expectedColumnEmail = AddNewUserContext.getUserEmail();
            expectedColumnPosition = AddNewUserContext.getUserPosition();
        } else if (scenarioName.contentEquals("Update user")){
            expectedColumnNameSurname = UpdateUserContext.getUserName() + " " + UpdateUserContext.getUserSurname();
            expectedColumnEmail = UpdateUserContext.getUserEmail();
            expectedColumnPosition = UpdateUserContext.getUserPosition();
        } else {
            throw new IllegalArgumentException("The " + scenarioName + " scenario is not defined");
        }

        for (int row = 1; row <= rows.size(); row++ ) {

            List<WebElement> columns = landingPage.getCellsUsersTable(row);

            String actualColumnNameSurname = columns.get(0).getText();
            String actualColumnEmail = columns.get(1).getText();
            String actualColumnPosition = columns.get(2).getText();
            if (actualColumnNameSurname.contentEquals(expectedColumnNameSurname) && actualColumnEmail.contentEquals(expectedColumnEmail) && actualColumnPosition.contentEquals(expectedColumnPosition)) {
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            throw new NoSuchElementException("There is no corresponding line for new user in the \"Users\" table");
        }
    }
}
