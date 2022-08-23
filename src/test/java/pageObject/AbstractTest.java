package pageObject;

import JUnit5.TriangleTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class AbstractTest {
    //private static WebDriver driverGoogleChrome;
    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);
    private static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void googleChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(10));

        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
    }

    @BeforeEach
    void goTo() {
        Assertions.assertDoesNotThrow(() -> getDriver().navigate().to("https://www.livejournal.com/"),
                "Страница не доступна");
    }

    @AfterEach
    public void checkBrowser() {
        List<LogEntry> allLogRows = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        if (!allLogRows.isEmpty()) {
            allLogRows.forEach(logEntry -> {
                logger.info(logEntry.getMessage());
            });
        }
    }

    @AfterAll
    public static void exit() {
        if (getDriver() != null) getDriver().quit();
    }

    public static WebDriver getDriver() {
        return eventDriver;
    }
}
