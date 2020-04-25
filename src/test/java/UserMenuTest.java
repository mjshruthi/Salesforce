import org.testng.Assert;
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
        Assert.assertTrue(homePage.isUserNavButtonVisible());
        homePage.clickUserMenuMySettings();
    }

    @Test
    public void testSelectMyProfile_TC06() {

    }

    @Test
    public void testSelectMySettings_TC07() {

    }

    @Test
    public void testSelectDevelopersConsole_TC08() {

    }

    @Test
    public void testSelectLogout_TC09() {

    }
}
