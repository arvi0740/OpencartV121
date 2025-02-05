package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test (groups = {"Sanity" ,"Master"})
    public void verify_login() {

        logger.info("***** starting TC002_LoginTest *****");

        try {
            //Home Page
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();

            logger.info("moving to login page");
            homePage.clickLogin();

            //Login Page
            LoginPage loginPage = new LoginPage(driver);
            logger.info("setting email from config.properties");
            loginPage.setEmail(properties.getProperty("email"));
            logger.info("setting password from config.properties");
            loginPage.setPassword(properties.getProperty("password"));
            logger.info("clicking Login button");
            loginPage.clickLogin();

            //My account Page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();
            logger.info("verifying if my Account page ios loaded! ");
            Assert.assertTrue(targetPage);

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("***** finished TC002_LoginTest *****");

    }
}
