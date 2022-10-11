package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProfileTests extends BaseTests{


    @Test
    public void editProfile() throws InterruptedException {
        loginPage.login("admin@admin.com", "12345");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        profilePage.visitProfilePage();
        Thread.sleep(2000);
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String phone = String.valueOf(faker.phoneNumber());
        String city = faker.address().city();
        String country = faker.address().country();
        String twitter = "https://" + faker.internet().domainName();
        String gitHub = "https://" + faker.name().firstName() + faker.internet().domainName();
        profilePage.editProfileData(name, phone, city, country, twitter, gitHub);
        Thread.sleep(4000);
        String expectedResult = name;
        String actualResult = profilePage.getNameField().getAttribute("value");
        Assert.assertEquals(actualResult, expectedResult);
        String expectedResultPhone = phone;
        String actualResultPhone = profilePage.getPhone().getAttribute("value");
        Assert.assertEquals(actualResultPhone, expectedResultPhone);
        String expectedResultCountry = country;
        String actualResultCountry = profilePage.getCountry().getAttribute("value");
        Assert.assertEquals(actualResultCountry, expectedResultCountry);
        String expectedResultTwitter = twitter;
        String actualResultTwitter = profilePage.getTwitter().getAttribute("value");
        Assert.assertEquals(actualResultTwitter, expectedResultTwitter);
        String expectedResultGithub = gitHub;
        String actualResultGithub = profilePage.getGitHub().getAttribute("value");
        Assert.assertEquals(actualResultGithub.toLowerCase(),expectedResultGithub.toLowerCase());
    }


}
