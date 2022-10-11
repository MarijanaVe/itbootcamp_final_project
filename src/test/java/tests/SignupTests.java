package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignupTests extends BaseTests{

    @Test (priority = 1)
    public void visitTheSignupPage () {
        signupPage.getSignUp().click();
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        String expResult = driver.getCurrentUrl();
        Assert.assertTrue(expResult.contains("signup"));
    }

    @Test (priority = 2)
    public void signupChecksInputTypes ()  {
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

    @Test (priority = 3)
    public void signUpUserAlreadyExists ()  {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        signupPage.getSignUp().click();
        signupPage.loginSignup("Test Test", "admin@admin.com","123654", "123654");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists(). getText().contains("E-mail already exists"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(signupPage.getErrorEmailAlreadyExists().isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test (priority = 4)
    public void signupFakerUser () throws InterruptedException {
        driver.get("https://vue-demo.daniel-avellaneda.com/signup");
        Faker faker =new Faker();
        String name =faker.internet().domainName();
        String email =faker.internet().emailAddress();
        signupPage.loginSignup(name,email, "123456", "123456" );
        Thread.sleep(2000);
        WebElement actResult=driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[1]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String expResult = "IMPORTANT: Verify your account";
        Assert.assertEquals(actResult.getText(), expResult);


    }
}
