package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTests {

    @Test
    public void forbidsVisitsToHomeUrl () {
        driver.get("https://vue-demo.daniel-avellaneda.com/home");     //Ucitati /home stranu kada korisnik nije ulogovan
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);     //Verifikovati da se u url-u stranice javlja ruta /login
    }

    @Test
    public void forbidsVisitsToProfileUrl () {
        driver.get("https://vue-demo.daniel-avellaneda.com/profile");     //Ucitati /profile stranu
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);     //Verifikovati da se u url-u stranice javlja ruta /login
    }


    @Test
    public void forbidsVisitsToAdminCities () {
        driver.get("https://vue-demo.daniel-avellaneda.com/admin/cities");     //Ucitati /admin/cities stranu
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);     //Verifikovati da se u url-u stranice javlja ruta /login
    }


    @Test
    public void forbidsVisitsToAdminUsers () {
        driver.get("https://vue-demo.daniel-avellaneda.com/admin/users");     //Ucitati /admin/users stranu
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);     //Verifikovati da se u url-u stranice javlja ruta /login
    }


}
