# E-Commerce UI Automation — Playwright + TypeScript

A maintainable UI automation framework using Playwright Test, TypeScript, Page Object Model, custom fixtures, data separation, cross-browser projects and HTML reporting.

## Application
https://www.saucedemo.com/

Demo credentials are stored in `test-data/users.ts` and are publicly documented by the demo application. Do not use these patterns for real customer credentials.

## Prerequisites
- Node.js 20+
- npm

## Setup
```bash
npm install
npx playwright install
```

## Execute
```bash
npm test
npm run test:headed
npm run test:chromium
npm run typecheck
npm run report
```

## Included scenarios
- Standard user login
- Locked-out user validation
- Invalid credentials validation
- Add product to cart
- End-to-end checkout
- Inventory sort price low-to-high

## Framework design
- `pages/`: page objects and reusable UI actions
- `fixtures/`: typed Playwright fixtures
- `tests/`: independent test specifications
- `test-data/`: test data
- `playwright.config.ts`: browser matrix, retries, trace, video, screenshots and reporting

## Engineering notes
- Prefer role/test-id locators over brittle XPath/CSS where possible.
- Keep assertions in tests or page-level expectation methods.
- Keep test data separate from page objects.
- CI retries are enabled only in CI to reduce local feedback time.
