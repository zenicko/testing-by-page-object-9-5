package ru.zenicko.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import ru.zenicko.pages.RegistrationPage;
import ru.zenicko.pages.components.CalendarComponents;

public class TestBase {

    public RegistrationPage registrationPage = new RegistrationPage();


    @BeforeAll
    static void screenMax() {
        Configuration.startMaximized = true;
    }
}
