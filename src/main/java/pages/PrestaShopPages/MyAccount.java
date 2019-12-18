package pages.PrestaShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.PrestaShopPages.BoolConditions.TITLECONTAINS;
import static pages.PrestaShopPages.Conditions.CLICKABLE;

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
    }

    public String getTextTitleOrders() {
        return $(order_header, TITLECONTAINS).getText();
    }

    public void openCredit() {
        $(credit_slips, CLICKABLE).click();
    }

    public String getTextTitleCredit() {
        return $(creedit_header, TITLECONTAINS).getText();
    }

    public void openAddress() {
        $(addresses, CLICKABLE).click();
    }

    public String getTextTitleAddress() {
        return $(addresses_header, TITLECONTAINS).getText();
    }

    public void openPersonInfo() {
        $(person_info, CLICKABLE).click();
    }

    public String getTextTitlePersonInfo() {
        return $(person_header, TITLECONTAINS).getText();
    }

    public void openWishlist() {
        $(wishlist, CLICKABLE).click();
    }

    public String getTextTitleWishlist() {
        return $(wishlist_header, TITLECONTAINS).getText();
    }
}
