package prestaShopTest;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public abstract class BasicPrestaShop {
    WebDriver driver;

    @BeforeClass
    public static void forEveryTest() {
        System.out.println("Preparation for all test");
    }

    @Before
    public void forEachTest() {
        System.out.println("Test is ready");
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector() {};

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

    @After
    public void afterEachTest() {
        System.out.println("Test finished");
    }

    @AfterClass
    public static void afterEveryTest() {
        System.out.println("All the test are finished");
    }
}