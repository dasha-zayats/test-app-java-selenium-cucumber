package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import config.ConfigReader;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LandingPage extends BasePage {

    static String environment = System.getProperty("env");
    static ConfigReader config = new ConfigReader(environment);

    private final String url = config.getProperty("frontend.url");

    @FindBy (tagName = "h2")
    private WebElement txtListOfUsers;

    @FindBy (css = "a[href='add-user.html']")
    private WebElement btnAddNewUser;

    @FindBy (css = "table#users-table > thead")
    private WebElement headerUsersTable;

    @FindBy (id = "users-table")
    private WebElement tableUsers;

    @FindBy (css = "#users-table-body > tr")
    private List<WebElement> rowsUsersTable;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public WebElement getTxtListOfUsers() {
        return txtListOfUsers;
    }

    public WebElement getBtnAddNewUser() {
        return btnAddNewUser;
    }

    public WebElement getHeaderUsersTable() {
        return headerUsersTable;
    }

    public WebElement getTableUsers() {
        return tableUsers;
    }

    public List<WebElement> getRowsUsersTable() {
        return rowsUsersTable;
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