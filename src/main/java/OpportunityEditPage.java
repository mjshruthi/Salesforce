import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpportunityEditPage {
    private static final By SAVE_BUTTON = By.xpath("//*[@id=\"topButtonRow\"]/input[1]");
    private static final By OPP_NAME_FIELD = By.xpath("//*[@id=\"opp3\"]");
    private static final By ACC_NAME_FIELD = By.xpath("//*[@id=\"opp4\"]");
    private static final By ACC_SEARCH_BUTTON = By.xpath("//*[@id=\"opp4_lkwgt\"]/img");
    private static final By ACC_FIRST_RESULT = By.xpath("//*[@id=\"new\"]/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a");
    private static final By CLOSE_DATE = By.xpath("//*[@id=\"opp9\"]");
    private static final By CLOSE_DATE_TODAY = By.xpath("//*[@id=\"datePicker\"]/div[2]/div/a");
    private static final By STAGE_QUALIFICATION = By.xpath("//*[@id=\"opp11\"]/option[2]");
    private static final By PROBABILITY = By.xpath("//*[@id=\"opp12\"]");

    private static WebDriver driver;
    private WebDriverWait wait;

    public OpportunityEditPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void quit() {
        driver.quit();
    }

    public void saveOpportunity() {
        waitFor(SAVE_BUTTON);
        driver.findElement(SAVE_BUTTON).click();
    }

    public void setOppNameField(String name) {
        waitFor(OPP_NAME_FIELD);
        driver.findElement(OPP_NAME_FIELD).sendKeys(name);
    }

    public void setAccountNameField(String accName) {
        waitFor(ACC_NAME_FIELD);
        driver.findElement(ACC_NAME_FIELD).sendKeys(accName);
        driver.findElement(ACC_SEARCH_BUTTON).click();
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

    public void setCloseDateToday() {
        waitFor(CLOSE_DATE);
        driver.findElement(CLOSE_DATE).click();
        driver.findElement(CLOSE_DATE_TODAY).click();
    }

    public void setStage() {
        waitFor(STAGE_QUALIFICATION);
        driver.findElement(STAGE_QUALIFICATION).click();
    }

    public void setProbablity(int probablity) {
        waitFor(PROBABILITY);
        driver.findElement(PROBABILITY).clear();
        driver.findElement(PROBABILITY).sendKeys(String.valueOf(probablity));
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // neglect.
        }
    }

}
