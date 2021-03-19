package tests;

import com.codeborne.selenide.Condition;
import configuration.TestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.AttachmentsHelper.getConsoleLogs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class ContactsPageTests extends TestBase {
    @Test
    @Description("Check footer social network links")
    @Tag("web")
    void contactsFooterSocialLinksTest() {
        open("");

        $("div.menu [href='/contacts']").click();
        $("body").$(byText("© 2018 – 2020 ai people")).scrollIntoView(true);
        $("div.footer-component .socials").$$("a").get(0).shouldHave(Condition.attribute("href", "https://www.facebook.com/pg/AIPeople-1604836239648797/about/?ref=page_internal"));
        $("div.footer-component .socials").$$("a").get(1).shouldHave(Condition.attribute("href", "https://vk.com/aipeopleteam"));
        $("div.footer-component .socials").$$("a").get(2).shouldHave(Condition.attribute("href", "https://www.instagram.com/ai.people/"));
    }

    @Test
    @Description("Open 'Contacts' page, check console logs")
    @Tag("web")
    void checkConsoleLogs() {
        open("");
        $("div.menu [href='/contacts']").click();

        assertThat(getConsoleLogs(), not(containsString("SEVERE")));
        //Yes, it probably happens just because Selenide is acting too fast, but nevertheless.

    }
}
