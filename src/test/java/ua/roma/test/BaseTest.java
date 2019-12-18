package ua.roma.test;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public abstract class BaseTest {
    @Rule
    public TestWatcher watcher = new TestWatcher() {


        @Override
        protected void succeeded(Description description) {
            super.succeeded(description);
            System.out.println(description.getDisplayName() + " Yahooooo");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            System.out.println(description.getDisplayName() + " Oh, nooooo!!! " + e.getMessage());
        }
    };
    @Rule
    public ErrorCollector collect = new ErrorCollector() {
    };
}
