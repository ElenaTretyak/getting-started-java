package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
       // Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        //Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }
}


