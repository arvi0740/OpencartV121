package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageBase.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    By txtEmail = By.xpath("//input[@id='input-email']");
    By txtPassword = By.xpath("//input[@id='input-password']");
    By btnLogin = By.xpath("//input[@value='Login']");


    public void setEmail(String email) {

        driver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String pass) {

        driver.findElement(txtPassword).sendKeys(pass);
    }

    public void clickLogin() {

        driver.findElement(btnLogin).click();
    }




}
