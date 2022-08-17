package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainSiteTest extends AbstractTest{


    @Test()
    void myTests() throws Exception {
        List<WebDriver> drivers = new ArrayList<>();
        drivers.add(getDriverGoogleChrome());
        drivers.add(getDriverFireFox());
        for (WebDriver driver : drivers) {
            goTo(driver);
            MyAction.login(driver);
            MyAction.goToPhotoAlbum(driver);
            MyAction.createAlbum(driver);
            MyAction.deleteAlbum(driver);
            MyAction.logOut(driver);
        }
    }
    @Test()
    void changingAvatarNegativeTest() {
        WebDriver driver = getDriverGoogleChrome();
        goTo(driver);
        MyAction.login(driver);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(".s-header-item__link--user")))
                .pause(1000)
                .click(driver.findElement(By.cssSelector(".s-header-sub-list-item__link--profile")))
                .build()
                .perform();
        driver.findElement(By.cssSelector(".b-myuserpic-current")).click();
        driver.findElement(By.id("radio_url")).click();
        driver.findElement(By.name("urlpic")).sendKeys("https://vk.com/livejournal");
        driver.findElement(By.cssSelector(".js-submit")).click();
        assertTrue(driver.getTitle().contains("Ошибка"), "нет сообщения об ошибке!");
        MyAction.logOut(driver);
    }

    @Test()
    void searchTesting(){
        WebDriver driver = getDriverGoogleChrome();
        goTo(driver);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("(//a[contains(text(), 'Наука и техника')])[1]")))
                .pause(1000)
                .click(driver.findElement(By.xpath("//a[contains(text(),'IT')]")))
                .build()
                .perform();
        assertDoesNotThrow(() -> driver.findElement(By.cssSelector(".search__control")),"Нет поля ввода поискового запроса" );
        driver.findElement(By.cssSelector(".search__control")).sendKeys("Тестирование");
        action.sendKeys(Keys.ENTER).build().perform();
        assertTrue(driver.getTitle().contains("Поиск"), "Нет названия Поиск в Title");
    }
}



