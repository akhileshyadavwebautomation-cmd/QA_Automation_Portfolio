
import { test, expect } from '../fixtures/test-fixtures';
import { users } from '../test-data/users';

test.describe('Cart and Checkout Functionality', () => {

  test.beforeEach(async ({ loginPage, inventoryPage }) => {
    await loginPage.goto();

    await loginPage.login(
      users.standard.username,
      users.standard.password
    );

    await inventoryPage.expectLoaded();
  });

  test('User can add a product to cart', async ({
    inventoryPage,
    cartPage
  }) => {

    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();

    await cartPage.expectLoaded();
    await cartPage.expectItemCount(1);
    await cartPage.expectProductVisible('Sauce Labs Backpack');
  });

  test('User can remove a product from cart', async ({
    inventoryPage,
    cartPage
  }) => {

    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();

    await cartPage.expectItemCount(1);

    await cartPage.removeProduct('Sauce Labs Backpack');

    await cartPage.expectItemCount(0);
  });

  test('User can complete checkout successfully', async ({
    inventoryPage,
    cartPage,
    checkoutPage
  }) => {

    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();

    await cartPage.expectItemCount(1);
    await cartPage.checkout();

    await checkoutPage.expectInformationPageLoaded();

    await checkoutPage.enterCustomerDetails(
      'Akhilesh',
      'Yadav',
      '243001'
    );

    await checkoutPage.expectCheckoutOverview();
    await checkoutPage.expectOrderItemCount(1);

    await checkoutPage.finishOrder();

    await checkoutPage.expectOrderConfirmation();
  });

  test('Checkout should display error for empty customer details', async ({
    inventoryPage,
    cartPage,
    checkoutPage
  }) => {

    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();

    await cartPage.checkout();

    await checkoutPage.enterCustomerDetails('', '', '');

    await checkoutPage.expectError(
      'Error: First Name is required'
    );
  });

});
