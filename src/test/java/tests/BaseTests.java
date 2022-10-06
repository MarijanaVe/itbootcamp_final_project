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
import pages.AdminCitiesPage;
import pages.LoginPage;
import pages.SignupPage;

import java.time.Duration;

public class BaseTests {

    private LoginPage loginPage;
    private SignupPage signupPage;
    private AdminCitiesPage adminCitiesPage;


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

    @Test (priority = 1)
    public void login () {
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResult = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResult, expectedResult);
        System.out.println("Current URL is: " + loginPage.getLoginPageUrl());
    }

    @Test (priority = 2)
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

    @Test (priority = 3)
    public void loginWithValidCredentials () {
        loginPage.login("admin@admin.com","12345" );
        String expectedResult = "Login - My Awesome App";
        String actualResult= loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test (priority = 4)
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

    @Test (priority = 5)
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

    @Test (priority = 6)
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



    @Test (priority = 7)
    public void visitTheSignupPage () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        String expResult = driver.getCurrentUrl();
        Assert.assertTrue(expResult.contains("signup"));
    }

    @Test (priority = 8)
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

    @Test (priority = 9)
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

    @Test (priority = 10)
    public void signupFakerUser () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");


        Faker faker =new Faker();
        String name =faker.internet().domainName();
        String email =faker.internet().emailAddress();
        signupPage.loginSignup(name,email, "123456", "123456" );
        Thread.sleep(2000);

        WebElement actResult=driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[1]"));
        Thread.sleep(2000);
        String expResult = "IMPORTANT: Verify your account";
        Assert.assertEquals(actResult.getText(), expResult);

    }

    @Test (priority = 11)
    public void adminCitiesPageAndListCities () throws InterruptedException {
        /*driver.get("https://vue-demo.daniel-avellaneda.com/login");
        adminCitiesPage.getSignUp().click();

        adminCitiesPage.loginACP("admin@admin.com", "12345");
        Thread.sleep(1000);

         */




    }








    @AfterClass
    public void AfterClass () {
        driver.quit();
    }




}
