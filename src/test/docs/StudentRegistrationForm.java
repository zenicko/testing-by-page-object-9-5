package ru.zenicko.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    @BeforeAll
    static void screenMax() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillStudentRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("Ivanov@a.ru");

        $("#genterWrapper").$(byText("Male")).click();

        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();

        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1999");
        $(.react-datepicker__month:not(.react-datepicker__day--outside-month)).$(byText("12")).click();

        $("#subjectsInput").setValue("Hindi");
        $("#react-select-2-option-0").click();

        $("#hobbiesWrapper").$(byText("Sports")).click();

        //$("#uploadPicture").uploadFile(new File("src/test/resources/img/photo_2020-11-17_15-25-27.jpg"));
        $("#uploadPicture").uploadFromClasspath("img/photo_2020-11-17_15-25-27.jpg");

        $("#currentAddress").setValue("RU, Moscow, st. Baba Galya, 1");
        $("#currentAddress-label").click();

        $("#submit").scrollIntoView(true);

        $("#state").$(".css-tlfecz-indicatorContainer").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();

        $("#city").$(".css-tlfecz-indicatorContainer").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit").click();


        // Checking
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text("Ivan" + " " + "Ivanov"), text("Ivanov@a.ru"),
                text("Male"), text("1234567890"), text("12 June,1999"), text("Hindi"), text("Sports"),
                text("img/photo_2020-11-17_15-25-27.jpg"), text("RU, Moscow, st. Baba Galya, 1"),
                text("NCR"), text("Delhi"));

        $(".table-responsive").shouldHave(text("Student Name")).parent().shouldHave(text("Ivan" + " " + "Ivanov"));
        $(".table-responsive").shouldHave(text("Student Email")).parent().shouldHave(text("Ivanov@a.ru"));
        $(".table-responsive").shouldHave(text("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("Mobile")).parent().shouldHave(text("1234567890"));

        $(".table-responsive").shouldHave(text("Date of Birth")).parent().shouldHave(text("12 June,1999"));
        $(".table-responsive").shouldHave(text("Subjects")).parent().shouldHave(text("Hindi"));

        $(".table-responsive").shouldHave(text("Hobbies")).parent().shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Picture")).parent().shouldHave(text("photo_2020-11-17_15-25-27.jpg"));
        $(".table-responsive").shouldHave(text("Address")).parent().shouldHave(text("RU, Moscow, st. Baba Galya, 1"));
        $(".table-responsive").shouldHave(text("State and City")).parent().shouldHave(text("NCR" + " " + "Delhi"));

    }
}
