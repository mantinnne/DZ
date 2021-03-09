package RunTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.Base;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartTest extends Base {

    @Test
    public void testRun() {
        open(url);
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#subjectsInput").setValue(subjectTwo).pressEnter();
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $$(".react-datepicker__day").find(exactText(dayOfBirth)).click();

        for (SelenideElement element : elementsHobs) {
            element.click();
        }

        $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();


        $(byText("Thanks for submitting the form")).shouldBe(Condition.visible);
        assertEquals(firstName + " " + lastName, getActualResult("Student Name"));
        assertEquals(userEmail, getActualResult("Student Email"));
        assertEquals(gender, getActualResult("Gender"));
        assertEquals(userNumber, getActualResult("Mobile"));
        assertEquals(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth,
                getActualResult("Date of Birth"));
        assertTrue(getActualResult("Subjects").contains(subjectTwo), "Subjects not contains all positions.");
        assertTrue(getActualResult("Hobbies").contains("Sports, Reading, Music"), "Not all hobbies.");
        assertEquals(picture, getActualResult("Picture"));
        assertEquals(currentAddress, getActualResult("Address"));
        assertEquals(state + " " + city, getActualResult("State and City"));
    }

    private String getActualResult(String label) {
        return $x("//td[text()='" + label + "']/following-sibling::td").getText();
    }
}
