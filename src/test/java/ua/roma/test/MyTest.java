package ua.roma.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MyTest extends BaseTest{

    @Test
    @Category(StringDataTest.class)
    public void strCheckTest() {
        final String A = "Hello," + " friend";
        Assert.assertThat(A, CoreMatchers.containsString("Hello, friend"));
    }

    @Test
    @Category(IntDataTest.class)
    public void intCheckTest() {
        int a = 1;
        int b = 2;
        int c = a + b;
        collect.checkThat("Indefine", 3, CoreMatchers.is(c));
    }

    @Test
    @Category(IntDataTest.class)
    public void separateTest(){
        int d = -10 / 5;
        collect.checkThat("Looks like", d, CoreMatchers.is(2));
    }
}
