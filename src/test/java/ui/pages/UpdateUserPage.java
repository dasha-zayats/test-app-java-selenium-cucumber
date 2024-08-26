package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateUserPage extends BasePage {
    private final String url = "http://localhost:8081/update-user.html";
    private final By btnUpdateUser = By.cssSelector("button.btn-success");
    private final By btnCancelUpdate = By.cssSelector("button.btn-danger");

    public UpdateUserPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public WebElement getBtnUpdateUser() {
        return driver.findElement(btnUpdateUser);
    }

    public WebElement getBtnCancelUpdate() {
        return driver.findElement(btnCancelUpdate);
    }
}
