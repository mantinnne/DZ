package RunTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.Base;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartTest extends Base {


    final String url = "https://demoqa.com/automation-practice-form";

    final String firstName = faker.name().firstName(),
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
            city = "Jaipur";

    @Test
    public void testRun() {
        open("https://demoqa.com/automation-practice-form");

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

        ElementsCollection elementsHobs = $$(".custom-checkbox");
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
