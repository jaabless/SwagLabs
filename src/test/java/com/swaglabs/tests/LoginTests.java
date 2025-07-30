package com.swaglabs.tests;

import com.swaglabs.base.BaseTest;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.data.LoginTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.LoginTestData#validLoginTestData")
    @Story("Login Functionality")
    @DisplayName("Test login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithValidData(String username, String password, boolean shouldSucceed, String expectedResult) {
        loginPage.enterCredentials(username, password);
        InventoryPage inventoryPage = loginPage.clickLogin();
        assertTrue(inventoryPage.isInventoryPageDisplayed(), "Expected to be on inventory page after successful login");

    }

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.LoginTestData#invalidLoginTestData")
    @Story("Login Functionality")
    @DisplayName("Test login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidData(String username, String password, boolean shouldSucceed, String expectedResult) {
        loginPage.enterCredentials(username, password);
        InventoryPage inventoryPage = loginPage.clickLogin();
        if (shouldSucceed) {
            assertTrue(inventoryPage.isInventoryPageDisplayed(), "Expected to be on inventory page after successful login");
        } else {
            assertEquals(expectedResult, loginPage.getErrorMessage(), "Unexpected error message");
        }
    }


    @Test
    @DisplayName("Verify restricted page access without login")
    @Story("Restricted Page Access")
    @Severity(SeverityLevel.NORMAL)
    public void testRestrictedPageAccess() {
        driver.get("https://www.saucedemo.com/inventory.html");
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be redirected to login page");
    }

    @Test
    @DisplayName("Verify login page displays form correctly")
    @Story("Login Page Form Display")
    @Severity(SeverityLevel.MINOR)
    public void testLoginPageFormDisplay() {
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page form not displayed correctly");
    }
}
