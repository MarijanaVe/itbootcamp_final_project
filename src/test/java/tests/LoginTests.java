package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTests{

    @Test
    public void login () {
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResult = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResult, expectedResult);
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

    @Test
    public void loginWithValidCredentials () {
        loginPage.login("admin@admin.com","12345" );
        String expectedResult = "Login - My Awesome App";
        String actualResult= loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void userDoesNotExist () {
        Faker faker = new Faker();
        String expectedResultMsg = "User does not exists\n" +
                "CLOSE";
        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";
        String email = faker.internet().emailAddress();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String password = "12345";
        loginPage.login(email,password);
        Assert.assertTrue(loginPage.getErrorMessageBtn(). getText().contains("User does not exists"));
        Assert.assertTrue(loginPage.getErrorMessageBtn().isDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageBtn().getText(), expectedResultMsg);
        Assert.assertEquals(loginPage.getLoginPageUrl(), expectedResultUrl);
    }

    @Test
    public void wrongPassword () {
        String expectedResultMsg = "Wrong password\n" +
                "CLOSE";
        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";
        String email = "admin@admin.com ";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String password = "45678";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage.login(email,password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(loginPage.getErrorMessageBtn(). getText().contains("Wrong password"));
        Assert.assertTrue(loginPage.getErrorMessageBtn().isDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageBtn().getText(), expectedResultMsg);
        Assert.assertEquals(loginPage.getLoginPageUrl(), expectedResultUrl);
    }

    @Test
    public void logout ()  {
        String expectedResultUrl = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResultUrl = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        loginPage.getloginButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage.getLogoutBtn().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(loginPage.getLogoutBtn().isDisplayed());
        String url = "https://vue-demo.daniel-avellaneda.com/login";
        Assert.assertTrue(url.contains("login"));
        loginPage.getLogoutBtn().click();
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(actualResultUrl.contains("login"));
    }
}
