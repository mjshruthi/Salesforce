import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPwdPage {
    private static final By USERNAME = By.xpath("//input[@id='un']");
    private static final By CHECKEMAIL = By.xpath("//h2[@id='header']");
    private static WebDriver driver;
    private WebDriverWait wait;

    public ForgotPwdPage() {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public ForgotPwdPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void enterUserName() {
        waitFor(USERNAME);
        driver.findElement(USERNAME).sendKeys(System.getProperty("username"));
    }

    public void clickContinue() {
        driver.findElement(By.id("continue")).sendKeys(System.getProperty("username"));
    }

    public boolean isCheckEmailVisible() {
        waitFor(CHECKEMAIL);
        boolean checkEmailVisible = driver.findElement(CHECKEMAIL).isDisplayed();
        return checkEmailVisible;
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void quit() {
        driver.quit();
    }
}
