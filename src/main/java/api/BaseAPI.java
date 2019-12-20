package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.presta_shop_pages.BoolConditions;
import pages.presta_shop_pages.Conditions;

import java.util.function.Function;

import static pages.presta_shop_pages.Conditions.CLICKABLE;
import static pages.presta_shop_pages.Conditions.PRESENCE;

public interface BaseAPI {
    
    WebDriver getDriver();

    default Boolean $(String title, Function<String, ExpectedCondition<Boolean>> title_condition) {
        return conditionWaiter(title_condition.apply(title));
    }

    default Boolean $(String title, BoolConditions boolConditions) {
        return conditionWaiter(boolConditions.getCondition().apply(title));
    }

    default void waitFor(String expString, BoolConditions condition) {
        conditionWaiter(condition.getCondition().apply(expString));
    }

    default WebElement $(By locator, Function<By, ExpectedCondition<WebElement>> condition) {
        return conditionWaiter(condition.apply(locator));
    }

    default WebElement $(By locator, Conditions conditions) {
        return conditionWaiter(conditions.getCondition().apply(locator));
    }

    default void click(By locator){
        $(locator, CLICKABLE).click();
    }

    default void clickk(String xpath){
        $(xpath, CLICKABLE).click();
    }

//    default WebElement $(String css, Conditions conditions) {
//        return $(By.cssSelector(css), conditions);
//    }

    default WebElement $(String xpath, Conditions conditions) {
        return $(By.xpath(xpath), conditions);
    }

    default WebElement $(By locator) {
        //return conditionWaiter(ExpectedConditions.presenceOfElementLocated(locator));
        return $(locator, PRESENCE);
    }

    default <T> T timeWaiter(ExpectedCondition<T> condition, long timeout) {
        return new WebDriverWait(getDriver(), timeout).until(condition);
    }

    default <T> T conditionWaiter(ExpectedCondition<T> condition) {
        return timeWaiter(condition, 10l);
    }
}
