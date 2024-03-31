package constants;

import java.time.Duration;
import java.util.Map;

import static constants.Constants.ScooterOrderTestData.OrderButtonPosition.BOTTOM;
import static constants.Constants.ScooterOrderTestData.OrderButtonPosition.TOP;
import static constants.Constants.ScooterOrderTestData.RentPeriod.ONE_DAY;
import static constants.Constants.ScooterOrderTestData.ScooterColor.BLACK;
import static java.time.Duration.ofSeconds;

public interface Constants {
    Duration DEFAULT_DURATION = ofSeconds(3);
    String SCOOTER_BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    interface ImportantQuestionTestData {
        Map<Integer, String> questionIndex2answer = Map.of(
                0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                1, "Пока что у нас так: один заказ — один самокат. "
                        + "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",

                2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
                        + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. "
                        + "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",

                3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",

                5, "Самокат приезжает к вам с полной зарядкой. "
                        + "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. "
                        + "Зарядка не понадобится.",

                6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        );
    }

    interface ScooterOrderTestData {
        enum OrderButtonPosition {
            TOP,
            BOTTOM
        }

        enum ScooterColor {
            BLACK("black"),
            GREY("grey");

            private final String colorValue;

            ScooterColor(String colorValue) {
                this.colorValue = colorValue;
            }

            public String getColorValue() {
                return colorValue;
            }
        }

        enum RentPeriod {
            ONE_DAY("сутки"),
            TWO_DAYS("двое суток"),
            THREE_DAYS("трое суток"),
            FOUR_DAYS("четверо суток"),
            FIVE_DAYS("пятеро суток"),
            SIX_DAYS("шестеро суток"),
            SEVEN_DAYS("семеро суток");

            private final String periodValue;

            RentPeriod(String periodValue) {
                this.periodValue = periodValue;
            }

            public String getPeriodValue() {
                return periodValue;
            }
        }

        Object[] SCOOTER_ORDER_TEST_DATA_TOP_BUTTON = {TOP, "Иванов", "Иван", "На деревню дедушке", "Бабушкинская", "+70123456789", "11.02.2024", ONE_DAY, BLACK, "Второй подъезд"};
        Object[] SCOOTER_ORDER_TEST_DATA_BOTTOM_BUTTON = {BOTTOM, "Иванов", "Иван", "На деревню дедушке", "Бабушкинская", "+70123456789", "11.02.2024", ONE_DAY, BLACK, "Второй подъезд"};
    }


}
