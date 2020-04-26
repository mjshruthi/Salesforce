import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsPage {
    private static final By NEW_ACCOUNT = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input[@name='new']");
    private static final By ACCOUNT_CANCEL_BUTTON = By.xpath("//*[@id=\"bottomButtonRow\"]/input[3]");
    private static final String VIEW_XPATH = "//*[@id='fcf']/option[text()='PLACE_HOLDER']";
    private static final By GO = By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input");
    private static final By EDIT_VIEW = By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]");
    private static final By CREATE_NEW_VIEW = By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
    private static final By VIEW_CANCEL_BUTTON = By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]");
    private static final By MERGE_ACCOUNTS = By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a");
    private static final By MERGE_ACCOUNTS_NEXT = By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[1]");
    private static final By ACCOUNT_REPORT_SAVE = By.xpath("//*[@id=\"ext-gen49\"]");
    private static final By ACCOUNT_REPORT = By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a");

    private static WebDriver driver;
    private WebDriverWait wait;

    public AccountsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public AccountEditPage createNewAccount(){
        waitFor(NEW_ACCOUNT);
        driver.findElement(NEW_ACCOUNT).click();
        waitFor(ACCOUNT_CANCEL_BUTTON);
        AccountEditPage accountEditPage = new AccountEditPage(driver);
        return accountEditPage;
    }

    public ViewEditPage createNewView(){
        waitFor(CREATE_NEW_VIEW);
        driver.findElement(CREATE_NEW_VIEW).click();
        waitFor(VIEW_CANCEL_BUTTON);
        ViewEditPage viewEditPage = new ViewEditPage(driver);
        return viewEditPage;

    }

    public ViewEditPage editView(String viewName) {
        waitFor(EDIT_VIEW);
        driver.findElement(getViewDropDown(viewName)).click();
        driver.findElement(EDIT_VIEW).click();
        waitFor(VIEW_CANCEL_BUTTON);
        ViewEditPage viewEditPage = new ViewEditPage(driver);
        return viewEditPage;
    }

    public MergeAccountPage clickMergeAccounts() {
        waitFor(MERGE_ACCOUNTS);
        driver.findElement(MERGE_ACCOUNTS).click();
        waitFor(MERGE_ACCOUNTS_NEXT);
        MergeAccountPage mergeAccountPage = new MergeAccountPage(driver);
        return mergeAccountPage;
    }

    public AccountReportPage clickAccountReport() {
        waitFor(ACCOUNT_REPORT);
        driver.findElement(ACCOUNT_REPORT).click();
        waitFor(ACCOUNT_REPORT_SAVE);
        AccountReportPage accountReportPage = new AccountReportPage(driver);
        return accountReportPage;
    }

    private By getViewDropDown(String name){
        String xpath = VIEW_XPATH.replace("PLACE_HOLDER", name);
        return By.xpath(xpath);
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void quit(){
        driver.quit();
    }
}
