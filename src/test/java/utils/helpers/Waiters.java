package utils.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Waiters {

    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public Waiters (WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageIsOpened(String url){
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertTrue(driver.getCurrentUrl().contains(url));
    }

    public void waitAndClick(WebElement elem){
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        actions.moveToElement(elem).click().perform();
    }
}
