package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideStepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;
    @Test
    public void testLambdaStep() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Лена\\.cache\\selenium\\chromedriver\\win64\\126.0.6478.126\\chromedriver.exe");
        SelenideLogger.addListener("allure", new AllureSelenide());

        step ("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step ("Ищем репозиторий" + REPOSITORY, () ->{
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY,()->{
            $(linkText(REPOSITORY)).click();
        });

        step ("Открываем tab Issue", () ->{
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером" + ISSUE, () ->{
            $(withText("#"+ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }
}
