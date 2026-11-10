package com.portfolio.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ScreenshotUtil {
    private ScreenshotUtil() {}
    public static String capture(WebDriver driver, String testName) {
        try {
            Path dir = Path.of("test-output", "screenshots");
            Files.createDirectories(dir);
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = dir.resolve(testName + ".png");
            Files.copy(source.toPath(), target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
