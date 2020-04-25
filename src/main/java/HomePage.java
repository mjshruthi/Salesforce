import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final By USER_NAV = By.xpath("//div[@id='userNavButton']");
    private static final By USER_NAV_MY_PROFILE = By.xpath("//a[@title='My Profile']");
    private static final By USER_NAV_MY_SETTINGS = By.xpath("//a[@title='My Settings']");
    private static final By USER_NAV_DEV_CONSOLE = By.xpath("//a[@title='Developer Console (New Window)']");
    private static final By USER_NAV_LIGHTNING_EXP = By.xpath("//a[@title='Switch to Lightning Experience']");
    private static final By USER_NAV_LOGOUT = By.xpath("//a[@title='Logout']");

    private static final By CRITICAL_UPDATE_CANCEL_BUTTON = By.xpath("//*[@id='cruc_notifyX']");
    private static WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void clickUserMenu() {
        waitFor(USER_NAV);
        driver.findElement(USER_NAV).click();
    }

    public void clickUserMenuMyProfile() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_MY_PROFILE).click();
    }

    public void clickUserMenuMySettings() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_MY_SETTINGS).click();
    }

    public void clickUserMenuDevConsole() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_DEV_CONSOLE).click();
    }

    public void clickUserMenuLightningExperience() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_LIGHTNING_EXP).click();
    }

    public void clickLogout() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_LOGOUT).click();
    }

    public void verifyMenuItems() {
        waitFor(USER_NAV_DEV_CONSOLE);
        waitFor(USER_NAV_MY_SETTINGS);
        waitFor(USER_NAV_MY_PROFILE);
        waitFor(USER_NAV_LIGHTNING_EXP);
        waitFor(USER_NAV_LOGOUT);
    }

    public boolean isUserNavButtonVisible() {
        waitFor(USER_NAV);
        return true;
    }

    public void quit(){
        driver.quit();
    }

    private void waitFor(By element){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }
}
