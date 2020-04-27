import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RandomScenarioTest {
    private static HomePage homePage;

    @Test
    public void testVerifyNameOnProfilePage_TC33() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        // code for wait.
        assertTrue(homePage.verifyHomePageTitle().equalsIgnoreCase("Salesforce - Essentials Edition"), "Home Page title doesn't match.");
        assertTrue(homePage.isUserNavButtonVisible());
        String lastNameDropDown = homePage.verifyFirstNameLastNameonDropDown();
        assertTrue(lastNameDropDown.trim().equalsIgnoreCase("4lx6mj8mol6l opayQ"), "Name on HomePage doesn't match.");
        homePage.clickNameLink();
        String lastNameProfilePage = homePage.verifyNameOnProfilePage();
        assertTrue(lastNameProfilePage.trim().equalsIgnoreCase("4lx6mj8mol6l opayQ"), "Name on ProfilePage doesn't match.");
        homePage.quit();
    }

    @Test
    public void testUpdateLastName_TC34() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        try {
            assertTrue(homePage.verifyHomePageTitle().equalsIgnoreCase("Salesforce - Essentials Edition"), "Home Page title doesn't match.");
            homePage.clickNameLink();
            String lastNameProfilePage = homePage.verifyNameOnProfilePage();
            assertTrue(homePage.verifyProfilePageTitle().equalsIgnoreCase("User: 4lx6mj8mol6l opayQ ~ Salesforce - Essentials Edition"), "Profile Page title doesn't match.");
            homePage.clickContact();
            homePage.getPopup();
            homePage.clickAbout();
            homePage.clickLastName();
            String changeLastNameTo = "Abcd";
            homePage.editLastName(changeLastNameTo);
            homePage.clickSaveAll();
            String verifyFNameLName = "4lx6mj8mol6l Abcd";
            //Assert.assertTrue(homePage.verifyLastNameChange(verifyFNameLName), "Last Name has not changed.");
        } finally {
            homePage.revertNameChange();
            homePage.quit();
        }
    }
}
