package com.itihas;

import com.itihas.base.BaseTest;
import com.itihas.pages.LoginPage;
import com.itihas.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

        @Test
        public void placeOrder() {

                driver.get("https://www.saucedemo.com/");

                WaitUtils wait = new WaitUtils(driver);

                // Login
                LoginPage loginPage = new LoginPage(driver);

                loginPage.login(
                        "standard_user",
                        "secret_sauce"
                );

                // Add product
                wait.waitForClickable(By.id("add-to-cart-sauce-labs-backpack")).click();

                // Open cart
                wait.waitForClickable(By.className("shopping_cart_link")).click();

                // Verify product
                String productName = wait.waitForVisibility(By.className("inventory_item_name")).getText();

                Assert.assertEquals(productName, "Sauce Labs Backpack");

                // Checkout
                wait.waitForClickable(By.id("checkout")).click();

                // Customer information
                wait.waitForVisibility(By.id("first-name")).sendKeys("Itihas");

                wait.waitForVisibility(By.id("last-name")).sendKeys("Verma");

                wait.waitForVisibility(By.id("postal-code")).sendKeys("201301");

                // Continue
                wait.waitForClickable(By.id("continue")).click();

                // Verify checkout product
                String checkoutProduct = wait.waitForVisibility(By.className("inventory_item_name")).getText();

                Assert.assertEquals(
                        checkoutProduct,
                        "Sauce Labs Backpack"
                );

                // Finish
                wait.waitForClickable(By.id("finish")).click();

                // Verify confirmation
                String confirmationMessage = wait.waitForVisibility(By.className("complete-header")).getText();

                Assert.assertEquals(
                        confirmationMessage,
                        "Thank you for your order!"
                );
        }
}