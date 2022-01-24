import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFromTest() {
        Selenide.open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Viktor");
        $("#lastName").setValue("Slon");
        $("#userEmail").setValue("viktornuts@gmail.com");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("8955245541");

        $("#dateOfBirthInput").click();
        $("[class='react-datepicker__month-select']").selectOption("June");
        $("[class='react-datepicker__year-select']").selectOption("1990");
        $("[class*='react-datepicker__day--021']").click();

        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("Maths").pressEnter();

        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();

        File lesson = new File("src/test/resources/lesson1.png");
        String path = lesson.getAbsolutePath();
        $("#uploadPicture").sendKeys(path);

        $("[placeholder='Current Address']").setValue("Nikolaya Shishka 21");
        $("[placeholder='Current Address']").scrollIntoView(true);
        $("#react-select-3-input").setValue("Raj").pressEnter();
        $("#react-select-4-input").setValue("Jaise").pressEnter();
        $("#submit").click();

        //Assert
        $$x("//*[@class='modal-body']//td[2]").shouldHave(CollectionCondition.exactTexts(
                "Viktor Slon", "viktornuts@gmail.com", "Male", "8955245541", "21 June,1990",
                "English, Maths", "Sports, Reading", "lesson1.png", "Nikolaya Shishka 21", "Rajasthan Jaiselmer"));


        // $(".table-responsive").shouldHave(text("Viktor"), text("Slon"); как 1 из вариантов

        // $(".table-responsive").$(byText("Student name")).parent().shouldHave(text("Slon"));
    }
}
