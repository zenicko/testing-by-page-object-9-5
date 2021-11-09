package ru.zenicko.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationWithObjectPageJavaFakerForm extends TestBase {

    private Faker faker = new Faker();

    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String userEmail = faker.internet().emailAddress();
    private String sex = StudentRegistrationWithObjectPageJavaFakerForm.getSex(3, faker);
    private String streetAddress = faker.address().streetAddress();
    private String useNumber = faker.phoneNumber().subscriberNumber(10);

    private static String getSex(int num, Faker faker) {

        int ramdomN = faker.number().numberBetween(1, num);

        if (ramdomN == 1) {
            return "Male";
        } else if (ramdomN == 2) {
            return "Female";
        } else {
            return "Other";
        }
    }

    @Test
    void fillStudentRegistrationForm() {
        registrationPage.openPage();
        registrationPage.setFistName(firstName);
        registrationPage.setLastName(lastName);
        registrationPage.setUserEmail(userEmail);
        registrationPage.setGender(sex);
        registrationPage.setUserNumber(useNumber);
        registrationPage.calendarComponents.setBirthDay("12", "June", "1999");
        registrationPage.setSubject("Hindi");
        registrationPage.setHobby("Sports");

        registrationPage.loadFile("img/photo_2020-11-17_15-25-27.jpg");
        registrationPage.setCurrentAddress(streetAddress);

        $("#submit").scrollIntoView(true);

        registrationPage.setStateAndCity("NCR", "Delhi");
        registrationPage.pushButtonSubmit();

        registrationPage
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", userEmail)
                .checkResultsValue("Gender", sex)
                .checkResultsValue("Date of Birth", "12 June,1999")
                .checkResultsValue("Mobile", useNumber)
                .checkResultsValue("Subjects", "Hindi")
                .checkResultsValue("Hobbies", "Sports")
                .checkResultsValue("Picture", "photo_2020-11-17_15-25-27.jpg")
                .checkResultsValue("Address", streetAddress)
                .checkResultsValue("State and City", "NCR" + " " + "Delhi");
    }
}
