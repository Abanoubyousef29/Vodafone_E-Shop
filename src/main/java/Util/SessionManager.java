package Util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;


public class SessionManager {

    public static void setSessionCookie(WebDriver driver, String cookieName, String cookieValue) {
        Cookie sessionCookie = new Cookie(cookieName, cookieValue);
        driver.manage().addCookie(sessionCookie);
    }

    public static void navigateToAuthenticatedPage(WebDriver driver, String url) {
        driver.get(url);
        driver.navigate().refresh(); // Refresh to apply the cookie if necessary
    }
}