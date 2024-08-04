package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import helpers.Attach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationRemoteTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        // Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        //Configuration.holdBrowserOpen = true;
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


        Configuration.pageLoadStrategy = "eager";
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Лена\\.cache\\selenium\\chromedriver\\win64\\126.0.6478.126\\chromedriver.exe");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void successfulRegistrationTest () {
        step("Open form", () -> {

            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Fill form", () ->{
            $("#firstName").setValue("Ivan");
            $("#lastName").setValue("Petrov");
            $("#userEmail").setValue("ivan@petrov.ru");
            $("label[for=gender-radio-1]").click();//good
            $("#userNumber").setValue("7911789652");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("1980");
            $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

            $("#subjectsInput").setValue("Math").pressEnter();
            $("#hobbiesWrapper").$(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("1.png");
            //$("#uploadPicture").click().uploadFile(new File("src/test/resources/img/1.png"));
            $("#currentAddress").setValue("123789, Moscow, Big Trip str., 15");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });

        step("Verify results", () ->{
            $(".modal-content").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Ivan"), text("Petrov"),
                    text("ivan@petrov.ru"), text("7911789652"));
        });
    }
}
