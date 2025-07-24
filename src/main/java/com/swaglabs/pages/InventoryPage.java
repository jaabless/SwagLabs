package com.swaglabs.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(String productName) {
        WebElement addButton = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']"));
        click(addButton);
    }

    public String getCartItemCount() {
        return getElementText(cartIcon.findElement(By.className("shopping_cart_badge")));
    }

    public CartPage clickCartIcon() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }

    public boolean isInventoryPageDisplayed() {
        return !inventoryItems.isEmpty();
    }
}
