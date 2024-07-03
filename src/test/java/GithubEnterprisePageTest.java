import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class GithubEnterprisePageTest {

    /*
    На главной странице GitHub выберите: Меню -> Solutions -> Enterprise (с помощью команды hover для Solutions).
    Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").
    */

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void checkTitleOnEnterprisePageTest() {

        open("/");

        $(".header-menu-wrapper")
                .$(Selectors.byText("Solutions"))
                .hover();
        $(".header-menu-wrapper")
                .$(Selectors.byTagAndText("a", "Enterprise")).
                click();
        $(".enterprise-lp")
                .$(Selectors.byTagName("h1"))
                .scrollTo()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("The AI-powered\ndeveloper platform."));
    }
}
