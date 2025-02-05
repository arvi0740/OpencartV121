package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageBase.BasePage;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }


    By txtFirstName = By.xpath("//input[@id='input-firstname']");
    By txtLastName = By.xpath("//input[@id='input-lastname']");
    By txtEmail = By.xpath("//input[@id='input-email']");
    By txtTelephone = By.xpath("//input[@id='input-telephone']");
    By txtPassword = By.xpath("//input[@id='input-password']");
    By txtConfirmPassword = By.xpath("//input[@id='input-confirm']");
    By ckdPolicy = By.xpath("//input[@name='agree']");
    By btnContinue = By.xpath("//input[@value='Continue']");
    By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");


    public void setFirstName(String firstName) {
        driver.findElement(txtFirstName).sendKeys(firstName);
    }

    public void setLastName(String lstName) {
        driver.findElement(txtLastName).sendKeys(lstName);
    }

    public void setEmail(String email) {
        driver.findElement(txtEmail).sendKeys(email);
    }

    public void setTelephone(String tel) {
        driver.findElement(txtTelephone).sendKeys(tel);
    }

    public void setPassword(String pwd) {
        driver.findElement(txtPassword).sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd) {
        driver.findElement(txtConfirmPassword).sendKeys(pwd);
    }

    public void setPrivacyPolicy() {
        driver.findElement(ckdPolicy).click();
    }

    public void clickContinue() {
        driver.findElement(btnContinue).click();
    }

    public String getConfirmationMsg() {

        try {
            return driver.findElement(msgConfirmation).getText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
