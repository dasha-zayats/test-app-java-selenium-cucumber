package ui.utils;

import org.openqa.selenium.WebDriver;
import ui.pages.AddNewUserPage;
import ui.pages.LandingPage;
import ui.pages.UpdateUserPage;

import java.util.HashMap;
import java.util.Map;

public class Pages {

    private static final Map<String, String> pageUrls = new HashMap<>();

    private static final WebDriver driver = WebDriverSingleton.getDriver();
    private static final LandingPage landingPage = new LandingPage(driver);
    private static final AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    private static final UpdateUserPage updateUserPage = new UpdateUserPage(driver);

    static {
        pageUrls.put("main", landingPage.getUrl());
        pageUrls.put("add new user", addNewUserPage.getUrl());
        pageUrls.put("update user", updateUserPage.getUrl());
    }

    public static String getUrl(String pageName) {
        return pageUrls.get(pageName);
    }
}
