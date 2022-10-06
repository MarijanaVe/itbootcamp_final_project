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
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import java.time.Duration;

public class BaseTests {

    private LoginPage loginPage;
    private SignupPage signupPage;
    private AdminCitiesPage adminCitiesPage;
    private HomePage homePage;


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
        homePage =new HomePage(driver, driverWait);
        adminCitiesPage =new AdminCitiesPage(driver,driverWait);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://vue-demo.daniel-avellaneda.com/login");
    }

    @Test (priority = 1)
    public void login () {
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";     //Visits the login page
        String actualResult = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResult, expectedResult);     //Verifikovati da se u url-u stranice javlja ruta /login
    }

    @Test (priority = 2)
    public void checkInputTypes () {
        loginPage.getloginButton().click();     //Checks input types
        loginPage.getEmail().isDisplayed();

        String expectedResultEmail = "email";     //Verifikovati da polje za unos emaila za atribut type ima vrednost email
        String actualResult = loginPage.getEmail().getAttribute("type");
        Assert.assertEquals(actualResult, expectedResultEmail);

        String expectedResultPassword = "password";     //Verifikovati da polje za unos lozinke za atribut type ima vrednost password
        String actualResult2 = loginPage.getPassword().getAttribute("type");
        Assert.assertEquals(actualResult2, expectedResultPassword);

    }

    @Test (priority = 3)
    public void loginWithValidCredentials () {
        loginPage.login("admin@admin.com","12345" );     //Login sa validnim emailom i passwordom
        String expectedResult = "Login - My Awesome App";
        String actualResult= loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test (priority = 4)
    public void userDoesNotExist () throws InterruptedException {
        Faker faker = new Faker();     //Login sa nevalidnim userom i passwordom

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

        Assert.assertTrue(loginPage.getErrorMessageBtn(). getText().contains("Wrong password"));     //Verifikovati da greska sadrzi poruku Wrong password
        Assert.assertTrue(loginPage.getErrorMessageBtn().isDisplayed());

        String actualResultUrl = loginPage.getLoginPageUrl();

        Assert.assertEquals(loginPage.getErrorMessageBtn().getText(), expectedResultMsg);
        Assert.assertEquals(loginPage.getLoginPageUrl(), expectedResultUrl);     //Verifikovati da se u url-u stranice javlja /login ruta
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
        Assert.assertTrue(loginPage.getLogoutBtn().isDisplayed());     //Verifikovati da je dugme logout vidljivo na stranici
        String url = "https://vue-demo.daniel-avellaneda.com/login";
        Assert.assertTrue(url.contains("login"));     //Verifikovati da se u url-u stranice javlja /login ruta
        loginPage.getLogoutBtn().click();
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(actualResultUrl.contains("login"));     //Nakon pokušaja otvaranja /home rute, u url-u stranice javlja /login ruta

    }



    @Test (priority = 7)
    public void visitTheSignupPage () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        String expResult = driver.getCurrentUrl();
        Assert.assertTrue(expResult.contains("signup"));     //Verifikovati da se u url-u stranice javlja /signup ruta
    }

    @Test (priority = 8)
    public void signupChecksInputTypes () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        signupPage.getSignUp().click();
        String expectedResult = "email";     //Polje za unos emaila za atribut type ima vrednost email
        String actualResult = signupPage.getEmailSP().getAttribute("Type");
        Assert.assertEquals(actualResult, expectedResult);
        String expectedResult2 = "password";     //Polje za unos lozinke za atribut type ima vrednost password
        String actualResult2 = signupPage.getPasswordSP().getAttribute("Type");
        Assert.assertEquals(actualResult2, expectedResult2);
        String expectedResult3 = "password";     //Polje za unos lozinke za potvrdu za atribut type ima vrednost password
        String actualResult3 = signupPage.getConfirmPassword().getAttribute("Type");
        Assert.assertEquals(actualResult3, expectedResult3);

    }

    @Test (priority = 9)
    public void signUpUserAlreadyExists () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        signupPage.getSignUp().click();
        signupPage.loginSignup("Test Test", "admin@admin.com","123654", "123654");
        Thread.sleep(1000);

        String expectedResult = "E-mail already exists";     //Verifikovati da greska sadrzi poruku E-mail already exists
        Thread.sleep(1000);

        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists(). getText().contains("E-mail already exists"));
        Thread.sleep(1000);

        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists().isDisplayed());

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));     //Verifikovati da se u url-u stranice javlja /signup ruta

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
        Assert.assertEquals(actResult.getText(), expResult);     //Dijalog za obavestenje sadrzi tekst IMPORTANT: Verify your account

    }

    @Test (priority=11)
    public void adminCitiesPageAndListCities () throws InterruptedException {

        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
        homePage.citiesPage();
        Thread.sleep(2000);

        String expectedResult = "https://vue-demo.daniel-avellaneda.com/admin/cities";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);     //Verifikovati da se u url-u stranice javlja /admin/cities ruta

        boolean actualRes = homePage.getLogoutBtn().isDisplayed();
        Assert.assertTrue(true, String.valueOf(actualRes));     //Verifikovati postojanje logut dugmeta


    }

    @Test (priority =12)
    public void createCity () throws InterruptedException {

        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
        homePage.citiesPage();
        Thread.sleep(2000);
        String addCity = "Ruma";
        Thread.sleep(5000);
        adminCitiesPage.AddNewCity(addCity);


        String expectedResult = "Saved successfully";
        Thread.sleep(5000);
        String actualResult = driver.findElement(By.xpath(("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]"))).getText();
        Thread.sleep(5000);
        Assert.assertTrue(actualResult.contains(expectedResult));     //Poruka sadrzi tekst Saved successfully
    }








    @AfterClass
    public void AfterClass () {
        driver.quit();
    }




}
