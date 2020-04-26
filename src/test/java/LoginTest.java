import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest {
    @Test
    public void testLoginErrorMessage_TC01() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.setUserName("mjshruthi");
        loginPage.setPassword("");
        loginPage.clickLoginButton();
        assertTrue(loginPage.getLoginErrorMessage().equalsIgnoreCase("Please enter your password."),
                "Error on screen did not match expected message.");
        loginPage.quit();
    }

    @Test
    public void testLoginToSalesforce_TC02() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        // code for wait.
        assertTrue(homePage.isUserNavButtonVisible());
        loginPage.quit();
    }

    @Test
    public void testRememberMe_TC03() {
        LoginPage loginPage = new LoginPage();
        loginPage.setRememberMe();
        HomePage homePage = loginPage.login();
        homePage.isUserNavButtonVisible();
        loginPage = homePage.clickLogout();
        loginPage.isUserNamePopulated();
        loginPage.isRememberMeSelected();
        loginPage.quit();
    }

    @Test
    public void testForgotPassword_TC04A() {
        LoginPage loginPage = new LoginPage();
        loginPage.isForgotPwdVisible();
        ForgotPwdPage forgotPwdPage = loginPage.clickForgotPwdButton();
        forgotPwdPage.enterUserName();
        forgotPwdPage.clickContinue();
        forgotPwdPage.isCheckEmailVisible();
        forgotPwdPage.quit();
    }

    @Test
    public void testForgotPasswordErrorMessage_TC04B() {
        LoginPage loginPage = new LoginPage();
        loginPage.setUserName("123");
        loginPage.setPassword("22131");
        loginPage.clickLoginButton();
        assertTrue(loginPage.getLoginErrorMessage().equalsIgnoreCase("Please check your username and password. If you still can't log in, contact your Salesforce administrator."),
                "Error on screen did not match expected message.");
        loginPage.quit();

    }
}
