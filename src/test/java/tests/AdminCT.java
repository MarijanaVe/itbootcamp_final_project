package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminCT extends BaseTests {


    @Test (priority = 5)
    public void adminCitiesPageAndListCities() throws InterruptedException {
        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
        homePage.citiesPage();

        String expectedResult = "https://vue-demo.daniel-avellaneda.com/admin/cities";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        boolean actualRes = homePage.getLogoutBtn().isDisplayed();
        Assert.assertTrue(true, String.valueOf(actualRes));

        Thread.sleep(2000);
        loginPage.getLogoutBtn().click();
    }

    @Test (priority = 6)
    public void createCity() throws InterruptedException {
        Faker faker = new Faker();
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.citiesPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String addCity = faker.country().capital();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        adminCitiesPage.AddNewCity(addCity);
        Thread.sleep(5000);
        loginPage.getLogoutBtn().click();

        String expectedResult = "Saved successfully";

        String actualResult = driver.findElement(By.xpath(("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]"))).getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test (priority = 7)
    public void editCity() throws InterruptedException {

        Faker faker = new Faker();
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.citiesPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String addCity = faker.country().capital();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        adminCitiesPage.AddNewCity(addCity);
        Thread.sleep(5000);
        adminCitiesPage.getEdit().click();
        adminCitiesPage.getInputField().sendKeys(" edited ");
        adminCitiesPage.getSaveNewCity().click();

        String expectedResult = "Saved successfully";
        Thread.sleep(5000);
        String actualResult = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")).getText();
        driver.findElement(By.className("v-snack__content")).getText();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(actualResult.contains(expectedResult));

        loginPage.getLogoutBtn().click();
    }

    @Test (priority = 8)
    public void searchCity() throws InterruptedException {
        Faker faker = new Faker();
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.citiesPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String addCity = faker.country().capital();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        adminCitiesPage.AddNewCity(addCity);
        Thread.sleep(5000);
        adminCitiesPage.getEdit().click();
        adminCitiesPage.getInputField().sendKeys(" edited ");
        adminCitiesPage.getSaveNewCity().click();

        Thread.sleep(5000);
        adminCitiesPage.getSearchField().sendKeys(adminCitiesPage.getFirstName().getText());
        Thread.sleep(5000);
        loginPage.getLogoutBtn().click();
    }

    @Test (priority = 9)
    public void deleteCity () throws InterruptedException {
        Faker faker = new Faker();
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.citiesPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String addCity = faker.country().capital();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        adminCitiesPage.AddNewCity(addCity);
        Thread.sleep(5000);
        adminCitiesPage.getEdit().click();
        adminCitiesPage.getInputField().sendKeys(" edited ");
        adminCitiesPage.getSaveNewCity().click();
        Thread.sleep(5000);
        adminCitiesPage.deleteCity();

        Thread.sleep(5000);
        Assert.assertTrue(adminCitiesPage.getSuccessfullyDeleted().getText().contains("Deleted successfully"));
        loginPage.getLogoutBtn().click();
    }
}
