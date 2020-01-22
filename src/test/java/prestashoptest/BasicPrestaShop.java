package prestashoptest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BasicPrestaShop {

    String url = "http://automationpractice.com/index.php";

    private static final Logger LOGGER = LogManager.getLogger(BasicPrestaShop.class);

    @Rule
    public final TestWatcher watcher = getWatcher();

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

    @Rule
    public ErrorCollector collector = new ErrorCollector() {
    };

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
                LOGGER.error("Test '{}' - FAILED due to: " + description.getMethodName(), e.getMessage());
                super.failed(e, description);
                //System.out.println(description.getDisplayName() + "Failed");
            }
        };
    }
}
