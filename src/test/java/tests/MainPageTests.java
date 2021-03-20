package tests;

import configuration.TestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTests extends TestBase {

    @Test
    @Tag("web")
    @Description("Check that 3 product cards are displayed on the main page")
    @Disabled
    void productsCountTest() {
        open("");
        $("#index-2 .cards").scrollIntoView(true);
        $("#index-2 .cards").$$(".project-card.card.white").shouldHaveSize(3);
        $("#index-2 .cards").$$(".project-card.card.white").get(0).shouldBe(visible);
        $("#index-2 .cards").$$(".project-card.card.white").get(1).shouldBe(visible);
        $("#index-2 .cards").$$(".project-card.card.white").get(2).shouldBe(visible);

    }

    @Test
    @Tag("web")
    @Description("Check that 7 cards are displayed in the 'Magnificent Seven' section")
    @Disabled
    void magnificentSevenCountTest() {
        open("");

        //Check that the section has title "Великолепная семерка"
        $("#index-3 .h1").scrollIntoView(true);
        $("#index-3 .h1").shouldHave(text("Великолепная семерка"));

        //Check that the big tile is displayed
        $("#index-3 .blog-featured .big").shouldBe(visible);
        //Check that the small tiles are displayed
        $$("#index-3 .blog-featured .small-list a").get(0).shouldBe(visible);
        $$("#index-3 .blog-featured .small-list a").get(1).shouldBe(visible);
        $$("#index-3 .blog-featured .small-list a").get(2).shouldBe(visible);

        //Check that the medium tiles are displayed.
        $$("#index-3 .other .d-row div").get(0).shouldBe(visible);
        $$("#index-3 .other .d-row div").get(1).shouldBe(visible);
        $$("#index-3 .other .d-row div").get(2).shouldBe(visible);
    }

    @Test
    @Tag("web")
    @Description("Check 'expanding' links are in place")
    @Disabled
    void checkExpandingLinksTest() {
        open("");

        $$(".link-container").get(0).$("a").shouldHave(attribute("href", "https://aipeople.ru/projects"));
        $$(".link-container").get(1).$("a").shouldHave(attribute("href", "https://aipeople.ru/blog"));
        $$(".link-container").get(2).$("a").shouldHave(attribute("href", "https://aipeople.ru/events"));
        $$(".link-container").get(3).$("a").shouldHave(attribute("href", "https://aipeople.ru/vacancies"));

    }

    @Test
    @Tag("web")
    @Tag("postitve")
    @Description("Check 'expanding' links lead to the correct pages")
    @Disabled
    void checkExpandingLinksWorkingTest() {
        open("");

        $(".link-container").scrollIntoView(true);

        $$(".link-container").get(0).click();
        $("body").shouldHave(text("Проекты"));

        open("");
        $(".link-container").scrollIntoView(true);
        $$(".link-container a").get(1).click();
        $("body").shouldHave(text("Блог"));

        open("");
        $(".link-container").scrollIntoView(true);
        $$(".link-container a").get(2).click();
        $("body").shouldHave(text("Проекты"));

        open("");
        $(".link-container").scrollIntoView(true);
        $$(".link-container a").get(3).click();
        $("body").shouldHave(text("События"));
    }


}
