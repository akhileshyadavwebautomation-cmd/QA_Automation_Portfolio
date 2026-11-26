
import { expect, Locator, Page } from '@playwright/test';

export class InventoryPage {
  readonly title: Locator;
  readonly inventoryItems: Locator;
  readonly cartLink: Locator;
  readonly cartBadge: Locator;
  readonly sortDropdown: Locator;

  constructor(private readonly page: Page) {
    this.title = page.getByText('Products', { exact: true });
    this.inventoryItems = page.locator('.inventory_item');
    this.cartLink = page.locator('.shopping_cart_link');
    this.cartBadge = page.locator('.shopping_cart_badge');
    this.sortDropdown = page.locator(
      '[data-test="product-sort-container"]'
    );
  }

  async expectLoaded() {
    await expect(this.title).toBeVisible();
    await expect(this.inventoryItems.first()).toBeVisible();
  }

  async addFirstProductToCart() {
    const firstItem = this.inventoryItems.first();

    await firstItem
      .getByRole('button', { name: 'Add to cart' })
      .click();
  }

  async addProductToCart(productName: string) {
    const product = this.inventoryItems.filter({
      has: this.page.getByText(productName, { exact: true })
    });

    await expect(product).toHaveCount(1);

    await product
      .getByRole('button', { name: 'Add to cart' })
      .click();
  }

  async expectCartCount(count: number) {
    if (count === 0) {
      await expect(this.cartBadge).toHaveCount(0);
    } else {
      await expect(this.cartBadge).toHaveText(String(count));
    }
  }

  async openCart() {
    await this.cartLink.click();
  }

  async sortBy(value: 'az' | 'za' | 'lohi' | 'hilo') {
    await this.sortDropdown.selectOption(value);
  }
}
