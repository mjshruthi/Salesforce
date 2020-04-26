import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewEditPage {
    public static final By SAVE_BUTTON = By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]");
    public static final By CANCEL_BUTTON = By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]");
    public static final By VIEW_NAME = By.xpath("//*[@id=\"fname\"]");
    public static final By VIEW_UNIQUE_NAME = By.xpath("//*[@id=\"devname\"]");
    public static final By FILTER_FIELD_ACCOUNT_NAME = By.xpath("//*[@id=\"fcol1\"]/option[2]");
    public static final By FILTER_OPERATOR_CONTAINS = By.xpath("//*[@id=\"fop1\"]/option[4]");
    public static final By FILTER_VALUE = By.xpath("//*[@id=\"fval1\"]");

    private static WebDriver driver;
    private WebDriverWait wait;

    public ViewEditPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void setViewName(String viewName) {
        waitFor(VIEW_NAME);
        driver.findElement(VIEW_NAME).sendKeys(viewName);
    }

    public void setViewUniqueName(String viewName) {
        waitFor(VIEW_UNIQUE_NAME);
        driver.findElement(VIEW_UNIQUE_NAME).sendKeys(viewName);
    }

    public void setFilterOnAccountName() {
        waitFor(FILTER_FIELD_ACCOUNT_NAME);
        driver.findElement(FILTER_FIELD_ACCOUNT_NAME).click();
    }

    public void setFilterOperatorContains() {
        waitFor(FILTER_OPERATOR_CONTAINS);
        driver.findElement(FILTER_OPERATOR_CONTAINS).click();
    }

    public void setFilterValue(String filterValue) {
        waitFor(FILTER_VALUE);
        driver.findElement(FILTER_VALUE).clear();
        driver.findElement(FILTER_VALUE).sendKeys(filterValue);
    }

    public void clickSaveView() {
        waitFor(SAVE_BUTTON);
        driver.findElement(SAVE_BUTTON).click();
    }

    public boolean isViewCreated(String viewName) {
        //*[@id="00B5w00000Be1Tb_listSelect"]/option[2]
        //*[@id="fcf"]/option[2]
        return true;
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

}
