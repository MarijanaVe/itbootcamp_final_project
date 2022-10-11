package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private By admin = By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]");
    private By cities= By.xpath("//*[@id=\"app\"]/div[3]/div[1]/a[1]");
    private By logoutBtn =By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]");

    public HomePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getAdmin() {
        return getDriver().findElement(admin);
    }

    public WebElement getCities() {
        return getDriver().findElement(cities);
    }

    public WebElement getLogoutBtn() {
        return getDriver().findElement(logoutBtn);
    }

    public void citiesPage () throws InterruptedException {
        getAdmin().click();
        Thread.sleep(6000);
        getCities().click();
    }

}
