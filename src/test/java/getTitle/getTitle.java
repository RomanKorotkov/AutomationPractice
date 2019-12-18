package getTitle;

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


public class getTitle {
    @BeforeClass
    public static void suiteSetup() {
        System.out.println("Precondition for all tests");
    }

    @Before
    public void beforeEach() {
        System.out.println("Precondition for earch tests");
    }

    @Rule
    public ErrorCollector collect = new ErrorCollector(){};

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
    public void oolleeTest() {
        //arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //act
        driver.get("https://ru.oollee.com/");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.titleIs("oollee - Провайдер воды"));
        //assert
        String expected = "oollee - Провайдер воды";
        Assert.assertEquals(
                String.format("String %s is not equal to th string %s", driver.getTitle(), expected),
                driver.getTitle(),
                expected);
        driver.quit();
    }

    @Test
    public void creddyTest(){
        //arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //act
        driver.get("https://creddy.ru/");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.titleIs("Creddy"));
        //assert
        String actual = driver.getTitle();
        String expected = "Creddy";
        Assert.assertThat(actual, CoreMatchers.containsString(expected));
        driver.quit();
    }

    @Test
    public void restTest(){
        //arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //act
        driver.get("https://have-a-rest.net/");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.titleIs("Главная"));
        //assert
        collect.checkThat("Strings must be equals", driver.getTitle(), CoreMatchers.is("Главная"));
        driver.quit();
    }

    @Test
    public void go() throws InterruptedException, NoSuchFieldException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\roman.korotkov\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("Bergrizen");
        driver.quit();
    }

    @After
    public void afterEarch() {
        System.out.println("Postcondition for earch tests");
    }

    @AfterClass
    public static void suiteSetdown() {
        System.out.println("Postcondition for all tests");
    }
}
