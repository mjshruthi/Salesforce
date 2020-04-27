import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpportunityTest {
    private static OpportunitiesPage opportunitiesPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        opportunitiesPage = homePage.clickOpportunitiesTab();
        threadSleep(1000);
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // ignore.
        }
    }

    @AfterMethod
    public void cleanUp(){
        opportunitiesPage.quit();
    }

    @Test
    public void testOpportunityDropdown_TC15() {
        opportunitiesPage.verifyDropDownMenuItems();
    }

    @Test
    public void testNewOpportunity_TC16() {
       OpportunityEditPage opportunityEditPage = opportunitiesPage.clickNewOpportunity();
        String oppName = "mjshruthi-opp-" + Math.round(Math.random() * 1000);
        opportunityEditPage.setOppNameField(oppName);
        opportunityEditPage.setAccountNameField("mjshruthi");
        opportunityEditPage.setCloseDateToday();
        opportunityEditPage.setStage();
        opportunityEditPage.setProbablity(90);
        opportunityEditPage.saveOpportunity();
    }

    @Test
    public void testOpportunityPipelineReport_TC17() {
        opportunitiesPage.runOpportunityReport();
    }

    @Test
    public void testStuckOpportunityReport_TC18() {
        opportunitiesPage.runOpportunityStuckReport();
    }

    @Test
    public void testQuarterlySummaryReport_TC19() {
        opportunitiesPage.runQuarterlySummaryReport();
    }
}
