package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTests{


    @Test
    public void editProfile() throws InterruptedException {

        loginPage.login("admin@admin.com", "12345");
        Thread.sleep(2000);
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
        Thread.sleep(10000);
        String expectedResult = name;     //Polje za unos imena za atribut type ima vrednost ime
        String actualResult = profilePage.getNameField().getAttribute("value");
        Assert.assertEquals(actualResult, expectedResult);
        String expectedResultPhone = phone;     //Polje za unos telefona za atribut type ima vrednost telefon
        String actualResultPhone = profilePage.getPhone().getAttribute("value");
        Assert.assertEquals(actualResultPhone, expectedResultPhone);
        String expectedResultCountry = country;     //Polje za unos drzave za atribut type ima vrednost drzava
        String actualResultCountry = profilePage.getCountry().getAttribute("value");
        Assert.assertEquals(actualResultCountry, expectedResultCountry);
        String expectedResultTwitter = twitter;     //Polje za unos Twitter naloga za atribut type ima vrednost Twitter
        String actualResultTwitter = profilePage.getTwitter().getAttribute("value");
        Assert.assertEquals(actualResultTwitter, expectedResultTwitter);
        String expectedResultGithub = gitHub;     //Polje za unos GitHub naloga za atribut type ima vrednost GitHub
        String actualResultGithub = profilePage.getGitHub().getAttribute("value");
        Assert.assertEquals(actualResultGithub.toLowerCase(),expectedResultGithub.toLowerCase());


    }


}
