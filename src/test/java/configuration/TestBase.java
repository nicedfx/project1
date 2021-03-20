package configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        TestConfig config = ConfigFactory.create(TestConfig.class, System.getProperties());
        System.setProperty("selenoidUrl", config.selenoidUrl()); //to use in getVideoUrl method

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browserVersion= config.browserVersion();
        Configuration.browser = config.browser();
        Configuration.startMaximized = config.startMaximized();
        Configuration.baseUrl = "https://aipeople.ru/";

        if (config.isRemote() == true) {
            Configuration.remote = config.selenoidUrl() + ":4444/wd/hub/";
        }
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        if (Configuration.browser.equals("chrome")) {
            attachAsText("Browser console logs", getConsoleLogs());
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            System.out.println(getConsoleLogs());
        }
//        getConsoleLogs(); There is an issue with fireFox console logs!
        attachVideo();
        closeWebDriver();
    }
}
