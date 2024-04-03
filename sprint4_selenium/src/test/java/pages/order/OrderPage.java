package pages.order;

import constants.Constants.ScooterOrderTestData.RentPeriod;
import constants.Constants.ScooterOrderTestData.ScooterColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.DEFAULT_DURATION;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;

public class OrderPage {
    private final WebDriver driver;

    public final By firstNameInput = xpath("//input[contains(@placeholder, 'Имя')]");
    public final By lastNameInput = xpath("//input[contains(@placeholder, 'Фамилия')]");
    public final By addressInput = xpath("//input[contains(@placeholder, 'Адрес')]");
    public final By phoneNumberInput = xpath("//input[contains(@placeholder, 'Телефон')]");
    public final By nextButton = xpath("//button[text() = 'Далее']");
    public final By rentDateInput = xpath("//input[contains(@placeholder, 'Когда привезти самокат')]");
    public final By commentInput = xpath("//input[contains(@placeholder, 'Комментарий для курьера')]");

    public final By subwayStationSelect = xpath("//input[contains(@placeholder, 'Станция метро')]");

    public final By subwayStationItem(String optionValue) {
        return xpath("//li[@class = 'select-search__row']/descendant::*[text()='" + optionValue + "']");
    }

    public final By rentPeriodSelect = xpath("//div[contains(text(), 'Срок аренды')]");

    public final By rentPeriodItem(RentPeriod period) {
        return xpath("//div[@role = 'option' and text() = '" + period.getPeriodValue() + "']");
    }

    public final By scooterColorCheckbox(ScooterColor color) {
        return xpath("//input[@type = 'checkbox' and @id = '" + color.getColorValue() + "']");
    }

    public final By submitButton = xpath("//div[contains(@class, 'Order_Buttons')]/button[text() = 'Заказать']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage fillCustomerForm(
            String customerFirstName,
            String customerLastName,
            String customerAddress,
            String subwayStationName,
            String customerPhoneNumber
    ) {
        driver.findElement(firstNameInput).sendKeys(customerFirstName);
        driver.findElement(lastNameInput).sendKeys(customerLastName);
        driver.findElement(addressInput).sendKeys(customerAddress);
        selectSubwayStation(subwayStationName);
        driver.findElement(phoneNumberInput).sendKeys(customerPhoneNumber);
        return this;
    }

    private void selectSubwayStation(String stationName) {
        driver.findElement(subwayStationSelect).click();
        By stationItem = subwayStationItem(stationName);
        new WebDriverWait(driver, DEFAULT_DURATION).until(driver -> (driver.findElement(stationItem).isDisplayed()));
        driver.findElement(stationItem).click();
    }

    public OrderPage goToRentForm() {
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPage fillRentForm(
            String rentDate,
            RentPeriod rentPeriod,
            ScooterColor color,
            String comment
    ) {
        driver.findElement(rentDateInput).sendKeys(rentDate, ENTER);
        selectRentPeriod(rentPeriod);
        driver.findElement(scooterColorCheckbox(color)).click();
        driver.findElement(commentInput).sendKeys(comment);
        return this;
    }

    private void selectRentPeriod(RentPeriod period) {
        driver.findElement(rentPeriodSelect).click();
        By periodItem = rentPeriodItem(period);
        new WebDriverWait(driver, DEFAULT_DURATION).until(driver -> (driver.findElement(periodItem).isDisplayed()));
        driver.findElement(periodItem).click();
    }

    public OrderConfirmationPage submitOrder() {
        driver.findElement(submitButton).click();
        return new OrderConfirmationPage(driver);
    }
}
