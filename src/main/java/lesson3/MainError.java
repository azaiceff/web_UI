package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainError {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.livejournal.com/");
        login(driver);
        //driver.quit();
    }
    private static void login(WebDriver driver) {
        WebElement login = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        login.click();
        WebElement name = driver.findElement(By.id("user"));
        WebElement password = driver.findElement(By.id("lj_loginwidget_password"));
        name.sendKeys("sedrac");
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(password));
        password.sendKeys("Asdfghzxcvbn11");
        WebElement in = driver.findElement(By.name("action:login"));
        in.click();
    }
}
