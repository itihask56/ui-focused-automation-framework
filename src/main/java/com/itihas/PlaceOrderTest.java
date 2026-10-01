package com.itihas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class PlaceOrderTest {

    public static void main(String[] args) {

        // -----------------------------
        // Browser configuration
        // -----------------------------

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Disable password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        // Disable autofill
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // -----------------------------
        // Open application
        // -----------------------------

        driver.get("https://www.saucedemo.com/");

        // -----------------------------
        // Login
        // -----------------------------

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("password")
        )).sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("login-button")
        )).click();

        // -----------------------------
        // Add product to cart
        // -----------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack")
        )).click();

        // -----------------------------
        // Open cart
        // -----------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link")
        )).click();

        // -----------------------------
        // Verify product
        // -----------------------------

        String productName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item_name")
                )
        ).getText();

        if (productName.equals("Sauce Labs Backpack")) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("Product was not added");
        }

        // -----------------------------
        // Checkout
        // -----------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("checkout")
        )).click();

        // -----------------------------
        // Customer information
        // -----------------------------

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first-name")
        )).sendKeys("Itihas");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("last-name")
        )).sendKeys("Verma");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("postal-code")
        )).sendKeys("201301");

        // -----------------------------
        // Continue
        // -----------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue")
        )).click();

        // -----------------------------
        // Verify checkout overview
        // -----------------------------

        String checkoutProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item_name")
                )
        ).getText();

        if (checkoutProduct.equals("Sauce Labs Backpack")) {
            System.out.println("Checkout product verified");
        } else {
            System.out.println("Checkout product verification failed");
        }

        // -----------------------------
        // Finish order
        // -----------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("finish")
        )).click();

        // -----------------------------
        // Verify order confirmation
        // -----------------------------

        String confirmationMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        ).getText();

        if (confirmationMessage.equals("Thank you for your order!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }

        // -----------------------------
        // Close browser
        // -----------------------------

        driver.quit();
    }
}