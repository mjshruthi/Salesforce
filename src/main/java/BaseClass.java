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

public class BaseClass {
    public static WebDriver driver = null;

    static void launchBrowser(String browserType) {

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

    static void loginToBrowser() throws InterruptedException {
//        String propertiesPath = System.getProperty("user.dir")+"/testData.properties";
        //C:\Users\navee\IdeaProjects\SalesForce\src\main\resources\testData.properties
//        try {
//            Properties prop = new Properties();
//            FileInputStream fi = new FileInputStream(propertiesPath);
//            prop.load(fi);
//            System.getProperties().putAll(prop);
//        }catch(Exception a) {
//            System.out.println(a);
//        }


        System.out.println("login browser Property value of Username = "+System.getProperty("username"));
        System.out.println("login browser Property value of Password = "+System.getProperty("password"));
        String userName = System.getProperty("username");
        String pwd = System.getProperty("password");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='username']"))));
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
        String sText = driver.findElement(By.xpath("//input[@id='Login']")).getText();
        System.out.println(sText);
        System.out.println(driver.findElement(By.xpath("//div[@id='signup']")).getText());
        driver.findElement(By.xpath("//input[@id='Login']")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='actionLink']"))));
//inline overlay dialog
        //wait.equals()
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='sessionserver']")));
        //driver.findElement(By.xpath("//iframe[@title='sessionserver']")).click();
        Thread.sleep(5000);


        //driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='classTop']//a[@id='tryLexDialogX'][@title='Close']")));
        //driver.switchTo().frame(driver.findElement(By.xpath("//a[@id='tryLexDialogX'][@title='Close']")));
        //WebElement ft=driver.findElement(By.xpath("//a[@class='button secondary']"),"TryForFree"));
        //clickButton(ft,"TryForFree");

        WebElement popup = driver.findElement(By.xpath("//*[@id='tryLexDialogX'][@title='Close']"));
        Thread.sleep(1000);
        if(popup.isDisplayed()){
            popup.click();
        }
    }



    static void readProperties(){
        String propertiesPath = System.getProperty("user.dir")+"/testData.properties";
        //C:\Users\navee\IdeaProjects\SalesForce\src\main\resources\testData.properties
        try {
            Properties prop = new Properties();
            FileInputStream fi = new FileInputStream(propertiesPath);
            prop.load(fi);
            System.getProperties().putAll(prop);
            System.out.println("Property value of Username = "+System.getProperty("username"));
            System.out.println("Property value of Password = "+System.getProperty("password"));
            System.setProperty("hi", "Hello");
            System.out.println(System.getProperty("os.name"));
            System.out.println(System.getProperty("user.dir"));
            System.out.println(System.getProperty("hi"));



            //12:05 PST

        }catch(Exception a) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        readProperties();
        launchBrowser("ch");
        loginToBrowser();

    }

}
