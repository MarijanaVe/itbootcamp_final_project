package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalePage extends BasePage {

    private By languageButton = By.xpath(("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button"));
    private By langES  = By.xpath("//*[@id=\"list-item-75\"]");
    private By langEN = By.xpath("//*[@id='list-item-73']/div");
    private By langFR = By.xpath("//*[@id='list-item-77']/div");
    private By headerTitle = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1");




    public LocalePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getLanguageButton() {
        return getDriver().findElement(languageButton);
    }

    public WebElement getLangES() {
        return getDriver().findElement(langES);
    }


    public WebElement getLangEN() {
        return getDriver().findElement(langEN);
    }

    public WebElement getLangFR() {
        return getDriver().findElement(langFR);
    }
    public WebElement getHeaderTitle() {
        return getDriver().findElement(headerTitle);
    }

}
