package WorkTogether;

import config.Base;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class workTogether extends Base {

    @Test
    void goTogether() {
        open("https://www.google.ru/");
        $("[name=q]").val("selenide").pressEnter();

        $$(".LC20lb").shouldHaveSize(7).first().click();
        switchTo().window(1);
        $(byText("Что такое Selenide?")).shouldBe(visible);
    }

    @Test
    void searchSelenideWithYandex() {
        open("https://yandex.ru/");
        $x("//input[@id='text']").val("Selenide").pressEnter();
        $("div #search-result").shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }
}
