import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountReportPage {
    private static final By ACCOUNT_REPORT_SAVE = By.xpath("//*[@id=\"ext-gen49\"]");
    private static final By ACCOUNT_REPORT = By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a");
    private static final By POPUP_CANCEL = By.xpath("//*[@id=\"ext-comp-1107\"]");
    private static final By DATE_FIELD = By.xpath("//*[@id=\"ext-gen148\"]");
    private static final By CREATE_DATE_DROPDOWN = By.xpath("//*[@id=\"ext-gen244\"]/div[3]");
    private static final By FROM_DATE_FIELD = By.xpath("//*[@id=\"ext-gen152\"]");
    private static final By FROM_DATE_FIELD_TODAY = By.xpath("//*[@id=\"ext-gen258\"]");////*[@id="ext-gen258"]

    private static final By REPORT_NAME = By.xpath("//*[@id=\"saveReportDlg_reportNameField\"]");
    private static final By REPORT_UNIQUE_NAME = By.xpath("//*[@id=\"saveReportDlg_DeveloperName\"]");
    private static final By REPORT_SAVE = By.xpath("//*[@id=\"dlgSaveReport\"]/tbody/tr[2]/td[2]");
    private static WebDriver driver;
    private WebDriverWait wait;

    public AccountReportPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void selectDateField() {
        waitFor(DATE_FIELD);
        driver.findElement(DATE_FIELD).click();
        threadSleep(2000);
        waitFor(CREATE_DATE_DROPDOWN);
        driver.findElement(CREATE_DATE_DROPDOWN).click();
    }

    public void selectCurrentDate() {
        waitFor(FROM_DATE_FIELD);
        driver.findElement(FROM_DATE_FIELD).click();
        threadSleep(2000);
        waitFor(FROM_DATE_FIELD_TODAY);
        driver.findElement(FROM_DATE_FIELD_TODAY).click();
    }

    public void clickReportSave(String reportName) {
        waitFor(ACCOUNT_REPORT_SAVE);
        driver.findElement(ACCOUNT_REPORT_SAVE).click();
        waitFor(REPORT_NAME);
        driver.findElement(REPORT_NAME).sendKeys(reportName);
        driver.findElement(REPORT_UNIQUE_NAME).sendKeys("");
        threadSleep(2000);
        driver.findElement(REPORT_SAVE).click();
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // neglect.
        }
    }
}
