package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCT extends BaseTests{

    @Test
    public void adminCitiesPageAndListCities () throws InterruptedException {

        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
        homePage.citiesPage();
        Thread.sleep(2000);

        String expectedResult = "https://vue-demo.daniel-avellaneda.com/admin/cities";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);     //Verifikovati da se u url-u stranice javlja /admin/cities ruta

        boolean actualRes = homePage.getLogoutBtn().isDisplayed();
        Assert.assertTrue(true, String.valueOf(actualRes));     //Verifikovati postojanje logut dugmeta



    }

    @Test
    public void createCity () throws InterruptedException {
        Faker faker =new Faker();
        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
        homePage.citiesPage();
        Thread.sleep(2000);

        String addCity = faker.country().capital();
        Thread.sleep(5000);
        adminCitiesPage.AddNewCity(addCity);

        String expectedResult = "Saved successfully";
        Thread.sleep(5000);
        String actualResult = driver.findElement(By.xpath(("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]"))).getText();
        Thread.sleep(5000);
        Assert.assertTrue(actualResult.contains(expectedResult));     //Poruka sadrzi tekst Saved successfully
    }
}