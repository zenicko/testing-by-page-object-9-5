package ru.zenicko.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.assertj.core.api.SoftAssertions;
import ru.zenicko.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentRegistrationJsForm extends TestBase {
    public String sex[] = {"Male", "Female", "Other"};
    private Faker faker = new Faker();

    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String userEmail = faker.internet().emailAddress();
    private String randomSex = getSex(sex);
    private String useNumber = faker.phoneNumber().subscriberNumber(10);
    private String dateOfBirth = "12 June,1999";
    private String subjects = "Hindi";
    private String hobbies = "Sports";
    private String picture = "photo_2020-11-17_15-25-27.jpg";
    private String streetAddress = faker.address().streetAddress();
    private String state = "NCR";
    private String city = "Delhi";

    Map<String, String> expectedData = new HashMap<String, String>() {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", userEmail);
        put("Gender", randomSex);
        put("Mobile", useNumber);
        put("Date of Birth", dateOfBirth);
        put("Subjects", subjects);
        put("Hobbies", hobbies);
        put("Picture", picture);
        put("Address", streetAddress);
        put("State and City", state + " " + city);
    }};


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

    private static String getSex(String[] sex) {
        int randomIntFromZeroToNine = new Random().nextInt(sex.length);
        return sex[randomIntFromZeroToNine];
    }

    private static String[] splitBirthOfDate(String expectedData) {
        String day = expectedData.split(" ")[0];
        int startPositionBlank = expectedData.indexOf(" ");
        int endPositionComma = expectedData.indexOf(",");
        String month = expectedData.substring(startPositionBlank + 1, endPositionComma);
        String year = expectedData.substring(endPositionComma + 1);

        return new String[]{day, month, year};
    }



    // The first method
    private static void chainChecks(RegistrationPage registrationPage, Map expectedData) {

        registrationPage
                .checkResultsValue("Student Name", expectedData.get("Student Name"))
                .checkResultsValue("Student Email", expectedData.get("Student Email"))
                .checkResultsValue("Gender", expectedData.get("Gender"))
                .checkResultsValue("Mobile", expectedData.get("Mobile"))
                .checkResultsValue("Date of Birth", expectedData.get("Date of Birth"))
                .checkResultsValue("Subjects", expectedData.get("Subjects"))
                .checkResultsValue("Hobbies", expectedData.get("Hobbies"))
                .checkResultsValue("Picture", expectedData.get("Picture"))
                .checkResultsValue("Address", expectedData.get("Address"))
                .checkResultsValue("State and City", expectedData.get("State and City"));
    }
    // The second method
    private static void chainSoftAsserts(Map expectedData) {
        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();
        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line : lines) {
            String key = line.$("td").text();
            String actualValue = line.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Unequal  key = %s actualValue = %s expected = %s", key, actualValue, expectedData.get(key)))
                    .isEqualTo(expectedData.get(key));
        }
        softly.assertAll();
    }

    // The third method
    private void checkByJavaScriptExecute(Map<String, String> expectedData) {
        Map<String, String> actualData = getContentByScriptJs();

        assertThat(expectedData).isEqualTo(actualData);

    }

    private Map<String, String> getContentByScriptJs() {
        String scriptJs = getScriptJsFromFile("src/test/resources/javascript/get_table_data.js");
        String data = Selenide.executeJavaScript(scriptJs);
        Map<String, String> actualData = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            actualData = mapper.readValue(data, new TypeReference<Map<String, String>>() {
            });

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(actualData);

        return actualData;
    }

    private String getScriptJsFromFile(String pathScript) {
        String scriptJs = null;
        try {
            scriptJs = Files.readString(Paths.get(pathScript), StandardCharsets.UTF_8);
            //scriptJs = FileUtils.readFileToString(new File(pathScript), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(scriptJs);
        return scriptJs;
    }


    @Test
    void fillStudentRegistrationForm() {

        registrationPage.openPage();
        registrationPage.setFistName(firstName);
        registrationPage.setLastName(lastName);
        registrationPage.setUserEmail(userEmail);
        registrationPage.setGender(randomSex);
        registrationPage.setUserNumber(useNumber);

        String[] splitDate = splitBirthOfDate(dateOfBirth);

        registrationPage.calendarComponents.setBirthDay(splitDate[0], splitDate[1], splitDate[2]);
        registrationPage.setSubject(subjects);
        registrationPage.setHobby(hobbies);

        registrationPage.loadFile("img/" + picture);
        registrationPage.setCurrentAddress(streetAddress);

        $("#submit").scrollIntoView(true);

        registrationPage.setStateAndCity(state, city);
        registrationPage.pushButtonSubmit();

        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));

        // The first method
        // chainChecks(registrationPage, expectedData);

        // The second method
        //chainSoftAsserts(expectedData);

        // The first method
        checkByJavaScriptExecute(expectedData);
    }
}






