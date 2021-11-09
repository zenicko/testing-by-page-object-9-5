package ru.zenicko.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void screenMax() {
        Configuration.startMaximized = true;
    }
}
