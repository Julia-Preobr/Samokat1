package page;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderPage {
    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String browser;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Ленина, д. 1", "Китай-город", "12345678901", "chrome"},
                {"Анна", "Петрова", "Санкт-Петербург, пл. Революции, д. 5", "Невский проспект", "09876543210", "firefox"}
        });
    }

    public OrderPage(String name, String surname, String address, String metro, String phone, String browser) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.browser = browser;
    }

    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testOrderCreation() {
        testOrderButton(MainPage.ORDER_BUTTON_TOP);
        driver.navigate().refresh(); // Сбрасываем страницу для следующего теста
        testOrderButton(MainPage.ORDER_BUTTON_BOTTOM);
    }

    private void testOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
        fillOrderForm();
        assertTrue(driver.findElement(MainPage.SUCCESS_MESSAGE).isDisplayed());
    }

    private void fillOrderForm() {
        driver.findElement(MainPage.NAME_INPUT).sendKeys(name);
        driver.findElement(MainPage.SURNAME_INPUT).sendKeys(surname);
        driver.findElement(MainPage.ADDRESS_INPUT).sendKeys(address);
        driver.findElement(MainPage.METRO_INPUT).sendKeys(metro);
        driver.findElement(MainPage.PHONE_INPUT).sendKeys(phone);
        driver.findElement(MainPage.NEXT_BUTTON).click();

        // Выбор даты
        driver.findElement(MainPage.DATE_PICKER).click();
        // Здесь выберите дату из календаря (например, следующую доступную дату)

        // Выбор срока аренды
        driver.findElement(MainPage.RENT_DURATION_DROPDOWN).click();
        driver.findElement(MainPage.RENT_DURATION_OPTION).click(); // Выбираем первый пункт, который соответствует 1-7

        // Выбор цвета самоката
        driver.findElement(MainPage.COLOR_RADIO_BUTTON).click(); // Выберите черный или серый

        // Ввод комментария
        driver.findElement(MainPage.COMMENT_INPUT).sendKeys("ПРИВЕТ");

        // Оформление заказа
        driver.findElement(MainPage.SUBMIT_BUTTON).click();
    }

    @Test
    public void testFAQQuestions() {
        // Проверка вопросов о важном
        driver.findElement(MainPage.FAQ_BUTTON).click();
        assertTrue(driver.findElement(MainPage.FAQ_QUESTION).isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}