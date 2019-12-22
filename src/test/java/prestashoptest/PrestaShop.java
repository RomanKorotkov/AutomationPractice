package prestashoptest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.presta_shop_pages.*;

public class PrestaShop extends BasicPrestaShopUI {

    @Test
    public void signIn() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        SignInForm signInForm = new SignInForm(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        signInForm.fillRegisterForm();
        //assert
        String header = signInForm.getHeaderTextAfterRegistration();
        Assert.assertThat(header, CoreMatchers.containsString("MY ACCOUNT"));
    }

    @Test
    public void signExist() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        SignInForm signInForm = new SignInForm(w_driver);
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
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
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
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
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
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        ContactUs contactUs = new ContactUs(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        mainPage.clickOnContactUsBtn();
        contactUs.fillContactForm();
        //assert
        String message = contactUs.getTextMessageAfterEmailSending();
        Assert.assertThat(message, CoreMatchers.containsString("Your message has been"));
    }

    @Test
    public void verifyOrdersPage() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        MyAccount myAccount = new MyAccount(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openOrder();
        //assert
        String header = myAccount.getTextHeaderOrders();
        Assert.assertThat(header, CoreMatchers.containsString("ORDER HISTORY"));
    }

    @Test
    public void verifyCreditPage() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        MyAccount myAccount = new MyAccount(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openCredit();
        //assert
        String header = myAccount.getTextHeaderCredit();
        Assert.assertThat(header, CoreMatchers.containsString("CREDIT SLIPS"));
    }

    @Test
    public void verifyAddressesPage() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        MyAccount myAccount = new MyAccount(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openAddress();
        //assert
        String header = myAccount.getTextHeaderAddress();
        Assert.assertThat(header, CoreMatchers.containsString("MY ADDRESSES"));
    }

    @Test
    public void verifyPersonInfoPage() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        MyAccount myAccount = new MyAccount(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openPersonInfo();
        //assert
        String header = myAccount.getTextHeaderPersonInfo();
        Assert.assertThat(header, CoreMatchers.containsString("YOUR PERSONAL INFORMATION"));
    }

    @Test
    public void verifyWishlistPage() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        LoginPage loginPage = new LoginPage(w_driver);
        MyAccount myAccount = new MyAccount(w_driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        myAccount.openWishlist();
        //assert
        String header = myAccount.getTextHeaderWishlist();
        Assert.assertThat(header, CoreMatchers.containsString("MY WISHLISTS"));
    }

    @Test
    @Category(RunWithOwnConditionTest.class)
    public void verifyTitleAndUrl() {
        //arrange
        MainPage mainPage = new MainPage(w_driver);
        //assert
        Assert.assertTrue(mainPage.getCurrentPageUrlAndTitle());
        assertAll(
                o -> Assert.assertEquals("http://automationpractice.com/index.php", getDriver().getCurrentUrl()),
                o -> Assert.assertEquals("My Store", getDriver().getTitle())
        );
    }

    @Test
    public void withLogg(){

    }
}
