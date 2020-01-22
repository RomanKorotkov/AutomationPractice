package pages.presta_shop_pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_controls.Tables;

public class HistoryOrdersPage extends BasePage {

    public HistoryOrdersPage(WebDriver driver) {
        super(driver);
    }

    private Tables ordersTable = new Tables(driver, By.id("order-list"));

    private Tables orderdetail = new Tables(driver, By.id("order-detail-content"));

    private By details_btn_for_firs_product = By.xpath(".//span[contains(text(), 'Details')]");

    public void getRefNo(int rowNo) {
        ordersTable.getDataTable(1, rowNo).click();
    }

    public void verifyAndClickOnDetailsBtnOfNewGoods(){
        Assert.assertEquals("On backorder", ordersTable.getDataTable(2, 5).getText());
        //ordersTable.getDataTable(2,1).click();
        $(details_btn_for_firs_product, Conditions.VISIBLE).click();
    }

    public void moveToOrderDetailTable(){
        executeScript("arguments[0].scrollIntoView(true);", $("//*[@id= 'order-detail-content']"));
    }

    public void verifyColumnProductThatContainsNewGoodsName(){
        Assert.assertEquals("Printed Chiffon Dress - Color : Yellow, Size : S", orderdetail.getDataTable( 6,2).getText());
    }
}
