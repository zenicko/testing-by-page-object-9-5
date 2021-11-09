package ru.zenicko.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.zenicko.pages.components.CalendarComponents;

import java.net.HttpCookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    // locators and elements
    private final String FORM_TITLE = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            fistNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapperRadio = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userSubjectSelect = $("#subjectsInput"),
            subjectFirstSelect = $("#react-select-2-option-0"),
            hobbiesWrapperCheckBox = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressTextArea = $("#currentAddress"),
            getOutTextArea = $("#currentAddress-label"),
            activeStateSelect = $("#state").$(".css-tlfecz-indicatorContainer"),
            stateSelect = $("#react-select-3-input"),
            activeCitySelect = $("#city").$(".css-tlfecz-indicatorContainer"),
            citySelect = $("#react-select-4-input"),
            buttonSubmit = $("#submit"),
            resultTable = $(".table-responsive");

    public CalendarComponents calendarComponents = new CalendarComponents();

    // actions

    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    public RegistrationPage setFistName(String fistName) {
        fistNameInput.setValue(fistName);

        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWrapperRadio.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        userSubjectSelect.setValue(subject);
        subjectFirstSelect.click();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbiesWrapperCheckBox.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage loadFile(String path) {
        //uploadPictureInput.uploadFile(new File(path));
        uploadPictureInput.uploadFromClasspath(path);

        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressTextArea.setValue(currentAddress);
        getOutTextArea.click();
        return this;
    }

    private RegistrationPage setState(String State) {
        activeStateSelect.click();
        stateSelect.setValue(State).pressEnter();
        return this;
    }
    private RegistrationPage setCity(String city) {
        activeCitySelect.click();
        citySelect.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        this.setState(state).setCity(city);
        return this;
    }


    public RegistrationPage pushButtonSubmit() {
        buttonSubmit.click();
        return this;
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        resultTable.shouldHave(text(key)).parent().shouldHave(text(value));

        return this;
    }


}
