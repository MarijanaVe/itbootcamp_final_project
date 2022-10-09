package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public class BaseTests {

    protected LoginPage loginPage;
    protected SignupPage signupPage;
    protected AdminCitiesPage adminCitiesPage;
    protected HomePage homePage;
    protected LocalePage localePage;
    protected ProfilePage profilePage;
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    private Faker faker;

    @BeforeClass
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tilma\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://vue-demo.daniel-avellaneda.com/ ");
        loginPage =new LoginPage(driver, driverWait);
        signupPage =new SignupPage(driver, driverWait);
        homePage =new HomePage(driver, driverWait);
        adminCitiesPage =new AdminCitiesPage(driver,driverWait);
        localePage =new LocalePage(driver, driverWait);
        profilePage =new ProfilePage(driver, driverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://vue-demo.daniel-avellaneda.com/login");
    }


    @AfterClass
    public void AfterClass () {
        driver.quit();
    }




}
