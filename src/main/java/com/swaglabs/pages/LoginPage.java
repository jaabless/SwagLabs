package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterCredentials(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public InventoryPage clickLogin() {
        loginButton.click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void navigateTo() {
        driver.get("https://www.saucedemo.com/");
    }

    public boolean isLoginPageDisplayed() {
        return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
    }
}
