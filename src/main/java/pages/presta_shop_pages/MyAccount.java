package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.presta_shop_pages.BoolConditions.IN_TITLE;
import static pages.presta_shop_pages.Conditions.CLICKABLE;
import static pages.presta_shop_pages.Conditions.VISIBLE;

public class MyAccount extends BasePage {

    public MyAccount(WebDriver driver) {
        super(driver);
    }

    private By order_details = By.xpath("//*[@title= 'Orders']");
    private By order_header = By.xpath("//h1[contains (text(), 'Order history')]");

    private By credit_slips = By.xpath("//*[@title= 'Credit slips']");
    private By creedit_header = By.xpath("//h1[contains (text(), 'Credit slips')]");

    private By addresses = By.xpath("//*[@title= 'Addresses']");
    private By addresses_header = By.xpath("//h1[contains (text(), 'My addresses')]");

    private By person_info = By.xpath("//*[@title= 'Information']");
    private By person_header = By.xpath("//h1[contains (text(), 'Your personal information')]");

    private By wishlist = By.xpath("//*[@title= 'My wishlists']");
    private By wishlist_header = By.xpath("//h1[contains (text(), 'My wishlists')]");

    public void openOrder() {
        $(order_details, CLICKABLE).click();
        waitFor("Order history - My Store", IN_TITLE);
    }

    public String getTextHeaderOrders() {
        return $(order_header, VISIBLE).getText();
    }

    public void openCredit() {
        $(credit_slips, CLICKABLE).click();
        waitFor("Order slip - My Store", IN_TITLE);
    }

    public String getTextHeaderCredit() {
        return $(creedit_header, VISIBLE).getText();
    }

    public void openAddress() {
        $(addresses, CLICKABLE).click();
        waitFor("Addresses - My Store", IN_TITLE);
    }

    public String getTextHeaderAddress() {
        return $(addresses_header, VISIBLE).getText();
    }

    public void openPersonInfo() {
        $(person_info, CLICKABLE).click();
        waitFor("Identity - My Store", IN_TITLE);
    }

    public String getTextHeaderPersonInfo() {
        return $(person_header, VISIBLE).getText();
    }

    public void openWishlist() {
        $(wishlist, CLICKABLE).click();
        waitFor("My Store", IN_TITLE);
    }

    public String getTextHeaderWishlist() {
        return $(wishlist_header, VISIBLE).getText();
    }
}
