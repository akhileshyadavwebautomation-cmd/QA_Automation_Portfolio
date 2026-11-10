import { expect, Page } from '@playwright/test';

export class CheckoutPage {
  constructor(private readonly page: Page) {}

  async enterCustomerDetails(first: string, last: string, postalCode: string) {
    await this.page.getByTestId('firstName').fill(first);
    await this.page.getByTestId('lastName').fill(last);
    await this.page.getByTestId('postalCode').fill(postalCode);
    await this.page.getByRole('button', { name: 'Continue' }).click();
  }

  async finishOrder() {
    await this.page.getByRole('button', { name: 'Finish' }).click();
  }

  async expectOrderConfirmation() {
    await expect(this.page.getByText('Thank you for your order!')).toBeVisible();
  }
}
