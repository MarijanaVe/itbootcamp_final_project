package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignupPage;

import java.time.Duration;

public class BaseTests {

    private LoginPage loginPage;
    private SignupPage signupPage;


    private WebDriver driver;
    private WebDriverWait driverWait;
    private Faker faker;

    @BeforeClass
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tilma\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://vue-demo.daniel-avellaneda.com/ ");
        loginPage =new LoginPage(driver, driverWait);
        signupPage =new SignupPage(driver, driverWait);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://vue-demo.daniel-avellaneda.com/login");
    }

    @Test
    public void login () {
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResult = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResult, expectedResult);
        System.out.println("Current URL is: " + loginPage.getLoginPageUrl());
    }

    @Test
    public void checkInputTypes () {
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
    public void loginWithValidCredentials () {
        loginPage.login("admin@admin.com","12345" );
        String expectedResult = "Login - My Awesome App";
        String actualResult= loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void userDoesNotExist () throws InterruptedException {
        Faker faker = new Faker();

        String expectedResultMsg = "User does not exists\n" +
                "CLOSE";
        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";

        String email = faker.internet().emailAddress();
        //String email1 = faker.name().firstName() + "@admin.com";
        Thread.sleep(1000);
        String password = "12345";
        Thread.sleep(1000);

        loginPage.login(email,password);
        Thread.sleep(1000);

        Assert.assertTrue(loginPage.getErrorMessageBtn(). getText().contains("User does not exists"));
        Assert.assertTrue(loginPage.getErrorMessageBtn().isDisplayed());

        String actualResultUrl = loginPage.getLoginPageUrl();

        Assert.assertEquals(loginPage.getErrorMessageBtn().getText(), expectedResultMsg);
        Assert.assertEquals(loginPage.getLoginPageUrl(), expectedResultUrl);
    }

    @Test
    public void wrongPassword () throws InterruptedException {

        String expectedResultMsg = "Wrong password\n" +
                "CLOSE";
        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";

        String email = "admin@admin.com ";

        Thread.sleep(1000);
        String password = "45678";
        Thread.sleep(1000);

        loginPage.login(email,password);
        Thread.sleep(1000);

        Assert.assertTrue(loginPage.getErrorMessageBtn(). getText().contains("Wrong password"));
        Assert.assertTrue(loginPage.getErrorMessageBtn().isDisplayed());

        String actualResultUrl = loginPage.getLoginPageUrl();

        Assert.assertEquals(loginPage.getErrorMessageBtn().getText(), expectedResultMsg);
        Assert.assertEquals(loginPage.getLoginPageUrl(), expectedResultUrl);
    }

    @Test
    public void logout () throws InterruptedException {

        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResultUrl = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        loginPage.getloginButton().click();
        Thread.sleep(1000);
        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(1000);
        loginPage.getLogoutBtn().isDisplayed();
        Thread.sleep(1000);
        Assert.assertTrue(loginPage.getLogoutBtn().isDisplayed());
        String url = "https://vue-demo.daniel-avellaneda.com/login";
        Assert.assertTrue(url.contains("login"));
        loginPage.getLogoutBtn().click();
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(actualResultUrl.contains("login"));

    }



    @Test
    public void visitTheSignupPage () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        String expResult = driver.getCurrentUrl();
        Assert.assertTrue(expResult.contains("signup"));
    }

    @Test
    public void signupChecksInputTypes () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        signupPage.getSignUp().click();
        String expectedResult = "email";
        String actualResult = signupPage.getEmailSP().getAttribute("Type");
        Assert.assertEquals(actualResult, expectedResult);
        String expectedResult2 = "password";
        String actualResult2 = signupPage.getPasswordSP().getAttribute("Type");
        Assert.assertEquals(actualResult2, expectedResult2);
        String expectedResult3 = "password";
        String actualResult3 = signupPage.getConfirmPassword().getAttribute("Type");
        Assert.assertEquals(actualResult3, expectedResult3);

    }

    @Test
    public void signUpUserAlreadyExists () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        signupPage.getSignUp().click();
        signupPage.loginSignup("Test Test", "admin@admin.com","123654", "123654");
        Thread.sleep(1000);

        String expectedResult = "E-mail already exists";
        Thread.sleep(1000);

        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists(). getText().contains("E-mail already exists"));
        Thread.sleep(1000);

        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists().isDisplayed());

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));

    }

    @Test
    public void signupITBootcampUser () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");

        signupPage.getSignUp().click();
        signupPage.loginSignup("mx mj", "mx.mj@itbootcamp.rs","123456", "123456");
        Thread.sleep(1000);

        //driver.switchTo().alert().getText();


        //Assert.assertTrue(driver.getPageSource().matches("IMPORTANT: Verify your account"));
        //Thread.sleep(1000);




    }








    @AfterClass
    public void AfterClass () {
        driver.quit();
    }




}
