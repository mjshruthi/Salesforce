import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LeadsPage {
    private static final By NEW_LEADS = By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input");
    private static final By DROP_DOWN = By.xpath("//select[@id='fcf']");
    private static final By USER_NAV = By.xpath("//div[@id='userNavButton']");
    private static final By USER_NAV_LOGOUT = By.xpath("//a[@title='Logout']");
    private static final By NEW_BUTTON = By.xpath("//input[contains(@name,'new')]");
    private static final By LAST_NAME = By.xpath("//input[contains(@name,'name_lastlea2')]");
    private static final By COMPANY_NAME = By.xpath("//input[contains(@name,'lea3')]");
    //private static final By SAVE_BUTTON = By.xpath("//div[contains(@class,'pbBottomButtons')]//input[1]");
    private static final By SAVE_BUTTON = By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]");
    private static final By NEWLEAD_LASTNAME = By.xpath("//h2[contains(@class,'topName')]");


    private static WebDriver driver;
    private WebDriverWait wait;

    public LeadsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public String verifyLeadsHome(){
       return driver.getTitle();
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

    public void clickNew(String lstName, String cmpnyName){
        //String parentWindow = driver.getWindowHandle();
        waitFor(NEW_BUTTON);
        driver.findElement(NEW_BUTTON).click();
        /*ArrayList<String> windowTabs = new ArrayList(driver.getWindowHandles());
        System.out.println(windowTabs.size());
        if (windowTabs.size() > 1) {
            driver.switchTo().window(windowTabs.get(1));
            WebElement formHandle = driver.findElement(By.xpath("//form[@name = 'editPage']"));
            driver.switchTo().frame(formHandle);*/
            //driver.findElement(By.xpath("//form[@name = 'editPage']")).click();
            driver.findElement(LAST_NAME).sendKeys(lstName);
            driver.findElement(COMPANY_NAME).sendKeys(cmpnyName);

    }

    public String clickSave(){
        driver.findElement(SAVE_BUTTON).click();//click Save
        return driver.findElement(NEWLEAD_LASTNAME).getText();
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
