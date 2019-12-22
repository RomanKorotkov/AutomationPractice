package prestashoptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class EventListener extends AbstractWebDriverEventListener {

    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to:'" + url + "'");
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find by: " + by.toString());
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Trying to click on: " + getElementLocator(element));
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] KeyToSend) {
        System.out.println("Trying to send keys for: " + getElementLocator(element));
    }

    public String getElementLocator(WebElement element) {
        String elDescription = element.toString();
        int descriptionLength = elDescription.length();
        int startIndex = elDescription.indexOf("-> ") + 3;
        return "by " + elDescription.substring(startIndex, descriptionLength - 1);
    }

}
