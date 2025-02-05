package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageBase.BasePage;

public class HomePage extends BasePage {

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver); //setting up for parent class
    }


    //Locators
    By lnkMyAccount = By.xpath("//span[normalize-space()='My Account']");
    By lnkRegister = By.xpath("//a[normalize-space()='Register']");
    By lnkLogin = By.xpath("//a[normalize-space()='Login']");


    //actions

    public void clickMyAccount() {
        driver.findElement(lnkMyAccount).click();
    }

    public void clickRegister() {
        driver.findElement(lnkRegister).click();
    }

    public void clickLogin() {
        driver.findElement(lnkLogin).click();
    }


}
