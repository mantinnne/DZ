package RunTest;

import com.codeborne.selenide.Condition;
import config.Base;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartTest extends Base {


    final String url = "https://demoqa.com/automation-practice-form";

    final String firstName = "Alexander";
    final String lastName = "Gerasimov";
    final String userEmail = "as.nanao@gmail.com";
    final String gender = "Male";
    final String userNumber = "9251029407";
    final String dayOfBirth = "26";
    final String monthOfBirth = "December";
    final String yearOfBirth = "1994";
    final String subjectTwo = "History";
    final String picture = "photo.jpg";
    final String currentAddress = "New Zealand, Shire";
    final String state = "Rajasthan";
    final String city = "Jaipur";

    @Test
    public void testRun() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $x("//*[@id='subjectsContainer']/descendant::input").setValue(subjectTwo).pressEnter();


        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $x("//option[contains(text(),'" + yearOfBirth + "')]").scrollTo().click();
        $(".react-datepicker__month-select").click();
        $x("//option[contains(text(),'" + monthOfBirth + "')]").click();
        $x("//*[@class='react-datepicker__month']/descendant::" + "div[text()='" + dayOfBirth + "' and not(contains(@class,'react-datepicker__day--outside-month'))]").click();


        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();

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