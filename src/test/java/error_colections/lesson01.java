package error_colections;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ua.roma.test.StringDataTest;

public class lesson01 {
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


    @BeforeClass
    public static void suiteSetup() {
        System.out.println("Precondition for all tests");
    }

    @Before
    public void beforeEach() {
        System.out.println("Precondition for earch tests");
    }

    @Test
    public void firstTest() {
        //arrange

        //act

        //assert
        Assert.assertTrue("Page wasn't opened", true);
    }

    @Test
    @Ignore
    public void secondTest() {
        //arrange
        String expected = "Hello";
        String actual = "Hail";
        //act

        //assert
        Assert.assertEquals(
                String.format("Actual %s wasn't equal to expected %s", actual, expected),
                actual,
                expected);
    }

    @Test
    public void thirdTest() {
        //arrange
        String expected = "Hello";
        String actual = "Hail";
        //act

        //assert
        Assert.assertThat(actual, CoreMatchers.containsString(expected));


    }

    @Test(timeout = 1000L)
    public void timeOut() throws InterruptedException {
        //arrange

        //act
        Thread.sleep(1500);
        //assert
    }

    @Test(expected = ArithmeticException.class)
    public void separate() {
        int a = 1 / 0;


    }
    //arange


    @After
    public void afterEarch() {
        System.out.println("Postcondition for earch tests");
    }

    @AfterClass
    public static void suiteSetdown() {
        System.out.println("Postcondition for all tests");
    }
}
