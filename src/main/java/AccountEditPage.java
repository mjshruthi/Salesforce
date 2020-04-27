import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountEditPage {

    private static WebDriver driver;
    private WebDriverWait wait;

    private static final By NAME = By.xpath("//*[@id=\"acc2\"]");
    private static final By TYPE_DROPDOWN = By.xpath("//*[@id=\"acc6\"]");
    private static final By TYPE_PARTNER = By.xpath("//*[@id=\"acc6\"]/option[7]");
    private static final By INDUSTRY_TECH = By.xpath("//*[@id=\"acc7\"]/option[30]");

    private static final By SAVE_BUTTON = By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]");
    private static final By SAVE_AND_NEW = By.xpath("//*[@id=\"bottomButtonRow\"]/input[2]");
    private static final By CANCEL = By.xpath("//*[@id=\"bottomButtonRow\"]/input[3]");

    private static final By NEW_ACCOUNT_NAME = By.xpath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");

    public AccountEditPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void setAccountName(String accountName) {
        waitFor(NAME);
        driver.findElement(NAME).sendKeys(accountName);
    }

    public void setType() {
        waitFor(TYPE_DROPDOWN);
        driver.findElement(TYPE_PARTNER).click();
    }

    public void setIndustry() {
        waitFor(TYPE_DROPDOWN);
        driver.findElement(INDUSTRY_TECH).click();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void clickSave() {
        waitFor(SAVE_BUTTON);
        driver.findElement(SAVE_BUTTON).click();
    }

    public boolean isAccountCreated(String accountName) {
        waitFor(NEW_ACCOUNT_NAME);
        String newAccountName = driver.findElement(NEW_ACCOUNT_NAME).getText();
        if (accountName.equals(newAccountName)){
            return true;
        } else {
            return false;
        }
    }

    public void clickSaveAndNew() {
        waitFor(SAVE_AND_NEW);
        driver.findElement(SAVE_AND_NEW).click();
    }

    public void clickCancel() {
        waitFor(CANCEL);
        driver.findElement(CANCEL).click();
    }
}
