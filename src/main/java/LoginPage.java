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
    private static WebDriver driver;
    private WebDriverWait wait;

    public LoginPage() {
        readProperties();
        launchBrowser(System.getProperty("browser"));
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

        WebElement popup = driver.findElement(By.xpath("//*[@id='tryLexDialogX'][@title='Close']"));
        if (popup.isDisplayed()) {
            popup.click();
        }

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
        String errorMsg = driver.findElement(By.xpath("//*[@id='error']")).getText();
        return errorMsg;
    }

    public void setRememberMe(){
        driver.findElement(By.xpath("//input[@id='rememberUn']"));
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

    public void quit(){
        driver.quit();
    }

}
