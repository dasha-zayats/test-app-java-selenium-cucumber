package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.utils.context.AddNewUserContext;
import ui.utils.models.UserData;

public class AddNewUserPage extends BasePage {

    private final UserData userData = new UserData();

    private final String url = "/add-user.html";

    @FindBy (id = "create-user-button")
    private WebElement btnCreateUser;

    @FindBy (id = "validationError")
    private WebElement errorTxtFillInDetails;

    @FindBy(css = "input#name")
    private WebElement fieldName;

    @FindBy(css = "input#surname")
    private WebElement fieldSurname;

    @FindBy(css = "input#email")
    private WebElement fieldEmail;

    @FindBy(css = "select#position")
    private WebElement fieldPosition;

    @FindBy(css = "label[for='name']")
    private WebElement titleFieldName;

    @FindBy(css = "label[for='surname']")
    private WebElement titleFieldSurname;

    @FindBy(css = "label[for='email']")
    private WebElement titleFieldEmail;

    @FindBy(css = "label[for='position']")
    private WebElement titleFieldPosition;

    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }


    public WebElement getBtnCreateUser() {
        return btnCreateUser;
    }

    public WebElement getErrorTxtFillInDetails() {
        return errorTxtFillInDetails;
    }

    public WebElement getFieldName() {
        return fieldName;
    }

    public WebElement getFieldSurname() {
        return fieldSurname;
    }

    public WebElement getFieldEmail() {
        return fieldEmail;
    }

    public WebElement getFieldPosition() {
        return fieldPosition;
    }

    public WebElement getTitleFieldName() {
        return titleFieldName;
    }

    public WebElement getTitleFieldSurname() {
        return titleFieldSurname;
    }

    public WebElement getTitleFieldEmail() {
        return titleFieldEmail;
    }

    public WebElement getTitleFieldPosition() {
        return titleFieldPosition;
    }

    public void enterUserData(String name, String surname, String email, String position) {
        this.getFieldName().sendKeys(name);
        this.getFieldSurname().sendKeys(surname);
        this.getFieldEmail().sendKeys(email);
        this.getFieldPosition().sendKeys(position);
    }

    public void enterUserDataMissingName( String surname, String email, String position) {
        this.getFieldSurname().sendKeys(surname);
        this.getFieldEmail().sendKeys(email);
        this.getFieldPosition().sendKeys(position);
    }

    public void enterUserDataMissingEmail(String name, String surname, String position) {
        this.getFieldName().sendKeys(name);
        this.getFieldSurname().sendKeys(surname);
        this.getFieldPosition().sendKeys(position);
    }

    public void setUserDataContext(String name, String surname, String email, String position) {
        AddNewUserContext.setUserName(name);
        AddNewUserContext.setUserSurname(surname);
        AddNewUserContext.setUserEmail(email);
        AddNewUserContext.setUserPosition(position);
    }

//    public Boolean isUserInUsersTable(String fullName, String email, String position){
//        boolean userFound = false;
//        List<WebElement> rows = landingPage.getRowsUsersTable();
//
//        for (int row = 1; row <= rows.size(); row++) {
//
//            List<WebElement> columns = landingPage.getCellsUsersTable(row);
//
//            String actualColumnNameSurname = columns.get(0).getText();
//            String actualColumnEmail = columns.get(1).getText();
//            String actualColumnPosition = columns.get(2).getText();
//            if (actualColumnNameSurname.contentEquals(expectedColumnFullName) &&
//                    actualColumnEmail.contentEquals(expectedColumnEmail) &&
//                    actualColumnPosition.contentEquals(expectedColumnPosition)) {
//                userFound = true;
//                break;
//            }
//        }
//        if (!userFound) {
//            throw new NoSuchElementException("There is no corresponding line for new user in the \"Users\" table");
//        }
//    }
}
