package prestashoptest;

import de.redsix.pdfcompare.PdfComparator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import pages.presta_shop_pages.*;
import utils.FileDownloader;

import java.io.File;
import java.io.IOException;


public class PrestaShop extends BasicPrestaShopUI {

    @Test
    public void signIn() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        SignInForm signInForm = new SignInForm(driver);
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
        String header = myAccount.getTextHeaderOrders();
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
        String header = myAccount.getTextHeaderCredit();
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
        String header = myAccount.getTextHeaderAddress();
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
        String header = myAccount.getTextHeaderPersonInfo();
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
        String header = myAccount.getTextHeaderWishlist();
        Assert.assertThat(header, CoreMatchers.containsString("MY WISHLISTS"));
    }

    @Test
    @Category(RunWithOwnConditionTest.class)
    public void verifyTitleAndUrl() {
        //arrange
        MainPage mainPage = new MainPage(driver);
        //assert
        Assert.assertTrue(mainPage.getCurrentPageUrlAndTitle());
        assertAll(
                o -> Assert.assertEquals("http://automationpractice.com/index.php", getDriver().getCurrentUrl()),
                o -> Assert.assertEquals("My Store", getDriver().getTitle())
        );
    }

    @Test
    public void buyGoods(){
        //arrange
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        MyAccount myAccount = new MyAccount(driver);
        HistoryOrdersPage historyOrdersPage = new HistoryOrdersPage(driver);
        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        mainPage.searchFor("Printed Chiffon Dress");
        mainPage.clickOnSearchBtn();
        String search_result = searchPage.verifySearchResult();
        searchPage.hoverOnProductChiffonDress();
        mainPage.clickOnAddToCartBtn();
        String cart_title = mainPage.OpenCartAndVerifyTitle();
        searchPage.clickOnCheckoutBtn();
        searchPage.proceedAdresse();
        searchPage.proceedShiping();
        searchPage.clickOnPayByBank();
        searchPage.clickOnConfirmOrderBtn();
        mainPage.clickOnAccountBtn();
        myAccount.openOrder();
        historyOrdersPage.verifyAndClickOnDetailsBtnOfNewGoods();
        historyOrdersPage.moveToOrderDetailTable();
        historyOrdersPage.verifyColumnProductThatContainsNewGoodsName();
        //assert
        Assert.assertThat(search_result, CoreMatchers.containsString("PRINTED CHIFFON DRESS"));
        Assert.assertThat(cart_title, CoreMatchers.containsString("SHOPPING-CART SUMMARY"));
    }

    @Test
    public void verifyFacebookFrame(){
        //arrange
        MainPage mainPage = new MainPage(driver);
        //act
        mainPage.moveToFacebookFrame();
        mainPage.verifyFrameFacebook();
    }

    @Test
    public void verifyDownloadMyOrder() throws Exception {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccount myAccount = new MyAccount(driver);
     //   HistoryOrdersPage historyOrdersPage = new HistoryOrdersPage(driver);
        FileDownloader fileDownloader = new FileDownloader(driver);

        //act
        mainPage.clickOnLoginBtn();
        loginPage.fillLogInForm("8767915@mail.com", "Qwerty0123456789");
        mainPage.clickOnAccountBtn();
        myAccount.openOrder();

        fileDownloader.setURI($(By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[6]/a")).getAttribute("href"));
        File actualFile = fileDownloader.downloadFile();
        int requestStatus = fileDownloader.getLastDownloadHTTPStatus();
        assertAll(o -> Assert.assertThat("Check status.", requestStatus, CoreMatchers.is(200)),
                o -> {
                    try {
                        Assert.assertThat(new PdfComparator(new File("IN147735.pdf"), actualFile)
                                .compare().writeTo("diffOutputOrder"), CoreMatchers.is(true));
                    } catch (IOException e) {
                       throw new AssertionError(e.getMessage());
                    }
                });
    }

    @Test
    public void verifyInputText(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.fillTheSearchField();
        String input_text = searchPage.verifyFilledText();
        Assert.assertThat(input_text, CoreMatchers.containsString("LalaLA"));
    }
}
