package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Локатор заголовка
    private By header = By.xpath("//h1[contains(text(), 'Самокат')]");

    // Локатор кнопки "Заказать" сверху
    private By orderButtonTop = By.xpath("//button[text()='Заказать']");

    // Локатор кнопки "Заказать" снизу
    private By orderButtonBottom = By.xpath("//div[@class='Home_Button__1_inx']/button");

    // Локатор выпадающего списка
    private By questionSection = By.xpath("//div[contains(text(), 'Вопросы о важном')]");
    private By questionArrow = By.xpath("//div[@class='accordion__item']//button");
    private By questionAnswer = By.xpath("//div[@class='accordion__item']//div[@class='accordion__panel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void toggleQuestionAnswer() {
        driver.findElement(questionArrow).click();
    }

    public String getAnswerText() {
        return driver.findElement(questionAnswer).getText();
    }
}
