package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BaseTests{

    @Test(priority = 7)
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
}
