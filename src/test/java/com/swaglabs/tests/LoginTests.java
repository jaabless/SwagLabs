package com.swaglabs.tests;

import com.swaglabs.base.BaseTest;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.data.LoginTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.LoginTestData#validLoginTestData")
    @Description("Test login functionality with various credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithValidData(String username, String password, boolean shouldSucceed, String expectedResult) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.navigateTo();
        loginPage.enterCredentials(username, password);
        InventoryPage inventoryPage = loginPage.clickLogin();
        assertTrue(inventoryPage.isInventoryPageDisplayed(), "Expected to be on inventory page after successful login");

    }

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.LoginTestData#invalidLoginTestData")
    @Description("Test login functionality with various credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidData(String username, String password, boolean shouldSucceed, String expectedResult) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.navigateTo();
        loginPage.enterCredentials(username, password);
        InventoryPage inventoryPage = loginPage.clickLogin();


        if (shouldSucceed) {
            assertTrue(inventoryPage.isInventoryPageDisplayed(), "Expected to be on inventory page after successful login");
        } else {
            assertEquals(expectedResult, loginPage.getErrorMessage(), "Unexpected error message");
        }
    }

    @Test
    @Description("Verify logout functionality after successful login")
    @Severity(SeverityLevel.NORMAL)
    public void testLogout() {
        loginPage.enterCredentials("standard_user", "secret_sauce");
        InventoryPage inventoryPage = loginPage.clickLogin();

        assertTrue(inventoryPage.isInventoryPageDisplayed(), "Login failed, inventory page not displayed");

        inventoryPage.logout();
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be on login page after logout");
    }

    @Test
    @Description("Verify restricted page access without login")
    @Severity(SeverityLevel.NORMAL)
    public void testRestrictedPageAccess() {
        driver.get("https://www.saucedemo.com/inventory.html");
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be redirected to login page");
    }

    @Test
    @Description("Verify login page displays form correctly")
    @Severity(SeverityLevel.MINOR)
    public void testLoginPageFormDisplay() {
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page form not displayed correctly");
    }
}
