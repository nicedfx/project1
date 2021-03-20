package tests;

import configuration.TestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {

    @Test
    @Tag("web")
    @Description("Check that 3 product cards are displayed on the main page")
    void productsCountTest() {
        step("Open the main page", () -> {
            open("");
        });

        step("Check that there are 3 cards in this section", () -> {
            $("#index-2 .cards").scrollIntoView(true);
            $("#index-2 .cards").$$(".project-card.card.white").shouldHaveSize(3);
        });

        step("Check that the first card is visible", () -> {
            $("#index-2 .cards").$$(".project-card.card.white").get(0).shouldBe(visible);
        });
        step("Check that the second card is visible", () -> {
            $("#index-2 .cards").$$(".project-card.card.white").get(1).shouldBe(visible);
        });
        step("Check that the third card is visible", () -> {
            $("#index-2 .cards").$$(".project-card.card.white").get(2).shouldBe(visible);
        });
    }

    @Test
    @Tag("web")
    @Description("Check that 7 cards are displayed in the 'Magnificent Seven' section")
    void magnificentSevenCountTest() {
        step("Open the main page", () -> {
            open("");
        });
        step("Check that the section has title \"Великолепная семерка\"", () -> {
            $("#index-3 .h1").scrollIntoView(true);
            $("#index-3 .h1").shouldHave(text("Великолепная семерка"));
        });
        step("Check that the big tile is displayed", () -> {
            $("#index-3 .blog-featured .big").shouldBe(visible);
        });
        step("Check that the small tiles are displayed", () -> {
            $$("#index-3 .blog-featured .small-list a").get(0).shouldBe(visible);
            $$("#index-3 .blog-featured .small-list a").get(1).shouldBe(visible);
            $$("#index-3 .blog-featured .small-list a").get(2).shouldBe(visible);
        });
        step("Check that the medium tiles are displayed", () -> {
            $$("#index-3 .other .d-row div").get(0).shouldBe(visible);
            $$("#index-3 .other .d-row div").get(1).shouldBe(visible);
            $$("#index-3 .other .d-row div").get(2).shouldBe(visible);
        });
    }

    @Test
    @Tag("web")
    @Description("Check 'expanding' links are in place")
    void checkExpandingLinksTest() {
        step("Open the main page", () -> {
            open("");
        });
        step("Check 'expanding' links have correct URLs", () -> {
            $$(".link-container").get(0).$("a").shouldHave(attribute("href", "https://aipeople.ru/projects"));
            $$(".link-container").get(1).$("a").shouldHave(attribute("href", "https://aipeople.ru/blog"));
            $$(".link-container").get(2).$("a").shouldHave(attribute("href", "https://aipeople.ru/events"));
            $$(".link-container").get(3).$("a").shouldHave(attribute("href", "https://aipeople.ru/vacancies"));
        });
    }

    @Test
    @Tag("web")
    @Tag("postitve")
    @Description("Check 'expanding' links lead to the correct pages")
    void checkExpandingLinksWorkingTest() {
        step("Check the 'projects' link", () -> {
            open("");
            $$(".link-container").get(0).$("a").scrollIntoView(true);
            $$(".link-container").get(0).$("a").click();
            $(".layout-view h1").shouldHave(text("Проекты"));
        });
        step("Check the 'Blog' link", () -> {
            open("");
            $$(".link-container").get(1).$("a").scrollIntoView(true);
            $$(".link-container").get(1).$("a").click();
            $(".layout-view h1").shouldHave(text("Блог"));
        });
        step("Check the 'events' link", () -> {
            open("");
            $$(".link-container").get(2).$("a").scrollIntoView(true);
            $$(".link-container").get(2).$("a").click();
            $(".layout-view h1").shouldHave(text("События"));
        });
        step("Check the 'vacancies' link", () -> {
            open("");
            $$(".link-container").get(3).$("a").scrollIntoView(true);
            $$(".link-container").get(3).$("a").click();
            $(".layout-view h1").shouldHave(text("Вакансии"));
        });
    }
}
