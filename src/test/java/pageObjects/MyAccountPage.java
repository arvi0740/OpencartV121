package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageBase.BasePage;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    By msgHeading = By.xpath("//h2[normalize-space()='My Account']");

    By btnLogout = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");

    public boolean isMyAccountPageExists() {

        try {
            return driver.findElement(msgHeading).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        driver.findElement(btnLogout).click();

    }
}
