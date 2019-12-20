package pages.presta_shop_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoadedCondition {

    public static ExpectedCondition<Boolean> pageLoaded(String url, String title) {
        return new ExpectedCondition<Boolean>() {
            private String pageUrl = "";
            private String pageTitle = "";

            public Boolean apply(WebDriver driver) {
                this.pageUrl = driver.getCurrentUrl();
                this.pageTitle = driver.getTitle();

                return url.equals(pageUrl) && title.equals(pageTitle);
            }

            public String toString(){
                return String.format("Expected page url %s, actual url is %s." +
                        "Actual page title %s, actual title is %s.", url, pageUrl, title, pageTitle);
            }
        };
    }
}

