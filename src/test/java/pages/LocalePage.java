package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalePage extends BasePage {

    private By languageButton = By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button");

    private By languageES = By.cssSelector(".btnES");
    ////*[@id="app"]/div[1]/div/header/div/div[3]/button
    ////*[@id="app"]/div[3]/div
    //#list-item-159

    //*[@id="list-item-75"]

    private By header = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1");

    private By languageEN = By.cssSelector(".btnEN");;
    ////*[@id="app"]/div[3]/div

    ////*[@id="list-item-73"]

    private By languageFR = By.cssSelector(".btnFR");;


    public LocalePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getLanguageButton () {
        return getDriver().findElement(languageButton);
    }


    public WebElement getLanguageES () {
        return getDriver().findElement(languageES);
    }


    public WebElement getHeader () {
        return getDriver().findElement(header);
    }

    public WebElement getLanguageEN () {
        return getDriver().findElement(languageEN);
    }


    public WebElement getLanguageFR () {
        return getDriver().findElement(languageFR);
    }




}
