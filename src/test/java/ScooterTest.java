import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import page.OrderPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ScooterTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Укажите путь к chromedriver
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPage = new MainPage(driver);
        orderPage = new tests.OrderTest(driver);
    }

    @Test
    public void testOrder() {
        mainPage.clickOrderButtonTop();
        orderPage.fillOrderForm("Юлия", "Преображенская", "г.Москва", "Полянка", "79883334455");
        orderPage.clickNextButton();
        String confirmationText = orderPage.getOrderConfirmationText();
        assertThat(confirmationText, is("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

