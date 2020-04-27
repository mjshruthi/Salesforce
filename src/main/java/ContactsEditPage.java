import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsEditPage {
    private static final By SAVE_BUTTON = By.xpath("//*[@id=\"topButtonRow\"]/input[1]");
    private static final By SAVE_AND_NEW = By.xpath("//*[@id=\"topButtonRow\"]/input[2]");
    private static final By CANCEL = By.xpath("//*[@id=\"topButtonRow\"]/input[3]");
    private static final By FIRST_NAME = By.xpath("//*[@id=\"name_firstcon2\"]");
    private static final By LAST_NAME = By.xpath("//*[@id=\"name_lastcon2\"]");
    private static final By ACCOUNT_NAME = By.xpath("//*[@id=\"con4\"]");
    private static final By ACCOUNT_SEARCH_BUTTON = By.xpath("//*[@id=\"con4_lkwgt\"]/img");
    private static final By ACC_FIRST_RESULT = By.xpath("//*[@id=\"new\"]/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a");
    private static final By CONTACT_SAVE_ERROR = By.xpath("//*[@id=\"errorDiv_ep\"]");
    private static final By CONTACT_DISPLAY_NAME = By.xpath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
    private static WebDriver driver;
    private WebDriverWait wait;

    public ContactsEditPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void clickSave() {
        waitFor(SAVE_BUTTON);
        driver.findElement(SAVE_BUTTON).click();
    }

    public void clickSaveAndNew() {
        waitFor(SAVE_AND_NEW);
        driver.findElement(SAVE_AND_NEW).click();
    }

    public void clickCancel() {
        waitFor(CANCEL);
        driver.findElement(CANCEL).click();
    }

    public void setFirstName(String name) {
        waitFor(FIRST_NAME);
        driver.findElement(FIRST_NAME).sendKeys(name);
    }

    public void setLastName(String name) {
        waitFor(LAST_NAME);
        driver.findElement(LAST_NAME).sendKeys(name);
    }

    public void setAccount(String accName) {
        waitFor(ACCOUNT_NAME);
        driver.findElement(ACCOUNT_NAME).sendKeys(accName);
        driver.findElement(ACCOUNT_SEARCH_BUTTON).click();
        threadSleep(1000);
        String parentWindowHandle = driver.getWindowHandle();
        List<String> windowTabs = new ArrayList(driver.getWindowHandles());
        System.out.println("Parent:" + parentWindowHandle);
        System.out.println("Number of Windows:" + Arrays.toString(windowTabs.toArray()));
        driver.switchTo().window(windowTabs.get(1));
        threadSleep(1000);
        driver.switchTo().frame("resultsFrame");
        driver.findElement(ACC_FIRST_RESULT).click();
        driver.switchTo().window(parentWindowHandle);
    }

    public String getContactDisplayName() {
        waitFor(CONTACT_DISPLAY_NAME);
        String name = driver.findElement(CONTACT_DISPLAY_NAME).getText();
        return name;
    }

    public String getContactSaveError() {
        waitFor(CONTACT_SAVE_ERROR);
        return driver.findElement(CONTACT_SAVE_ERROR).getText();
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // neglect.
        }
    }
}
