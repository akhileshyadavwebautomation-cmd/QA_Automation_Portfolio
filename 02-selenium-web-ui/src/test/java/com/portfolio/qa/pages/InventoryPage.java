package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By title = By.cssSelector(".title");
    private final By firstAddButton = By.cssSelector(".inventory_item button");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By sort = By.cssSelector("[data-test='product-sort-container']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText().equals("Products");
    }

    public void addFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddButton)).click();
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public void openCart() { driver.findElement(cartLink).click(); }

    public void sortLowToHigh() {
        new Select(driver.findElement(sort)).selectByValue("lohi");
    }
}
