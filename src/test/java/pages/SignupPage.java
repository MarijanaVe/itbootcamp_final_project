package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BasePage {
    private By name = By.id("name");
    private By emailSP = By.id("email");
    private By passwordSP = By.id("password");
    private By confirmPassword = By.id("confirmPassword");
    private By signMeUpBtn = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button");

    private By signUp= By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]");

    private By errorEmailAlreadyExists = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]/ul/li");

    private By verifyAccMessage = By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[1]");

    public SignupPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getName() {
        return getDriver().findElement(name);
    }

    public WebElement getEmailSP() {
        return getDriver().findElement(emailSP);
    }

    public WebElement getPasswordSP() {
        return getDriver().findElement(passwordSP);
    }

    public WebElement getConfirmPassword() {
        return getDriver().findElement(confirmPassword);
    }

    public WebElement getSignMeUpBtn() {
        return getDriver().findElement(signMeUpBtn);
    }

    public WebElement getSignUp() {
        return getDriver().findElement(signUp);
    }

    public WebElement getErrorEmailAlreadyExists() {
        return getDriver().findElement(errorEmailAlreadyExists);
    }

    public WebElement verifyAccMessage() {
        return getDriver().findElement(verifyAccMessage);
    }


    public void loginSignup(String name, String emailSP,String passwordSP, String confirmPassword) {
        getName().sendKeys(name);
        getEmailSP().sendKeys(emailSP);
        getPasswordSP().sendKeys(passwordSP);
        getConfirmPassword().sendKeys(confirmPassword);
        getSignMeUpBtn().click();
    }


}
