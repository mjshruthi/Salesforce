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
        assertTrue(leadsPage.verifyLeadsHome().equals("Leads: Home ~ Salesforce - Essentials Edition"));
    }

    @Test
    public void testLeadsSelectView_TC21() {
        leadsPage.clickDropDown();
    }

    @Test
    public void testLeadsDefaultView_TC22() {
        assertTrue(leadsPage.verifyLeadsHome().equals("Leads: Home ~ Salesforce - Essentials Edition"));
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
        assertTrue(leadsPage.verifyLeadsHome().equals("Leads: Home ~ Salesforce - Essentials Edition"));
        leadsPage.clickDropDownANDselectTodaysLead();//do click drop down and select in one flow to reduce errors.
        assertTrue(leadsPage.verifyDropDownItemSelected().equals("Today's Leads"));
    }

    @Test
    public void testLeadsNewButton_TC24() {
        assertTrue(leadsPage.verifyLeadsHome().equals("Leads: Home ~ Salesforce - Essentials Edition"));
        //assertTrue(leadsPage.verifyNewLeadPage().equals("Lead Edit: New Lead ~ Salesforce - Essentials Edition"));
        String lName = "TestLastname"+ Math.round(Math.random() * 100);
        String cName = "CompanyXY" + Math.round(Math.random() * 100);
        leadsPage.clickNew(lName, cName);
        String lastTestName = leadsPage.clickSave();
        assertTrue(lastTestName.equals(lName), "Lastname displayed is different.");
    }


    @AfterMethod
    public void cleanUp() {
        leadsPage.quit();
    }
}
