package api;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.presta_shop_pages.BoolConditions;
import pages.presta_shop_pages.Conditions;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import static pages.presta_shop_pages.Conditions.PRESENCE;

public interface BaseAPI {

    Logger LOGGER = LogManager.getLogger(BaseAPI.class);

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

    default WebElement $(By locator) {
        //return conditionWaiter(ExpectedConditions.presenceOfElementLocated(locator));
        return $(locator, PRESENCE);
    }

    default WebElement $(String xpath, Conditions conditions) {
        return $(By.xpath(xpath), conditions);
    }

    default WebElement $(String xpath) {
        return $(xpath, PRESENCE);
    }

    default void captureScreenshot(String methodName) {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotName = screenshot.getName().replace("screenshot", methodName + "_");
        String path = System.getProperty("report.path") + "/screenshots/" + screenshotName;
        try {
            FileUtils.copyFile(screenshot, new File(path));
            LOGGER.error("Screenshot was got: " + screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    default void click(By locator){
//        $(locator, CLICKABLE).click();
//    }

//    default void clickk(String xpath){
//        $(xpath, CLICKABLE).click();
//    }


    // default WebElement $(String xpath, Conditions conditions) {
//        return $(By.xpath(xpath), conditions);
//    }

    default <T> T timeWaiter(ExpectedCondition<T> condition, long timeout) {
        return new WebDriverWait(getDriver(), timeout).until(condition);
    }

    default <T> T conditionWaiter(ExpectedCondition<T> condition) {
        return timeWaiter(condition, 10l);
    }

    default JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    default Object executeScript(String js, Object... args) {
        return getJSExecutor().executeScript(js, args);
    }

    default Object executeAsyncScript(String js, Object... args) {
        return getJSExecutor().executeScript(js, args);
    }
}
