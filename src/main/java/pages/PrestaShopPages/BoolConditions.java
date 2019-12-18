package pages.PrestaShopPages;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public enum BoolConditions {
    IN_TITLE(ExpectedConditions::titleContains),
    TITLE_IS(ExpectedConditions::titleIs),
    IN_URL(ExpectedConditions::urlContains),
    URL_IS(ExpectedConditions::urlToBe);

    BoolConditions(Function<String, ExpectedCondition<Boolean>> title_condition) {
        this.title_condition = title_condition;
    }

    private final Function<String, ExpectedCondition<Boolean>> title_condition;

    public Function<String, ExpectedCondition<Boolean>> getCondition() {
        return title_condition;
    }
}
