package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class Base {
    public Faker faker = new Faker();


    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;

    }
}


