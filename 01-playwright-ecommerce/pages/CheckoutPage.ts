
import { expect, Locator, Page } from '@playwright/test';

export class CheckoutPage {
  readonly pageTitle: Locator;
  readonly firstName: Locator;
  readonly lastName: Locator;
  readonly postalCode: Locator;
  readonly continueButton: Locator;
  readonly finishButton: Locator;
  readonly cancelButton: Locator;
  readonly errorMessage: Locator;
  readonly summaryItems: Locator;
  readonly confirmationMessage: Locator;

  constructor(private readonly page: Page) {
    this.pageTitle = page.locator('.title');

    this.firstName = page.getByTestId('firstName');
    this.lastName = page.getByTestId('lastName');
    this.postalCode = page.getByTestId('postalCode');

    this.continueButton = page.getByRole('button', {
      name: 'Continue'
    });

    this.finishButton = page.getByRole('button', {
      name: 'Finish'
    });

    this.cancelButton = page.getByRole('button', {
      name: 'Cancel'
    });

    this.errorMessage = page.locator('[data-test="error"]');
    this.summaryItems = page.locator('.cart_item');

    this.confirmationMessage = page.getByText(
      'Thank you for your order!',
      { exact: true }
    );
  }

  async expectInformationPageLoaded() {
    await expect(this.pageTitle).toHaveText(
      'Checkout: Your Information'
    );
  }

  async enterCustomerDetails(
    first: string,
    last: string,
    postalCode: string
  ) {
    await this.firstName.fill(first);
    await this.lastName.fill(last);
    await this.postalCode.fill(postalCode);

    await this.continueButton.click();
  }

  async expectCheckoutOverview() {
    await expect(this.pageTitle).toHaveText(
      'Checkout: Overview'
    );
  }

  async expectOrderItemCount(count: number) {
    await expect(this.summaryItems).toHaveCount(count);
  }

  async expectError(message: string) {
    await expect(this.errorMessage).toBeVisible();
    await expect(this.errorMessage).toContainText(message);
  }

  async finishOrder() {
    await this.finishButton.click();
  }

  async expectOrderConfirmation() {
    await expect(this.pageTitle).toHaveText(
      'Checkout: Complete!'
    );

    await expect(this.confirmationMessage).toBeVisible();
  }

  async cancelCheckout() {
    await this.cancelButton.click();
  }
}
