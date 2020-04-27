import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class UserMenuTest {

    private static HomePage homePage;

    @BeforeMethod
    public void setup(){
        LoginPage loginPage = new LoginPage();
        homePage = loginPage.login();
    }

    @Test
    public void testSelectUserMenu_TC05() {
        homePage.isUserNavButtonVisible();
        homePage.clickUserMenu();
        homePage.verifyMenuItems();
    }

    @Test
    public void testSelectMyProfile_TC06() {
        homePage.clickUserMenuMyProfile();
        homePage.clickContact();
        homePage.getPopup();
        homePage.clickAbout();
        homePage.clickLastName();
        homePage.editLastName();
        homePage.clickSaveAll();
        Assert.assertTrue(homePage.verifyLastNameChange(), "Last Name has not changed.");
        homePage.clickPost();
        String postData = " some data - " + Math.round(Math.random()*1000);
        homePage.enterTextToPost();
        //homePage.clickShare();
       // homePage.verifyPostedText();
    }

    @Test
    public void testSelectMySettings_TC07() {
        homePage.clickUserMenuMySettings();
    }

    @Test
    public void testSelectDevelopersConsole_TC08() {
        homePage.clickUserMenuDevConsole();
    }

    @Test
    public void testSelectLogout_TC09() {
        homePage.clickLogout();
    }

    @AfterMethod
    public void cleanUp(){
        homePage.quit();
    }
}
