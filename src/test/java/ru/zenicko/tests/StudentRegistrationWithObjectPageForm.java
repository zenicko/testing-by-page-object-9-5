package ru.zenicko.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationWithObjectPageForm extends TestBase {

    @Test
    void fillStudentRegistrationForm() {
        registrationPage.openPage();
        registrationPage.setFistName("Ivan");
        registrationPage.setLastName("Ivanov");
        registrationPage.setUserEmail("Ivanov@a.ru");
        registrationPage.setGender("Male");
        registrationPage.setUserNumber("1234567890");

        registrationPage.calendarComponents.setBirthDay("12", "June", "1999");
        registrationPage.setSubject("Hindi");
        registrationPage.setHobby("Sports");

        registrationPage.loadFile("img/photo_2020-11-17_15-25-27.jpg");
        registrationPage.setCurrentAddress("RU, Moscow, st. Baba Galya, 1");

        $("#submit").scrollIntoView(true);

        registrationPage.setStateAndCity("NCR", "Delhi");
        registrationPage.pushButtonSubmit();

        registrationPage
                .checkResultsValue("Student Name", "Ivan" + " " + "Ivanov")
                .checkResultsValue("Student Email", "Ivanov@a.ru")
                .checkResultsValue("Gender", "Male")
                .checkResultsValue("Date of Birth", "12 June,1999")
                .checkResultsValue("Gender", "Male")
                .checkResultsValue("Mobile", "1234567890")
                .checkResultsValue("Subjects", "Hindi")
                .checkResultsValue("Hobbies", "Sports")
                .checkResultsValue("Picture", "photo_2020-11-17_15-25-27.jpg")
                .checkResultsValue("Address", "RU, Moscow, st. Baba Galya, 1")
                .checkResultsValue("State and City", "NCR" + " " + "Delhi1");
    }
}
