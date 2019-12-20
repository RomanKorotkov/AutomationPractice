package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.presta_shop_pages.BoolConditions.IN_URL;
import static pages.presta_shop_pages.Conditions.*;

public class ContactUs extends BasePage {

    public ContactUs(WebDriver driver) {
        super(driver);
    }

    private By set_subject = By.xpath("//*[@value= '1']");

    private By upload = By.id("fileUpload");

    private By message = By.id("message");

    private By send_btn = By.id("submitMessage");
    private By success_msg = By.xpath("//*[@class= 'alert alert-success']");

    public void fillContactForm() {
        waitFor("http://automationpractice.com/index.php?controller=contact", IN_URL);
        $(set_subject, CLICKABLE).click();
        $(message, PRESENCE).sendKeys("test TEST Test TeSt tEsT teST TEst");
        $(upload, PRESENCE).sendKeys("C:\\Users\\roman.korotkov\\IdeaProjects\\automatedtests\\TestData.txt");
        $(send_btn, CLICKABLE).click();
    }

    public String getTextMessageAfterEmailSending() {
        return $(success_msg, VISIBLE).getText();
    }
}
