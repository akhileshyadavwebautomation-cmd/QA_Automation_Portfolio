# Selenium Web UI Automation — Java + TestNG

A Java UI automation starter framework using Selenium 4, TestNG, Maven, Page Object Model, explicit waits, ExtentReports and failure screenshots.

## Prerequisites
- Java 17+
- Maven 3.9+
- Google Chrome

Selenium Manager (included in Selenium 4) resolves the compatible browser driver in most standard environments.

## Run
```bash
mvn clean test
```
To run headed, edit `testng.xml` to add a `<parameter name="headless" value="false"/>` under the test element, or set up your preferred browser configuration.

## Report
After execution, open `test-output/ExtentReport.html`.

## Included tests
- Standard user login
- Locked-out user validation
- Add product to cart

## Structure
- `base/`: driver lifecycle and report lifecycle
- `pages/`: page objects
- `tests/`: TestNG tests
- `utils/`: screenshot helper

## Notes
- Uses explicit waits and disables implicit waits to avoid mixed-wait behavior.
- Current driver setup is Chrome-only and creates a fresh browser per test.
- For parallel execution, move driver state to `ThreadLocal<WebDriver>` and use a thread-safe report strategy before enabling TestNG parallel mode.
- Add environment-specific configuration and secret management before using against private applications.
