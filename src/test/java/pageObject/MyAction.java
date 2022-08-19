package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import static org.junit.jupiter.api.Assertions.*;

public class MyAction {
    static void login(WebDriver driver, String name, String password) throws InterruptedException {
        UserMenu userMenu = new UserMenu(driver);
        assertTrue(driver.getTitle().contains("Главное — ЖЖ"), "страница входа недоступна");
        new HomePage(driver).actionToLogin(name, password);
        assertDoesNotThrow(userMenu::userMenuIn, "Авторизация не выполнена!");
        assertEquals(name, userMenu.getNameUserMenu(), "Пользователь не авторизован");
    }

    static void logOut(WebDriver driver) {
        UserMenu userMenu = new UserMenu(driver);
        userMenu.clickLogOut();
        assertThrows(WebDriverException.class, userMenu::userMenuIn, "Пользователь авторизован!");
    }

    static void goToPhotoAlbum(WebDriver driver) {
        new UserMenu(driver).clickPhotoAlbumPageFromUserMenu();
        assertTrue(driver.getTitle().contains("Фотоальбом"), "страница входа 'Фотоальбом' недоступна");
        assertEquals("фотоальбом", new PhotoAlbumPage(driver).getFlatmediaTitle().getText().toLowerCase(), "Элемент 'Фотоальбом' не найден на странице");
    }

    static void createAlbum(WebDriver driver, String nameAlbum, String description) throws InterruptedException {
        PhotoAlbumPage photoAlbumPage = new PhotoAlbumPage(driver);
        assertEquals("СОЗДАТЬ АЛЬБОМ", photoAlbumPage.getCreateAlbumLink().getText().toUpperCase(), "Попап для создания фотоальбома не доступен");
        photoAlbumPage.saveAlbum(nameAlbum, description);
        Thread.sleep(200);
        assertDoesNotThrow(() -> photoAlbumPage.getElementNewAlbum(nameAlbum), "Альбом не создан!");
    }

    static void deleteAlbum(WebDriver driver, String nameAlbum) throws InterruptedException {
        PhotoAlbumPage photoAlbumPage = new PhotoAlbumPage(driver);
        photoAlbumPage.gotoEditAlbum(nameAlbum);
        assertEquals("РЕДАКТИРОВАТЬ АЛЬБОМ", photoAlbumPage.getPopupOption().getText().toUpperCase(), "Попап для редактирования фотоальбома не доступен");
        photoAlbumPage.removeAlbum();
        assertThrows(Exception.class, () -> photoAlbumPage.getElementNewAlbum(nameAlbum), "Альбом не удален");
    }
}
