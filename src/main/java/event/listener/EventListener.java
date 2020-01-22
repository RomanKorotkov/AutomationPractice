package event.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.Arrays;

public class EventListener extends AbstractWebDriverEventListener {

    public static final Logger LOGGER = LogManager.getLogger(EventListener.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOGGER.info("Navigated to:'" + url + "'");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        if(element == null) {
            LOGGER.debug("Trying to find by: " + by.toString());
        }else{
            LOGGER.debug("Finding element: " + by + " within root: " + getElementLocator(element));
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOGGER.debug("Trying to click on: " + getElementLocator(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] KeyToSend) {
        LOGGER.debug("Trying to send keys " + Arrays.toString(KeyToSend) + " for : " + getElementLocator(element));
    }

    public String getElementLocator(WebElement element) {
        String elDescription = element.toString();
        int descriptionLength = elDescription.length();
        int startIndex = elDescription.indexOf("-> ") + 3;
        return "by " + elDescription.substring(startIndex, descriptionLength - 1);
    }
}
