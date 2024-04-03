package pages.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.DEFAULT_DURATION;
import static org.openqa.selenium.By.xpath;

public class OrderConfirmationPage {
    private final WebDriver driver;

    public final By confirmationDialog = xpath("//div[text() = 'Хотите оформить заказ?']");
    public final By confirmButton = xpath("//button[text() = 'Да']");

    public final By completionDialog = xpath("//div[text() = 'Заказ оформлен']");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmOrder() {
        new WebDriverWait(driver, DEFAULT_DURATION).until(driver -> (driver.findElement(confirmationDialog).isDisplayed()));
        driver.findElement(confirmButton).click();
    }

    public Boolean isOrderCompleted() {
        return new WebDriverWait(driver, DEFAULT_DURATION).until(driver -> (driver.findElement(completionDialog).isDisplayed()));
    }
}
