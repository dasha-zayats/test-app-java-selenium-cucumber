package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends BasePage {

    @FindBy(tagName  = "h1")
    private WebElement h1Header;

    @FindBy(css = "p.lead")
    private WebElement txtEnjoyYourTestAutomationJourney;

    @FindBy(css = "a.navbar-brand[href='index.html']")
    private WebElement lnkTestApp;

    @FindBy(css = "p.m-0")
    private WebElement txtCopyright;

    @FindBy(tagName = "h2")
    private WebElement txtAddNewUser;

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getH1Header() {
        return h1Header;
    }

    public WebElement getTxtEnjoyYourTestAutomationJourney() {
        return txtEnjoyYourTestAutomationJourney;
    }

    public WebElement getLnkTestApp() {
        return lnkTestApp;
    }

    public WebElement getTxtCopyright() {
        return txtCopyright;
    }

    public WebElement getTxtAddNewUser() {
        return txtAddNewUser;
    }

}
