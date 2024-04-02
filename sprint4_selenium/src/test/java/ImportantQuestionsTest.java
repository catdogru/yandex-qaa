import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ImportantQuestionsPage;
import pages.MainPage;
import rule.DriverRule;

import static constants.Constants.ImportantQuestionTestData.questionIndex2answer;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    @Rule
    public DriverRule driverRule = new DriverRule();
    private final int questionIndex;
    private final String expectedAnswerText;

    public ImportantQuestionsTest(int questionIndex, String expectedAnswerText) {
        this.questionIndex = questionIndex;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsData() {
        return new Object[][]{
                {0, questionIndex2answer.get(0)},
                {1, questionIndex2answer.get(1)},
                {2, questionIndex2answer.get(2)},
                {3, questionIndex2answer.get(3)},
                {4, questionIndex2answer.get(4)},
                {5, questionIndex2answer.get(5)},
                {6, questionIndex2answer.get(6)},
                {7, questionIndex2answer.get(7)},
        };
    }

    @Before
    public void openScooterMainPage() {
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.open().confirmCookies();
    }

    @Test
    public void answerShouldHaveText() {
        ImportantQuestionsPage importantQuestionsPage = new ImportantQuestionsPage(driverRule.getDriver());
        String actualAnswerText = importantQuestionsPage
                .scrollToImportantQuestions()
                .getAnswerByQuestionIndex(questionIndex);

        assertEquals("Answers should be equals", expectedAnswerText, actualAnswerText);
    }
}
