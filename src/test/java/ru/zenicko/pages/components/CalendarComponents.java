package ru.zenicko.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponents {
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput"),
        dayInput = $(".react-datepicker__month:not(.react-datepicker__day--outside-month)"),
        monthInput = $(".react-datepicker__month-select"),
        yearInput = $(".react-datepicker__year-select");

    public void setBirthDay(String day, String month, String year) {
        dateOfBirthInput.click();
        monthInput.selectOption(month);
        yearInput.selectOption(year);
        dayInput.$(byText(day)).click();
    }
}
