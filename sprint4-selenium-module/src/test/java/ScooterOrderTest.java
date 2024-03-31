import constants.Constants.ScooterOrderTestData.OrderButtonPosition;
import constants.Constants.ScooterOrderTestData.RentPeriod;
import constants.Constants.ScooterOrderTestData.ScooterColor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.order.OrderConfirmationPage;
import pages.order.OrderPage;
import rule.DriverRule;

import static constants.Constants.ScooterOrderTestData.SCOOTER_ORDER_TEST_DATA_BOTTOM_BUTTON;
import static constants.Constants.ScooterOrderTestData.SCOOTER_ORDER_TEST_DATA_TOP_BUTTON;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    @Rule
    public DriverRule driverRule = new DriverRule();
    private final OrderButtonPosition orderButtonPosition;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerAddress;
    private final String subwayStation;
    private final String customerPhoneNumber;
    private final String rentDate;
    private final RentPeriod rentPeriod;
    private final ScooterColor color;
    private final String comment;

    public ScooterOrderTest(
            OrderButtonPosition orderButtonPosition,
            String customerFirstName,
            String customerLastName,
            String customerAddress,
            String subwayStation,
            String customerPhoneNumber,
            String rentDate,
            RentPeriod rentPeriod,
            ScooterColor color,
            String comment
    ) {
        this.orderButtonPosition = orderButtonPosition;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.subwayStation = subwayStation;
        this.customerPhoneNumber = customerPhoneNumber;
        this.rentDate = rentDate;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsData() {
        return new Object[][]{
                SCOOTER_ORDER_TEST_DATA_TOP_BUTTON,
                SCOOTER_ORDER_TEST_DATA_BOTTOM_BUTTON
        };
    }

    @Test
    public void answerShouldHaveText() throws Exception {
        MainPage mainPage = new MainPage(driverRule.getDriver());
        OrderPage orderPage = mainPage.open().confirmCookies().createOrder(orderButtonPosition);
        orderPage
                .fillCustomerForm(customerFirstName, customerLastName, customerAddress, subwayStation, customerPhoneNumber)
                .goToRentForm()
                .fillRentForm(rentDate, rentPeriod, color, comment);

        OrderConfirmationPage orderConfirmationPage = orderPage.submitOrder();
        orderConfirmationPage.confirmOrder();

        assertTrue("Should be true", orderConfirmationPage.isOrderCompleted());
    }
}
