import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LeadsTest {
    private static LeadsPage leadsPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        leadsPage = homePage.clickLeadsTab();
    }

    @Test
    public void testLeadsTab_TC20() {
        leadsPage.verifyOnLeadsHome();
    }

    @Test
    public void testLeadsSelectView_TC21() {
        leadsPage.clickDropDown();
    }

    @Test
    public void testLeadsDefaultView_TC22() {
        leadsPage.verifyOnLeadsHome();
        leadsPage.clickDropDownANDselectTodaysLead();
        assertTrue(leadsPage.verifyDropDownItemSelected().equals("Today's Leads"));
        leadsPage.clickDropDownANDselectMyUnreadLeads();
        assertTrue(leadsPage.verifyDropDownItemSelected().equals("My Unread Leads"));
        //leadsPage.selectMyUnreadLeads();
        LoginPage loginPage = leadsPage.clickLogout();
        HomePage homePage = loginPage.login();
        //incomplete testcase - Crashes here due to popup in login
        leadsPage = homePage.clickLeadsTab();
    }

    @Test
    public void testLeadsTodaysLeads_TC23() {

    }

    @Test
    public void testLeadsNewButton_TC24() {

    }


    @AfterMethod
    public void cleanUp() {
        leadsPage.quit();
    }
}
