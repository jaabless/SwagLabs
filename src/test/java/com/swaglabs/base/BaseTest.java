package com.swaglabs.base;

import com.swaglabs.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import org.openqa.selenium.WebDriver;;
public class BaseTest {
    protected static WebDriver driver;
    protected static LoginPage loginPage;

    @BeforeEach
    public void setupDriver() {
        // Detect OS
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        }

        ChromeOptions options = new ChromeOptions();
        if (!os.contains("win")) {
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
