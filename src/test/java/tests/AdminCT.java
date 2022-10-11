package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminCT extends BaseTests {


    @Test
    public void adminCitiesPageAndListCities() throws InterruptedException {
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.citiesPage();

        String expectedResult = "https://vue-demo.daniel-avellaneda.com/admin/cities";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        boolean actualRes = homePage.getLogoutBtn().isDisplayed();
        Assert.assertTrue(true, String.valueOf(actualRes));

        Thread.sleep(2000);
        loginPage.getLogoutBtn().click();
    }

    @Test
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

    @Test
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

        Thread.sleep(1000);
        Assert.assertTrue(actualResult.contains(expectedResult));

        loginPage.getLogoutBtn().click();
    }

    @Test
    public void searchCity() throws InterruptedException {
        Faker faker = new Faker();
        Thread.sleep(1000);
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

    @Test
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
        adminCitiesPage.getDeleteButton().click();
        adminCitiesPage.getFinallyDeleteButton().click();
        String actualResult = adminCitiesPage.getSaveMsg().getText();
        Thread.sleep(1000);
        String expectedResult ="Deleted successfully\nCLOSE";
        Assert.assertTrue(actualResult.contains(expectedResult));
        loginPage.getLogoutBtn().click();
    }
}
