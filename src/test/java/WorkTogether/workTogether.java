package WorkTogether;

import config.Base;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
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
}
