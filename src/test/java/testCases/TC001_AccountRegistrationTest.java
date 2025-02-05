package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test (groups = {"Regression" , "Master"})
    public void verify_account_registration() {

        logger.info("**** Starting TC001_AccountRegistrationTest **** ");
        try {


            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("clicked on My account link");

            homePage.clickRegister();
            logger.info("clicked on Resister link");


            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details...");

            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String pass = randomAlphaNumeric();
            regpage.setPassword(pass);
            regpage.setConfirmPassword(pass);
            regpage.setPrivacyPolicy();
            regpage.clickContinue();


            logger.info("validating expected message...");

            String confirmationMsg = regpage.getConfirmationMsg();
            if (confirmationMsg.equals("Your Account Has Been Created!")) {

                Assert.assertTrue(true);
            } else {
                logger.error("test failed");
                logger.debug("Debug logs");
                Assert.assertTrue(false);
            }

//            Assert.assertEquals(confirmationMsg, "Your Account Has Been Created^^!");
// hard assertion, this should be at last>> if this fails, then code below this will not be considered at run time


        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("**** Finished TC001_AccountRegistrationTest **** ");

    }


}
