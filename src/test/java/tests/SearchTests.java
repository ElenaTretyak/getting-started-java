package tests;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.open;

public class SearchTests {
    @Test
    void successfulSearchTest() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Лена\\.cache\\selenium\\chromedriver\\win64\\126.0.6478.126\\chromedriver.exe");
        open("https://www.google.com/");
        $(byName("q")).setValue("selenide").pressEnter();
       // $("[name=q]").setValue("selenide").pressEnter();
        $("#search").shouldHave(text("https://selenide.org"));

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }
}
