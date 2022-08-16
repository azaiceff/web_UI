package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public abstract class AbstractTest {
    private static WebDriver driverFireFox;
    private static WebDriver driverGoogleChrome;

    @BeforeAll
    static void firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions optionsFox = new FirefoxOptions();
        optionsFox.addArguments("--incognito");
        optionsFox.addArguments("--headless");
        optionsFox.addArguments("start-maximized");
        driverFireFox = new FirefoxDriver(optionsFox);
        driverFireFox.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @BeforeAll
    static void googleChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--incognito");
        optionsChrome.addArguments("start-maximized");
        driverGoogleChrome = new ChromeDriver(optionsChrome);
        driverGoogleChrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    void goTo(WebDriver driver){
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("https://www.livejournal.com/"),
                "Страница не доступна");
    }
    @AfterAll
    static void close(){
        driverFireFox.quit();
        driverGoogleChrome.quit();
    }
    public static WebDriver getDriverFireFox() {
        return driverFireFox;
    }

    public static WebDriver getDriverGoogleChrome() {
        return driverGoogleChrome;
    }
}
