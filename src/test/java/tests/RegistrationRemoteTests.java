package tests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationRemoteTests extends RemoteTestBase {

    @Test
    void successfulRegistrationTest () {
        step("Open form", () -> {

            open("https://demoqa.com/automation-practice-form");
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
