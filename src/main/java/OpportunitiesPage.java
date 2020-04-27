import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpportunitiesPage {
    private static final By OPP_DROP_DOWN = By.xpath("//*[@id=\"fcf\"]");
    private static final By ALL_OPP_VIEW = By.xpath("//*[@id=\"fcf\"]/option[1]");
    private static final By CLOSING_NEXT_MONTH_VIEW = By.xpath("//*[@id=\"fcf\"]/option[2]");
    private static final By NEW_OPPORTUNITY = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static final By OPP_SAVE_BUTTON = By.xpath("//*[@id=\"topButtonRow\"]/input[1]");
    private static final By OPP_PIPELINE_REPORT = By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a");
    private static final By OPP_STUCK_REPORT = By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a");
    private static final By OPP_QUARTERLY_REPORT = By.xpath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input");
    private static WebDriver driver;
    private WebDriverWait wait;

    public OpportunitiesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public OpportunityEditPage clickNewOpportunity() {
        waitFor(NEW_OPPORTUNITY);
        driver.findElement(NEW_OPPORTUNITY).click();
        threadSleep(2000);
        OpportunityEditPage oppEditPage = new OpportunityEditPage(driver);
        return oppEditPage;
    }

    public void runOpportunityReport() {
        waitFor(OPP_PIPELINE_REPORT);
        driver.findElement(OPP_PIPELINE_REPORT).click();
    }

    public void runOpportunityStuckReport() {
        waitFor(OPP_STUCK_REPORT);
        driver.findElement(OPP_STUCK_REPORT).click();
    }

    public void runQuarterlySummaryReport() {
        waitFor(OPP_QUARTERLY_REPORT);
        driver.findElement(OPP_QUARTERLY_REPORT).click();
    }

    public void verifyDropDownMenuItems() {
        waitFor(ALL_OPP_VIEW);
        waitFor(CLOSING_NEXT_MONTH_VIEW);
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void quit() {
        driver.quit();
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // neglect.
        }
    }
}
