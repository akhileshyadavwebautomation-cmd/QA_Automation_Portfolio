
import { expect, Locator, Page } from '@playwright/test';

export class CartPage {
  readonly pageTitle: Locator;
  readonly cartItems: Locator;
  readonly checkoutButton: Locator;
  readonly continueShoppingButton: Locator;

  constructor(private readonly page: Page) {
    this.pageTitle = page.getByText('Your Cart', { exact: true });
    this.cartItems = page.locator('.cart_item');

    this.checkoutButton = page.getByRole('button', {
      name: 'Checkout'
    });

    this.continueShoppingButton = page.getByRole('button', {
      name: 'Continue Shopping'
    });
  }

  async expectLoaded() {
    await expect(this.pageTitle).toBeVisible();
  }

  async expectItemCount(count: number) {
    await expect(this.cartItems).toHaveCount(count);
  }

  async expectProductVisible(productName: string) {
    const product = this.cartItems.filter({
      has: this.page.getByText(productName, { exact: true })
    });

    await expect(product).toHaveCount(1);
  }

  async expectProductPrice(productName: string, price: string) {
    const product = this.cartItems.filter({
      has: this.page.getByText(productName, { exact: true })
    });

    await expect(
      product.locator('.inventory_item_price')
    ).toHaveText(price);
  }

  async removeProduct(productName: string) {
    const product = this.cartItems.filter({
      has: this.page.getByText(productName, { exact: true })
    });

    await expect(product).toHaveCount(1);

    await product.getByRole('button', {
      name: 'Remove'
    }).click();
  }

  async continueShopping() {
    await this.continueShoppingButton.click();
  }

  async checkout() {
    await this.checkoutButton.click();
  }
}
