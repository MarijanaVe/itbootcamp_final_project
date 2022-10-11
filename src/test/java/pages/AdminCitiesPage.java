package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminCitiesPage extends BasePage{

    private By email = By.id("email");
    private By password = By.id("password");
    private By signUp= By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]");
    private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button/span");
    private By logoutButton =By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]");
    private By admin = By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]");
    private By cities= By.xpath("*[@id=\"app\"]/div[3]/div[1]/a[1]");
    private By edit =By.id ("edit");
    private By delete = By.xpath(".v-dialog .v-card__actions > button:last-child");
    private By newItem =By.xpath("/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[1]/div[3]/form/div[1]/button");
    private By inputField = By.id("name");
    private By saveNewCity = By.xpath("//*[@id=\"app\"]/div[5]/div/div/div[3]/button[2]");
    private By textBoxName = By.id("name");
    private By saveNamBtn = By.xpath("//*[@id=\"app\"]/div[7]/div/div/div[3]/button[2]");
    private By nameField = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[2]");
    private By searchField = By.xpath("//*[@id=\"search\"]");
    private By firstName = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[2]");
    private By delete1 = By.xpath("//*[@id=\"delete\"]/span/i");
    private By successfullyDeleted = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div");
    private By deleteButton = By.xpath("//*[@id=\"delete\"]/span");
    private By finallyDeleteButton = By.xpath("//*[@id=\"app\"]/div[6]/div/div/div[2]/button[2]");
    private By saveMsg = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]");


    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getEmail() {
        return getDriver().findElement(email);
    }

    public WebElement getPassword() {
        return getDriver().findElement(password);
    }

    public WebElement getSignUp() {
        return getDriver().findElement(signUp);
    }

    public WebElement getLoginButton() {
        return getDriver().findElement(loginButton);
    }

    public WebElement getLogoutButton() {
        return getDriver().findElement(logoutButton);
    }

    public WebElement getAdmin() {
        return getDriver().findElement(admin);
    }

    public WebElement getCities() {
        return getDriver().findElement(cities);
    }

    public WebElement getNameField() {
        return getDriver().findElement(nameField);
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

    public WebElement getTextBoxName() {
        return getDriver().findElement(textBoxName);
    }

    public WebElement getSaveNameBtn() {
        return getDriver().findElement(saveNamBtn);
    }

    public WebElement getSuccessfullyDeleted() {
        return getDriver().findElement(successfullyDeleted);
    }
    public WebElement getDelete() {
        return getDriver().findElement(delete);
    }

    public WebElement getDelete1() {
        return getDriver().findElement(delete1);
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

    public void deleteCity() throws InterruptedException {
        getDelete1().click();
        Thread.sleep(5000);
        getDelete().click();
    }




}
