import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final By USER_NAV = By.xpath("//div[@id='userNavButton']");
    private static final By USER_NAV_MY_PROFILE = By.xpath("//a[@title='My Profile']");
    private static final By USER_NAV_MY_SETTINGS = By.xpath("//a[@title='My Settings']");

    private static final By CRITICAL_UPDATE_CANCEL_BUTTON = By.xpath("//*[@id='cruc_notifyX']");
    private static WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void clickUserMenu() {
        driver.findElement(USER_NAV).click();
    }

    public void clickUserMenuMyProfile() {
        clickUserMenu();
        driver.findElement(USER_NAV_MY_PROFILE).click();
    }

    public void clickUserMenuMySettings() {
        clickUserMenu();
        driver.findElement(USER_NAV_MY_SETTINGS).click();
    }

    public boolean isUserNavButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(USER_NAV)));
        return true;
    }

}
