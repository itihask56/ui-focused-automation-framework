package com.itihas;
import com.itihas.base.BaseTest;
import com.itihas.driver.DriverFactory;
import com.itihas.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "User was not redirected to inventory page"
        );
    }
}