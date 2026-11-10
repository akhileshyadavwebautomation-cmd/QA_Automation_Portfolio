package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.InventoryPage;
import com.portfolio.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    public void userCanAddProductToCart() {
        test = extent.createTest("User can add product to cart");
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertTrue(inventory.isLoaded());
        inventory.addFirstItem();
        Assert.assertEquals(inventory.getCartCount(), "1");
        inventory.openCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}
