package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    ///// SelenideElements
    CalendarComponent calendar = new CalendarComponent();
    SelenideElement titleLabel = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            phoneNumber = $("#userNumber"),
            subject = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            picture = $("#uploadPicture"),
            address = $("#currentAddress"),
            state = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            city = $("#city"),
            submit = $("#submit"),
            confirmModalWindowInformation = $(".table-responsive");


    ///// Actions
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        titleLabel.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhoneNumber(String value) {
        phoneNumber.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate("20", "July", "1980");

        return this;
    }

    public RegistrationPage setSubject(String value) {
        subject.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage addPicture() {
        picture.uploadFile(new File("src/test/resources/1.png"));

        return this;
    }

    public RegistrationPage setAddress(String value) {
        address.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        state.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        city.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public void submit() {
        submit.click();
    }

    public RegistrationPage checkResult(String key, String value) {
        confirmModalWindowInformation.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }
}
