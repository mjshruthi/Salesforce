import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LeadsPage {
    private static final By NEW_LEADS = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static final By DROP_DOWN = By.xpath("//select[@id='fcf']");
    private static final By USER_NAV = By.xpath("//div[@id='userNavButton']");
    private static final By USER_NAV_LOGOUT = By.xpath("//a[@title='Logout']");


    private static WebDriver driver;
    private WebDriverWait wait;

    public LeadsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public void verifyOnLeadsHome(){
        System.out.println(driver.getTitle());
    }

    public void clickDropDown(){
        waitFor(DROP_DOWN);
        driver.findElement(DROP_DOWN).click();
       Select drpDownlist = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
        drpDownlist.selectByVisibleText("Today's Leads");
     /*   List<WebElement> dropDownValues =  drpDownlist.getAllSelectedOptions();
        for(WebElement element : dropDownValues ) {
            HashMap hm = new HashMap();
        }*/
        threadSleep(2000);
    }

    public void clickDropDownANDselectTodaysLead(){
        waitFor(DROP_DOWN);
        driver.findElement(DROP_DOWN).click();
        Select drpDownlist = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
        drpDownlist.selectByVisibleText("Today's Leads");
    }

    public String verifyDropDownItemSelected(){
        Select drpDownlist = new Select(driver.findElement(By.xpath("//select[@name='fcf']")));
        String selectedItem = drpDownlist.getFirstSelectedOption().getText();
        return selectedItem;
    }

    public void clickDropDownANDselectMyUnreadLeads(){
        waitFor(DROP_DOWN);
        driver.findElement(DROP_DOWN).click();
        Select drpDownlist = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
        drpDownlist.selectByVisibleText("My Unread Leads");
    }

    public LoginPage clickLogout() {
        waitFor(USER_NAV);
        clickUserMenu();
        driver.findElement(USER_NAV_LOGOUT).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            // neglect.
        }
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }


    public void clickUserMenu() {
        waitFor(USER_NAV);
        driver.findElement(USER_NAV).click();
    }

    public void newAccount(){
        waitFor(NEW_LEADS);
        driver.findElement(NEW_LEADS).click();
    }

    private void waitFor(By element) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // ignore.
        }
    }

    public void quit() {
        driver.quit();
    }
}
