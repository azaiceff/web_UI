package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    private final WebDriver driver;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver getDriver() {
        return this.driver;
    }

    void driverWait(WebElement value, int ofMillis) {
        new WebDriverWait(driver, Duration.ofMillis(ofMillis)).until(ExpectedConditions.visibilityOf(value));
    }

    void driverWait(WebElement value) {
        driverWait(value, 200);
    }
}
