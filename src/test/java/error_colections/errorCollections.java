package error_colections;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;

public class errorCollections {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Preparation before class");
    }

    @Before
    public void beforeTest(){
        System.out.println("Preparation before test");
    }

    @Rule
    public ErrorCollector collect = new ErrorCollector(){};

    @Test
    public void collectionTesting(){
        String x = "Hello";
        String y = "Welcome";
        collect.checkThat("Strings must be equals", "Helo", is("Heelo"));
        collect.checkThat("Strings must be equals", x, is(y));
    }

    @Test
    public void collectionTesting2(){
        int a = 1/1;
        int b = 2*2;
        collect.checkThat("Indefine", b, is(1));
        collect.checkThat("Indefine", 4, is(a));
    }

    @Test
    public void collectionTesting3(){
        int a = 1/1;
        int b = 2*2;
        collect.checkThat("Indefine", b, is(4));
        collect.checkThat("Indefine", 1, is(a));
    }

    @After
    public void afterTest(){
        System.out.println("Preparation after test");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Preparation after class");
    }
}

