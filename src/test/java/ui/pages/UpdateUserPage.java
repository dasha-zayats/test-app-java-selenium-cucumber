package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.utils.context.AddNewUserContext;
import ui.utils.context.UpdateUserContext;

public class UpdateUserPage extends BasePage {
    private final String url = "/update-user.html";

    @FindBy (css = "button.btn-success")
    private WebElement btnUpdateUser;

    @FindBy (css = "button.btn-danger")
    private WebElement btnCancelUpdate;

    @FindBy (css = "input#name")
    private WebElement fieldName;

    @FindBy (css = "input#surname")
    private WebElement fieldSurname;

    @FindBy (css = "input#email")
    private WebElement fieldEmail;

    @FindBy (css = "select#position")
    private WebElement fieldPosition;

    @FindBy (css = "label[for='name']")
    private WebElement titleFieldName;

    @FindBy (css = "label[for='surname']")
    private WebElement titleFieldSurname;

    @FindBy (css = "label[for='email']")
    private WebElement titleFieldEmail;

    @FindBy (css = "label[for='position']")
    private WebElement titleFieldPosition;

    public UpdateUserPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public WebElement getBtnUpdateUser() {
        return btnUpdateUser;
    }

    public WebElement getBtnCancelUpdate() {
        return btnCancelUpdate;
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

    public void setUserDataContext(String name, String surname, String email, String position) {
        UpdateUserContext.setUserName(name);
        UpdateUserContext.setUserSurname(surname);
        UpdateUserContext.setUserEmail(email);
        UpdateUserContext.setUserPosition(position);
    }
}
