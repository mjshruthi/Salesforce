import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;

public class LoginPage {
    private static final By REMEMBER_ME = By.xpath("//*[@id='rememberUn']");
    private static final By FORGOT_PWD = By.xpath("//*[@id='forgot_password_link']");
    private static WebDriver driver;
    private WebDriverWait wait;

    public LoginPage() {
        readProperties();
        launchBrowser(System.getProperty("browser"));
        wait = new WebDriverWait(driver, 30);
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    private void launchBrowser(String browserType) {

        if (browserType.startsWith("ch")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserType.startsWith("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("You have not given browser type correctly.");
        }
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");
    }

    public HomePage login() {
        String userName = System.getProperty("username");
        String pwd = System.getProperty("password");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='username']"))));

        setUserName(userName);
        setPassword(pwd);
        clickLoginButton();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='actionLink']"))));

        //driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='classTop']//a[@id='tryLexDialogX'][@title='Close']")));
        //driver.switchTo().frame(driver.findElement(By.xpath("//a[@id='tryLexDialogX'][@title='Close']")));
        //WebElement ft=driver.findElement(By.xpath("//a[@class='button secondary']"),"TryForFree"));
        //clickButton(ft,"TryForFree");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            // ignore.
        }


        WebElement popup = driver.findElement(By.xpath("//*[@id='tryLexDialogX'][@title='Close']"));
        if (popup.isDisplayed()) {
            popup.click();
        }
 /*       else{
            return new HomePage(driver);
        }*/

        return new HomePage(driver);
    }

    public void setUserName(String userName) {
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@id='Login']")).click();
    }

    public String getLoginErrorMessage() {
        waitFor(By.xpath("//*[@id='error']"));
        String errorMsg = driver.findElement(By.xpath("//*[@id='error']")).getText();
        return errorMsg;
    }

    public void setRememberMe() {
        waitFor(REMEMBER_ME);
        driver.findElement(REMEMBER_ME).click();
    }

    public boolean isRememberMeSelected() {
        boolean remembermeSelected = driver.findElement(REMEMBER_ME).isSelected();
        return remembermeSelected;
    }

    public boolean isUserNamePopulated() {
        boolean userNamePopulated = driver.findElement(By.xpath("//*[@id='idcard-identity']"))
                .getText().equals(System.getProperty("username"));
        return userNamePopulated;
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public boolean isRememberMeVisible() {
        waitFor(REMEMBER_ME);
        return true;
    }

    public boolean isForgotPwdVisible() {
        waitFor(FORGOT_PWD);
        return true;
    }

    public ForgotPwdPage clickForgotPwdButton() {
        driver.findElement(FORGOT_PWD).click();
        return new ForgotPwdPage(driver);
    }

    private void readProperties() {
        String propertiesPath = System.getProperty("user.dir") + "/testData.properties";
        try {
            Properties prop = new Properties();
            FileInputStream fi = new FileInputStream(propertiesPath);
            prop.load(fi);
            System.getProperties().putAll(prop);
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    public void quit() {
        driver.quit();
    }
}
