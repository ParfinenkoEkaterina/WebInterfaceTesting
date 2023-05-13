package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в каталоге C:\tmp
        WebDriverManager.chromedriver().driverVersion("113").setup();

    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    void shouldTestV1() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995555555");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button__text")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestInvalidName() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Antonov Anton");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7999555555");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button__text")).click();


        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestInvalidPhone() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+799955555");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button__text")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestInvalidAgreement() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995555555");
        driver.findElement(By.className("button__text")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestInvalidNoName() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995555555");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button__text")).click();


        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        String expected = "Поле обязательно для заполнения";

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestInvalidNoPhone() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button__text")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        String expected = "Поле обязательно для заполнения";

        assertEquals(expected, actual);

    }

}
