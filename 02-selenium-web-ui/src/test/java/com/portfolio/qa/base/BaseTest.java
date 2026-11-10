package com.portfolio.qa.base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.portfolio.qa.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        try { Files.createDirectories(Path.of("test-output")); } catch (Exception e) {
            throw new RuntimeException("Unable to create test-output directory", e);
        }
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Framework", "Selenium + TestNG + POM");
        extent.setSystemInfo("Language", "Java 17");
    }

    @BeforeMethod
    @Parameters({"headless"})
    public void setUp(@Optional("true") String headless) {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(headless)) options.addArguments("--headless=new");
        options.addArguments("--window-size=1440,1000", "--disable-dev-shm-usage", "--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(System.getProperty("baseUrl", "https://www.saucedemo.com/"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (test != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable());
                String screenshot = ScreenshotUtil.capture(driver, result.getName());
                if (screenshot != null) {
                    try { test.addScreenCaptureFromPath(screenshot); } catch (Exception ignored) {}
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) test.pass("Test passed");
            else test.skip("Test skipped");
        }
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() { if (extent != null) extent.flush(); }
}
