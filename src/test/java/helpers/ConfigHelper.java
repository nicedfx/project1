package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configuration.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ConfigHelper {
    public static void configureDriver() {
        //Adding Allure listener
        SelenideLogger.addListener("allure", new AllureSelenide());
        //Creating Owner config class instance
        TestConfig config = ConfigFactory.create(TestConfig.class, System.getProperties());

        Configuration.browserVersion = config.browserVersion();
        Configuration.browser = config.browser();
        Configuration.startMaximized = config.startMaximized();
        Configuration.baseUrl = "https://aipeople.ru/";

        if (config.isRemote() == true) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = config.selenoidUrl() + ":4444/wd/hub/";
            System.setProperty("selenoidUrl", config.selenoidUrl()); //to use in getVideoUrl method
        }
    }
}
