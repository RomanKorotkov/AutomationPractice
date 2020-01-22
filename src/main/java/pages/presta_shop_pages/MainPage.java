package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static pages.presta_shop_pages.BoolConditions.IN_TITLE;
import static pages.presta_shop_pages.Conditions.*;

//see package implicit_wait
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By search_btn = By.xpath("//*[@type= 'submit']");

    private By loginButton = By.xpath("//*[@class= 'login']");
    private By error_msg = By.xpath("//ol/li[contains(text(),'Authentication failed.')]");

    private By contact_us_btn = By.id("contact-link");

    private By searchInput = By.id("search_query_top");

    public By firstTip = By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"/*"//*[@id=\"index\"]/div[2]/ul/li[1]"*/);

    private By logout_btn = By.xpath("//*[@title= 'Log me out']");
    private By out_header = By.xpath("//h1");

    private By add_to_cart_btn = By.xpath("//*[@title= 'Add to cart']");

    private By open_cart = By.xpath("//*[@title= 'View my shopping cart']");
    private By cart_title = By.id("cart_title");

    private By account_btn = By.xpath("//*[@class= 'account']");

    private By frame_title = By.xpath("//title[contains (text(), 'Facebook')]");

    private By iframe_facebook = By.xpath("//*[@title= 'fb:like_box Facebook Social Plugin']");
    WebElement iframe_facebook_element = driver.findElement(iframe_facebook);

    public void clickOnSearchBtn(){
        $(search_btn, CLICKABLE).click();
    }

    public void clickOnLoginBtn() {
        $(loginButton, CLICKABLE).click();
    }

    public String getErrorAfterLogin() {
        return $(error_msg, VISIBLE).getText();
    }

    public void clickOnLogOutBtn() {
        $(logout_btn, CLICKABLE).click();
        waitFor("Login - My Store", IN_TITLE);
    }

    public String getTitleAfterLogOut() {
        return $(out_header, VISIBLE).getText();
    }

    public void clickOnContactUsBtn() {
        $(contact_us_btn, CLICKABLE).click();
    }

    public String getFirstTipText() {
        return $(firstTip, VISIBLE).getText();
    }

    public WebElement waitForHoverOnFirstTip() {
        return conditionWaiter(CustomCondition.attributeContains(firstTip, "class", "ac_over"));
    }

    public void clickOnAddToCartBtn(){
        $(add_to_cart_btn, CLICKABLE).click();
    }

    public void clickOnAccountBtn(){
        $(account_btn, CLICKABLE).click();
    }

    public String OpenCartAndVerifyTitle(){
        $(open_cart, CLICKABLE).click();
        return $(cart_title, VISIBLE).getText();
    }

    public Boolean getCurrentPageUrlAndTitle(){
        return conditionWaiter(PageLoadedCondition.pageLoaded("http://automationpractice.com/index.php", "My Store"));
    }

    public void searchFor(String query) {
        $(searchInput, CLICKABLE).click();
        $(searchInput, PRESENCE).clear();
        $(searchInput, PRESENCE).sendKeys(query);
    }

    public void moveToFacebookFrame(){
        executeScript("arguments[0].scrollIntoView(true);", $("//*[@id= 'facebook_block']"));
    }

    public void verifyFrameFacebook(){
        getDriver().switchTo().frame(iframe_facebook_element);
        $(frame_title, PRESENCE);
    }

    //See test verifySecondTextInSearch (ImplicitWait)
//    public void verifyFirstTipIsAbsent(){
//        (new WebDriverWait(driver,10))
//                .until(ExpectedConditions.stalenessOf((WebElement)firstTip));
//    }
//
    public String getTextOfSecondTip(){
        return $(firstTip, VISIBLE).getText();
    }
    //Finish see
}
