import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {
    private static final By NEW_CONTACTS = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static WebDriver driver;
    private WebDriverWait wait;

    public ContactsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void newAccount(){
        waitFor(NEW_CONTACTS);
        driver.findElement(NEW_CONTACTS).click();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }
}
