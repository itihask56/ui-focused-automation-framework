package com.itihas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static void main(String[] args) {

        // 1. Launch browser
        WebDriver driver = new ChromeDriver();

        // 2. Maximize browser
        driver.manage().window().maximize();

        // 3. Open application
        driver.get("https://www.saucedemo.com/");

        // 4. Enter username
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        // 5. Enter password
        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        // 6. Click login
        driver.findElement(By.id("login-button"))
                .click();

        // 7. Verify login
        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.contains("inventory.html")) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        // 8. Close browser
        driver.quit();
    }
}