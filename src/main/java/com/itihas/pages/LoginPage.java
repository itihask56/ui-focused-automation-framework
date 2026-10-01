package com.itihas.pages;

import com.itihas.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WaitUtils wait;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.wait = new WaitUtils(driver);
    }

    public void enterUsername(String username) {

        wait.waitForVisibility(usernameField)
                .sendKeys(username);
    }

    public void enterPassword(String password) {

        wait.waitForVisibility(passwordField)
                .sendKeys(password);
    }

    public void clickLogin() {

        wait.waitForClickable(loginButton)
                .click();
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}