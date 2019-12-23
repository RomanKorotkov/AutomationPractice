package prestashoptest;

import api.BaseAPI;
import event.listener.EventListener;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class BasicPrestaShopUI extends BasicPrestaShop implements BaseAPI {

    @Before
    public void forEachTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        EventFiringWebDriver w_driver = new EventFiringWebDriver(new ChromeDriver());
        EventListener eventListener = new EventListener();
        w_driver.register(eventListener);
        driver = w_driver;
        driver.manage().window().setSize(new Dimension(1920, 1080));
        System.out.println("WebDriver is ready for run test");
        driver.get(url);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void afterEachTest() {
        driver.quit();
    }
}
