package expectedConditions;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Until {
    WebDriver driver;
    @BeforeClass
    public static void suiteSetup() {
        System.out.println("Precondition for all tests");
    }

    @Before
    public void beforeEach() {
        System.out.println("WebDriver and window size are ready");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Rule
    public ErrorCollector collect = new ErrorCollector() {
    };

    @Rule
    public TestWatcher watcher = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            super.succeeded(description);
            System.out.println(description.getDisplayName() + "Succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            System.out.println(description.getDisplayName() + "Failed");
        }
    };

    @Test
    public void rozetka() {
        //act
        driver.get("https://rozetka.com.ua/");
        driver.findElement(By.name("search")).sendKeys("phones");
        driver.findElement(By.cssSelector("div.header-search.js-app-search-suggest form > button")).click();
        //assert
        collect.checkThat("Text is absent or changed",
                (new WebDriverWait(driver, 5))
                        .until(ExpectedConditions.presenceOfElementLocated(By.id("search_result_title_text")))
                        .getText(), CoreMatchers.is("phones"));
    }

    @Test
    public void tbm() throws InterruptedException {
        //act
        driver.get("https://tbm-dev.fntcluster.com/");
        driver.findElement(By.id("username")).sendKeys("super_admin");
        driver.findElement(By.id("password")).sendKeys("super_admin");
        driver.findElement(By.id("kc-login")).click();
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//fnt-block-dashlet-item/button[@title='Blueprints']")))
                .click();
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content span")));
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.id("create"))).click();
        driver.findElement(By.id("name")).sendKeys("dngzfzwcmhdubzchbchdbfchzbfehxmerhzhfhhzbhefbhfdbhs");
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("form button"))).click();
    }

    @Test
    public void commandAbsent() throws InterruptedException {
        //act
        driver.get("https://tbm-dev.fntcluster.com/");
        driver.findElement(By.id("username")).sendKeys("super_admin");
        driver.findElement(By.id("password")).sendKeys("super_admin");
        driver.findElement(By.id("kc-login")).click();
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//fnt-block-dashlet-item/button[@title='Command']")));
    }

    @After
    public void afterEarch() {
        System.out.println("Postcondition for earch tests");
        driver.quit();
    }

    @AfterClass
    public static void suiteSetdown() {
        System.out.println("Postcondition for all tests");
    }
}
