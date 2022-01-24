import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static com.codeborne.selenide.Selenide.$;


import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.browser = "Chrome";
    }

    static Stream<Arguments> commonSearchTestCsvSource() {
        return Stream.of(
                Arguments.of("89642483134", "Билайн"),
                Arguments.of("89512333371", "МТС"),
                Arguments.of("89049764432", "TELE2")
        );
    }


    @MethodSource("commonSearchTestCsvSource")
    @ParameterizedTest(name = "Тест проверят работу сервиса по определеню оператора по номеру телефона с данными: {0}")
    void numberTest(String testData, String expected) {
        Selenide.open("https://www.spravportal.ru/Services/PhoneCodes/MobilePhoneInfo.aspx");
        $("input[name = 'ctl00$ctl00$cphMain$cphServiceMain$textNum']").setValue(testData);
        $("input[type='submit']").click();
        $("#lblOp").shouldHave(Condition.text(expected));
    }
}
