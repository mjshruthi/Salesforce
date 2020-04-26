import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AccountTest {
    private static AccountsPage accountsPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        accountsPage = homePage.clickAccountsTab();
        threadSleep(1000);
    }

    @Test
    public void testCreateNewAccount_TC10() {
        AccountEditPage accountEditPage = accountsPage.createNewAccount();
        String accountName = "mjshruthi" + Math.round(Math.random() * 100);
        accountEditPage.setAccountName(accountName);
        accountEditPage.setType();
        accountEditPage.setIndustry();
        accountEditPage.clickSave();
        assertTrue(accountEditPage.isAccountCreated(accountName));
    }

    @Test
    public void testCreateNewVeiw_TC11() {
        ViewEditPage viewEditPage = accountsPage.createNewView();
        String viewName = "mjshruthi-view-" + Math.round(Math.random() * 100);
        viewEditPage.setViewName(viewName);
        viewEditPage.setViewUniqueName("");
        viewEditPage.clickSaveView();
        assertTrue(viewEditPage.isViewCreated(viewName));
    }

    @Test
    public void testEditView_TC12() {
        String view = "mjshruthi-view-53";
        ViewEditPage viewEditPage = accountsPage.editView(view);
        viewEditPage.setFilterOnAccountName();
        viewEditPage.setFilterOperatorContains();
        viewEditPage.setFilterValue("mjshruthi");
        viewEditPage.clickSaveView();
    }

    @Test
    public void testMergeAccounts_TC13() {
        /* test might fail if there are less than two accounts.
         Create more if necessary by running testCreateNewAccount_TC10 */
        MergeAccountPage mergeAccountPage = accountsPage.clickMergeAccounts();
        mergeAccountPage.setSearchString("mjshruthi");
        mergeAccountPage.clickFindAccounts();
        mergeAccountPage.selectFirstTwoAccountsToMerge();
        mergeAccountPage.clickNext();
        mergeAccountPage.mergeAccounts();
    }

    @Test
    public void testCreateAccountReport_TC14() {
        String reportName = "mjshruthi-report-" + Math.round(Math.random() * 100);
        AccountReportPage accountReportPage = accountsPage.clickAccountReport();
        accountReportPage.selectDateField();
        accountReportPage.selectCurrentDate();
        accountReportPage.clickReportSave(reportName);
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
        accountsPage.quit();
    }

}
