import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
