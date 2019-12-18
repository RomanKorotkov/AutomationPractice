package implicit_wait;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PrestaShopPages.MainPage;

import java.util.concurrent.TimeUnit;

public class ImplicitWait {
    WebDriver driver;

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

    @BeforeClass
            public static void suiteSetup(){
        System.out.println("Preparation before class");
    }

    @Before
    public void beforeEach() {
        System.out.println("WebDriver is ready for run test");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void verifyFirstTextInSearch(){
        //act
        driver.findElement(By.id("search_query_top")).click();
        driver.findElement(By.id("search_query_top")).sendKeys("Dress");
        //assert
        String firstText = driver.findElement(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]")).getText();
        Assert.assertThat(firstText, CoreMatchers.containsString("Dress"));
    }

    @Test
    public void verifySecondTextInSearch(){
        MainPage mainPage = new MainPage(driver);
        final String firstQueryText = "Dress";
        final String secondQueryText = "T-shirt";
        mainPage.searchFor(firstQueryText);
        mainPage.searchFor(secondQueryText);
//        (new WebDriverWait(driver,10))
//                .until(ExpectedConditions.stalenessOf((WebElement) mainPage.firstTip));
        //mainPage.checkElementStallen();
        //mainPage.verifyFirstTipIsAbsent();
        //String secondText = mainPage.getTextOfSecondTip();//mainPage.firstTip.getText();
        //Assert.assertThat(secondText, CoreMatchers.containsString("T-shirt"));
    }

    @Test
    public void verifyFirstTipBecomesOverAfterClickOnArrowDown() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        final String queryText = "Dress";
        // act
        mainPage.searchFor(queryText);
        String firstTipText = mainPage.getFirstTipText();
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement firstTip = mainPage.waitForHoverOnFirstTip();
        // assertion
        Assert.assertThat(firstTipText, CoreMatchers.containsString(queryText));
    }

    @After
    public void afterEarch(){
        System.out.println("Browser closed after test");
        driver.quit();
    }
    @AfterClass
    public static void suiteSetdown(){
        System.out.println("Postcondition for all tests");
    }
}
