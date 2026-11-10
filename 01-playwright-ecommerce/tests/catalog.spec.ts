import { test, expect } from '../fixtures/test-fixtures';
import { users } from '../test-data/users';

test('inventory can be sorted by price low to high', async ({ loginPage, inventoryPage, page }) => {
  await loginPage.goto();
  await loginPage.login(users.standard.username, users.standard.password);
  await inventoryPage.sortBy('lohi');

  const prices = await page.locator('.inventory_item_price').allTextContents();
  const numericPrices = prices.map(price => Number(price.replace('$', '')));
  expect(numericPrices).toEqual([...numericPrices].sort((a, b) => a - b));
});
