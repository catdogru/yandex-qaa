package rule;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static java.lang.System.getProperty;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver() {
        if ("firefox".equals(getProperty("browser"))) {
            initFirefox();
        } else {
            initChrome();
        }
    }

    private void initFirefox() {
        firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    private void initChrome() {
        chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
