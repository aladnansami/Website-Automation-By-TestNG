package TestRunner;

import Base.Setup;
import Pages.LoginPage;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    Utils utils;
    @Test(priority = 1)
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        utils =new Utils();
        utils.getUserCreds(utils.getUserCount());
        driver.get("http://automationpractice.com/");
        boolean isLogoutFound= loginPage.doLoginWithValidCreds(utils.getEmail(),utils.getPassword());
        Assert.assertEquals(isLogoutFound,true);
        loginPage.linkLogout.click();
    }
    @Test(priority = 2)
    public void doLoginWithInvalidPass() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage=new LoginPage(driver);
        utils=new Utils();
        utils.getUserCreds(1);
        String validationMessage= loginPage.doLoginWithInvalidPass(utils.getEmail(),utils.getPassword());
        Assert.assertTrue(validationMessage.contains("failed"));
    }
}
