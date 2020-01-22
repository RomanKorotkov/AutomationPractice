package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static pages.presta_shop_pages.Conditions.*;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);

    private By chiffon_dress = By.xpath("//span[contains(text(), '\"Printed Chiffon Dress\"')]");

    private By checkout_btn1 = By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]");

    private By checkout_btn2 = By.xpath("//*[@id=\"center_column\"]/form/p/button");

    private By agree_checkbox = By.id("uniform-cgv");
    private By checkout_btn3 = By.xpath("//*[@id=\"form\"]/p/button");

    private By pay_bank = By.xpath("//*[@title= 'Pay by bank wire']");

    private By confirm_order_btn = By.xpath("//*[@class= 'button btn btn-default button-medium']");

    private By hover_on_chiffon_dress = By.xpath("//*[@class= 'product-container']");
    WebElement chiffon_dress_hovered = driver.findElement(hover_on_chiffon_dress);

    private By search_field = By.id("search_query_top");

    public String verifySearchResult() {
        return $(chiffon_dress, VISIBLE).getText();
    }

    public void hoverOnProductChiffonDress() {
        WebElement hover = driver.findElement(By.xpath("//*[@class= 'product-container']"));
        $(hover_on_chiffon_dress, PRESENCE);
        actions.moveToElement(hover).perform();
    }

    public void clickOnCheckoutBtn() {
        $(checkout_btn1, CLICKABLE).click();
    }

    public void proceedAdresse() {
        $(checkout_btn2, CLICKABLE).click();
    }

    public void proceedShiping() {
        $(agree_checkbox, VISIBLE).click();
        $(checkout_btn3, CLICKABLE).click();
    }

    public void clickOnPayByBank() {
        $(pay_bank, CLICKABLE).click();
    }

    public void clickOnConfirmOrderBtn() {
        $(confirm_order_btn, CLICKABLE).click();
    }

    public void fillTheSearchField(){
        $(search_field, VISIBLE).sendKeys("LalaLA");
    }

    public String verifyFilledText(){
        return $(search_field, VISIBLE).getAttribute("value");
    }
}
