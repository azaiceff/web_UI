package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    @FindBy(css = ".s-header-item__link--login")
    private WebElement login;
    @FindBy(id = "user")
    private WebElement fieldUser;
    @FindBy(name = "password")
    private WebElement fieldPassword;
    @FindBy(name = "action:login")
    private WebElement buttonLogin;
    @FindBy(xpath = "(//a[contains(text(), 'Наука и техника')])[1]")
    private WebElement menuSection;
    @FindBy(xpath = "//a[contains(text(),'IT')]")
    private WebElement chapterIT;
    @FindBy(css = ".search__control")
    private WebElement searchControl;

    public void sendToSearchControlField(String value) {
        searchControl.sendKeys(value);
    }

    public WebElement getElementSearchControl() {
        return searchControl;
    }

    public void openSearchControlField() {
        Actions action = new Actions(getDriver());
        action.moveToElement(menuSection)
                .pause(500)
                .click(chapterIT)
                .build()
                .perform();
    }

    public void actionToLogin(String login, String password) throws InterruptedException {
        clickLogin();
        sendFieldUser(login);
        sendFieldPassword(password);
        driverWait(buttonLogin);
        buttonLogin.click();
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void sendFieldUser(String value) {
        driverWait(fieldUser);
        fieldUser.sendKeys(value);
    }

    private void sendFieldPassword(String value) throws InterruptedException {
        Thread.sleep(200);
        driverWait(fieldPassword);
        fieldPassword.sendKeys(value);
    }

    private void clickLogin() {
        driverWait(login, 500);
        login.click();
    }
}
