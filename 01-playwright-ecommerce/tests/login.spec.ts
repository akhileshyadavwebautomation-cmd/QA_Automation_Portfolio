import { test, expect } from '../fixtures/test-fixtures';
import { users } from '../test-data/users';

test.describe('Login', () => {
  test('standard user can log in', async ({ loginPage, inventoryPage }) => {
    await loginPage.goto();
    await loginPage.login(users.standard.username, users.standard.password);
    await inventoryPage.expectLoaded();
  });

  test('locked user sees an error', async ({ loginPage }) => {
    await loginPage.goto();
    await loginPage.login(users.locked.username, users.locked.password);
    await loginPage.expectError('Sorry, this user has been locked out');
  });

  test('invalid credentials show an error', async ({ loginPage }) => {
    await loginPage.goto();
    await loginPage.login(users.invalid.username, users.invalid.password);
    await expect(loginPage.errorMessage).toBeVisible();
  });
});
