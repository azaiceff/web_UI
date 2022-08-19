package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.time.Duration;

public class AbstractTest {
    private static WebDriver driverGoogleChrome;

    @BeforeAll
    static void googleChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--incognito");
        optionsChrome.addArguments("start-maximized");
        driverGoogleChrome = new ChromeDriver(optionsChrome);
        driverGoogleChrome.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
    }

    @BeforeEach
    void goTo() {
        Assertions.assertDoesNotThrow(() -> driverGoogleChrome.navigate().to("https://www.livejournal.com/"),
                "Страница не доступна");
    }

    @AfterAll
    public static void exit() {
        if (driverGoogleChrome != null) driverGoogleChrome.quit();
    }

    public WebDriver getDriver() {
        return driverGoogleChrome;
    }
}
