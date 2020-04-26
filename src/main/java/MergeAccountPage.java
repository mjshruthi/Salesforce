import com.sun.org.apache.bcel.internal.generic.NEW;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MergeAccountPage {
    private static final By SEARCH_FIELD = By.xpath("//*[@id=\"srch\"]");
    private static final By FIND_ACCOUNTS = By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]");
    private static final By FIRST_ACCOUNT = By.xpath("//*[@id=\"cid0\"]");
    private static final By SECOND_ACCOUNT = By.xpath("//*[@id=\"cid1\"]");
    private static final By NEXT = By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[1]");
    private static final By MERGE = By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]");
    private static WebDriver driver;
    private WebDriverWait wait;

    public MergeAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void setSearchString(String searchTerm) {
        waitFor(SEARCH_FIELD);
        driver.findElement(SEARCH_FIELD).click();
        driver.findElement(SEARCH_FIELD).sendKeys(searchTerm);
    }

    public void clickFindAccounts() {
        waitFor(FIND_ACCOUNTS);
        driver.findElement(FIND_ACCOUNTS).click();
    }

    public void selectFirstTwoAccountsToMerge() {
        waitFor(FIRST_ACCOUNT);
        driver.findElement(FIRST_ACCOUNT).click();
        driver.findElement(SECOND_ACCOUNT).click();
    }

    public void clickNext() {
        waitFor(NEXT);
        driver.findElement(NEXT).click();
    }

    public void mergeAccounts() {
        waitFor(MERGE);
        driver.findElement(MERGE).click();
        driver.switchTo().alert().accept();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }
}
