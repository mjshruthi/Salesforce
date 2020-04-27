import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactTest {
    private static ContactsPage contactsPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        contactsPage = homePage.clickContactsTab();
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
    public void cleanUp() {
        contactsPage.quit();
    }

    @Test
    public void testCreateNewContacts_TC25() {
        ContactsEditPage contactsEditPage = contactsPage.clickNewContacts();
        String firstName = "mjshruthi";
        String lastName = "contact_" + Math.round(Math.random() * 1000);
        contactsEditPage.setFirstName(firstName);
        contactsEditPage.setLastName(lastName);
        contactsEditPage.setAccount("mjshruthi");
        contactsEditPage.clickSave();
        String savedContactName = contactsEditPage.getContactDisplayName();
        Assert.assertTrue(savedContactName.contains(firstName));
        Assert.assertTrue(savedContactName.contains(lastName));
    }

    @Test
    public void testCreateNewView_TC26() {
        String viewName = "view_" + Math.round(Math.random() * 1000);
        contactsPage.clickCreateNewVeiw();
        contactsPage.setNewViewName(viewName);
        contactsPage.setNewViewUniqueName("");
        contactsPage.saveNewView();
    }

    @Test
    public void testCheckRecentlyCreatedContacts_TC27() {
        contactsPage.selectRecentlyCreatedContacts();
    }

    @Test
    public void testMyContactsView_TC28() {
        contactsPage.selectMyContactsView();
    }

    @Test
    public void testViewContacts_TC29() {
        contactsPage.selectMyContactsView();
        contactsPage.selectFirstMyContact();
    }

    @Test
    public void testCreateContactsError_TC30() {
        ContactsEditPage contactsEditPage = contactsPage.clickNewContacts();
        String firstName = "mjshruthi";
        String lastName = "contact_" + Math.round(Math.random() * 1000);
        contactsEditPage.setFirstName(firstName);
        contactsEditPage.setLastName(lastName);
        // Do not set accounts to get error
        // contactsEditPage.setAccount("mjshruthi");
        contactsEditPage.clickSave();
        String errorMsg = contactsEditPage.getContactSaveError();
        Assert.assertTrue(errorMsg.contains("Error: Invalid Data."));
    }

    @Test
    public void testCreateNewViewCancel_TC31() {
        contactsPage.clickCreateNewVeiw();
        contactsPage.cancelNewView();
    }

    @Test
    public void testSaveAndNewContacts_TC32() {
        ContactsEditPage contactsEditPage = contactsPage.clickNewContacts();
        String firstName = "mjshruthi";
        String lastName = "contact_" + Math.round(Math.random() * 1000);
        contactsEditPage.setFirstName(firstName);
        contactsEditPage.setLastName(lastName);
        contactsEditPage.setAccount("mjshruthi");
        contactsEditPage.clickSaveAndNew();
    }
}
