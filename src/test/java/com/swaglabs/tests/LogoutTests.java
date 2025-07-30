package com.swaglabs.tests;

import com.swaglabs.base.BaseTest;
import com.swaglabs.pages.InventoryPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTests extends BaseTest {

    @Test
    @DisplayName("Verify logout functionality after successful login")
    @Story("Logout Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testLogout() {
        loginPage.enterCredentials("standard_user", "secret_sauce");
        InventoryPage inventoryPage = loginPage.clickLogin();

        assertTrue(inventoryPage.isInventoryPageDisplayed(), "Login failed, inventory page not displayed");

        inventoryPage.logout();
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be on login page after logout");
    }
}
