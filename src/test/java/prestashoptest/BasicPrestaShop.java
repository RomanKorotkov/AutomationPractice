package prestashoptest;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BasicPrestaShop {
    WebDriver driver;
    public EventFiringWebDriver w_driver;
    public EventListener eventListener;
    String url = "http://automationpractice.com/index.php";

    @BeforeClass
    public static void forEveryTest() {
        System.out.println("Preparation for all test");
    }

    @Before
    public void forEachTest() {
        System.out.println("Test is ready");
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector() {
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

    @After
    public void afterEachTest() {
        System.out.println("Test finished");
    }

    @AfterClass
    public static void afterEveryTest() {
        System.out.println("All the test are finished");
    }

    protected void assertAll(Consumer<Boolean>... assertions) {
        List<AssertionError> errors = new ArrayList<>();

        for (Consumer<Boolean> assertion : assertions) {
            try {
                assertion.accept(true);
            } catch (AssertionError ae) {
                errors.add(ae);
            }
        }

        assert errors.isEmpty() : errors
                .stream()
                .map(assertionError -> assertionError.getMessage().replace("java.lang.AssertionError:", ""))
                .collect(Collectors.toList()).toString();
    }
}
