package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MyAction {

    static void deleteAlbum(WebDriver driver) throws InterruptedException {
        Thread.sleep(100);
        driver.findElement(By.cssSelector(".item-flatmedia__thumb__pic")).click();
        driver.findElement(By.cssSelector(".flatmedia-albumtitle__edit")).click();
        WebElement popupAlbum = driver.findElement(By.xpath("//*[@ng-bind=\"popup.options.title\"]"));
        assertEquals("РЕДАКТИРОВАТЬ АЛЬБОМ", popupAlbum.getText().toUpperCase(), "Попап для редактирования фотоальбома не доступен");
        driver.findElement(By.cssSelector(".flatpopup-delete-btn")).click();
        driver.findElement(By.xpath("//button[@ng-click='remove.submit()']")).click();
        assertThrows(Exception.class, () -> driver.findElement(By.xpath("//div[contains(text(),'ertyyufghj')] ")),"Альбом не удален" );
    }

    static void createAlbum(WebDriver driver) {
        driver.findElement(By.cssSelector(".item-flatmedia__link")).click();
        WebElement popupAlbum = driver.findElement(By.xpath("//*[@ng-bind=\"popup.options.title\"]"));
        assertEquals("СОЗДАТЬ АЛЬБОМ", popupAlbum.getText().toUpperCase(), "Попап для создания фотоальбома не доступен");
        WebElement createAlbum = driver.findElement(By.name("create_album"));
        createAlbum.click();
        createAlbum.sendKeys("ertyyufghj");
        WebElement descAlbum = driver.findElement(By.name("desc_album"));
        descAlbum.click();
        descAlbum.sendKeys("dfghhgj");
        driver.findElement(By.cssSelector(".b-flatbutton:nth-child(1)")).click();
        assertDoesNotThrow(() -> driver.findElement(By.xpath("//div[contains(text(),'ertyyufghj')] ")),"Альбом не создан!" );
    }

    static void logOut(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".s-header-item__link--user"))).perform();
        WebElement webElement = driver.findElement(By.cssSelector(".s-header-sub-list-item__link--logout"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
        assertThrows(NoSuchElementException.class,() -> driver.findElement(By.cssSelector(".s-nav-item__name")),"Пользователь авторизован!" );
    }

    static void goToPhotoAlbum(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".s-header-item__link--user"))).perform();
        WebElement webElement = driver.findElement(By.cssSelector(".s-header-sub-list-item__link--photo"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
        WebElement title = driver.findElement(By.xpath("//*[@lj-ml=\"flatmedia.title.mine.photo\"]"));
        assertTrue(driver.getTitle().contains("Фотоальбом"), "страница входа 'Фотоальбом' недоступна");
        assertEquals("фотоальбом", title.getText().toLowerCase(), "Элемент 'Фотоальбом' не найден на странице");
    }

    static void login(WebDriver driver) {
        assertTrue(driver.getTitle().contains("Главное — ЖЖ"), "страница входа недоступна");
        driver.findElement(By.cssSelector(".s-header-item__link--login")).click();
        driver.findElement(By.id("user")).sendKeys("sedrac");
        WebElement password = driver.findElement(By.id("lj_loginwidget_password"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(password));
        password.sendKeys("Asdfghzxcvbn11");
        driver.findElement(By.name("action:login")).click();
        assertDoesNotThrow(() -> driver.findElement(By.cssSelector(".s-nav-item__name")),"Авторизация не выполнена!" );
        assertEquals("sedrac", driver.findElement(By.cssSelector(".s-nav-item__name")).getText().toLowerCase(), "Пользователь не авторизован");
    }
}
