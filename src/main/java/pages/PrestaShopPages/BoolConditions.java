package pages.PrestaShopPages;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public enum BoolConditions {
    TITLEIS(ExpectedConditions::titleIs),
    TITLECONTAINS(ExpectedConditions::titleContains),
    URLCONTAINS(ExpectedConditions::urlContains);

    BoolConditions(Function<String, ExpectedCondition<Boolean>> title_condition) {
        this.title_condition = title_condition;
    }

    private final Function<String, ExpectedCondition<Boolean>> title_condition;

    public Function<String, ExpectedCondition<Boolean>> getTitle_condition() {
        return title_condition;
    }
}
