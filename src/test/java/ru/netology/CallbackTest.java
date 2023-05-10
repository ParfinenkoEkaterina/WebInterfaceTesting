package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CallbackTest {
    private WebDriver driver;

    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в каталоге C:\tmp
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
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
    void shouldTestV1() throws InterruptedException {
        driver.get("http://localhost:9999/");
       Thread.sleep(8000);
        List<WebElement> imputs = driver.findElements(By.tagName("imput"));
        imputs.get(0).sendKeys("Антонов Антон");
        imputs.get(1).sendKeys("+79995555555");
        driver.findElement(By.className("checkbox__box"));
        driver.findElement(By.className("button__text"));
        // driver.findElement().sendKeys("+79995555555");
        // driver.findElement().click();
        //  driver.findElement().click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.className("order-success")).getText();

        assertEquals(expected, actual);

    }

    //@Test
    // void shouldTestSomething() {
    //  throw new UnsupportedOperationException();
    // }
}
