package pageObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pageObject.MyAction.*;

public class MainSiteTest extends AbstractTest {
    private final String name = "sedrac";
    private final String password = "Asdfghzxcvbn11";

    @ParameterizedTest
    @CsvFileSource(resources = "/pageObject/albums.csv", delimiter = ';')
    void myTests(String nameAlbum, String description) throws InterruptedException {
        login(getDriver(), name, password);
        goToPhotoAlbum(getDriver());
        createAlbum(getDriver(), nameAlbum, description);
        deleteAlbum(getDriver(), nameAlbum);
        logOut(getDriver());
    }

    @Test
    void changingAvatarNegativeTest() throws InterruptedException {
        login(getDriver(), name, password);
        new UserMenu(getDriver()).openProfilePage();
        new ProfilePage(getDriver()).changingAvatar("https://vk.com/livejournal");
        assertTrue(getDriver().getTitle().contains("Ошибка"), "нет сообщения об ошибке!");
        logOut(getDriver());
    }

    @Test
    void searchTesting() {
        HomePage homePage = new HomePage(getDriver());
        homePage.openSearchControlField();
        assertDoesNotThrow(homePage::getElementSearchControl, "Нет поля ввода поискового запроса");
        homePage.sendToSearchControlField("Тестирование");
        Actions action = new Actions(getDriver());
        action.sendKeys(Keys.ENTER).build().perform();
        assertTrue(getDriver().getTitle().contains("Поиск"), "Нет названия Поиск в Title");
    }
}
