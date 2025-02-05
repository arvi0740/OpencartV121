package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

import java.io.IOException;
import java.util.Arrays;


//Data valid -- login success --test pass -- logout
//Data valid -- login Failed --test Fail

//Data Invalid -- login success --test Fail -- logout
//Data Invalid -- login Failed --test pass


public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class , groups = "Datadriven") //getting data provider from diff class
    public void verify_loginDDT(String email, String pass, String expectedResult) throws IOException {

        logger.info("***** starting TC003_LoginDDTTest *****");


        /**  //this is for reading the data which is getting passed from Data provider method
         DataProviders dataProviders = new DataProviders();
         dataProviders.readingLoginData();
         **/


        try {


            //Home Page
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            //Login Page
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(pass);
            loginPage.clickLogin();

            //My account Page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();

            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (targetPage) {

                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }


            if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        } finally {
            logger.info("***** Finished TC003_LoginDDTTest *****");

        }


    }
}
