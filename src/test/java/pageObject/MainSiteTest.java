package pageObject;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pageObject.MyAction.*;
@Story("Site Tests")
public class MainSiteTest extends AbstractTest {
    private final String name = "sedrac";
    private final String password = "Asdfghzxcvbn11";

    @ParameterizedTest
    @CsvFileSource(resources = "/pageObject/albums.csv", delimiter = ';')
    @DisplayName("Create Album")
    @Description("Login-GotoPhoyoAlbum-Create-Delete-Logout Album")
    @Link("https://www.livejournal.com/")
    @Issue("https://www.livejournal.com/")
    @TmsLink("https://www.atlassian.com/ru/software/jira")
    @Severity(SeverityLevel.CRITICAL)
    void myTests(String nameAlbum, String description) throws InterruptedException, IOException {
        saveScreen("login");
        login(getDriver(), name, password);
        goToPhotoAlbum(getDriver());
        createAlbum(getDriver(), nameAlbum, description);
        saveScreen("AfterCreating");
        deleteAlbum(getDriver(), nameAlbum);
        saveScreen("AfterDeleting");
        logOut(getDriver());
    }

    private void saveScreen(String name) throws IOException {
        File file = MyUtils.makeScreenshot(getDriver(),name + " " + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Step("Степ 1")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    @Feature("Фича 1")
    @Test
    @DisplayName("Changing Avatar")
    @Description("Changing Avatar - NegativeTest")
    @Link("https://www.livejournal.com/")
    @Issue("https://www.livejournal.com/")
    @TmsLink("https://www.atlassian.com/ru/software/jira")
    @Severity(SeverityLevel.MINOR)
    void changingAvatarNegativeTest() throws InterruptedException {
        login(getDriver(), name, password);
        new UserMenu(getDriver()).openProfilePage();
        new ProfilePage(getDriver()).changingAvatar("https://vk.com/livejournal");
        assertTrue(getDriver().getTitle().contains("Ошибка"), "нет сообщения об ошибке!");
        logOut(getDriver());
    }
    @Feature("Фича 2")
    @Test
    @DisplayName("Search Testing")
    @Description("Search Testing - PositiveTest")
    @Link("https://www.livejournal.com/")
    @Issue("https://www.livejournal.com/")
    @TmsLink("https://www.atlassian.com/ru/software/jira")
    @Severity(SeverityLevel.TRIVIAL)
    void searchTesting() throws IOException {
        HomePage homePage = new HomePage(getDriver());
        homePage.openSearchControlField();
        assertDoesNotThrow(homePage::getElementSearchControl, "Нет поля ввода поискового запроса");
        homePage.sendToSearchControlField("Тестирование");
        saveScreen("Поиск");
        Actions action = new Actions(getDriver());
        action.sendKeys(Keys.ENTER).build().perform();

        assertTrue(getDriver().getTitle().contains("ЖЖ"), "Нет в названии ЖЖ в Title");
    }
}
