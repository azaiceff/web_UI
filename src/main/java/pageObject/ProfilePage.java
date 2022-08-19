package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbstractPage {

    @FindBy(css = ".b-myuserpic-current")
    private WebElement avatar;
    @FindBy(id = "radio_url")
    private WebElement radio_url;
    @FindBy(name = "urlpic")
    private WebElement urlpic;
    @FindBy(css = ".js-submit")
    private WebElement buttonSubmit;

    public void changingAvatar(String url) {
        avatar.click();
        radio_url.click();
        urlpic.sendKeys(url);
        buttonSubmit.click();
    }

    ProfilePage(WebDriver driver) {
        super(driver);
    }
}
