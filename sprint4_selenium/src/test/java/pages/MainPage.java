package pages;

import constants.Constants.ScooterOrderTestData.OrderButtonPosition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.order.OrderPage;

import static constants.Constants.SCOOTER_BASE_URL;
import static constants.Constants.ScooterOrderTestData.OrderButtonPosition.BOTTOM;
import static constants.Constants.ScooterOrderTestData.OrderButtonPosition.TOP;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class MainPage {
    private final WebDriver driver;
    private final By cookieConfirmation = id("rcc-confirm-button");
    public final By topOrderButton = xpath("//div[contains(@class, 'Header')]/button[text() = 'Заказать']");
    public final By bottomOrderButton = xpath("//div[contains(@class, 'Home')]/button[text() = 'Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(SCOOTER_BASE_URL);
        return this;
    }

    public MainPage confirmCookies() {
        if (driver.findElement(cookieConfirmation).isDisplayed()) {
            driver.findElement(cookieConfirmation).click();
        }
        return this;
    }

    public OrderPage createOrder(OrderButtonPosition orderButtonPosition) throws Exception {
        By orderButton;
        if (orderButtonPosition == TOP)
            orderButton = topOrderButton;
        else if (orderButtonPosition == BOTTOM)
            orderButton = bottomOrderButton;
        else
            throw new Exception("unknown order button position");

        driver.findElement(orderButton).click();
        return new OrderPage(driver);
    }
}
