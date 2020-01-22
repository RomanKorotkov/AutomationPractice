package prestashoptest;

import api.BaseAPI;
import event.listener.EventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BasicPrestaShopUI extends BasicPrestaShop implements BaseAPI {

    private final Logger LOGGER = LogManager.getLogger(BasicPrestaShopUI.class);

    protected WebDriver driver;

    @Before
    public void forEachTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        EventFiringWebDriver w_driver = new EventFiringWebDriver(new ChromeDriver());
        EventListener eventListener = new EventListener();
        w_driver.register(eventListener);
        driver = w_driver;
        driver.manage().window().setSize(new Dimension(1920, 1080));
        LOGGER.debug("WebDriver is ready for run test");
        driver.get(url);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected TestWatcher getWatcher() {
        return new TestWatcher() {
            @Override
            protected void starting(Description description) {
                super.starting(description);
                LOGGER.info("Test '{}' - STARTED", description.getMethodName());
            }

            @Override
            protected void succeeded(Description description) {
                LOGGER.info("Test '{}' - PASSED", description.getMethodName());
                super.succeeded(description);
                //System.out.println(description.getDisplayName() + "Succeeded");
            }

            @Override
            protected void failed(Throwable e, Description description) {
                captureScreenshot(description.getMethodName());
                LOGGER.error("Test '{}' - FAILED due to: {}", description.getMethodName(), e.getMessage());
                super.failed(e, description);
                //System.out.println(description.getDisplayName() + "Failed");
            }

            @Override
            protected void finished(Description description) {
                super.finished(description);
                driver.quit();
                LOGGER.debug("ChromeDriver has been shut down");
            }
        };
    }
}
