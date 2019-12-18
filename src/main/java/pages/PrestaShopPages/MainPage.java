package pages.PrestaShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pages.PrestaShopPages.BoolConditions.TITLECONTAINS;
import static pages.PrestaShopPages.Conditions.*;

//see package implicit_wait
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By loginButton = By.xpath("//*[@class= 'login']");
    private By error_msg = By.xpath("//ol/li[contains(text(),'Authentication failed.')]");

    private By contact_us_btn = By.id("contact-link");

    private By searchInput = By.id("search_query_top");

    public By firstTip = By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"/*"//*[@id=\"index\"]/div[2]/ul/li[1]"*/);

    private By logout_btn = By.xpath("//*[@title= 'Log me out']");
    private By out_header = By.xpath("//h1");

    public void clickOnLoginBtn() {
        $(loginButton, CLICKABLE).click();
    }

    public String getErrorAfterLogin() {
        return $(error_msg, VISIBLE).getText();
    }

    public void clickOnLogOutBtn() {
        $(logout_btn, CLICKABLE).click();
    }

    public String getTitleAfterLogOut() {
        return $(out_header, TITLECONTAINS).getText();
    }

    public void clickOnContactUsBtn() {
        $(contact_us_btn, CLICKABLE).click();
    }

    public String getFirstTipText() {
        return $(firstTip, TITLECONTAINS).getText();
    }

    public WebElement waitForHoverOnFirstTip(){
        return conditionWaiter(CustomCondition.attributeContains(firstTip, "class", "ac_over"));
    }

    public void searchFor(String query) {
        $(searchInput, CLICKABLE).click();
        $(searchInput, PRESENCE).clear();
        $(searchInput, PRESENCE).sendKeys(query);
    }

    //See test verifySecondTextInSearch (ImplicitWait)
    public void verifyFirstTipIsAbsent(){
        (new WebDriverWait(driver,10))
                .until(ExpectedConditions.stalenessOf((WebElement)firstTip));
    }

    public String getTextOfSecondTip(){
        return $(firstTip, VISIBLE).getText();
    }
    //Finish see
}
