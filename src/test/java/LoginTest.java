import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    @Test
    public void testLoginErrorMessage() throws InterruptedException{
        LoginPage loginPage = new LoginPage();
        loginPage.setUserName("mjshruthi");
        loginPage.setPassword("");
        loginPage.clickLoginButton();
        assertTrue(loginPage.getLoginErrorMessage().equalsIgnoreCase("Please enter your password."),
                "Error on screen did not match expected message.");
    }

    @Test
    public void testLoginToSalesforce() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @Test
    public void testRememberMe() {

    }

    @Test
    public void testForgotPassword() {

    }

    @Test
    public void testForgotPasswordErrorMessage() {

    }
}
