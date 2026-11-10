import { test } from '../fixtures/test-fixtures';
import { users } from '../test-data/users';

test.describe('Cart and checkout', () => {
  test.beforeEach(async ({ loginPage }) => {
    await loginPage.goto();
    await loginPage.login(users.standard.username, users.standard.password);
  });

  test('user can add a product to cart', async ({ inventoryPage, cartPage }) => {
    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();
    await cartPage.expectItemCount(1);
  });

  test('user can complete checkout', async ({ inventoryPage, cartPage, checkoutPage }) => {
    await inventoryPage.addFirstProductToCart();
    await inventoryPage.openCart();
    await cartPage.checkout();
    await checkoutPage.enterCustomerDetails('Akhilesh', 'QA', '243001');
    await checkoutPage.finishOrder();
    await checkoutPage.expectOrderConfirmation();
  });
});
