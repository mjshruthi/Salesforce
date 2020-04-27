import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomePage {

    private static final By USER_NAV = By.xpath("//div[@id='userNavButton']");
    private static final By USER_NAV_MY_PROFILE = By.xpath("//a[@title='My Profile']");
    private static final By USER_NAV_MY_SETTINGS = By.xpath("//a[@title='My Settings']");
    private static final By USER_NAV_DEV_CONSOLE = By.xpath("//a[@title='Developer Console (New Window)']");
    private static final By USER_NAV_LIGHTNING_EXP = By.xpath("//a[@title='Switch to Lightning Experience']");
    private static final By USER_NAV_LOGOUT = By.xpath("//a[@title='Logout']");
    private static final By CONTACT_INFO = By.xpath("//*[@class='contactInfoLaunch editLink']//img");
    private static final By ABOUT_TAB = By.id("aboutTab");
    private static final By LAST_NAME = By.xpath("//*[@id=\"lastName\"]");
    private static final By POST = By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]");
    private static final By CRITICAL_UPDATE_CANCEL_BUTTON = By.xpath("//*[@id='cruc_notifyX']");

    private static final By ACCOUNTS_TAB = By.xpath("//li[@id='Account_Tab']");
    private static final By OPPORTUNITIES_TAB = By.xpath("//li[@id='Opportunity_Tab']");
    private static final By LEADS_TAB = By.xpath("//li[@id='Lead_Tab']");
    private static final By CONTACTS_TAB = By.xpath("//li[@id='Contact_Tab']");

    private static WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
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
        //String parent = driver.getWindowHandle();
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_DEV_CONSOLE).click();
        ArrayList<String> windowTabs = new ArrayList(driver.getWindowHandles());
        System.out.println(windowTabs.size());
        if (windowTabs.size() > 1) {
            driver.switchTo().window(windowTabs.get(1));
            driver.close();
        }
        driver.switchTo().window(windowTabs.get(0));
    }

    public void clickContact() {
        waitFor(CONTACT_INFO);
        driver.findElement(CONTACT_INFO).click();

    }

    public void getPopup() {
        //String parent=driver.getWindowHandle();
        WebElement popup = driver.findElement(By.xpath("//h2[@id='contactInfoTitle']"));
        if (popup.isDisplayed()) {
            popup.click();
        }
        /*ArrayList<String> windowTabs = new ArrayList(driver.getWindowHandles());
        System.out.println("Number of Windows:" +windowTabs.size());
        WebDriver popup = driver.switchTo().window(windowTabs.get(0));*/
        System.out.println(popup.getText());
    }


    public void clickAbout() {
        WebElement iFrame = driver.findElement(By.xpath("//iframe[@id='contactInfoContentId']"));
        driver.switchTo().frame(iFrame);
        System.out.println("Switched to iframe");
        waitFor(ABOUT_TAB);
   /*     try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            // neglect.
        }*/
        driver.findElement(ABOUT_TAB).click();
    }

    public void clickLastName() {
        waitFor(LAST_NAME);
        driver.findElement(LAST_NAME).click();
    }

    public void editLastName() {
        driver.findElement(LAST_NAME).clear();
        driver.findElement(LAST_NAME).sendKeys("test");
    }

    public void clickSaveAll() {
        driver.findElement(By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]")).click();
        driver.switchTo().defaultContent();
    }

    public boolean verifyLastNameChange() {
        waitFor(USER_NAV);
        System.out.println(driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]")).getText());
        String editedLastName = driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]")).getText();
        return editedLastName.equalsIgnoreCase("4lx6mj8mol6l test ");
    }

    public void clickPost() {
        waitFor(POST);
        driver.findElement(POST).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // neglect.
        }
    }

    public void enterTextToPost() {
        WebElement iFrame = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
        driver.switchTo().frame(iFrame);
        System.out.println("Switched to iframe for Post.");
        String postData = "let me post this data.";
        driver.findElement(By.cssSelector("body")).sendKeys(postData);
        /*WebElement body = driver.findElement(By.xpath("//iframe[contains(@class,'cke_wysiwyg_frame cke_reset')]"));
        body.click();
        body.sendKeys("Test Post");*/
        // driver.findElement(By.xpath("//body[@class='chatterPublisherRTE vsc-initialized cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']"));
        //driver.findElement(By.xpath("//textarea[@id='publishereditablearea']")).sendKeys("Test Post");
    }

    public void clickShare() {
        driver.findElement(By.xpath("//*[@id='publishersharebutton']")).click(); // //*[@id="publishersharebutton"]
    }

    public void verifyPostedText() {
        String postData = "let me post this data.";
        driver.findElement(By.xpath("//*[@id=\"publishersharebutton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedwrapper\"]//p[contains(text(),postData)]"));
    }

    public void clickUserMenuLightningExperience() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_LIGHTNING_EXP).click();
    }

    public LoginPage clickLogout() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_LOGOUT).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            // neglect.
        }
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
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

    public void quit() {
        driver.quit();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public AccountsPage clickAccountsTab() {
        waitFor(ACCOUNTS_TAB);
        driver.findElement(ACCOUNTS_TAB).click();
        return new AccountsPage(driver);
    }

    public ContactsPage clickContactsTab() {
        waitFor(CONTACTS_TAB);
        driver.findElement(CONTACTS_TAB).click();
        return new ContactsPage(driver);
    }

    public OpportunitiesPage clickOpportunitiesTab() {
        waitFor(OPPORTUNITIES_TAB);
        driver.findElement(OPPORTUNITIES_TAB).click();
        return new OpportunitiesPage(driver);
    }

    public LeadsPage clickLeadsTab() {
        waitFor(LEADS_TAB);
        driver.findElement(LEADS_TAB).click();
        return new LeadsPage(driver);
    }
}
