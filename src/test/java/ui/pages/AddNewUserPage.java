package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewUserPage extends BasePage {

    private final String url = "http://localhost:8081/add-user.html";
    private final By btnCreateUser = By.id("create-user-button");
    private final By errorTxtFillInDetails = By.id("validationError");

    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public WebElement getBtnCreateUser() {
        return driver.findElement(btnCreateUser);
    }

    public WebElement getErrorTxtFillInDetails() {
        return driver.findElement(errorTxtFillInDetails);
    }
}
