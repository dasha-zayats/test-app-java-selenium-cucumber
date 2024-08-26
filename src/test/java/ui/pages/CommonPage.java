package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage extends BasePage {

    private final By h1Header = By.cssSelector("h1");
    private final By txtEnjoyYourTestAutomationJourney = By.cssSelector("p.lead");
    private final By lnkTestApp = By.cssSelector("a.navbar-brand[href='index.html']");
    private final By txtCopyright = By.cssSelector("p.m-0");
    private final By txtAddNewUser = By.tagName("h2");
    private final By fieldName = By.cssSelector("input#name");
    private final By fieldSurname = By.cssSelector("input#surname");
    private final By fieldEmail = By.cssSelector("input#email");
    private final By fieldPosition = By.cssSelector("select#position");
    private final By titleFieldName = By.cssSelector("label[for='name']");
    private final By titleFieldSurname = By.cssSelector("label[for='surname']");
    private final By titleFieldEmail = By.cssSelector("label[for='email']");
    private final By titleFieldPosition = By.cssSelector("label[for='position']");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getH1Header() {
        return driver.findElement(h1Header);
    }

    public WebElement getTxtEnjoyYourTestAutomationJourney() {
        return driver.findElement(txtEnjoyYourTestAutomationJourney);
    }

    public WebElement getLnkTestApp() {
        return driver.findElement(lnkTestApp);
    }

    public WebElement getTxtCopyright() {
        return driver.findElement(txtCopyright);
    }

    public WebElement getTxtAddNewUser() {
        return driver.findElement(txtAddNewUser);
    }

    public WebElement getFieldName() {
        return driver.findElement(fieldName);
    }

    public WebElement getFieldSurname() {
        return driver.findElement(fieldSurname);
    }

    public WebElement getFieldEmail() {
        return driver.findElement(fieldEmail);
    }

    public WebElement getFieldPosition() {
        return driver.findElement(fieldPosition);
    }

    public WebElement getTitleFieldName() {
        return driver.findElement(titleFieldName);
    }

    public WebElement getTitleFieldSurname() {
        return driver.findElement(titleFieldSurname);
    }

    public WebElement getTitleFieldEmail() {
        return driver.findElement(titleFieldEmail);
    }

    public WebElement getTitleFieldPosition() {
        return driver.findElement(titleFieldPosition);
    }
}
