package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithPageObjectTests extends TestBase {
    @Tag("demoqa")
    @Test

    void successfulRegistrationTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        String userName = "Ivan";

        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Лена\\.cache\\selenium\\chromedriver\\win64\\126.0.6478.126\\chromedriver.exe");
        registrationPage.openPage()
                        .setFirstName("Ivan")
                        .setLastName("Petrov")
                        .setEmail("ivan@petrov.ru")
                        .setGender("Male")
                        .setPhoneNumber("7911789652")
                        .setDateOfBirth("20","July","1980")
                        .setSubject("Math")
                        .setHobby("Music") // "Music"
                        .addPicture()
                        .setAddress("123789, Moscow, Big Trip str., 15")
                        .setState("NCR")
                        .setCity("Delhi")
                        .submit();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        registrationPage.checkResult ("Student Name", "Ivan Petrov")
                        .checkResult("Student Email", "ivan@petrov.ru")
                        .checkResult("Mobile", "7911789652");
    }
}
