package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage{

    private By myProfileBtn = By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[3]");
    private By nameField = By.id("name");
    private By phone = By.id("phone");
    private By city = By.id("city");
    private By country = By.id("country");
    private By twitter = By.id("urlTwitter");
    private By gitHub = By.id("urlGitHub");
    private By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div/div[8]/button");



    public ProfilePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getMyProfileBtn() {
        return getDriver().findElement(myProfileBtn);
    }
    public WebElement getNameField() {
            return getDriver().findElement(nameField);
    }

    public WebElement getPhone() {
        return getDriver().findElement(phone);
    }

    public WebElement getCity() {
        return getDriver().findElement(city);
    }

    public WebElement getCountry() {
        return getDriver().findElement(country);
    }

    public WebElement getTwitter() {
        return getDriver().findElement(twitter);
    }

    public WebElement getGitHub() {
        return getDriver().findElement(gitHub);
    }

    public WebElement getSaveBtn() {
        return getDriver().findElement(saveBtn);
    }

    public void visitProfilePage(){
        getMyProfileBtn().click();
    }

    public void editProfileData (String name, String phone, String city, String country, String twitter, String gitHub){
        getNameField().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getNameField().sendKeys(name);
        getPhone().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getPhone().sendKeys(phone);
        getCity().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getCity().sendKeys(city);
        getCountry().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getCountry().sendKeys(country);
        getTwitter().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getTwitter().sendKeys(twitter);
        getGitHub().sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        getGitHub().sendKeys(gitHub);
        getSaveBtn().click();

        }








    }
