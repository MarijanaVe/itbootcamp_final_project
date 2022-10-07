package tests;

import org.testng.annotations.Test;

public class ProfileTests extends BaseTests{

    @Test
    public void editsProfile () {
        loginPage.getloginButton().click();
        loginPage.login("admin@admin.com", "12345");
        profilePage.getMyProfileBtn().click();



    }


}
