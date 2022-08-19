package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhotoAlbumPage extends AbstractPage {

    @FindBy(xpath = "//*[@lj-ml=\"flatmedia.title.mine.photo\"]")
    private WebElement flatmediaTitle;
    @FindBy(css = ".item-flatmedia__link")
    private WebElement createAlbumLink;
    @FindBy(xpath = "//*[@ng-bind=\"popup.options.title\"]")
    private WebElement popupCreateAlbum;
    @FindBy(name = "create_album")
    private WebElement createAlbumFieldName;
    @FindBy(name = "desc_album")
    private WebElement descAlbum;
    @FindBy(css = ".b-flatbutton:nth-child(1)")
    private WebElement buttonSave;
    @FindBy(css = ".flatmedia-albumtitle__edit")
    private WebElement editAlbum;
    @FindBy(xpath = "//*[@ng-bind=\"popup.options.title\"]")
    private WebElement popupOption;
    @FindBy(css = ".flatpopup-delete-btn")
    private WebElement buttonDelete;
    @FindBy(xpath = "//button[@ng-click='remove.submit()']")
    private WebElement buttonRemoveSubmit;

    public void removeAlbum() {
        buttonDelete.click();
        buttonRemoveSubmit.click();
    }

    public WebElement getPopupOption() {
        return popupOption;
    }

    public void gotoEditAlbum(String nameAlbum) throws InterruptedException {
        clickElementNewAlbum(nameAlbum);
        Thread.sleep(200);
        clickEditAlbum();
    }

    private void clickEditAlbum() {
        editAlbum.click();
    }

    private void clickElementNewAlbum(String nameAlbum) {
        getElementNewAlbum(nameAlbum).click();
    }

    public WebElement getElementNewAlbum(String nameAlbum) {
        String xpath = "//div[contains(text(),'" + nameAlbum + "')]";
        return getDriver().findElement(By.xpath(xpath));
    }

    public void saveAlbum(String nameAlbum, String description) {
        sendCreateAlbumFieldName(nameAlbum);
        sendDescAlbum(description);
        buttonSave.click();
    }

    private void sendDescAlbum(String description) {
        descAlbum.click();
        descAlbum.sendKeys(description);
    }

    private void sendCreateAlbumFieldName(String nameAlbum) {
        createAlbumFieldName.click();
        createAlbumFieldName.sendKeys(nameAlbum);
    }

    public WebElement getCreateAlbumLink() {
        clickCreateAlbumLink();
        return popupCreateAlbum;
    }

    public void clickCreateAlbumLink() {
        driverWait(createAlbumLink);
        createAlbumLink.click();
    }

    public PhotoAlbumPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getFlatmediaTitle() {
        return flatmediaTitle;
    }
}
