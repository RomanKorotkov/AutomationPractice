package prestashoptest;

import api.BaseAPI;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BasicPrestaShopUI extends BasicPrestaShop implements BaseAPI {

    @Before
    public void forEachTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        System.out.println("WebDriver is ready for run test");
        driver.get("http://automationpractice.com/index.php");
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
