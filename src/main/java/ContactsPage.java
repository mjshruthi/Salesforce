import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {
    private static final By NEW_CONTACTS = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static final By EDIT_VIEW = By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]");
    private static final By CREATE_NEW_VIEW = By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
    private static final By RECENTLY_CREATED = By.xpath("//*[@id=\"hotlist_mode\"]/option[1]");
    private static final By MY_CONTACTS = By.xpath("//*[@id=\"fcf\"]/option[3]");
    private static final By GO_TO_VIEW = By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input");
    private static final By FIRST_MY_CONTACT = By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a");

    private static final By VIEW_NAME = By.xpath("//*[@id=\"fname\"]");
    private static final By VIEW_UNIQUE_NAME = By.xpath("//*[@id=\"devname\"]");
    private static final By SAVE_VIEW_BUTTON = By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]");
    private static final By CANCEL_VIEW_SAVE = By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]");

    private static WebDriver driver;
    private WebDriverWait wait;

    public ContactsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public ContactsEditPage clickNewContacts(){
        waitFor(NEW_CONTACTS);
        driver.findElement(NEW_CONTACTS).click();
        ContactsEditPage contactsEditPage = new ContactsEditPage(driver);
        return contactsEditPage;
    }

    public void clickEditView() {
        waitFor(EDIT_VIEW);
        driver.findElement(EDIT_VIEW).click();
    }

    public void clickCreateNewVeiw() {
        waitFor(CREATE_NEW_VIEW);
        driver.findElement(CREATE_NEW_VIEW).click();
    }

    public void setNewViewName(String viewName) {
        waitFor(VIEW_NAME);
        driver.findElement(VIEW_NAME).sendKeys(viewName);
    }

    public void setNewViewUniqueName(String uniqueName) {
        waitFor(VIEW_UNIQUE_NAME);
        driver.findElement(VIEW_UNIQUE_NAME).sendKeys(uniqueName);
    }

    public void saveNewView() {
        waitFor(SAVE_VIEW_BUTTON);
        driver.findElement(SAVE_VIEW_BUTTON).click();
    }

    public void cancelNewView() {
        waitFor(CANCEL_VIEW_SAVE);
        driver.findElement(CANCEL_VIEW_SAVE).click();
    }

    public void selectMyContactsView() {
        waitFor(MY_CONTACTS);
        driver.findElement(MY_CONTACTS).click();
    }

    public void goToView() {
        waitFor(GO_TO_VIEW);
        driver.findElement(GO_TO_VIEW).click();
    }

    public void selectRecentlyCreatedContacts() {
        waitFor(RECENTLY_CREATED);
        driver.findElement(RECENTLY_CREATED).click();
    }

    public void selectFirstMyContact() {
        waitFor(FIRST_MY_CONTACT);
        driver.findElement(FIRST_MY_CONTACT).click();
    }

    public void quit() {
        driver.quit();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }
}
