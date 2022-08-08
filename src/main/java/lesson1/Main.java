package lesson1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        List<WebDriver> driverList = new ArrayList<>();
        driverList.add(getChromeWebDriver());
        driverList.add(getFireFoxWebDriver());
        driverList.forEach(driver -> {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://www.livejournal.com/");
            allTests(driver);
        });
    }

    private static void allTests(WebDriver driver) {
        login(driver);
        goToPhotoAlbum(driver);
        createAlbum(driver);
        deleteAlbum(driver);
        logOut(driver);
        //driver.quit();
    }

    private static WebDriver getChromeWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    private static WebDriver getFireFoxWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        return new FirefoxDriver(options);
    }

    private static void deleteAlbum(WebDriver driver) {
        driver.findElement(By.cssSelector(".item-flatmedia__thumb__pic")).click();
        driver.findElement(By.cssSelector(".flatmedia-albumtitle__edit")).click();
        driver.findElement(By.cssSelector(".flatpopup-delete-btn")).click();
        driver.findElement(By.xpath("//button[@ng-click='remove.submit()']")).click();
    }

    private static void createAlbum(WebDriver driver) {
        driver.findElement(By.cssSelector(".item-flatmedia__link")).click();
        WebElement createAlbum = driver.findElement(By.name("create_album"));
        createAlbum.click();
        createAlbum.sendKeys("ertyyufghj");
        WebElement descAlbum = driver.findElement(By.name("desc_album"));
        descAlbum.click();
        descAlbum.sendKeys("dfghhgj");
        driver.findElement(By.cssSelector(".b-flatbutton:nth-child(1)")).click();
    }

    private static void logOut(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".s-header-item__link--user"))).perform();
        WebElement webElement = driver.findElement(By.cssSelector(".s-header-sub-list-item__link--logout"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    private static void goToPhotoAlbum(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".s-header-item__link--user"))).perform();
        WebElement webElement = driver.findElement(By.cssSelector(".s-header-sub-list-item__link--photo"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
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
