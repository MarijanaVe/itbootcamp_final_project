package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTests{

    @Test

    public void setLocaleToES () throws InterruptedException {


        localePage.getLanguageButton().click();
        Thread.sleep(5000);
        localePage.getLangES().click();
        Thread.sleep(5000);
        localePage.getHeaderTitle().isDisplayed();
        Thread.sleep(5000);
        String expectedResult = "Página de aterrizaje";
        String actualResult = localePage.getHeaderTitle().getText();
        Assert.assertEquals(expectedResult, actualResult);









    }

}
