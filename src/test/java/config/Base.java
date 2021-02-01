package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class Base {

    @BeforeAll
    public static void setUp() {

    }

    @AfterEach
    public void clean() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }


}


