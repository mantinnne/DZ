package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.$$;

public class Base {
    protected Faker faker = new Faker();

    protected String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = "Male",
            userNumber = faker.phoneNumber().subscriberNumber(10),
            dayOfBirth = "26",
            monthOfBirth = "December",
            yearOfBirth = "1994",
            subjectTwo = "History",
            picture = "photo.jpg",
            currentAddress = faker.address().streetAddress(),
            state = "Rajasthan",
            city = "Jaipur",
            url = "https://demoqa.com/automation-practice-form";

    protected ElementsCollection elementsHobs = $$(".custom-checkbox");

    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;

    }
}


