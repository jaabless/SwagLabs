package com.swaglabs.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.base.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private void login() {
        loginPage.enterCredentials("standard_user", "secret_sauce");
        loginPage.clickLogin();
    }

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.CartTestData#cartTestData")
    @DisplayName("Test adding products to cart and verifying cart count")
    @Story("Add to Cart Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddToCart(String products, String expectedCount, boolean addToCart) {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        String[] productList = products.split(",");

        if (addToCart) {
            for (String product : productList) {
                inventoryPage.addProductToCart(product.trim());
            }
        }
        CartPage cartPage = inventoryPage.clickCartIcon();
        assertEquals(expectedCount, cartPage.getCartItemCount(), "Unexpected cart item count");

        if (addToCart) {
            for (String product : productList) {
                assertTrue(cartPage.isProductInCart(product.trim()), "Product " + product + " not found in cart");
            }
        }
    }

    @Test
    @Description("Verify removing product from cart")
    @DisplayName("Test removing product from cart")
    @Story("Remove from Cart Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveFromCart() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartPage = inventoryPage.clickCartIcon();
        cartPage.removeProduct("Sauce Labs Backpack");
        assertEquals("0", cartPage.getCartItemCount(), "Cart should be empty after removing product");
        assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"), "Product should not be in cart");
    }

    @Test
    @Description("Verify checkout button is disabled with empty cart")
    @DisplayName("Test checkout button with empty cart")
    @Story("Checkout Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckoutButtonWithEmptyCart() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        assertFalse(cartPage.isCheckoutButtonEnabled(), "Checkout button should be disabled for empty cart");
    }

    @Test
    @Description("Verify cart page displays items and checkout button")
    @DisplayName("Test cart page display with items")
    @Story("Cart Page Display")
    @Severity(SeverityLevel.NORMAL)
    public void testCartPageDisplay() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Product should be displayed in cart");
        assertTrue(cartPage.isCheckoutButtonEnabled(), "Checkout button should be enabled");
    }

    @Test
    @Description("Verify adding to cart without login")
    @DisplayName("Test add to cart without login")
    @Story("Add to Cart Without Login")
    @Severity(SeverityLevel.NORMAL)
    public void testAddToCartWithoutLogin() {
        driver.get("https://www.saucedemo.com/inventory.html");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be redirected to login page");
    }
}