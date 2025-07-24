package com.swaglabs.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeProduct(String productName) {
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button[text()='Remove']"));
        click(removeButton);
    }

    public String getCartItemCount() {
        try {
            return getElementText(cartIcon.findElement(By.className("shopping_cart_badge")));
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public boolean isProductInCart(String productName) {
        return cartItems.stream().anyMatch(item -> item.findElement(By.className("inventory_item_name")).getText().equals(productName));
    }
}
