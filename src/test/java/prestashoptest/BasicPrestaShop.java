package prestashoptest;

import event.listener.EventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public static final Logger LOGGER = LogManager.getLogger(BasicPrestaShop.class);

    WebDriver driver;

    String url = "http://automationpractice.com/index.php";

    @BeforeClass
    public static void forEveryTest() {
        LOGGER.debug("Preparation for all test is ready");
    }

    @Before
    public void forEachTest() {
        LOGGER.debug("Test is ready");
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector() {
    };

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            LOGGER.info("Test '{}' - PASSED", description.getMethodName());
            super.succeeded(description);
            //System.out.println(description.getDisplayName() + "Succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOGGER.error("Test '{}' - FAILED due to: " + description.getMethodName(), e.getMessage());
            super.failed(e, description);
            //System.out.println(description.getDisplayName() + "Failed");
        }
    };

    @After
    public void afterEachTest() {
        LOGGER.debug("Test finished");
    }

    @AfterClass
    public static void afterEveryTest() {
        LOGGER.debug("All the test are finished");
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
