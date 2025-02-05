package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {

        //loading config.properties

        FileReader propertiesFile = new FileReader("./src//test//resources//config.properties");

//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\ArvindKumar\\IdeaProjects\\OpencartV121\\src\\test\\resources\\config.properties");

        properties = new Properties();
        properties.load(propertiesFile);

        logger = LogManager.getLogger(this.getClass());

        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {

            String hubUrl = "http://localhost:4444/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os

            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);

            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);

            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);

            } else {
                System.out.println("No matching");
                return;
            }

            //browser

            switch (browser.toLowerCase()) {

                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;

                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;

                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "internet explorer":
                    capabilities.setBrowserName("internet explorer");
                    break;

                default:
                    System.out.println("Invalid browser name");
                    return;
            }

            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);


        }


        if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {


            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    System.out.println("Invalid browser name");
                    return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appUrl1")); // reading YRL from a properties file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        driver.quit();
    }


    public String randomString() {

        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {

        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {

        String randomNumber = RandomStringUtils.randomNumeric(3);
        String randomString = RandomStringUtils.randomAlphabetic(3);
        return randomString + "@" + randomNumber;
    }

    public String captureScreen(String tName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir" + "\\screenshots\\" + tName + "_" + timeStamp);
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;


    }


}
