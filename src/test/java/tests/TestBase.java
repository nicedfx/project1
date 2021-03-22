package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.ConfigHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.ConfigHelper.*;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        configureDriver();
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        if (Configuration.browser.equals("chrome")) {
            attachAsText("Browser console logs", getConsoleLogs());
        }
        attachVideo();
        closeWebDriver();
    }
}
