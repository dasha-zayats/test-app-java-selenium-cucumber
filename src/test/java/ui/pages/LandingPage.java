package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends BasePage {

    private final String url = "http://localhost:8081";
    private final By txtListOfUsers = By.tagName("h2");
    private final By btnAddNewUser = By.cssSelector("a[href='add-user.html']");
    private final By headerUsersTable = By.cssSelector("table#users-table > thead");
    private final By tableUsers = By.cssSelector("#users-table");
    private final By rowsUsersTable = By.cssSelector("#users-table-body > tr");
    private final By cellsUsersTable = By.cssSelector("#users-table-body > tr > td");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public WebElement getTxtListOfUsers() {
        return driver.findElement(txtListOfUsers);
    }

    public WebElement getBtnAddNewUser() {
        return driver.findElement(btnAddNewUser);
    }

    public WebElement getHeaderUsersTable() {
        return driver.findElement(headerUsersTable);
    }

    public WebElement getTableUsers() {
        return driver.findElement(tableUsers);
    }

    public List<WebElement> getRowsUsersTable() {
        return (this.getTableUsers()).findElements(rowsUsersTable);
    }

    public List<WebElement> getCellsUsersTable(Number row) {
        return (this.getTableUsers()).findElements(By.cssSelector("#users-table-body >" + "tr:nth-child(" + row + ")" + " > td"));
    }

    public WebElement getBtnUpdateUser(Number row) {
        return (this.getTableUsers()).findElement(By.cssSelector("#users-table-body > tr:nth-child(" + row + ") > td:nth-child(4) > button:nth-child(2)"));
    }

    public WebElement getBtnRemoveUser(Number row) {
        return (this.getTableUsers()).findElement(By.cssSelector("#users-table-body > tr:nth-child(" + row + ") > td:nth-child(4) > button:nth-child(3)"));
    }

}