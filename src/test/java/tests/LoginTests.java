package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTests{

    @Test(priority = 1)
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String password = "12345";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        loginPage.login(email,password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String password = "45678";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        loginPage.login(email,password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage.getLogoutBtn().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(loginPage.getLogoutBtn().isDisplayed());     //Verifikovati da je dugme logout vidljivo na stranici
        String url = "https://vue-demo.daniel-avellaneda.com/login";
        Assert.assertTrue(url.contains("login"));     //Verifikovati da se u url-u stranice javlja /login ruta
        loginPage.getLogoutBtn().click();
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(actualResultUrl.contains("login"));     //Nakon pokušaja otvaranja /home rute, u url-u stranice javlja /login ruta

    }
}
