package com.portfolio.qa.tests;

import com.aventstack.extentreports.Status;
import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.InventoryPage;
import com.portfolio.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void standardUserCanLogin() {
        test = extent.createTest("Standard user can log in");
        new LoginPage(driver).login("standard_user", "secret_sauce");
        Assert.assertTrue(new InventoryPage(driver).isLoaded(), "Inventory page should be displayed");
    }

    @Test
    public void lockedUserCannotLogin() {
        test = extent.createTest("Locked user sees validation message");
        LoginPage login = new LoginPage(driver);
        login.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(login.getErrorText().contains("locked out"), "Expected locked-out validation");
    }
}
