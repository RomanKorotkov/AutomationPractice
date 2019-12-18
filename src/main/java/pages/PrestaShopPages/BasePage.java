package pages.PrestaShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static pages.PrestaShopPages.Conditions.PRESENCE;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected Boolean $(String title, Function<String, ExpectedCondition<Boolean>> title_condition){
        return conditionWaiter(title_condition.apply(title));
    }
    protected Boolean $(String title, BoolConditions boolConditions){
        return conditionWaiter(boolConditions.getTitle_condition().apply(title));
    }

    protected WebElement $(By locator, Function<By, ExpectedCondition<WebElement>> condition){
        return conditionWaiter(condition.apply(locator));
    }

    protected WebElement $(By locator, Conditions conditions){
        return conditionWaiter(conditions.getCondition().apply(locator));
    }

    protected WebElement $(By locator, BoolConditions titleis){
        //return conditionWaiter(ExpectedConditions.presenceOfElementLocated(locator));
        return $(locator, PRESENCE);
    }

    protected<T> T timeWaiter(ExpectedCondition<T> condition, long timeout){
        return new WebDriverWait(driver,timeout).until(condition);
    }

    protected<T> T conditionWaiter(ExpectedCondition<T> condition){
        return timeWaiter(condition, 10l);
    }
}
