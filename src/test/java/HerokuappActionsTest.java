import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class HerokuappActionsTest {

    /*
    (опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
    - Откройте https://the-internet.herokuapp.com/drag_and_drop
    - Перенесите прямоугольник А на место В
    - Проверьте, что прямоугольники действительно поменялись
    - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
    */

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
//        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void checkDragAndDropTest() {
        open("/drag_and_drop");
        $("#column-a").$(byTagAndText("header", "B")).shouldNotBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldNotBe(exist);
        $("#column-a").dragAndDrop(to("#column-b"));
        $("#column-a").$(byTagAndText("header", "B")).shouldBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldBe(exist);
    }

    @Test
    void checkActionTest() {
        open("/drag_and_drop");
        $("#column-a").$(byTagAndText("header", "B")).shouldNotBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldNotBe(exist);

        actions()
                .clickAndHold($("#column-a"))
                .moveToElement($("#column-b")).release().perform();
        $("#column-a").$(byTagAndText("header", "B")).shouldBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldBe(exist);
    }

    @Test
    void checkActionSourceTest() {
        open("/drag_and_drop");
        SelenideElement sourceA = $("#column-a");
        SelenideElement sourceB = $("#column-b");
        sourceA.$(byTagAndText("header", "B")).shouldNotBe(exist);
        sourceB.$(byTagAndText("header", "A")).shouldNotBe(exist);

        actions().clickAndHold(sourceA)
                .moveToElement(sourceB)
                .release().build().perform();
        sourceB.dragAndDrop(to(sourceA));
        sourceA.$(byTagAndText("header", "A")).shouldBe(exist);
        sourceB.$(byTagAndText("header", "B")).shouldBe(exist);
    }
}
