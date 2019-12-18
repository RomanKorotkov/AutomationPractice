package pages.PrestaShopPages;

import com.sun.deploy.security.SelectableSecurityManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomCondition {

    public static ExpectedCondition<WebElement> attributeContains(By locator, String attr, String value) {
        return new ExpectedCondition<WebElement>() {
            private String currentValue = null;

            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                currentValue = element.getAttribute(attr);

                if (currentValue != null && !currentValue.isEmpty() && currentValue.contains(value))
                    return element;
                else
                    return null;
            }

            public String toSring() {
                return String
                        .format("found by %s attr='%s' to contain \"%s\". Current value: \"%s\"", locator, attr, value,
                                this.currentValue);
            }
        };
    }
}
