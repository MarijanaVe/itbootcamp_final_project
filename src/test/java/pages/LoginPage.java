package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    private By email = By.id("email");
    private By password = By.id("password");
    String loginPageUrl = "https://vue-demo.daniel-avellaneda.com/login";
    private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button/span");
    private By errorMessageBtn = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]");
    private By logoutBtn = By.xpath("/html/body/div/div/div/header/div/div[3]/button[2]");

    public LoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getEmail() {
        return getDriver().findElement(email);
    }

    public WebElement getPassword() {
        return getDriver().findElement(password);
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public WebElement getloginButton () {
        return getDriver().findElement(loginButton);
    }

    public WebElement getErrorMessageBtn () {
        return getDriver().findElement(errorMessageBtn);
    }

    public WebElement getLogoutBtn () {
        return getDriver().findElement(logoutBtn);
    }

    public void login(String email, String password) {
        getEmail().sendKeys(email);
        getPassword().sendKeys(password);
        getloginButton().click();
    }
}
