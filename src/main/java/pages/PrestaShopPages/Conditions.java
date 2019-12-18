package pages.PrestaShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public enum Conditions {
    PRESENCE(ExpectedConditions::presenceOfElementLocated),
    VISIBLE(ExpectedConditions::visibilityOfElementLocated),
    CLICKABLE(ExpectedConditions::elementToBeClickable);

    Conditions(Function<By, ExpectedCondition<WebElement>> condition) {
        this.condition = condition;
    }

    private final Function<By, ExpectedCondition<WebElement>> condition;

    public Function<By, ExpectedCondition<WebElement>> getCondition() {
        return condition;
    }
}
