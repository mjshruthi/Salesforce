import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadsPage {
    private static final By NEW_LEADS = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static WebDriver driver;
    private WebDriverWait wait;

    public LeadsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void newAccount(){
        waitFor(NEW_LEADS);
        driver.findElement(NEW_LEADS).click();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }
}
