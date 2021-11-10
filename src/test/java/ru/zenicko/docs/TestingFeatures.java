package ru.zenicko.docs;

import com.github.javafaker.Faker;

public class TestingFeatures {
    public static void main(String[] args) {
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton
        String streetAddress = faker.address().streetAddress();
        String sex = faker.demographic().sex();

        System.out.println("name " + name);
        System.out.println("firstName " + firstName);
        System.out.println("lastName " + lastName);
        System.out.println("streetAddress " + streetAddress);
        System.out.println("sex " + sex);

    }
}
