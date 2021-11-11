# I'm a readme file and will tell you about this project!
This project used a new-old pattern "Page object". *Continue the second lesson.*

## About home tasks
___
1. Возьмите ваш код написанный для [формы] [1] в рамках ДЗ ко второму занятию.

2. Добавьте в ваш код PageObjects.

3. Добавьте в ваш код генерацию рандомных значений используя Java Faker.

[1]: https://demoqa.com/automation-practice-form "The form of the home task of lesson 2"

## Steps
___

Step 1. Create files build.gradle, .gitignore, readme.md and a structure of directories.

Step 2. Make refactoring `class StudentRegistrationForm` from lesson 2. Put the file into /docs. Create a new `class TestBase`.

Step 3. Make `class RegistrationPage` is used the pattern of Page Object. 
Create `class CalendarComponents` is used Page Component.
Rrefactoring `class StudentRegistrationForm` and make `class StudentRegistrationWithObjectPageForm`.

Step 4. Apply Java Faker. Generate test data by Java Faker.

Step 5. Change the algorithm of a random selection of a sex. The method `getSex` is overrided.

Step 6. Make three methods a checking by patterns "chain", "Collection Elements" and "JavaScript".


## What's new?
___
### 1. Java
   1. Faker
      1. Demografic.sex `/src/main/resources/en/demographic.yml`
         ```
         en:
         faker:
         demographic:
         sex: ["Male", "Female"]
         ```
      2. Formatted string
      
         'format("%s %s, %s", str1, str2, str3);'
      3. Convert `String` to `Map`
         ```
         String response = "{"key1": "hello"}";
         Map<String, String> data = null;
         ObjectMapper mapper = new ObjectMapper();
         data = mapper.readValue(response, new TypeReference <Map<String, String>>(){});         
         ```
      4. Convert `Map` to `String`
      
         `StringUtils.join(data);`
      5. Execute a JS script
      
         `String browserResponse = Selenide.executeJavaScript(jsCode);`
      6. Convert `File` to `String`
        ```
        String fileContent;
        fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ```
      7. Make a soft assertions by `class SoftAssertions`
         `SoftAssertions softly = new SoftAssertions();`
         `softly.assertThat(actualValue__1).isEqualTo(expectedData__1);`
         `softly.assertThat(actualValue__2).isEqualTo(expectedData__2);`
         `softly.AssertAll();`  
      8. Parsing JSON
         1. 'com.fasterxml.jackson.core:jackson-databind:2.12.3'
      
### 2. Gradle
### 3. Selenide
### 4. Git
### 5. Allure
___
### -2. require "Resources"
   1. About MD
      1. [ru](https://gist.github.com/Jekins/2bf2d0638163f1294637#Links)
      2. [en](https://guides.github.com/features/mastering-markdown/)
      3. [Git, en](https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
   2. The examples of selenide' tests.
      1. [autotests-cloud/selenide-web-ios-android-tests](https://github.com/autotests-cloud/selenide-web-ios-android-tests)
   3. About Java Faker
      1. https://dius.github.io/java-faker/
      2. https://www.baeldung.com/java-faker
      3. https://github.com/DiUS/java-faker
      4. https://mvnrepository.com/artifact/com.github.javafaker/javafaker
   4. JS
      1. https://api.jquery.com/each/
      2. https://coderoad.ru/40853501/%D0%A7%D0%B5%D0%BC-%D0%B7%D0%B0%D0%BC%D0%B5%D0%BD%D0%B8%D1%82%D1%8C-%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D1%8E-jQuery-each-%D0%B2-JavaScript
      3. https://www.youtube.com/watch?v=-N1vfa_wfQo
      
### -1. Miscellaneous
   1. MD. 
      1. A Block code:
      
      ```
         statement1
         statement2
         statement3
      ```
      2. Heading
      
         `#<blank>text`
      3. The types of the Pattern Page Object.
         1. The Steps
         2. The Script
         3. The Chain / DSL
      
## Checking
___


