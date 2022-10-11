package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminCitiesPage extends BasePage{

    private By edit =By.id ("edit");
    private By newItem =By.xpath("/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[1]/div[3]/form/div[1]/button");
    private By inputField = By.id("name");
    private By saveNewCity = By.xpath("//*[@id=\"app\"]/div[5]/div/div/div[3]/button[2]");
    private By searchField = By.xpath("//*[@id=\"search\"]");
    private By firstName = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[2]");
    private By deleteButton = By.xpath("//*[@id=\"delete\"]/span");
    private By finallyDeleteButton = By.xpath("//*[@id=\"app\"]/div[6]/div/div/div[2]/button[2]");
    private By saveMsg = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]");

    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getEdit() {
        return getDriver().findElement(edit);
    }

    public WebElement getNewItem() {
        return getDriver().findElement(newItem);
    }

    public WebElement getInputField() {
        return getDriver().findElement(inputField);
    }

    public WebElement getSaveNewCity() {
        return getDriver().findElement(saveNewCity);
    }

    public WebElement getSearchField() {
        return getDriver().findElement(searchField);
    }

    public WebElement getFirstName() {
        return getDriver().findElement(firstName);
    }
    public WebElement getFinallyDeleteButton() {
        return getDriver().findElement(finallyDeleteButton);
    }

    public WebElement getDeleteButton() {
        return getDriver().findElement(deleteButton);
    }

    public WebElement getSaveMsg() {
        return getDriver().findElement(saveMsg);
    }

    public void AddNewCity(String city) throws InterruptedException {
        getNewItem().click();
        Thread.sleep(1000);
        getInputField().sendKeys(city);
        Thread.sleep(1000);
        getSaveNewCity().click();
    }


}
