package prestaShopTest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import pages.PrestaShopPages.*;

public class prestaShop extends BasicPrestaShopUI {

    @Test
    public void signIn() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        SignInForm signInForm = new SignInForm(driver);
        //act
        mainPage.clickOnLoginBtn();
        signInForm.fillRegisterForm();
        //assert
        String header = signInForm.getTitleTextAfterRegistration();
        Assert.assertThat(header, CoreMatchers.containsString("MY ACCOUNT"));
    }

    @Test
    public void signExist() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        SignInForm signInForm = new SignInForm(driver);
        //act
        mainPage.clickOnLoginBtn();
        signInForm.signWithExistedEmail();
        //assert
        String error = signInForm.getErrorTextIfUserExist();
        Assert.assertThat(error, CoreMatchers.containsString("An account using this email address"));
    }

    @Test
    public void logInError() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("hohohihi@mail.com", "qscvfergbngr");
        //assert
        String error = mainPage.getErrorAfterLogin();
        Assert.assertThat(error, CoreMatchers.containsString("Authentication"));
    }

    @Test
    public void logOut() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        mainPage.clickOnLogOutBtn();
        //assert
        String header = mainPage.getTitleAfterLogOut();
        Assert.assertThat(header, CoreMatchers.containsString("AUTHENTICATION"));
    }

    @Test
    public void contactUs() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ContactUs contactUs = new ContactUs(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        mainPage.clickOnContactUsBtn();
        Assert.assertTrue(contactUs.getUrlContactUsPage());
        contactUs.fillContactForm();
        //assert
        String message = contactUs.getTextMessageAfterEmailSending();
        Assert.assertThat(message, CoreMatchers.containsString("Your message has been"));
    }

    @Test
    public void verifyOrdersPage() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openOrder();
        //assert
        String header = myAccount.getTextTitleOrders();
        Assert.assertThat(header, CoreMatchers.containsString("ORDER HISTORY"));
    }

    @Test
    public void verifyCreditPage() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openCredit();
        //assert
        String header = myAccount.getTextTitleCredit();
        Assert.assertThat(header, CoreMatchers.containsString("CREDIT SLIPS"));
    }

    @Test
    public void verifyAddressesPage() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openAddress();
        //assert
        String header = myAccount.getTextTitleAddress();
        Assert.assertThat(header, CoreMatchers.containsString("MY ADDRESSES"));
    }

    @Test
    public void verifyPersonInfoPage() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openPersonInfo();
        //assert
        String header = myAccount.getTextTitlePersonInfo();
        Assert.assertThat(header, CoreMatchers.containsString("YOUR PERSONAL INFORMATION"));
    }

    @Test
    public void verifyWishlistPage() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openWishlist();
        //assert
        String header = myAccount.getTextTitleWishlist();
        Assert.assertThat(header, CoreMatchers.containsString("MY WISHLISTS"));
    }
}
