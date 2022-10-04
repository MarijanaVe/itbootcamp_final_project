package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class BaseTests {

    private LoginPage loginPage;


    private WebDriver driver;
    private WebDriverWait driverWait;
    private Faker faker;

    @BeforeClass
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tilma\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://vue-demo.daniel-avellaneda.com/ ");
        loginPage =new LoginPage(driver, driverWait);

    }

    @Test
    public void login () {

        driver.get("https://vue-demo.daniel-avellaneda.com/login");
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResult = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResult, expectedResult);
        System.out.println("Current URL is: " + loginPage.getLoginPageUrl());
    }

    @Test
    public void checkInputTypes () {
        driver.get("https://vue-demo.daniel-avellaneda.com/login");
        loginPage.getloginButton().click();
        loginPage.getEmail().isDisplayed();

        String expectedResultEmail = "email";
        String actualResult = loginPage.getEmail().getAttribute("type");
        Assert.assertEquals(actualResult, expectedResultEmail);

        String expectedResultPassword = "password";
        String actualResult2 = loginPage.getPassword().getAttribute("type");
        Assert.assertEquals(actualResult2, expectedResultPassword);

    }

    @Test (dependsOnMethods = "login")
    public void LoginWithValidCredentials () {
        loginPage.login("admin@admin.com","12345" );
        String expectedResult = "Login - My Awesome App";
        String actualResult= loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test (dependsOnMethods = "login")
    public void displaysErrors (String errorMessageButton) {
        loginPage.login("admin1@admin.com","12345" );
        String expectedResult = "User does not exists";
        String actualResult= loginPage.getErrorMessageBtn().getText();
        Assert.assertEquals(actualResult, expectedResult);
    }


    @AfterClass
    public void AfterClass () {
        driver.quit();
    }




}
