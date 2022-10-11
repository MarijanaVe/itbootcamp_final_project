package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTests {

    @Test
    public void forbidsVisitsToHomeUrl () {
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);
    }

    @Test
    public void forbidsVisitsToProfileUrl () {
        driver.get("https://vue-demo.daniel-avellaneda.com/profile");
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);
    }

    @Test
    public void forbidsVisitsToAdminCities () {
        driver.get("https://vue-demo.daniel-avellaneda.com/admin/cities");
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);
    }

    @Test
    public void forbidsVisitsToAdminUsers () {
        driver.get("https://vue-demo.daniel-avellaneda.com/admin/users");
        boolean actualResult = driver.getCurrentUrl().contains("/login");
        Assert.assertEquals(true, actualResult);
    }


}
