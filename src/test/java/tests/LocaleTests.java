package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTests{



    @Test
    public void esLanguage () throws InterruptedException {
        localePage.getLanguageButton().click();
        Thread.sleep(5000);
        localePage.getLanguageES().isDisplayed();
        Thread.sleep(5000);
        localePage.getLanguageES().click();
        Thread.sleep(5000);
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        localePage.getHeader().isDisplayed();
        Thread.sleep(5000);
        String expectedResult = "Página de aterrizaje";
        String actualResult = localePage.getHeader().getText();
        Assert.assertEquals(expectedResult,actualResult);

        }

        @Test
        public void enLanguage() throws InterruptedException {

            localePage.getLanguageButton().click();
            Thread.sleep(5000);
            localePage.getLanguageEN().isDisplayed();
            Thread.sleep(5000);
            localePage.getLanguageEN().click();
            Thread.sleep(5000);
            driver.get("https://vue-demo.daniel-avellaneda.com/");
            localePage.getHeader().isDisplayed();
            Thread.sleep(5000);
            String expectedResult = "Landing";
            String actualResult = localePage.getHeader().getText();
            Assert.assertEquals(expectedResult,actualResult);

        }

        @Test
        public void frLanguage () throws InterruptedException {
        localePage.getLanguageButton().click();
        Thread.sleep(5000);
        localePage.getLanguageFR().isDisplayed();
        Thread.sleep(5000);
        localePage.getLanguageFR().click();
        Thread.sleep(5000);
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        localePage.getHeader().isDisplayed();
        Thread.sleep(5000);
        String expectedResult = "Page d'atterrissage";
        String actualResult = localePage.getHeader().getText();
        Assert.assertEquals(expectedResult, actualResult);

        }





}


