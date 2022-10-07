package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminCitiesPage extends BasePage{

    private By email = By.id("email");
    private By password = By.id("password");
    private By signUp= By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]");
    private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button/span");
    private By logoutButton =By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]");
    private By admin = By.xpath("//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]");
    private By cities= By.xpath("*[@id=\"app\"]/div[3]/div[1]/a[1]");
    private By edit =By.id ("edit");
    private By delete = By.id ("delete");
    private By newItem =By.xpath("/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[1]/div[3]/form/div[1]/button");
    private By inputField = By.id("name");
    private By saveNewCity = By.xpath("//*[@id=\"app\"]/div[5]/div/div/div[3]/button[2]");
    private By textBoxName = By.id("name");
    private By saveNamBtn = By.xpath("//*[@id=\"app\"]/div[7]/div/div/div[3]/button[2]");


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

    public void loginACP(String email, String password) {
        getEmail().sendKeys(email);
        getPassword().sendKeys(password);
        getLoginButton().click();
    }

    public void AddNewCity(String city) throws InterruptedException {

        getNewItem().click();
        Thread.sleep(5000);
        getInputField().sendKeys(city);
        Thread.sleep(5000);
        getSaveNewCity().click();
    }

    public void editCity () {
        getEdit().click();
        getTextBoxName().sendKeys(" edit ");
        getSaveNameBtn().click();
    }



}
