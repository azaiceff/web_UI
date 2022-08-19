package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UserMenu extends AbstractPage {
    @FindBy(css = ".s-nav-item__name")
    private WebElement userMenuIn;
    @FindBy(css = ".s-header-sub-list-item__link--photo")
    private WebElement photoAlbumPageFromUserMenu;
    @FindBy(css = ".s-header-sub-list-item__link--logout")
    private WebElement logout;
    @FindBy(css = ".s-header-sub-list-item__link--profile")
    private WebElement profile;

    public void openProfilePage() {
        moveToUserMenu();
        driverWait(profile, 500);
        profile.click();
    }

    public void clickLogOut() {
        moveToUserMenu();
        driverWait(logout, 500);
        logout.click();
    }

    public void clickPhotoAlbumPageFromUserMenu() {
        moveToUserMenu();
        driverWait(photoAlbumPageFromUserMenu, 500);
        photoAlbumPageFromUserMenu.click();
    }

    private void moveToUserMenu() {
        Actions action = new Actions(getDriver());
        action.moveToElement(userMenuIn).perform();
    }

    public String getNameUserMenu() {
        driverWait(userMenuIn);
        return userMenuIn.getText().toLowerCase();
    }

    public WebElement userMenuIn() {
        driverWait(userMenuIn, 500);
        return userMenuIn;
    }

    public UserMenu(WebDriver driver) {
        super(driver);
    }
}
