package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class CallbackTest {
    private WebDriver driver;

    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в каталоге C:\tmp
        System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
    }
    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999/");
        driver.findElement()
    }

    @Test
    void shouldTestSomething() {
        throw new UnsupportedOperationException();
    }
}
