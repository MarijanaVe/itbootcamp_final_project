package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocaleTests extends BaseTests{



    @Test
    public void esLanguage () {
        localePage.getLanguageButton().click();
        localePage.getLanguageES().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        localePage.getLanguageES().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        localePage.getHeader().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String expectedResult = "Página de aterrizaje";
        String actualResult = localePage.getHeader().getText();
        Assert.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void enLanguage() {
        localePage.getLanguageButton().click();
        localePage.getLanguageEN().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        localePage.getLanguageEN().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        localePage.getHeader().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String expectedResult = "Landing";
        String actualResult = localePage.getHeader().getText();
        Assert.assertEquals(expectedResult,actualResult);
    }

        @Test
        public void frLanguage () {
        localePage.getLanguageButton().click();
        localePage.getLanguageFR().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        localePage.getLanguageFR().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        localePage.getHeader().isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String expectedResult = "Page d'atterrissage";
        String actualResult = localePage.getHeader().getText();
        Assert.assertEquals(expectedResult, actualResult);
    }





}


