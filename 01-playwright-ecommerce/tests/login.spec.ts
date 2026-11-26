
import { test, expect } from '../fixtures/test-fixtures';
import { users } from '../test-data/users';

test.describe('Login Functionality', () => {

  test.beforeEach(async ({ loginPage }) => {
    await loginPage.goto();
  });

  test('TC_LOGIN_001 - Standard user can log in',
    async ({ loginPage, inventoryPage }) => {

      await loginPage.login(
        users.standard.username,
        users.standard.password
      );

      await inventoryPage.expectLoaded();
    }
  );

  test('TC_LOGIN_002 - Locked user sees error',
    async ({ loginPage }) => {

      await loginPage.login(
        users.locked.username,
        users.locked.password
      );

      await loginPage.expectError(
        'Sorry, this user has been locked out'
      );
    }
  );

  test('TC_LOGIN_003 - Invalid credentials show error',
    async ({ loginPage }) => {

      await loginPage.login(
        users.invalid.username,
        users.invalid.password
      );

      await expect(loginPage.errorMessage).toBeVisible();
    }
  );

});
