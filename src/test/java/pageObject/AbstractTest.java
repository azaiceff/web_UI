package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;

public class AbstractTest {
    private static WebDriver driverGoogleChrome;
    private static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void googleChromeDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions optionsFox = new FirefoxOptions();
        optionsFox.addArguments("--incognito");
        optionsFox.addArguments("--headless");
        optionsFox.addArguments("start-maximized");
        eventDriver = new EventFiringWebDriver(new FirefoxDriver(optionsFox));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    }

    @BeforeEach
    void goTo() {
        Assertions.assertDoesNotThrow(() -> getDriver().navigate().to("https://www.livejournal.com/"),
                "Страница не доступна");
    }

    @AfterAll
    public static void exit() {
        if (getDriver() != null) getDriver().quit();
    }

    public static WebDriver getDriver() {
        return eventDriver;
    }
}
