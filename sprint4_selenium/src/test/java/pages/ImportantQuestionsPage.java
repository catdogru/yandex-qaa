package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.DEFAULT_DURATION;

public class ImportantQuestionsPage {
    private final WebDriver driver;
    private final By importantQuestionsHeader = By.xpath("//div[text() = 'Вопросы о важном']");

    private By questionElement(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By answerElement(int index) {
        return By.id("accordion__panel-" + index);
    }

    public ImportantQuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ImportantQuestionsPage scrollToImportantQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(importantQuestionsHeader));
        return this;
    }

    public String getAnswerByQuestionIndex(int index) {
        driver.findElement(questionElement(index)).click();
        new WebDriverWait(driver, DEFAULT_DURATION).until(driver -> (driver.findElement(answerElement(index)).isDisplayed()));
        return driver.findElement(answerElement(index)).getText();
    }
}
